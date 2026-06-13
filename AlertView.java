package view;

import model.Medicine;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AlertView extends JFrame {

    public AlertView(List<Medicine> medicines) {

        setTitle("Medicine Expiry Alerts");

        setSize(850, 450);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ================= TITLE =================

        JLabel title =
                new JLabel("Medicine Expiry Alert System");

        title.setFont(new Font("Arial", Font.BOLD, 24));

        title.setHorizontalAlignment(SwingConstants.CENTER);

        title.setForeground(new Color(25, 25, 112));

        add(title, BorderLayout.NORTH);

        // ================= TABLE =================

        String[] columns = {

                "Medicine Name",

                "Registration No",

                "Manufacturer",

                "Expiry Date",

                "Days Left",

                "Status"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns, 0);

        JTable table =
                new JTable(model);

        table.setRowHeight(30);

        table.setFont(new Font("Arial", Font.PLAIN, 14));

        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        table.getTableHeader().setBackground(
                new Color(70, 130, 180)
        );

        table.getTableHeader().setForeground(Color.WHITE);

        // ================= DATA =================

        for (Medicine m : medicines) {

            long daysLeft =
                    ChronoUnit.DAYS.between(
                            LocalDate.now(),
                            m.getExpiryDate()
                    );

            String status;

            if (daysLeft < 0) {

                status =
                        "EXPIRED";

            }
            else if (daysLeft <= 30) {

                status =
                        "NEAR EXPIRY";

            }
            else {

                status =
                        "SAFE";
            }

            model.addRow(new Object[]{

                    m.getName(),

                    m.getRegistrationNo(),

                    m.getManufacturedBy(),

                    m.getExpiryDate(),

                    daysLeft,

                    status
            });
        }

        // ================= COLOR RENDERER =================

        table.setDefaultRenderer(
                Object.class,
                new StatusColorRenderer()
        );

        JScrollPane pane =
                new JScrollPane(table);

        add(pane, BorderLayout.CENTER);

        setVisible(true);
    }

    // ================= COLOR CLASS =================

    class StatusColorRenderer
            extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(

                JTable table,

                Object value,

                boolean isSelected,

                boolean hasFocus,

                int row,

                int column
        ) {

            Component c =
                    super.getTableCellRendererComponent(

                            table,

                            value,

                            isSelected,

                            hasFocus,

                            row,

                            column
                    );

            String status =
                    table.getValueAt(row, 5).toString();

            // EXPIRED = RED

            if (status.equals("EXPIRED")) {

                c.setBackground(new Color(255, 102, 102));

                c.setForeground(Color.BLACK);
            }

            // NEAR EXPIRY = ORANGE

            else if (status.equals("NEAR EXPIRY")) {

                c.setBackground(new Color(255, 204, 102));

                c.setForeground(Color.BLACK);
            }

            // SAFE = GREEN

            else {

                c.setBackground(new Color(144, 238, 144));

                c.setForeground(Color.BLACK);
            }

            return c;
        }
    }
}