package com.ss.DAO;

import com.ss.entity.Book;
import com.ss.entity.Branch;
import com.ss.entity.Copies;
import com.ss.entity.Genre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchDAO extends BaseDAO<Branch>{
    public BranchDAO(Connection conn) {
        super(conn);
    }

    public List<Branch> readAllBranches() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_library_branch", null);
    }


    public void setBookCopies(Integer bookId, Integer branchId, Integer noOfCopies) throws ClassNotFoundException, SQLException{
        save("INSERT INTO tbl_book_copies(bookId, branchId, noOfCopies) values(? ,?, ?)", new Object[] {bookId, branchId, noOfCopies});
    }

    public void addBranch(Branch branch) throws ClassNotFoundException, SQLException{
        save("INSERT INTO tbl_library_branch(branchName, branchAddress) values (?, ?)",
                new Object[]{branch.getBranchName(), branch.getBranchAddress()});
    }

    public void updateBranch(Branch branch) throws ClassNotFoundException, SQLException{
        save("UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?",
                new Object[]{branch.getBranchName(), branch.getBranchAddress(), branch.getBranchId()});
    }



    public void deleteBranch(Integer branchId) throws ClassNotFoundException, SQLException{
        save("DELETE FROM tbl_library_branch where branchId = ?",
                new Object[]{branchId});
    }



    @Override
    public List<Branch> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<Branch> branches = new ArrayList<>();
        while (rs.next()) {
            branches.add(new Branch(rs.getInt("branchId"),
                    rs.getString("branchName"),
                    rs.getString("branchAddress"))
            );

        }
        return branches;
    }
}
