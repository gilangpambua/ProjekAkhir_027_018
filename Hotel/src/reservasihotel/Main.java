package reservasihotel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
    }   
}

class Menu extends JFrame{
    JButton btnLogin = new JButton("Login");
    JButton btnRegis = new JButton("Regis");
    
    Menu(){
        setTitle("RESERVASI HOTEL");
        setLayout(null);
        setSize(450 , 250);

        add(btnLogin);
        add(btnRegis);
        
        btnLogin.setBounds(55,90,150,40);
        btnRegis.setBounds(235,90,150,40);

        btnLogin.addActionListener(new ActionListener() {
            @Override
              public void actionPerformed(ActionEvent e) {
                  dispose();
                  Login login = new Login();        
              }
        });
        btnRegis.addActionListener(new ActionListener() {
            @Override
              public void actionPerformed(ActionEvent e) {
                  dispose();
                  Regis regis = new Regis();        
              }
        });

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    class Login extends JFrame{
        public String id, username, password;
        final JTextField fusername = new JTextField(10);
        final JPasswordField fpassword = new JPasswordField(10);

        JLabel lusername = new JLabel("Username");
        JLabel lpassword = new JLabel("Password");

        JButton btnLoginAdmin = new JButton("LOGIN");
        String query;
        String DBtabel = "login";
        String[][] datanya = new String[2][2];
        Connector connector = new Connector();
        
     Login() {
        setTitle("LOGIN ADMIN HOTEL");
        setSize(350,250);
        setLayout(null);

        add(lusername);
        add(fusername);
        add(lpassword);
        add(fpassword);
        add(btnLoginAdmin);  

        setVisible(true);
        lusername.setBounds(20,50,120,20);
        fusername.setBounds(110,50,175,20);
        lpassword.setBounds(20,80,120,20);
        fpassword.setBounds(110,80,175,20);
        btnLoginAdmin.setBounds(110,120,120,20);

        btnLoginAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connector.statement = connector.koneksi.createStatement();
                    query = "SELECT 'username' FROM " + DBtabel + " WHERE username = '" + getFusername() + "';";
                    ResultSet result_user = connector.statement.executeQuery(query);
                    while (result_user.next())
                        datanya[0][0] = result_user.getString("username");
                    query = "SELECT 'password' FROM " + DBtabel + " WHERE password = '" + getFpassword() + "';";
                    ResultSet result_pass = connector.statement.executeQuery(query);
                    while (result_pass.next())
                        datanya[0][1] =result_pass.getString("password");
                    if (getFusername().isEmpty()|| getFpassword().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Silahkan input data");
                    }else if (datanya[0][0] == null){
                        JOptionPane.showMessageDialog(null,"Username tidak ditemukan");
                    } else if (datanya[0][1] == null){
                        JOptionPane.showMessageDialog(null,"Password Salah");
                    } else {
                        JOptionPane.showMessageDialog(null,"Login berhasil");
                        dispose();// baru
                        MVC mvc = new MVC();
                        mvc.menuutama();
                    }
                    datanya[0][0]= null; datanya[0][1]= null;
                } catch (SQLException e1){
                    System.out.println(e1.getMessage());
                }
             }
         });
         setVisible(true);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         setLocationRelativeTo(null);
     }

        public String getFusername() {
            return fusername.getText();
        }

        public String getFpassword() {
            return fpassword.getText();
        }
     
     
    }
    
    class Regis extends JFrame{
        public String id, username, password;
        final JTextField frusername = new JTextField(10);
        final JPasswordField frpassword = new JPasswordField(10);
        
        JLabel lrusername = new JLabel("Username");
        JLabel lrpassword = new JLabel("Password");

        JButton btnRegisAdmin = new JButton("Register ");
        String query;
        String DBtabel = "login";
        String[][] datanya = new String[2][2];
        Connector connector = new Connector();
        Regis(){
            setTitle("REGISTER ADMIN HOTEL");
            setSize(350,250);
            setLayout(null);

            add(lrusername);
            add(frusername);
            add(lrpassword);
            add(frpassword);
            add(btnRegisAdmin);  

            setVisible(true);
            lrusername.setBounds(20,50,120,20);
            frusername.setBounds(110,50,175,20);
            lrpassword.setBounds(20,80,120,20);
            frpassword.setBounds(110,80,175,20);
            btnRegisAdmin.setBounds(110,120,120,20);

        btnRegisAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connector.statement = connector.koneksi.createStatement();
                    query = "SELECT 'username' FROM " + DBtabel + " WHERE username = '" + getFrusername() + "';";
                    ResultSet result_user = connector.statement.executeQuery(query);
                    while (result_user.next())
                        datanya[0][0] = result_user.getString("username");
                    if (getFrusername().isEmpty()|| getFrpassword().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Silahkan input data");
                    }else if(datanya[0][0] != null){
                        JOptionPane.showMessageDialog(null,"Username telah digunakan");
                    } else {
                        query = "INSERT  INTO " + DBtabel + " (username, password) VALUES ('" + getFrusername() + "','" + getFrpassword() + "')";
                        connector.statement.executeUpdate(query);
                        JOptionPane.showMessageDialog(null,"Registrasi berhasil");
                        dispose();
                        Menu menu = new Menu();
                    }
                    datanya[0][0] = null;
                }catch (SQLException e2){
                    System.out.println(e2.getMessage());
                }
             }
         });
         setVisible(true);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         setLocationRelativeTo(null);
        }

        public String getFrusername() {
            return frusername.getText();
        }

        public String getFrpassword() {
            return frpassword.getText();
        }
    }
    
    
    
}
