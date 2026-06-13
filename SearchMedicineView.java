package view;

import model.DatabaseHelper;
import model.Medicine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SearchMedicineView extends JFrame {

    private JLabel searchLabel;
    private JTextField searchField;

    public SearchMedicineView() {

        setTitle("Search Medicine");

        setSize(650,400);

        setLocationRelativeTo(null);

        setLayout(null);

        JLabel title =
                new JLabel("Search Medicine");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        title.setBounds(
                220,
                20,
                250,
                30
        );

        add(title);

        JLabel byLabel =
                new JLabel("Search By");

        byLabel.setBounds(
                50,
                70,
                100,
                25
        );

        add(byLabel);

        JRadioButton nameRadio =
                new JRadioButton("Medicine Name");

        JRadioButton regRadio =
                new JRadioButton("Registration No");

        JRadioButton licRadio =
                new JRadioButton("Manufacturing License No");

        JRadioButton manuRadio =
                new JRadioButton("Manufactured By");

        JRadioButton expRadio =
                new JRadioButton("Expiry Date");

        nameRadio.setBounds(50,100,180,25);
        regRadio.setBounds(250,100,180,25);
        licRadio.setBounds(430,100,180,25);

        manuRadio.setBounds(50,140,180,25);
        expRadio.setBounds(250,140,180,25);

        add(nameRadio);
        add(regRadio);
        add(licRadio);
        add(manuRadio);
        add(expRadio);

        ButtonGroup group =
                new ButtonGroup();

        group.add(nameRadio);
        group.add(regRadio);
        group.add(licRadio);
        group.add(manuRadio);
        group.add(expRadio);

        searchLabel =
                new JLabel("Search Name");

        searchLabel.setBounds(
                50,
                200,
                150,
                25
        );

        add(searchLabel);

        searchField =
                new JTextField();

        searchField.setBounds(
                220,
                200,
                250,
                25
        );

        add(searchField);

        JButton searchBtn =
                new JButton("Search");

        searchBtn.setBounds(
                180,
                260,
                120,
                35
        );

        add(searchBtn);

        JButton clearBtn =
                new JButton("Clear Fields");

        clearBtn.setBounds(
                340,
                260,
                140,
                35
        );

        add(clearBtn);

        nameRadio.setSelected(true);

        nameRadio.addActionListener(e ->
                searchLabel.setText("Search Name"));

        regRadio.addActionListener(e ->
                searchLabel.setText("Search Value"));

        licRadio.addActionListener(e ->
                searchLabel.setText("Search Value"));

        manuRadio.addActionListener(e ->
                searchLabel.setText("Search Name"));

        expRadio.addActionListener(e ->
                searchLabel.setText("Search Date"));

        searchBtn.addActionListener(e -> {

            String value =
                    searchField.getText().trim();

            if(value.isEmpty()){

                JOptionPane.showMessageDialog(
                        this,
                        "Please Enter Search Value"
                );

                return;
            }

            List<Medicine> medicines =
                    DatabaseHelper.getAllMedicines();

            // NAME

            if(nameRadio.isSelected()){

                for(Medicine m : medicines){

                    if(m.getName()
                            .equalsIgnoreCase(value)){

                        showSingleMedicine(m);

                        return;
                    }
                }

                JOptionPane.showMessageDialog(
                        this,
                        "Medicine Not Found"
                );
            }

            // REG NO

            else if(regRadio.isSelected()){

                for(Medicine m : medicines){

                    if(m.getRegistrationNo()
                            .equalsIgnoreCase(value)){

                        showSingleMedicine(m);

                        return;
                    }
                }

                JOptionPane.showMessageDialog(
                        this,
                        "Medicine Not Found"
                );
            }

            // LICENSE NO

            else if(licRadio.isSelected()){

                for(Medicine m : medicines){

                    if(m.getManufacturingLicenseNo()
                            .equalsIgnoreCase(value)){

                        showSingleMedicine(m);

                        return;
                    }
                }

                JOptionPane.showMessageDialog(
                        this,
                        "Medicine Not Found"
                );
            }

            // MANUFACTURER

            else if(manuRadio.isSelected()){

                showTableByManufacturer(
                        value,
                        medicines
                );
            }

            // EXPIRY DATE

            else if(expRadio.isSelected()){

                showTableByExpiry(
                        value,
                        medicines
                );
            }
        });

        clearBtn.addActionListener(e -> {

            searchField.setText("");

            nameRadio.setSelected(true);

            searchLabel.setText("Search Name");

            searchField.requestFocus();
        });

        setVisible(true);
    }

    private void showSingleMedicine(Medicine m){

        JOptionPane.showMessageDialog(

                this,

                "ID : " + m.getId()

                        + "\nMedicine Name : "
                        + m.getName()

                        + "\nRegistration No : "
                        + m.getRegistrationNo()

                        + "\nManufacturing License No : "
                        + m.getManufacturingLicenseNo()

                        + "\nManufactured By : "
                        + m.getManufacturedBy()

                        + "\nQuantity : "
                        + m.getQuantity()

                        + "\nExpiry Date : "
                        + m.getExpiryDate()
        );
    }

    private void showTableByManufacturer(
            String manufacturer,
            List<Medicine> medicines){

        String[] cols = {

                "ID",
                "Medicine",
                "Reg No",
                "License",
                "Manufacturer",
                "Quantity",
                "Expiry Date"
        };

        DefaultTableModel model =
                new DefaultTableModel(cols,0);

        for(Medicine m : medicines){

            if(m.getManufacturedBy()
                    .equalsIgnoreCase(manufacturer)){

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
        }

        if(model.getRowCount() == 0){

            JOptionPane.showMessageDialog(
                    this,
                    "Medicine Not Found"
            );

            return;
        }

        JTable table =
                new JTable(model);

        JFrame frame =
                new JFrame("Search Result");

        frame.setSize(850,400);

        frame.add(
                new JScrollPane(table)
        );

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    private void showTableByExpiry(
            String date,
            List<Medicine> medicines){

        String[] cols = {

                "ID",
                "Medicine",
                "Reg No",
                "License",
                "Manufacturer",
                "Quantity",
                "Expiry Date"
        };

        DefaultTableModel model =
                new DefaultTableModel(cols,0);

        for(Medicine m : medicines){

            if(m.getExpiryDate()
                    .toString()
                    .equals(date)){

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
        }

        if(model.getRowCount() == 0){

            JOptionPane.showMessageDialog(
                    this,
                    "Medicine Not Found"
            );

            return;
        }

        JTable table =
                new JTable(model);

        JFrame frame =
                new JFrame("Search Result");

        frame.setSize(850,400);

        frame.add(
                new JScrollPane(table)
        );

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}