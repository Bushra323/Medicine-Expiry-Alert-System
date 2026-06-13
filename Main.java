import model.DatabaseHelper;
import view.LoginView;

public class Main {

  public static void main(String[] args) {

    // Connect MySQL
    DatabaseHelper.connect();

    // Open Login Screen
    new LoginView();
  }
}