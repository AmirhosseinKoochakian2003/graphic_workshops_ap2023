package view;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Meteorite;
import model.Rocket;

public class MeteoriteAnimation extends Transition {

    private Rocket rocket;
    private Pane pane;
    private Meteorite meteorite;
    private Text healthOfRocket;
    private ProgressBar progressBar;

    public MeteoriteAnimation(Rocket rocket, Pane pane, Meteorite meteorite, Text healthOfRocket, ProgressBar progressBar) {
        this.rocket = rocket;
        this.pane = pane;
        this.meteorite = meteorite;
        this.healthOfRocket = healthOfRocket;
        this.progressBar = progressBar;

        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(5000));
    }

    @Override
    protected void interpolate(double v) {
        int number = 1;
        if (0 <= v && v <= 0.33) number = 1;
        else if (0.33 < v && v <= 0.66) number = 2;
        else if (0.66 < v && v <= 1) number = 3;

        meteorite.setFill(new ImagePattern(new Image(
                MeteoriteAnimation.class.getResource("/images/rock" + number + ".png").toExternalForm())));

        double y = meteorite.getY() + 1;

        if (meteorite.getBoundsInParent().intersects(rocket.getBoundsInParent())) {
           collision(meteorite, rocket);
        }

        if (y >= 580) {
            pane.getChildren().remove(meteorite);
            this.stop();
        }
        meteorite.setY(y);
    }

    private void collision(Meteorite meteorite, Rocket rocket) {
        Game.gameController.decreaseHealth(rocket, meteorite);
        healthOfRocket.setText(Game.gameController.getUsername() + "'s life : " + rocket.getHealth() + "%");
        progressBar.setProgress(progressBar.getProgress() - (double) meteorite.getDamage() / 100);

        if (rocket.getHealth() == 0) {
            System.out.println("the game is over");
            System.exit(0);
        }
        pane.getChildren().remove(meteorite);
        this.stop();

        RotateTransition transition = new RotateTransition();
        transition.setNode(rocket);
        transition.setDuration(Duration.millis(1000));
        transition.setFromAngle(0);
        transition.setToAngle(360);
        transition.play();

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(rocket);
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0.2);
        fadeTransition.play();

        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rocket.setOpacity(1);
            }
        });

    }
}
