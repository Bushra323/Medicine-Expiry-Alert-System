package view;

import controller.MedicineController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddMedicineView extends JDialog {

    public AddMedicineView(JFrame parent) {

        super(parent, "Add Medicine", true);

        MedicineController controller =
                new MedicineController();

        setSize(450, 400);

        setLocationRelativeTo(parent);

        setLayout(new GridLayout(7, 2, 10, 10));

        // ================= FIELDS =================

        JTextField nameField =
                new JTextField();

        JTextField regField =
                new JTextField();

        JTextField licenseField =
                new JTextField();

        JTextField manufacturerField =
                new JTextField();

        JTextField qtyField =
                new JTextField();

        // NORMAL EMPTY FIELD

        JTextField expiryField =
                new JTextField();

        // ================= ONLY INTEGER INPUT =================

        KeyAdapter numberOnly =
                new KeyAdapter() {

                    @Override
                    public void keyTyped(KeyEvent e) {

                        char c =
                                e.getKeyChar();

                        if (!Character.isDigit(c)) {

                            e.consume();
                        }
                    }
                };

        regField.addKeyListener(numberOnly);

        licenseField.addKeyListener(numberOnly);

        qtyField.addKeyListener(numberOnly);

        // ================= LABELS =================

        add(new JLabel("Medicine Name"));
        add(nameField);

        add(new JLabel("Registration No"));
        add(regField);

        add(new JLabel("License No"));
        add(licenseField);

        add(new JLabel("Manufactured By"));
        add(manufacturerField);

        add(new JLabel("Quantity"));
        add(qtyField);

        // FORMAT WRITTEN OUTSIDE BOX

        add(new JLabel("Expiry Date (YYYY-MM-DD)"));
        add(expiryField);

        // ================= BUTTONS =================

        JButton saveButton =
                new JButton("Save");

        JButton cancelButton =
                new JButton("Cancel");

        add(saveButton);

        add(cancelButton);

        // ================= SAVE ACTION =================

        saveButton.addActionListener(e -> {

            String result =
                    controller.addMedicine(

                            nameField.getText(),

                            regField.getText(),

                            licenseField.getText(),

                            manufacturerField.getText(),

                            qtyField.getText(),

                            expiryField.getText()
                    );

            JOptionPane.showMessageDialog(
                    this,
                    result
            );

            dispose();
        });

        // ================= CANCEL =================

        cancelButton.addActionListener(e ->
                dispose());

        setVisible(true);
    }
}