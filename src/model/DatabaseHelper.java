package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private static final String URL =
            "jdbc:mysql://localhost:3306/medicine_expiry_system";

    private static final String USER = "root";

    private static final String PASSWORD = "Mysql7651&&";

    public static Connection connect() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }


    public static boolean registerUser(
            String name,
            String password
    ) {

        String checkSql =
                "SELECT * FROM users WHERE name=?";

        String insertSql =
                "INSERT INTO users(name,password) VALUES (?,?)";

        try (
                Connection conn = connect()
        ) {

            PreparedStatement check =
                    conn.prepareStatement(checkSql);

            check.setString(1, name);

            ResultSet rs =
                    check.executeQuery();

            if (rs.next()) {

                return false;
            }

            PreparedStatement insert =
                    conn.prepareStatement(insertSql);

            insert.setString(1, name);

            insert.setString(2, password);

            insert.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public static boolean registerPharmacist(
            String pharmacistId,
            String password
    ) {

        String checkSql =
                "SELECT * FROM pharmacists WHERE pharmacist_id=?";

        String insertSql =
                "INSERT INTO pharmacists(pharmacist_id,password) VALUES (?,?)";

        try (
                Connection conn = connect()
        ) {

            PreparedStatement check =
                    conn.prepareStatement(checkSql);

            check.setString(1, pharmacistId);

            ResultSet rs =
                    check.executeQuery();

            if (rs.next()) {

                return false;
            }

            PreparedStatement insert =
                    conn.prepareStatement(insertSql);

            insert.setString(1, pharmacistId);

            insert.setString(2, password);

            insert.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public static boolean userLogin(
            String name,
            String password
    ) {

        String sql =
                "SELECT * FROM users WHERE name=? AND password=?";

        try (
                Connection conn = connect();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1, name);

            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public static boolean pharmacistLogin(
            String id,
            String password
    ) {

        String sql =
                "SELECT * FROM pharmacists WHERE pharmacist_id=? AND password=?";

        try (
                Connection conn = connect();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1, id);

            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public static void addMedicine(Medicine m) {

        String sql =
                "INSERT INTO medicines(medicine_name, registration_no, manufacturing_license_no, manufactured_by, quantity, expiry_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = connect();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1, m.getName());

            ps.setString(2, m.getRegistrationNo());

            ps.setString(3, m.getManufacturingLicenseNo());

            ps.setString(4, m.getManufacturedBy());

            ps.setInt(5, m.getQuantity());

            ps.setDate(
                    6,
                    Date.valueOf(m.getExpiryDate())
            );

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static List<Medicine> getAllMedicines() {

        List<Medicine> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM medicines";

        try (
                Connection conn = connect();

                Statement st =
                        conn.createStatement();

                ResultSet rs =
                        st.executeQuery(sql)
        ) {

            while (rs.next()) {

                list.add(

                        new Medicine(

                                rs.getInt("id"),

                                rs.getString("medicine_name"),

                                rs.getString("registration_no"),

                                rs.getString("manufacturing_license_no"),

                                rs.getString("manufactured_by"),

                                rs.getInt("quantity"),

                                rs.getDate("expiry_date")
                                        .toLocalDate()
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    public static ResultSet searchMedicine(
            String name,
            String reg,
            String license,
            String manufacturer
    ) {

        try {

            Connection conn = connect();

            String sql =
                    "SELECT * FROM medicines WHERE medicine_name=? AND registration_no=? AND manufacturing_license_no=? AND manufactured_by=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, name);

            ps.setString(2, reg);

            ps.setString(3, license);

            ps.setString(4, manufacturer);

            return ps.executeQuery();

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    public static void updateMedicine(
            int id,
            int qty,
            LocalDate expiry
    ) {

        String sql =
                "UPDATE medicines SET quantity=?, expiry_date=? WHERE id=?";

        try (
                Connection conn = connect();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(1, qty);

            ps.setDate(
                    2,
                    Date.valueOf(expiry)
            );

            ps.setInt(3, id);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void deleteMedicine(int id) {

        String sql =
                "DELETE FROM medicines WHERE id=?";

        try (
                Connection conn = connect();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}