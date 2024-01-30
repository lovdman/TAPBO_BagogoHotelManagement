package View;



import Model.ForgetPass;
import Model.LoginModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static Model.LoginModel.isAdmin;

public class LoginView extends JFrame implements ActionListener {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JToggleButton eyeBtn;
    private final JButton signup;
    private final JButton exitButton;
    private final JButton loginButton;
    private final JButton forgot;

    public LoginView() {
        setResizable(false);
        setTitle("Dafam Pacific Caesar");
        setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("../images/titleIcon.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 903, 554);
        setLocationRelativeTo(null);
        JPanel contentPane = new JPanel();
        contentPane.setForeground(Color.LIGHT_GRAY);
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(null);

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // ... (rest of the view code)

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        loginButton.setBounds(653, 345, 153, 40);
        loginButton.setFocusable(false);
        contentPane.add(loginButton);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Tahoma", Font.BOLD, 10));
        exitButton.setBounds(809, 486, 70, 21);
        exitButton.setFocusable(false);
        contentPane.add(exitButton);

        eyeBtn.addActionListener(this);
        signup.addActionListener(this);
        exitButton.addActionListener(this);
        loginButton.addActionListener(this);
        forgot.addActionListener(this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the user input from the username and password fields
        String user = usernameField.getText();
        String pass = String.valueOf(passwordField.getPassword());

        // Check if the username and password fields are empty
        boolean userEmpty = user.isEmpty();
        boolean passEmpty = pass.isEmpty();

        // Check which button was clicked
        // Other code...

        // Check which button was clicked
        if (e.getSource() == eyeBtn) {
            // If the "show password" button was clicked, toggle the password visibility
            if (eyeBtn.isSelected()) {
                eyeBtn.setIcon(on);
                passwordField.setEchoChar((char) 0); // Show password
            } else {
                eyeBtn.setIcon(off);
                passwordField.setEchoChar('*'); // Hide password
            }
        } else if (e.getSource() == signup) {
            // If the "signup" button was clicked, go to the signup page
            System.out.println("Exited from Login class");
            this.setVisible(false);
            new Signup();
        } else if (e.getSource() == exitButton) {
            // If the "exit" button was clicked, prompt the user to confirm if they want to exit
            int yesORno =
                    JOptionPane.showConfirmDialog(
                            null, "Are you sure ?", "Alert!", JOptionPane.YES_NO_OPTION);

            if (yesORno == 0) {
                System.out.println("Exited from Login class");
                System.out.println("Exited from The program");
                System.exit(1); // If the user chooses "yes", exit the program
            }
        } else if (e.getSource() == loginButton) {
            // If the "login" button was clicked, check if the username and password fields are filled
            if (userEmpty || passEmpty) {
                JOptionPane.showMessageDialog(
                        null, "Please fill all of the fields.", "Warning!", JOptionPane.WARNING_MESSAGE);
            } else {
                boolean userbool = false; // Flag to check if the user is an admin or not
                isAdmin = false; // Flag to check if the user is an admin or not
                try {
                    // Check if the admin login file exists, if not create it and add default admin
                    // credentials
                    File file = new File("./files/admin_login.txt");
                    if (!file.exists()) {
                        boolean created = file.createNewFile();
                        if (created) {
                            System.out.println("File created successfully.");
                        } else {
                            System.out.println("File creation failed.");
                        }
                        FileWriter fileWriter = new FileWriter(file, true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        PrintWriter printWriter = new PrintWriter(bufferedWriter);

                        printWriter.println("===============================================");
                        printWriter.println("User Name : admin");
                        printWriter.println("Password : admin");
                        printWriter.println("===============================================");
                        printWriter.close();
                    }

                    // Create strings for the username and password
                    String uname = "User Name : " + user;
                    String pin = "Password : " + pass;

                    // Check if the user is an admin by reading the admin login file
                    BufferedReader readFile1 = new BufferedReader(new FileReader("./files/admin_login.txt"));

                    int totalLines1 = 0;
                    while (readFile1.readLine() != null) {
                        totalLines1++;
                    }
                    readFile1.close();

                    // Iterate over each line in the admin login file and check if the user credentials match
                    for (int i = 0; i < totalLines1; i++) {
                        Path adminLoginPath = Paths.get("./files/admin_login.txt");
                        String line = Files.readAllLines(adminLoginPath).get(i);
                        if (line.equals(uname)) {
                            String line2 = Files.readAllLines(adminLoginPath).get((i + 1));
                            System.out.println("user name matched to admin");
                            // Set flags and username, and open the dashboard window
                            if (line2.equals(pin)) {
                                LoginModel.getloginFlag()= true;
                                isAdmin = true;
                                USERNAME = user;
                                System.out.println("pin matched to admin user");
                                this.setVisible(false);
                                System.out.println("Exited from Login class");
                                // Show the admin Dashboard
                                new DashBoard();
                                break;
                            } else {
                                isAdmin = false;
                            }
                        } else {
                            isAdmin = false;
                        }
                    }

                    // Check if the user is a regular user
                    if (!isAdmin) { // Check if the user is not an admin
                        // Read the user_login.txt file
                        File userfile = new File("./files/user_login.txt");
                        if (userfile.exists()) { // Check if the file exists
                            // Create a buffered reader to read the file
                            BufferedReader readFile =
                                    new BufferedReader(new FileReader("./files/user_login.txt"));
                            int totalLines = 0;
                            // Count the total number of lines in the file
                            while (readFile.readLine() != null) {
                                totalLines++;
                            }
                            readFile.close();

                            // Loop through each line of the file
                            for (int i = 0; i < totalLines; i++) {
                                // Get the i-th line of the file
                                Path userLoginPath = Paths.get("./files/user_login.txt");
                                String line = Files.readAllLines(userLoginPath).get(i);
                                // Check if the username matches the i-th line of the file
                                if (line.equals(uname)) {
                                    // Check if the password matches the (i+1)-th line of the file
                                    System.out.println("User found");
                                    String line2 = Files.readAllLines(userLoginPath).get((i + 1));
                                    if (line2.equals(pin)) {
                                        System.out.println("Password matched with user name");
                                        // Set login flag, username, full name, phone number, and old password
                                        loginFlag = true;
                                        userbool = true;
                                        USERNAME = user;
                                        fullName = Files.readAllLines(userLoginPath).get(i - 1);
                                        phoneNumber = Files.readAllLines(userLoginPath).get(i + 2);
                                        oldPassword = Files.readAllLines(userLoginPath).get(i + 1);
                                        fullUsername = uname;
                                        // Hide the login frame and show the User dashboard
                                        System.out.println("Exited from Login class");
                                        this.setVisible(false);
                                        new UDashBoard();
                                        // Exit the loop
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    // If the login is unsuccessful, show an error message
                    if (!userbool && !isAdmin) {
                        JOptionPane.showMessageDialog(
                                null, "Invalid Name or Password!", "Warning!", JOptionPane.WARNING_MESSAGE);
                    }
                    // Catch any exceptions and show an error message
                } catch (Exception ex) {
                    if (!userbool && !isAdmin) {
                        JOptionPane.showMessageDialog(
                                null, "Invalid Name or Password!", "Warning!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } else if (e.getSource()
                == forgot) { // If the "forgot" button was clicked, go to the ForgotPass page
            this.setVisible(false);
            System.out.println("Exited from Login class");
            new ForgetPass();
        }
    }}
