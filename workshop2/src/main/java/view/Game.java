package view;

import controller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Meteorite;
import model.Rocket;

public class Game extends Application {

    public static GameController gameController;
    @Override
    public void start(Stage stage) throws Exception {
        Pane gamePane = FXMLLoader.load(Game.class.getResource("/fxml/game.fxml"));

        ImageView background = new ImageView(new Image(Game.class.getResource("/images/background.png").toString(), 800 ,600, false, false));
        ImageView background2 = new ImageView(new Image(Game.class.getResource("/images/background.png").toString(), 800 ,600, false, false));

        gamePane.getChildren().addAll(background, background2);

        Rocket rocket = createRocket(gamePane);
        gamePane.getChildren().add(rocket);

/*        Background background = new Background(setBackGround());
        gamePane.setBackground(background);*/

        TranslateTransition trans1 = new TranslateTransition(Duration.seconds(10), background);
        trans1.setFromY(0);
        trans1.setToY(600);
        trans1.setCycleCount(-1);
        TranslateTransition trans2 = new TranslateTransition(Duration.seconds(10), background2);
        trans2.setFromY(-600);
        trans2.setToY(0);
        trans2.setCycleCount(-1);
        ParallelTransition parTrans = new ParallelTransition(trans1, trans2);
        parTrans.play();

        HBox hBox = new HBox();
        Text healthOfRocket = new Text(360, 50,
                gameController.getUsername() + "'s life : " + rocket.getHealth() + "%");
        healthOfRocket.setFill(Color.ALICEBLUE);
        ProgressBar progressBar = new ProgressBar(1);
        createHbox(hBox, healthOfRocket, progressBar);

        gamePane.getChildren().add(hBox);

        Scene scene = new Scene(gamePane);
        stage.setScene(scene);

        gamePane.getChildren().get(2).requestFocus();

        setMeteoriteTimeLine(gamePane, rocket, healthOfRocket, progressBar);

        stage.show();
    }

    private void setMeteoriteTimeLine(Pane gamePane, Rocket rocket, Text healthOfRocket, ProgressBar progressBar) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5000),
                actionEvent -> meteoriteFalling(gamePane, rocket, healthOfRocket, progressBar)));
        timeline.setCycleCount(-1);
        timeline.play();
    }

    private void meteoriteFalling(Pane gamePane, Rocket rocket, Text healthOfRocket, ProgressBar progressBar) {
        Meteorite meteorite = new Meteorite(rocket.getX(), 100, 500, 20);
        gameController.setMeteorite(meteorite);
        gamePane.getChildren().add(meteorite);
        MeteoriteAnimation meteoriteAnimation =
                new MeteoriteAnimation(rocket, gamePane, meteorite,healthOfRocket, progressBar);
        meteorite.setMeteoriteAnimation(meteoriteAnimation);
        meteoriteAnimation.play();
    }

    private void createHbox(HBox hBox, Text healthOfRocket, ProgressBar progressBar) {
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(healthOfRocket);
        hBox.setSpacing(50);
        hBox.getChildren().add(progressBar);
    }

    private BackgroundImage setBackGround() {
        Image image = new Image(Game.class.getResource("/images/background.png").toExternalForm(), 800 ,600, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return backgroundImage;
    }

    private Rocket createRocket(Pane gamePane) {
        Rocket rocket = new Rocket(100, 100);

        rocket.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();

                if (keyName.equals("Left"))
                    Game.gameController.moveLeft(rocket);
                else if (keyName.equals("Right"))
                    Game.gameController.moveRight(rocket);
                else if (keyName.equals("Up"))
                    Game.gameController.moveUp(rocket);
                else if (keyName.equals("Down"))
                    Game.gameController.moveDown(rocket);
                else if (keyName.equals("Space"))
                    Game.gameController.shoot(rocket, gamePane);
            }
        });
        return rocket;
    }
}
