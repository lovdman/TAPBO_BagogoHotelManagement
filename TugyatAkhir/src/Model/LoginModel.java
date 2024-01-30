package Model;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class LoginModel {
    public static String USERNAME;
    protected static boolean loginFlag;
    public static boolean isAdmin;
    protected static String fullName;
    protected static String oldPassword;
    protected static String phoneNumber;
    protected static String fullUsername;

    // Other methods and properties specific to the model can be added here.
    // For example, methods to handle authentication logic.

    public static boolean getLoginFlag() {
        return loginFlag;
    }
}
