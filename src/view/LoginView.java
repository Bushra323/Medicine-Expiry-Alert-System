package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    public LoginView() {

        setTitle("Medicine Expiry Alert System");

        setSize(450,350);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(null);

        JLabel title =
                new JLabel("Choose Login Type");

        title.setFont(
                new Font("Arial",
                        Font.BOLD,
                        24)
        );

        title.setBounds(
                90,
                50,
                300,
                30
        );

        add(title);

        JButton userBtn =
                new JButton("USER");

        userBtn.setBounds(
                120,
                120,
                180,
                45
        );

        add(userBtn);

        JButton pharmacistBtn =
                new JButton("PHARMACIST");

        pharmacistBtn.setBounds(
                120,
                190,
                180,
                45
        );

        add(pharmacistBtn);

        userBtn.addActionListener(e -> {

            new UserLoginView();

            dispose();
        });

        pharmacistBtn.addActionListener(e -> {

            new PharmacistLoginView();

            dispose();
        });

        setVisible(true);
    }
}