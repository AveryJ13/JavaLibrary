package com.ss.DAO;

import com.ss.entity.Copies;
import com.ss.entity.Genre;
import com.ss.entity.Loans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoansDAO extends BaseDAO<Loans>{

    public LoansDAO(Connection conn) {
        super(conn);
    }

    public List<Loans> readAllLoans() throws SQLException, ClassNotFoundException {
        return read("select tbl.bookId, branchId, cardNo, dateOut, dueDate, dateIn, title  from tbl_book_loans tbl\n" +
                "inner join tbl_book bk where tbl.bookId = bk.bookId",null);
    }

    public List<Loans> readCurrentUserLoans(Integer cardNo) throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_book_loans WHERE cardNo = ? AND isnull(dateIn)",
                new Object[]{cardNo});
    }

    public List<Loans> readCheckedOut(Integer cardNo) throws SQLException, ClassNotFoundException {
        return read("select tbl.bookId, tb.title, branchId, cardNo, dateOut, dueDate, dateIn from tbl_book_loans tbl inner join tbl_book tb on tbl.bookId = tb.bookId where cardNo = ? and dateIn IS NULL",
                new Object[]{cardNo});
    }

    public void returnBook(Integer bookId, Integer branchId, Integer cardNo) throws ClassNotFoundException, SQLException {
        save("UPDATE tbl_book_loans SET dateIn = current_timestamp() where bookId = ? and branchId = ? and cardNo = ?",
                new Object[]{bookId, branchId, cardNo});
        save("UPDATE tbl_book_copies SET noOfCopies = noOfCopies + 1 where bookId = ? and branchId = ?",
                new Object[]{bookId, branchId});
        save("DELETE FROM tbl_book_loans WHERE bookId = ?  and branchId = ? and cardNo = ?",
                new Object[]{bookId, branchId, cardNo});
    }

    public void overideDueDate(Integer cardNo, Integer bookId) throws SQLException, ClassNotFoundException{
        save("UPDATE tbl_book_loans set dueDate = date_add(current_timestamp(), interval 2 week) where CardNo = ? and bookId = ?",
                new Object[]{cardNo, bookId});
    }

    @Override
    public List<Loans> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<Loans> loans = new ArrayList<>();
        while (rs.next()) {
            loans.add(new Loans(rs.getInt("bookId"),
                    rs.getInt("branchId"),
                    rs.getInt("cardNo"),
                    rs.getString("dateOut"),
                    rs.getString("dueDate"),
                    rs.getString("dateIn"),
                    rs.getString("title"))
            );

        }
        return loans;
    }
}
