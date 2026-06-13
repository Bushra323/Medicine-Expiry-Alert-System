package view;

import model.DatabaseHelper;
import model.Medicine;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DeleteMedicineView extends JFrame {

    public DeleteMedicineView() {

        setTitle("Delete Medicine");

        setSize(450, 220);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        List<Medicine> medicines =
                DatabaseHelper.getAllMedicines();

        JComboBox<Medicine> medicineBox =
                new JComboBox<>(
                        medicines.toArray(
                                new Medicine[0]
                        )
                );

        JButton deleteButton =
                new JButton("Delete Selected Medicine");

        JPanel panel =
                new JPanel(new GridLayout(2,1,10,10));

        panel.add(medicineBox);

        panel.add(deleteButton);

        add(panel, BorderLayout.CENTER);

        deleteButton.addActionListener(e -> {

            Medicine selectedMedicine =
                    (Medicine) medicineBox.getSelectedItem();

            if(selectedMedicine == null){

                JOptionPane.showMessageDialog(
                        this,
                        "No medicine selected."
                );

                return;
            }

            int choice =
                    JOptionPane.showConfirmDialog(

                            this,

                            "Are you sure you want to delete\n\n"
                                    + selectedMedicine.getName()
                                    + " ?",

                            "Confirm Delete",

                            JOptionPane.YES_NO_OPTION,

                            JOptionPane.WARNING_MESSAGE
                    );

            if(choice == JOptionPane.YES_OPTION){

                DatabaseHelper.deleteMedicine(
                        selectedMedicine.getId()
                );

                JOptionPane.showMessageDialog(

                        this,

                        "Medicine deleted successfully."
                );

                dispose();
            }
        });

        setVisible(true);
    }
}