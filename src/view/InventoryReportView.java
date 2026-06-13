package view;

import model.DatabaseHelper;
import model.Medicine;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class InventoryReportView extends JFrame {

    public InventoryReportView() {

        setTitle("Inventory Report");

        setSize(550, 500);

        setLocationRelativeTo(null);

        setLayout(new GridLayout(9, 1, 10, 10));

        List<Medicine> medicines =
                DatabaseHelper.getAllMedicines();

        int totalMedicines = 0;
        int safeMedicines = 0;
        int nearMedicines = 0;
        int expiredMedicines = 0;

        int totalQuantity = 0;
        int safeQuantity = 0;
        int nearQuantity = 0;
        int expiredQuantity = 0;

        for (Medicine m : medicines) {

            totalMedicines++;

            totalQuantity += m.getQuantity();

            long days =
                    ChronoUnit.DAYS.between(
                            LocalDate.now(),
                            m.getExpiryDate()
                    );

            if (days < 0) {

                expiredMedicines++;

                expiredQuantity += m.getQuantity();
            }

            else if (days <= 30) {

                nearMedicines++;

                nearQuantity += m.getQuantity();
            }

            else {

                safeMedicines++;

                safeQuantity += m.getQuantity();
            }
        }

        JLabel title =
                new JLabel(
                        "Inventory Report",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        title.setForeground(
                new Color(25, 25, 112)
        );

        JLabel totalMed =
                new JLabel(
                        "Total Medicines : "
                                + totalMedicines
                );

        totalMed.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        JLabel safeMed =
                new JLabel(
                        "Safe Medicines : "
                                + safeMedicines
                );

        safeMed.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        safeMed.setForeground(
                new Color(0, 128, 0)
        );

        JLabel nearMed =
                new JLabel(
                        "Near Expiry Medicines : "
                                + nearMedicines
                );

        nearMed.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        nearMed.setForeground(
                Color.ORANGE
        );

        JLabel expiredMed =
                new JLabel(
                        "Expired Medicines : "
                                + expiredMedicines
                );

        expiredMed.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        expiredMed.setForeground(
                Color.RED
        );

        JLabel totalQty =
                new JLabel(
                        "Total Quantity : "
                                + totalQuantity
                );

        totalQty.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        JLabel safeQty =
                new JLabel(
                        "Safe Quantity : "
                                + safeQuantity
                );

        safeQty.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        safeQty.setForeground(
                new Color(0, 128, 0)
        );

        JLabel nearQty =
                new JLabel(
                        "Near Expiry Quantity : "
                                + nearQuantity
                );

        nearQty.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        nearQty.setForeground(
                Color.ORANGE
        );

        JLabel expiredQty =
                new JLabel(
                        "Expired Quantity : "
                                + expiredQuantity
                );

        expiredQty.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        expiredQty.setForeground(
                Color.RED
        );

        add(title);

        add(totalMed);

        add(safeMed);

        add(nearMed);

        add(expiredMed);

        add(totalQty);

        add(safeQty);

        add(nearQty);

        add(expiredQty);

        setVisible(true);
    }
}