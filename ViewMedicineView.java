package view;

import model.DatabaseHelper;
import model.Medicine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ViewMedicineView extends JFrame {

    public ViewMedicineView() {

        setTitle("All Medicines");

        setSize(700,400);

        setLocationRelativeTo(null);

        String[] cols = {
                "ID",
                "Name",
                "Reg No",
                "License",
                "Manufacturer",
                "Quantity",
                "Expiry"
        };

        DefaultTableModel model =
                new DefaultTableModel(cols,0);

        List<Medicine> list =
                DatabaseHelper.getAllMedicines();

        for(Medicine m : list){

            model.addRow(new Object[]{
                    m.getId(),
                    m.getName(),
                    m.getRegistrationNo(),
                    m.getManufacturingLicenseNo(),
                    m.getManufacturedBy(),
                    m.getQuantity(),
                    m.getExpiryDate()
            });
        }

        JTable table = new JTable(model);

        add(new JScrollPane(table));

        setVisible(true);
    }
}