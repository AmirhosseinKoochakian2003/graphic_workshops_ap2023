package view;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.Laser;
import model.Meteorite;
import model.Rocket;

public class ShootingAnimation extends Transition {

    private Pane pane;

    private Rocket rocket;

    private Laser laser;

    public ShootingAnimation(Pane pane, Rocket rocket, Laser laser) {
        this.pane = pane;
        this.rocket = rocket;
        this.laser = laser;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        double y = laser.getY() - 10;

        Meteorite meteorite = Game.gameController.getMeteorite();
        if (meteorite != null && meteorite.getBoundsInParent().intersects(laser.getLayoutBounds())) {
            Game.gameController.decreaseMeteoriteHealth(meteorite, rocket);

            if (meteorite.getHealth() <= 0) {
                Media media = new Media(getClass().getResource("/media/exp4.wav").toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);

                meteorite.stopTheAnimation();
                ExplosionAnimation explosionAnimation = new ExplosionAnimation(meteorite, pane);
                explosionAnimation.play();
            }
            pane.getChildren().remove(laser);
            this.stop();
        }



        if (y <= 20) {
            pane.getChildren().remove(laser);
            this.stop();
        }

        laser.setY(y);
    }
}
