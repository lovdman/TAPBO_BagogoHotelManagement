package Model;

public class LoginModel {
    private static String USERNAME;
    private static boolean loginFlag;
    private static boolean isAdmin;
    private static String fullName;
    private static String oldPassword;
    private static String phoneNumber;
    private static String fullUsername;

    // Other methods and properties specific to the model can be added here.
    // For example, methods to handle authentication logic.

    public static boolean getLoginFlag() {
        return loginFlag;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static boolean isAdmin() {
        return isAdmin;
    }

    // Metode setter untuk memperbarui nilai variabel
    public static void setLoginFlag(boolean flag) {
        loginFlag = flag;
    }

    public static void setUSERNAME(String username) {
        USERNAME = username;
    }

    public static void setAdmin(boolean adminStatus) {
        isAdmin = adminStatus;
    }
}
