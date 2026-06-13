package controller;

import model.DatabaseHelper;
import model.Medicine;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class AlertController {


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


            if(daysLeft < 0){

                expiredList.add(m);
            }
        }

        return expiredList;
    }


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


            if(daysLeft >= 0 && daysLeft <= 30){

                nearList.add(m);
            }
        }

        return nearList;
    }


    public List<Medicine> getAllAlerts() {

        List<Medicine> allAlerts =
                new ArrayList<>();

        allAlerts.addAll(getExpiredMedicines());

        allAlerts.addAll(getNearExpiryMedicines());

        return allAlerts;
    }
}