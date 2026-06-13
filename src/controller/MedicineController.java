package controller;

import model.DatabaseHelper;
import model.Medicine;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class MedicineController {


    public String addMedicine(
            String name,
            String regNo,
            String licenseNo,
            String manufacturer,
            String qtyStr,
            String expiryStr
    ) {

        if (name.isEmpty()
                || regNo.isEmpty()
                || licenseNo.isEmpty()
                || manufacturer.isEmpty()
                || qtyStr.isEmpty()
                || expiryStr.isEmpty()) {

            return "Fill all fields";
        }

        int quantity;

        try {

            quantity =
                    Integer.parseInt(qtyStr);

        } catch (Exception e) {

            return "Quantity must be number";
        }

        LocalDate expiryDate;

        try {

            expiryDate =
                    LocalDate.parse(expiryStr);

        } catch (DateTimeParseException e) {

            return "Date format must be YYYY-MM-DD";
        }

        Medicine medicine =
                new Medicine(

                        0,

                        name,

                        regNo,

                        licenseNo,

                        manufacturer,

                        quantity,

                        expiryDate
                );


        DatabaseHelper.addMedicine(medicine);

        return "Medicine Added Successfully";
    }



    public List<Medicine> getAllMedicines() {

        return DatabaseHelper.getAllMedicines();
    }

    public void updateMedicine(
            int id,
            int qty,
            LocalDate expiry
    ) {

        DatabaseHelper.updateMedicine(
                id,
                qty,
                expiry
        );
    }


    public void deleteMedicine(int id) {

        DatabaseHelper.deleteMedicine(id);
    }
}