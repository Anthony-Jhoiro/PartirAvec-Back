package com.partiravec.destinationservice.controllers;

import com.netflix.discovery.converters.Auto;
import com.partiravec.destinationservice.dao.BookDao;
import com.partiravec.destinationservice.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BookController {

    private final BookDao bookDao;

    @Autowired
    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    /**
     * Check if a user has access to a wanted book
     * @param user user to check
     * @param bookId id of the book to check
     * @return true if the user can access the book, false if he can't or if the book does not exist
     */
    public boolean userHasAccessToBook(String user, Integer bookId) {
        Optional<Book> optionalBook = bookDao.findById(bookId);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return book.getUsers().contains(user);
        }
        return false;
    }

    /**
     * Get all the books accessible by the current user
     * @param user current user
     * @return List of accessible books
     */
    @GetMapping("/books")
    public List<Book> getBookList(@RequestParam("_user") String user) {
        return bookDao.getAuthorizeBooks(user);
    }

    /**
     * Create a new book with current user
     * @param book book informations
     * @param user current user
     * @return Created book
     */
    @PostMapping("/book")
    public Book createBook(@RequestBody Book book, @RequestParam("_user") String user) {
        // Add the author to the book users
        book.setUsers(Collections.singleton(user));
        bookDao.save(book);
        return book;
    }


}
