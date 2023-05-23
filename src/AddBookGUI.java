import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddBookGUI extends JFrame implements ActionListener{
    private JTextField bookNamefield;
    private JTextField genrefield;
    private JTextField pricefield;
    private JButton button;
    private BookDao bookDao;

    public AddBookGUI(BookDao bookDao) {
        this.bookDao = bookDao;

        setTitle("Add Book");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
       
        JPanel panel = new JPanel(new GridLayout(4, 0));
        JPanel contentPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JLabel bookNameLabel = new JLabel("Book Name");
        JLabel genreLabel = new JLabel("Genre");
        JLabel priceLabel = new JLabel("Price");
        JTextField bookNamefield = new JTextField();
        JTextField genrefield = new JTextField();
        JTextField pricefield = new JTextField();

        
      JButton  button = new JButton("Submit");
        button.addActionListener(this);
        panel.add(bookNameLabel);
        panel.add(bookNamefield);
        panel.add(genreLabel);
        panel.add(genrefield);
        panel.add(priceLabel);
        panel.add(pricefield);
        panel.add(button);
        buttonPanel.add(button);
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(contentPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String bookName = bookNamefield.getText();
            String genre = genrefield.getText();
            double price = Double.parseDouble(pricefield.getText());

            Book book = new Book(bookName, genre, price);
            bookDao.addBook(book);
            bookNamefield.setText("");
            genrefield.setText("");
            pricefield.setText("");
        }
    }

    public static void main(String[] args) {
        BookDao bookDao = new BookDaoImp();
        AddBookGUI book = new AddBookGUI(bookDao);
    }
}

class Book {
    private String Name;
    private String Genre;
    private double Price;

    public Book(String name, String genre, double price) {
        this.Name = name;
        this.Genre = genre;
        this.Price = price;
    }

    public String getName() {
        return Name;
    }

    public String getGenre() {
        return Genre;
    }

    public double getPrice() {
        return Price;
    }
}