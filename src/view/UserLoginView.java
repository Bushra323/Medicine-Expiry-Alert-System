package view;

import model.DatabaseHelper;

import javax.swing.*;
import java.awt.*;

public class UserLoginView extends JFrame {

    public UserLoginView() {

        setTitle("User Login");

        setSize(500,450);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(null);

        JLabel title =
                new JLabel("User Login");

        title.setFont(
                new Font("Arial",
                        Font.BOLD,
                        24)
        );

        title.setBounds(
                180,
                30,
                200,
                30
        );

        add(title);

        JLabel userLabel =
                new JLabel("Username");

        userLabel.setBounds(
                70,
                100,
                120,
                25
        );

        add(userLabel);

        JTextField userField =
                new JTextField();

        userField.setBounds(
                220,
                100,
                180,
                25
        );

        add(userField);

        JLabel passLabel =
                new JLabel("Password");

        passLabel.setBounds(
                70,
                150,
                120,
                25
        );

        add(passLabel);

        JPasswordField passField =
                new JPasswordField();

        passField.setBounds(
                220,
                150,
                180,
                25
        );

        add(passField);

        JCheckBox show =
                new JCheckBox("Show Password");

        show.setBounds(
                220,
                180,
                150,
                25
        );

        add(show);

        show.addActionListener(e -> {

            if(show.isSelected()){

                passField.setEchoChar((char)0);

            }else{

                passField.setEchoChar('•');
            }
        });

        JButton loginBtn =
                new JButton("LOGIN");

        loginBtn.setBounds(
                70,
                260,
                140,
                40
        );

        add(loginBtn);

        JButton registerBtn =
                new JButton("REGISTER");

        registerBtn.setBounds(
                250,
                260,
                140,
                40
        );

        add(registerBtn);

        JButton backBtn =
                new JButton("BACK");

        backBtn.setBounds(
                160,
                320,
                140,
                40
        );

        add(backBtn);

        loginBtn.addActionListener(e -> {

            boolean success =
                    DatabaseHelper.userLogin(
                            userField.getText().trim(),
                            new String(
                                    passField.getPassword()
                            )
                    );

            if(success){

                new UserDashboardView();

                dispose();

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Login"
                );
            }
        });

        registerBtn.addActionListener(e -> {

            boolean success =
                    DatabaseHelper.registerUser(
                            userField.getText().trim(),
                            new String(
                                    passField.getPassword()
                            )
                    );

            if(success){

                JOptionPane.showMessageDialog(
                        this,
                        "User Registered Successfully.\nPlease Login."
                );

                userField.setText("");
                passField.setText("");

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Username Already Exists"
                );
            }
        });

        backBtn.addActionListener(e -> {

            new LoginView();

            dispose();
        });

        setVisible(true);
    }
}