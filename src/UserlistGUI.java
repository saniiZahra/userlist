import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class UserlistGUI extends JFrame {
    public UserlistGUI(ArrayList<User> userList) {
        setTitle("Users List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("UID");
        tableModel.addColumn("USERNAME");
        tableModel.addColumn("PASSWORD");
        tableModel.addColumn("ADMIN");

        
        for (User user : userList) {
            Object[] rowData = {user.getUid(), user.getUname(), user.getPassword(), Boolean.toString(user.isAdmin())};
            tableModel.addRow(rowData);
        }

        
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        ArrayList<User> userList = userDAO.getAllUsers();
        UserlistGUI userListGUI = new UserlistGUI(userList);
    }
}
class User {
    private int Uid;
    private String Username;
    private String Password;
    private boolean Admin;

    public User(int uid, String uname, String password, boolean admin) {
        this.Uid = uid;
        this.Username = uname;
        this.Password = password;
        this.Admin = admin;
    }

    public int getUid() {
        return Uid;
    }

    public String getUname() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public boolean isAdmin() {
        return Admin;
    }
}
class UserDAO {
    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(1, "Admin", "admin123", true));
        return userList;
    }
}