package controller;

import model.DatabaseHelper;
import model.Medicine;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class AlertController {

    // ================= EXPIRED MEDICINES =================

    public List<Medicine> getExpiredMedicines() {

        List<Medicine> expiredList =
                new ArrayList<>();

        List<Medicine> allMedicines =
                DatabaseHelper.getAllMedicines();

        for(Medicine m : allMedicines){

            long daysLeft =
                    ChronoUnit.DAYS.between(
                            LocalDate.now(),
                            m.getExpiryDate()
                    );

            // EXPIRED

            if(daysLeft < 0){

                expiredList.add(m);
            }
        }

        return expiredList;
    }

    // ================= NEAR EXPIRY =================

    public List<Medicine> getNearExpiryMedicines() {

        List<Medicine> nearList =
                new ArrayList<>();

        List<Medicine> allMedicines =
                DatabaseHelper.getAllMedicines();

        for(Medicine m : allMedicines){

            long daysLeft =
                    ChronoUnit.DAYS.between(
                            LocalDate.now(),
                            m.getExpiryDate()
                    );

            // BETWEEN 0 AND 30 DAYS

            if(daysLeft >= 0 && daysLeft <= 30){

                nearList.add(m);
            }
        }

        return nearList;
    }

    // ================= ALL ALERTS =================

    public List<Medicine> getAllAlerts() {

        List<Medicine> allAlerts =
                new ArrayList<>();

        allAlerts.addAll(getExpiredMedicines());

        allAlerts.addAll(getNearExpiryMedicines());

        return allAlerts;
    }
}