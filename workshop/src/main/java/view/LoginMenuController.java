package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginMenuController {
    public PasswordField password;
    @FXML
    private TextField username;

    public void submit(MouseEvent mouseEvent) throws Exception {
        if (password.getText().length() < 4) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("submit error");
            alert.setContentText("your password is weak");
            alert.showAndWait();
        }
        else {
            new MainMenu().start(LoginMenu.stage);
        }
    }

    public void reset(MouseEvent mouseEvent) {
        username.setText("");
        password.setText("");
    }
}
