package controller;

import model.DatabaseHelper;
import model.Medicine;

import java.time.LocalDate;
import java.util.List;

public class InventroyController {

    private MedicineController medicineController = new MedicineController();

    public List<Medicine> getFullInventory() {
        return DatabaseHelper.getAllMedicines();
    }

    public void updateRecord(int id, int newQty, LocalDate newExpiry) {
        DatabaseHelper.updateMedicine(id, newQty, newExpiry);
    }

    public void removeRecord(int id) {
        DatabaseHelper.deleteMedicine(id);
    }
}