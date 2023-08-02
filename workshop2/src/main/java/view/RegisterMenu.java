package view;

import controller.GameController;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class RegisterMenu extends Application {
    public Label label;
    public TextField username;

    public static Stage stage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        RegisterMenu.stage = stage;
        BorderPane registerPane = FXMLLoader.load(
                new URL(Game.class.getResource("/fxml/registerMenu.fxml").toExternalForm()));

        Scene scene = new Scene(registerPane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        username.textProperty().addListener((observable, oldText, newText)->{
            label.setText("hello " + newText);
        });
    }

    public void startTheGame(MouseEvent mouseEvent) throws Exception {
        Game.gameController = new GameController(username.getText());
        new Game().start(RegisterMenu.stage);
    }
}