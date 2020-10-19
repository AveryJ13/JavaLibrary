package com.ss.DAO;

import com.ss.entity.Author;
import com.ss.entity.Book;
import com.ss.entity.Publisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherDAO extends BaseDAO<Publisher> {

    public PublisherDAO(Connection conn) {
        super(conn);
    }

    public List<Publisher> readAllPublishers() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_publisher", null);
    }
    public void updatePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
        save("UPDATE tbl_publisher SET publisherName = ?, publisherAddress = ?, publisherPhone = ? WHERE publisherId = ?",
                new Object[]{publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone(), publisher.getPublisherId()});
    }

    public void addPublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
        save("INSERT INTO tbl_publisher(publisherName, publisherAddress, publisherPhone) values (?, ?, ?)",
                new Object[]{publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone()});
    }

    public void deletePublisher(Integer pubId) throws ClassNotFoundException, SQLException {
        save("DELETE FROM tbl_publisher WHERE publisherId = ?",
                new Object[]{pubId});
    }


    @Override
    public List<Publisher> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<Publisher> publishers = new ArrayList<>();
        while (rs.next()) {
            publishers.add(new Publisher(rs.getInt("publisherId"),
                    rs.getString("publisherName"),
                    rs.getString("publisherAddress"),
                    rs.getString("publisherPhone")));
            //also populate the books written by this Author
        }
        return publishers;
    }
}
