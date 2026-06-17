package com.example.demo;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.example.demo.dao.BookDao;
import com.example.demo.dto.BookRequest;
import com.example.demo.model.Book;
import com.example.demo.service.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	@Mock
	private BookDao bookDao;

	@InjectMocks
	private BookServiceImpl bookServiceImpl;

	private Book mockBook;

	@BeforeEach
	void setUp() {
		mockBook = new Book();
		mockBook.setBookId(1);
		mockBook.setTitle("unit test book");
	}

	@Test
	void testGetBookId() {
		// 設定行為
		when(bookDao.getBookById(1)).thenReturn(mockBook);

		// 執行測試
		Book result = bookServiceImpl.getBookById(1);

		// 驗證結果
		assertNotNull(mockBook);
		assertEquals(1, mockBook.getBookId());
		assertEquals("unit test book", mockBook.getTitle());
	}
	
	@Test
	void testCreateBook() {
		BookRequest bookRequest = new BookRequest();
		when(bookDao.createBook(bookRequest)).thenReturn(1);
		
		Integer bookId = bookServiceImpl.createBook(bookRequest);
		assertEquals(1, bookId);
		verify(bookDao, times(1)).createBook(bookRequest);
	}

	@Test
    void testDeleteBookById() {
        // 執行測試
        bookServiceImpl.deleteBookById(1);

        // 驗證 dao 的方法有被正確呼叫
        verify(bookDao, times(1)).deleteBookById(1);
    }
}
