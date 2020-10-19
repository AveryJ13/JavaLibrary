package com.ss.DAO;

import com.ss.entity.Author;
import com.ss.entity.Book;
import com.ss.entity.Genre;
import com.ss.entity.Publisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO extends BaseDAO<Genre>{

    public GenreDAO(Connection conn) {
        super(conn);
    }

    public List<Genre> readAllGenres() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM tbl_genre", null);
    }

    public void addGenre(Genre genre) throws ClassNotFoundException, SQLException {
        save("INSERT INTO tbl_genre(genre_name) VALUES(?)",
                new Object[]{genre.getGenreName()}) ;
    }

    public void updateGenre(Genre genre) throws ClassNotFoundException, SQLException {
        save("UPDATE tbl_genre set genre_name = ? WHERE genre_id = ?",
                new Object[]{genre.getGenreName(), genre.getGenreId()});
    }

    public void deleteGenre(Integer genreId) throws ClassNotFoundException, SQLException {
        save("DELETE FROM tbl_genre WHERE genre_id = ?",
                new Object[]{genreId});
    }
    public void setBookGenre(Book book) throws ClassNotFoundException, SQLException{
        save("INSERT INTO tbl_book_genres(genre_id, bookId) values(? ,?)", new Object[] {book.getGenre(), book.getBookId()});
    }

    @Override
    public List<Genre> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<Genre> genres = new ArrayList<>();
        while (rs.next()) {
            genres.add(new Genre(rs.getInt("genre_id"),
                    rs.getString("genre_name"))
                    );

        }
        return genres;
    }
}
