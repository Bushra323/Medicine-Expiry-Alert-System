package model;

public class Pharmacist extends User {

    public Pharmacist(int id, String username, String password) {
        super(id, username, password, "PHARMACIST");
    }

    public boolean canManageInventory() {
        return true;
    }
}