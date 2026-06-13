package model;

public class Pharmacist extends User {

    // Pharmacist inherits id, username, password, role from User
    // It just always has role = "PHARMACIST"

    public Pharmacist(int id, String username, String password) {
        super(id, username, password, "PHARMACIST");
    }

    // Extra method only pharmacists have
    public boolean canManageInventory() {
        return true;
    }
}