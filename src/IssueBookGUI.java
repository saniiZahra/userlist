import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IssueBookGUI extends JFrame {
    public IssueBookGUI() {
        setTitle("Enter Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel bookIdLabel = new JLabel("Book ID(BID)");
        JTextField bookIdField = new JTextField();
        JLabel userIdLabel = new JLabel("User ID(UID)");
        JTextField userIdField = new JTextField();
        JLabel periodLabel = new JLabel("Period(Days)");
        JTextField periodField = new JTextField();
        JLabel issuedDateLabel = new JLabel("Issued Date(DD-MM-YYYY)");
        JTextField issuedDateField = new JTextField();
        JButton button = new JButton("Submit");

        panel.add(bookIdLabel);
        panel.add(bookIdField);
        panel.add(userIdLabel);
        panel.add(userIdField);
        panel.add(periodLabel);
        panel.add(periodField);
        panel.add(issuedDateLabel);
        panel.add(issuedDateField);

        buttonPanel.add(button);

        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(contentPanel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bookId = bookIdField.getText();
                String userId = userIdField.getText();
                int period = Integer.parseInt(periodField.getText());
                String issuedDate = issuedDateField.getText();

                BookDAO bookDAO = new BookDAO();
                bookDAO.issueBook(bookId, userId, period, issuedDate);
                JOptionPane.showMessageDialog(IssueBookGUI.this, "Book issued successfully!");
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        IssueBookGUI issueBookGUI = new IssueBookGUI();
        issueBookGUI.setVisible(true);
    }
}

class BookDAO {
    private static String DB_URL = "jdbc:mysql://localhost:3306/mysql";
    private static String DB_USERNAME = "username";
    private static String DB_PASSWORD = "password";

    public void issueBook(String bookId, String userId, int period, String issuedDate) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)){
            String query = "INSERT INTO issued_books (Book_Id, User_Id, Period, Issued_date) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, bookId);
            statement.setString(2, userId);
            statement.setInt(3, period);
            statement.setString(4, issuedDate);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
