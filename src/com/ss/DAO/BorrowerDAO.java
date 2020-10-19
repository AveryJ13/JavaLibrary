package com.ss.DAO;

import com.ss.entity.Book;
import com.ss.entity.Borrower;
import com.ss.entity.Genre;
import com.ss.entity.Loans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowerDAO extends BaseDAO<Borrower> {

    public BorrowerDAO(Connection conn) {
        super(conn);
    }

    public List<Borrower> readAllBorrowers() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_borrower", null);
    }

    public List<Borrower> readSpecificBorrower(Integer cardNumber)throws SQLException, ClassNotFoundException{
        return read("SELECT * FROM tbl_borrower WHERE cardNo = ?",
                new Object[]{cardNumber});
    }

    public void addBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
        save("INSERT INTO tbl_borrower(name, address, phone) VALUES(?, ?, ?)",
                new Object[]{borrower.getName(), borrower.getAddress(), borrower.getPhone()}) ;
    }

    public void updateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
        save("UPDATE tbl_borrower set name = ?, address = ?, phone = ? WHERE cardNo = ?",
                new Object[]{borrower.getName(), borrower.getAddress(), borrower.getPhone(), borrower.getCardNo()});
    }

    public void checkoutBook(Integer bookId, Integer branchId, Integer cardNo) throws ClassNotFoundException, SQLException{
        save("INSERT INTO tbl_book_loans(bookId, branchId, cardNo, dateOut, dueDate) VALUES (?, ?, ?, current_timestamp(), date_add(current_timestamp(), interval 2 week))",
                new Object[]{bookId, branchId, cardNo});
        save("update tbl_book_copies set noOfCopies = noOfCopies - 1 where bookId = ? and branchId = ?",
                new Object[] {bookId, branchId});
    }


    public void deleteBorrower(Integer borrowerId) throws ClassNotFoundException, SQLException {
        save("DELETE FROM tbl_borrower WHERE cardNo = ?",
                new Object[]{borrowerId});
    }
    public void setBookGenre(Book book) throws ClassNotFoundException, SQLException{
        save("INSERT INTO tbl_book_genres(genre_id, bookId) values(? ,?)", new Object[] {book.getGenre(), book.getBookId()});
    }

    @Override
    public List<Borrower> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<Borrower> borrowers = new ArrayList<>();
        while (rs.next()) {
            borrowers.add(new Borrower(rs.getInt("cardNo"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone"))
            );

        }
        return borrowers;
    }
}
