package view;

import controller.AlertController;
import model.Medicine;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class PharmacistDashboardView extends JFrame {

    public PharmacistDashboardView() {

        setTitle("Pharmacist Dashboard");

        setSize(550, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.setLayout(
                new GridLayout(8, 1, 10, 10)
        );

        // ================= BUTTONS =================

        JButton addBtn =
                new JButton("Add Medicine");

        JButton viewBtn =
                new JButton("View Medicines");

        JButton updateBtn =
                new JButton("Update Medicine");

        JButton deleteBtn =
                new JButton("Delete Medicine");

        JButton searchBtn =
                new JButton("Search Medicine");

        JButton alertBtn =
                new JButton("Expiry Alerts");

        JButton reportBtn =
                new JButton("Inventory Report");

        JButton logoutBtn =
                new JButton("Logout");

        // ================= ADD TO PANEL =================

        panel.add(addBtn);

        panel.add(viewBtn);

        panel.add(updateBtn);

        panel.add(deleteBtn);

        panel.add(searchBtn);

        panel.add(alertBtn);

        panel.add(reportBtn);

        panel.add(logoutBtn);

        // ================= BUTTON ACTIONS =================

        addBtn.addActionListener(e ->
                new AddMedicineView(this));

        viewBtn.addActionListener(e ->
                new ViewMedicineView());

        updateBtn.addActionListener(e ->
                new UpdateMedicineView());

        deleteBtn.addActionListener(e ->
                new DeleteMedicineView());

        searchBtn.addActionListener(e ->
                new SearchMedicineView());

        alertBtn.addActionListener(e ->
                new AlertView(
                        new AlertController()
                                .getAllAlerts()
                ));

        reportBtn.addActionListener(e ->
                new InventoryReportView());

        // ================= LOGOUT =================

        logoutBtn.addActionListener(e -> {

            int choice =
                    JOptionPane.showConfirmDialog(

                            this,

                            "Are you sure you want to logout?",

                            "Logout",

                            JOptionPane.YES_NO_OPTION
                    );

            if (choice ==
                    JOptionPane.YES_OPTION) {

                dispose();

                new LoginView();
            }
        });

        // ================= AUTO NOTIFICATIONS =================

        showNotifications();

        add(panel);

        setVisible(true);
    }

    // ================= EXPIRY NOTIFICATIONS =================

    private void showNotifications() {

        List<Medicine> medicines =
                new AlertController()
                        .getAllAlerts();

        if (medicines.isEmpty()) {

            return;
        }

        StringBuilder msg =
                new StringBuilder();

        for (Medicine m : medicines) {

            long days =
                    ChronoUnit.DAYS.between(
                            LocalDate.now(),
                            m.getExpiryDate()
                    );

            if (days < 0) {

                msg.append("❌ ")
                        .append(m.getName())
                        .append(" expired ")
                        .append(Math.abs(days))
                        .append(" days ago\n");
            }

            else if (days <= 30) {

                msg.append("⚠ ")
                        .append(m.getName())
                        .append(" expires in ")
                        .append(days)
                        .append(" days\n");
            }
        }

        if (!msg.toString().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    msg.toString(),
                    "Medicine Alerts",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}