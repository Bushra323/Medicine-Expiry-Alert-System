package controller;

import model.DatabaseHelper;
import model.Medicine;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class MedicineController {

    // ================= ADD MEDICINE =================

    public String addMedicine(
            String name,
            String regNo,
            String licenseNo,
            String manufacturer,
            String qtyStr,
            String expiryStr
    ) {

        // EMPTY CHECK

        if (name.isEmpty()
                || regNo.isEmpty()
                || licenseNo.isEmpty()
                || manufacturer.isEmpty()
                || qtyStr.isEmpty()
                || expiryStr.isEmpty()) {

            return "Fill all fields";
        }

        // QUANTITY CHECK

        int quantity;

        try {

            quantity =
                    Integer.parseInt(qtyStr);

        } catch (Exception e) {

            return "Quantity must be number";
        }

        // DATE CHECK

        LocalDate expiryDate;

        try {

            expiryDate =
                    LocalDate.parse(expiryStr);

        } catch (DateTimeParseException e) {

            return "Date format must be YYYY-MM-DD";
        }

        // CREATE OBJECT

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

        // SAVE TO DATABASE

        DatabaseHelper.addMedicine(medicine);

        return "Medicine Added Successfully";
    }

    // ================= GET ALL =================

    public List<Medicine> getAllMedicines() {

        return DatabaseHelper.getAllMedicines();
    }

    // ================= UPDATE =================

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

    // ================= DELETE =================

    public void deleteMedicine(int id) {

        DatabaseHelper.deleteMedicine(id);
    }
}