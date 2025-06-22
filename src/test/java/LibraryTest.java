import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import v_and_v.Book;
import v_and_v.Library;
import v_and_v.LibraryUtils;
import v_and_v.Member;

public class LibraryTest {
    Library library;
    Member member;
    Book book1;
    Book book2;
    Book book3;

    @BeforeEach
    void setup() {
        library = new Library();
        member = new Member("Nixon");
        book1 = new Book("One Piece", "Eiichiro Oda");
        book2 = new Book("Naruto", "Masashi Kishimoto");
        book3 = new Book("Dragon Ball", "Akira Toriyama");
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.registerMember(member);
    }

    @Test
    @Order(1)
    // Borrowing a Book
    void test1() {
        assertFalse(book1.isBorrowed());
        book1.borrow();
        assertTrue(book1.isBorrowed());
    }

    @Test
    @Order(2)
    // Borrowing an Already Borrowed Book
    void test2() {
        assertFalse(book1.isBorrowed());
        book1.borrow();
        assertTrue(book1.isBorrowed());
        IllegalStateException e = assertThrows(IllegalStateException.class, book1::borrow);
        assertEquals("Book already borrowed", e.getMessage());
    }

    @Test
    // Returning a Book
    void test3() {
        assertFalse(book1.isBorrowed());
        book1.borrow();
        assertTrue(book1.isBorrowed());
        book1.returnBook();
        assertFalse(book1.isBorrowed());
    }

    @Test
    // Returning a Book That Was Not Borrowed
    void test4() {
        assertFalse(book1.isBorrowed());
        IllegalStateException e = assertThrows(IllegalStateException.class, book1::returnBook);
        assertEquals("Book was not borrowed", e.getMessage());
    }

    @Test
    // Member Borrowing Limit
    void test5() {
        assertEquals(0, member.getBorrowedBooks());
        member.borrowBook();
        assertEquals(1, member.getBorrowedBooks());
        member.borrowBook();
        assertEquals(2, member.getBorrowedBooks());
        member.borrowBook();
        assertEquals(3, member.getBorrowedBooks());
    }

    @Test
    // Exceeding Borrow Limit
    void test6() {
        assertEquals(0, member.getBorrowedBooks());
        member.borrowBook();
        assertEquals(1, member.getBorrowedBooks());
        member.borrowBook();
        assertEquals(2, member.getBorrowedBooks());
        member.borrowBook();
        assertEquals(3, member.getBorrowedBooks());
        IllegalStateException e = assertThrows(IllegalStateException.class, member::borrowBook);
        assertEquals("Cannot borrow more than 3 books", e.getMessage());
    }

    @Test
    // Returning a Book by Member
    void test7() {
        assertEquals(0, member.getBorrowedBooks());
        member.borrowBook();
        assertEquals(1, member.getBorrowedBooks());
        member.returnBook();
        assertEquals(0, member.getBorrowedBooks());
    }

    @Test
    // Returning a Book When Member Has None Borrowed
    void test8() {
        assertEquals(0, member.getBorrowedBooks());
        IllegalStateException e = assertThrows(IllegalStateException.class, member::returnBook);
        assertEquals("No books to return", e.getMessage());
    }

    @Test
    // Finding a Book in the Library
    void test9() {
        assertEquals(book1.getTitle(), library.findBook(book1.getTitle()).getTitle());
        assertEquals(book2, library.findBook(book2.getTitle()));
        assertNull(library.findBook("Pokemon"));
    }

    @Test
    // Checking Book Availability in Library
    void test10() {
        assertTrue(library.isBookAvailable(book1.getTitle()));
        book1.borrow();
        assertFalse(library.isBookAvailable(book1.getTitle()));
        assertFalse(library.isBookAvailable("Pokemon"));
    }

    @Test
    // Counting Available Books Using LibraryUtils
    void test11() {
        assertEquals(3, LibraryUtils.countAvailableBooks(library));
        book1.borrow();
        assertEquals(2, LibraryUtils.countAvailableBooks(library));
    }
}
