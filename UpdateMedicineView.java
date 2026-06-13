package view;

import model.DatabaseHelper;
import model.Medicine;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class UpdateMedicineView extends JFrame {

    public UpdateMedicineView() {

        setTitle("Update Medicine");

        setSize(400,300);

        setLocationRelativeTo(null);

        setLayout(new GridLayout(4,2));

        List<Medicine> list =
                DatabaseHelper.getAllMedicines();

        JComboBox<Medicine> box =
                new JComboBox<>(list.toArray(new Medicine[0]));

        JTextField qty = new JTextField();

        JTextField expiry = new JTextField();

        JButton update = new JButton("Update");

        add(new JLabel("Select Medicine"));
        add(box);

        add(new JLabel("New Quantity"));
        add(qty);

        add(new JLabel("New Expiry (YYYY-MM-DD)"));
        add(expiry);

        add(update);

        update.addActionListener(e -> {

            Medicine m =
                    (Medicine) box.getSelectedItem();

            DatabaseHelper.updateMedicine(
                    m.getId(),
                    Integer.parseInt(qty.getText()),
                    LocalDate.parse(expiry.getText())
            );

            JOptionPane.showMessageDialog(this,
                    "Updated Successfully");

            dispose();
        });

        setVisible(true);
    }
}
