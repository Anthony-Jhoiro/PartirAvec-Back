package com.partiravec.destinationservice.dao;

import com.partiravec.destinationservice.models.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends CrudRepository<Book, Integer> {

    @Query(value = "SELECT * FROM book b INNER JOIN book_users bu ON bu.book_id = b.id AND bu.users = ?1", nativeQuery = true)
    public List<Book> getAuthorizeBooks(String user);
}
