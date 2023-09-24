package seminars.fourth.book;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookServiceTest {
    BookRepository repositoryMock;


    @BeforeAll
    void setUp() {
        repositoryMock = mock(BookRepository.class);
        Book book_one = new Book("01", "title_book_01", "autor_book_01");
        Book book_two = new Book("02", "title_book_02", "autor_book_02");

        List<Book> bookList = new ArrayList<>();
        bookList.add(book_one);
        bookList.add(book_two);

        when(repositoryMock.findById("01")).thenReturn(book_one);
        when(repositoryMock.findById("02")).thenReturn(book_two);
        when(repositoryMock.findAll()).thenReturn(bookList);

    }


    @Test
    void testFindBookById() {
        BookService bookService = new BookService(repositoryMock);
        Book testBook = bookService.findBookById("01");

        assertEquals("autor_book_01", testBook.getAuthor());
        assertEquals("title_book_01", testBook.getTitle());
        assertEquals("01", testBook.getId());

    }


    @Test
    void testFindAllBooks() {
        BookService bookService = new BookService(repositoryMock);
        List<Book> testList = bookService.findAllBooks();
        List<String> listAutors = new ArrayList<>();
        List<String> listTitle = new ArrayList<>();

        for(Book item: testList){
            listAutors.add(item.getAuthor());
            listTitle.add(item.getTitle());
        }

        assertEquals(2, testList.size());
        assertTrue(listAutors.contains("autor_book_01"));
        assertTrue(listAutors.contains("autor_book_02"));
        assertTrue(listTitle.contains("title_book_01"));
        assertTrue(listTitle.contains("title_book_02"));

    }
}