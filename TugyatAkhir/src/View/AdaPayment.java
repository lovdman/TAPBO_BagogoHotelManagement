package View;

import myinterface.ConfirmPayment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdaPayment extends JFrame implements ActionListener, ConfirmPayment {

  final JLabel ada_lbl;
  final JTextField num_fld; // Text field for entering the mobile number
  final JButton back_btn; // Button for navigating back
  final JButton next_btn; // Button for proceeding to the next step
  final JPasswordField pass_fld; // Password field for entering the PIN


  AdaPayment() {
    System.out.println("Currently in AdaPayment class");
    setTitle("Bkash Payment");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 903, 554);
    setLocationRelativeTo(null);
    setResizable(false);
    getContentPane().setBackground(Color.GRAY);
    setResizable(false);

    ImageIcon img = new ImageIcon("./images/AdaPayment.png");
    Image i = img.getImage();
    Image new_img = i.getScaledInstance(854, 580, Image.SCALE_SMOOTH);
    img = new ImageIcon(new_img);
    ada_lbl = new JLabel("", img, JLabel.CENTER);
    ada_lbl.setBounds(-190, -220, 854, 580);

    // Create and position text field for entering mobile number
    num_fld = new JTextField();
    num_fld.setBounds(260, 250, 300, 25);
    this.add(num_fld);

    // Create and position password field for entering PIN
    pass_fld = new JPasswordField();
    pass_fld.setBounds(260, 380, 300, 25);
    this.add(pass_fld);

    // Add Bkash logo label to the JFrame
    this.add(ada_lbl);

    // Create and position back button
    back_btn = new JButton("Back");
    back_btn.setFont(new Font("Abadi", Font.BOLD, 15));
    back_btn.setBackground(Color.BLACK);
    back_btn.setForeground(Color.WHITE);
    back_btn.setBounds(720, 460, 80, 25);
    back_btn.addActionListener(this);
    this.add(back_btn);

    // Create and position next button
    next_btn = new JButton("Next");
    next_btn.setFont(new Font("Abadi", Font.BOLD, 15));
    next_btn.setBackground(Color.BLACK);
    next_btn.setForeground(Color.WHITE);
    next_btn.setBounds(620, 460, 80, 25);
    next_btn.addActionListener(this);
    this.add(next_btn);

    this.add(ada_lbl);

    setVisible(true);
  }


  public void actionPerformed(ActionEvent ae) {
    String MobileNumber = num_fld.getText();
    String Pin = String.valueOf(pass_fld.getPassword());

    if (ae.getSource() == back_btn) {
      new Payment();
      this.setVisible(false);
      System.out.println("Exited from AdaPayment class");
    } else if (ae.getSource() == next_btn) {
      boolean isMobileNumberEmpty = MobileNumber.isEmpty();
      boolean isPinEmpty = Pin.isEmpty();
      confirmPayment(isMobileNumberEmpty, isPinEmpty, this, num_fld, pass_fld);
    }
  }


  @Override
  public void confirmPayment(
      boolean isMobileNumberEmpty,
      boolean isPinEmpty,
      JFrame paymentFrame,
      JTextField number,
      JPasswordField password) {
    System.out.println("confirmPayment function called");
    if (isMobileNumberEmpty) {
      JOptionPane.showMessageDialog(null, "Invalid Phone number ");
      System.out.println("Number field is empty");
    } else {
      if (isPinEmpty) {
        JOptionPane.showMessageDialog(null, "Invalid  Pin");
        System.out.println("Pin number is invalid");
      } else {
        if (inputValidation(number, password) && inputLength(number, password)) {
          JOptionPane.showMessageDialog(null, "Payment Confirmed\nThank You For Staying At Tipton");
          System.out.println("Payment Done Successfully");
          new UDashBoard();
          paymentFrame.setVisible(false);
          System.out.println("Exited from AdaPayment class");
        }
      }
    }
    System.out.println("confirmPayment funtion executed successfully");
  }


  @Override
  public boolean inputValidation(JTextField number, JPasswordField password) {
    System.out.println("inputValidation funtion called");
    boolean n = true;
    boolean p = true;
    String numberText = number.getText();
    for (int i = 0; i < numberText.length(); i++) {
      char c = numberText.charAt(i);
      if (!Character.isDigit(c)) {
        n = false;
        break;
      }
    }

    String passText = String.valueOf(password.getPassword());
    for (int i = 0; i < passText.length(); i++) {
      char c = passText.charAt(i);
      if (!Character.isDigit(c)) {
        p = false;
        break;
      }
    }
    if (!n && !p) {
      JOptionPane.showMessageDialog(
          null,
          "Phone number and Pin number cannot contain characters",
          "Input error",
          JOptionPane.WARNING_MESSAGE);
      return false;
    } else if (!n) {
      JOptionPane.showMessageDialog(
          null,
          "Phone Number cannot contain characters",
          "Phone number error",
          JOptionPane.WARNING_MESSAGE);
      return false;
    } else if (!p) {
      JOptionPane.showMessageDialog(
          null,
          "Pin number cannot contain characters",
          "Pin number error",
          JOptionPane.WARNING_MESSAGE);
      return false;
    }
    System.out.println("inputValtidation funtion executed successfully");
    return true;
  }


  @Override
  public boolean inputLength(JTextField number, JPasswordField password) {
    System.out.println("inputLength funtion called");
    String numberText = number.getText();
    String passText = String.valueOf(password.getPassword());

    if (numberText.length() != 11 && passText.length() != 4) {
      JOptionPane.showMessageDialog(
          null,
          "Phone number must contain exactly 11 digits and PIN number must contain exactly 4"
              + " digits",
          "Length error",
          JOptionPane.WARNING_MESSAGE);
      return false;
    } else if (numberText.length() != 11) {
      JOptionPane.showMessageDialog(
          null,
          "Phone number must contain exactly 11 digits",
          "Length error",
          JOptionPane.WARNING_MESSAGE);
      return false;
    } else if (passText.length() != 4) {
      JOptionPane.showMessageDialog(
          null,
          "PIN number must contain exactly 4 digits",
          "Length error",
          JOptionPane.WARNING_MESSAGE);
      return false;
    }

    System.out.println("inputLength funtion executed successfully");
    return true;
  }
}
