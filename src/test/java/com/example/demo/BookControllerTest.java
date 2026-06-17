package com.example.demo;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.BookController;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@WebMvcTest(controllers = BookController.class)
class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	
	@BeforeEach
	void setUp() {
		Book mockBook = new Book();
	    mockBook.setBookId(1);
	    mockBook.setTitle("JUnit 5 Guide");
	    mockBook.setAuthor("Author Name");
	    mockBook.setPrice(100);
	    
	    when(bookService.getBookById(1)).thenReturn(mockBook);
		
	}
	
	@Test
	public void testGetBook_Success() throws Exception {
		// 3. 執行測試並加入 print() 來檢查詳細日誌
        mockMvc.perform(get("/book/1"))
               .andDo(print()) // 這會把詳細請求路徑印出來，幫你抓出為什麼 404
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.bookId").value(1))
               .andExpect(jsonPath("$.title").value("JUnit 5 Guide"));
	}
	
	@Test
    public void testDeleteBook_Success() throws Exception {
        // 驗證 DELETE 請求
        mockMvc.perform(delete("/book/1"))
                .andExpect(status().isNoContent());

        // 驗證 service 的 delete 方法是否有被呼叫
        verify(bookService, times(1)).deleteBookById(1);
    }
}
