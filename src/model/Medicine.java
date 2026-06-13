package model;

import java.time.LocalDate;

public class Medicine {

    private int id;
    private String name;
    private String registrationNo;
    private String manufacturingLicenseNo;
    private String manufacturedBy;
    private int quantity;
    private LocalDate expiryDate;

    public Medicine(int id,
                    String name,
                    String registrationNo,
                    String manufacturingLicenseNo,
                    String manufacturedBy,
                    int quantity,
                    LocalDate expiryDate) {

        this.id = id;
        this.name = name;
        this.registrationNo = registrationNo;
        this.manufacturingLicenseNo = manufacturingLicenseNo;
        this.manufacturedBy = manufacturedBy;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public String getManufacturingLicenseNo() {
        return manufacturingLicenseNo;
    }

    public String getManufacturedBy() {
        return manufacturedBy;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
}