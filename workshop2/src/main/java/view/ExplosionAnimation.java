package view;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Meteorite;

public class ExplosionAnimation extends Transition {
    private Meteorite meteorite;
    private Pane pane;

    public ExplosionAnimation(Meteorite meteorite, Pane pane) {
        this.meteorite = meteorite;
        this.pane = pane;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(1000));
    }

    @Override
    protected void interpolate(double v) {
        int number = 1;
        if (0 <= v && v <= 0.33) number = 1;
        else if (0.33 < v && v <= 0.66) number = 2;
        else if (0.66 < v && v <= 1) number = 3;

        meteorite.setFill(new ImagePattern(new Image(
                MeteoriteAnimation.class.getResource("/images/exp" + number + ".png").toExternalForm())));

        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Game.gameController.setMeteorite(null);
                pane.getChildren().remove(meteorite);
            }
        });

    }
}
