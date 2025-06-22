package v_and_v;

public class LibraryUtils {
    public static int countAvailableBooks(Library library) {
        int count = 0;
        for (Book book : library.books) {
            if (!book.isBorrowed()) {
                count++;
            }
        }
        return count;
    }
}
