package com.example.demo.dao;

import com.example.demo.dto.BookRequest;
import com.example.demo.model.Book;

public interface BookDao {

    Book getBookById(Integer bookId);

    Integer createBook(BookRequest bookRequest);

    void updateBook(Integer bookId, BookRequest bookRequest);

    void deleteBookById(Integer bookId);
}