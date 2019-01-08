package View.loginpanel;

import View.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by kreisso on 02.11.2018.
 */
public class RegisterFrame extends Frame {
    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frameWidth = 500;
    private int frameHeight = 500;
//    private int halfFieldWidth = (frameWidth - 150) / 2;
    private int firstLabelHeight = 50;
    private int firstFieldHeight = 70;
    private int spaceBetween = 90;
    private JButton buttonSignUp;
    private JButton buttonGoToLogin;
    private JTextField inputLogin;
    private JPasswordField inputPassword;
    private JPasswordField inputRepeatPassword;


    public String getInputLogin() {
        return inputLogin.getText();
    }

    public char[] getInputPassword() {
        return inputPassword.getPassword();
    }

    public char[] getInputRepeatPassword() {
        return inputRepeatPassword.getPassword();
    }



    public RegisterFrame(String name) throws HeadlessException {
        super(name);

        this.setLocation((screenWidth- frameWidth)/2, (screenHeight- frameHeight)/2);

        this.setSize(frameWidth, frameHeight);

        this.setLayout(null);

        JLabel labelLogin = new JLabel("Login:");
        labelLogin.setSize(labelLogin.getPreferredSize());
        labelLogin.setLocation(50, firstLabelHeight);
        this.add(labelLogin);

        inputLogin = new JTextField();
        inputLogin.setSize(frameWidth -100, 40);
        inputLogin.setLocation(50, firstFieldHeight);
        this.add(inputLogin);

        JLabel labelPassword = new JLabel("Hasło:");
        labelPassword.setSize(labelPassword.getPreferredSize());
        labelPassword.setLocation(50, firstLabelHeight+spaceBetween);
        this.add(labelPassword);

        inputPassword = new JPasswordField();
        inputPassword.setSize(frameWidth -100, 40);
        inputPassword.setLocation(50, firstFieldHeight+spaceBetween);
        this.add(inputPassword);

        JLabel labelRepeatPassword = new JLabel("Powtórz hasło:");
        labelRepeatPassword.setSize(labelRepeatPassword.getPreferredSize());
        labelRepeatPassword.setLocation(50, firstLabelHeight+(2*spaceBetween));
        this.add(labelRepeatPassword);

        inputRepeatPassword = new JPasswordField();
        inputRepeatPassword.setSize(frameWidth -100, 40);
        inputRepeatPassword.setLocation(50, firstFieldHeight+(2*spaceBetween));
        this.add(inputRepeatPassword);

    }

    public void setButtonSignUp(ActionListener actionListener){

        buttonSignUp = new JButton("Zarejestruj");
        buttonSignUp.setSize(200,50);
        buttonSignUp.setLocation(450-200,380);
        buttonSignUp.addActionListener(actionListener);

        this.getRootPane().setDefaultButton(buttonSignUp);
        this.add(buttonSignUp);
    }

    public void setButtonGoToLogIn(ActionListener actionListener){

        buttonGoToLogin = new JButton("Logowanie");
        buttonGoToLogin.setBorder(null);
        buttonGoToLogin.setSize(buttonGoToLogin.getPreferredSize());
        buttonGoToLogin.setLocation(50, 400);
        buttonGoToLogin.setForeground(Color.blue);
        buttonGoToLogin.addActionListener(actionListener);
        this.add(buttonGoToLogin);
    }

    public void setErrorLabel(String errorMessage){
        JOptionPane.showMessageDialog(this,
                errorMessage,
                "Błędne dane",
                JOptionPane.ERROR_MESSAGE);
    }
}