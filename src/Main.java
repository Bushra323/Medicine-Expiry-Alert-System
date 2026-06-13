import model.DatabaseHelper;
import view.LoginView;

public class Main {

  public static void main(String[] args) {

    DatabaseHelper.connect();

    new LoginView();
  }
}