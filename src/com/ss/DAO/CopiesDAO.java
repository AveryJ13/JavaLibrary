package com.ss.DAO;

import com.ss.entity.Branch;
import com.ss.entity.Copies;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CopiesDAO extends BaseDAO<Copies> {
    public CopiesDAO(Connection conn) {
        super(conn);
    }

    public List<Copies> readBranchCopies(Integer branchId) throws SQLException, ClassNotFoundException {
        return read("select tbc.bookId, branchId, noOfCopies, title from tbl_book_copies tbc inner join tbl_book tb on tbc.bookId = tb.bookId where branchId = ?",
                new Object[]{branchId});
    }

    public List<Copies> readBranchCopiesAvailable(Integer branchId) throws SQLException, ClassNotFoundException {
        return read("select tbc.bookId, branchId, noOfCopies, title from tbl_book_copies tbc inner join tbl_book tb on tbc.bookId = tb.bookId where branchId = ?  AND noOfCopies is not null AND noOfCopies !=0",
                new Object[]{branchId});
    }

    public void setNewCopies(Integer branchId, Integer bookId, Integer count) throws SQLException, ClassNotFoundException {
        save("UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? AND branchId = ?",
                new Object[]{count, bookId, branchId});
    }

    @Override
    public List<Copies> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<Copies> copies = new ArrayList<>();
        while (rs.next()) {
            copies.add(new Copies(rs.getInt("bookId"),
                    rs.getInt("branchId"),
                    rs.getInt("noOfCopies"),
                    rs.getString("title"))
            );

        }
        return copies;
    }
}
