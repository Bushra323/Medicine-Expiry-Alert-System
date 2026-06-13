package view;

import model.DatabaseHelper;
import model.Medicine;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class UserDashboardView extends JFrame {

    public UserDashboardView() {

        setTitle("User Dashboard");

        setSize(650, 550);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        panel.setLayout(null);

        // ================= TITLE =================

        JLabel title =
                new JLabel("Medicine Expiry Checker");

        title.setFont(new Font("Arial", Font.BOLD, 24));

        title.setBounds(170, 20, 350, 30);

        panel.add(title);

        // ================= MEDICINE NAME =================

        JLabel medLabel =
                new JLabel("Medicine Name");

        medLabel.setBounds(40, 90, 180, 25);

        panel.add(medLabel);

        JTextField medField =
                new JTextField();

        medField.setBounds(260, 90, 250, 25);

        panel.add(medField);

        // ================= REGISTRATION NO =================

        JLabel regLabel =
                new JLabel("Registration No");

        regLabel.setBounds(40, 130, 180, 25);

        panel.add(regLabel);

        JTextField regField =
                new JTextField();

        regField.setBounds(260, 130, 250, 25);

        panel.add(regField);

        // ================= LICENSE NO =================

        JLabel licLabel =
                new JLabel("Manufacturing License No");

        licLabel.setBounds(40, 170, 180, 25);

        panel.add(licLabel);

        JTextField licField =
                new JTextField();

        licField.setBounds(260, 170, 250, 25);

        panel.add(licField);

        // ================= MANUFACTURER =================

        JLabel manuLabel =
                new JLabel("Manufactured By");

        manuLabel.setBounds(40, 210, 180, 25);

        panel.add(manuLabel);

        JTextField manuField =
                new JTextField();

        manuField.setBounds(260, 210, 250, 25);

        panel.add(manuField);

        // ================= RESULT LABELS =================

        JLabel resultLabel =
                new JLabel("");

        resultLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        16)
        );

        resultLabel.setBounds(
                50,
                330,
                550,
                30
        );

        panel.add(resultLabel);

        JLabel expiryLabel =
                new JLabel("");

        expiryLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        16)
        );

        expiryLabel.setBounds(
                50,
                370,
                550,
                30
        );

        panel.add(expiryLabel);

        JLabel statusLabel =
                new JLabel("");

        statusLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        20)
        );

        statusLabel.setBounds(
                50,
                410,
                550,
                40
        );

        panel.add(statusLabel);

        // ================= BUTTONS =================

        JButton checkButton =
                new JButton("Check Expiry");

        checkButton.setBounds(
                50,
                270,
                150,
                40
        );

        panel.add(checkButton);

        JButton clearButton =
                new JButton("Clear Fields");

        clearButton.setBounds(
                240,
                270,
                150,
                40
        );

        panel.add(clearButton);

        JButton logoutButton =
                new JButton("Logout");

        logoutButton.setBounds(
                430,
                270,
                120,
                40
        );

        panel.add(logoutButton);

        // ================= CHECK EXPIRY =================

        checkButton.addActionListener(e -> {

            String name =
                    medField.getText().trim();

            String reg =
                    regField.getText().trim();

            String lic =
                    licField.getText().trim();

            String manu =
                    manuField.getText().trim();

            List<Medicine> medicines =
                    DatabaseHelper.getAllMedicines();

            boolean found = false;

            for (Medicine m : medicines) {

                if (m.getName().equalsIgnoreCase(name)
                        &&
                        m.getRegistrationNo().equalsIgnoreCase(reg)
                        &&
                        m.getManufacturingLicenseNo().equalsIgnoreCase(lic)
                        &&
                        m.getManufacturedBy().equalsIgnoreCase(manu)) {

                    found = true;

                    LocalDate expiryDate =
                            m.getExpiryDate();

                    long daysLeft =
                            ChronoUnit.DAYS.between(
                                    LocalDate.now(),
                                    expiryDate
                            );

                    resultLabel.setText(
                            "Medicine: " + m.getName()
                    );

                    expiryLabel.setText(
                            "Expiry Date: "
                                    + expiryDate
                                    + " | Days Left: "
                                    + daysLeft
                    );

                    if(daysLeft < 0){

                        statusLabel.setText(
                                "❌ EXPIRED"
                        );

                        statusLabel.setForeground(
                                Color.RED
                        );
                    }

                    else if(daysLeft <= 30){

                        statusLabel.setText(
                                "⚠ NEAR EXPIRY"
                        );

                        statusLabel.setForeground(
                                Color.ORANGE
                        );
                    }

                    else{

                        statusLabel.setText(
                                "✔ SAFE TO USE"
                        );

                        statusLabel.setForeground(
                                new Color(0,128,0)
                        );
                    }

                    break;
                }
            }

            if(!found){

                JOptionPane.showMessageDialog(
                        this,
                        "Medicine Not Found"
                );
            }
        });

        // ================= CLEAR FIELDS =================

        clearButton.addActionListener(e -> {

            medField.setText("");

            regField.setText("");

            licField.setText("");

            manuField.setText("");

            resultLabel.setText("");

            expiryLabel.setText("");

            statusLabel.setText("");

            medField.requestFocus();
        });

        // ================= LOGOUT =================

        logoutButton.addActionListener(e -> {

            int choice =
                    JOptionPane.showConfirmDialog(

                            this,

                            "Are you sure you want to logout?",

                            "Logout",

                            JOptionPane.YES_NO_OPTION
                    );

            if(choice ==
                    JOptionPane.YES_OPTION){

                dispose();

                new LoginView();
            }
        });

        add(panel);

        setVisible(true);
    }
}