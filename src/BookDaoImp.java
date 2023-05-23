
public class BookDaoImp implements BookDao {
    public void addBook(Book book) {
        System.out.println("Book Name" + book.getName());
        System.out.println("Genre" + book.getGenre());
        System.out.println("Price" + book.getPrice());
    }
}
