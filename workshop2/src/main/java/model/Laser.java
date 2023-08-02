package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Laser extends Rectangle {

    public Laser(Rocket rocket) {
        super(rocket.getX() + 31, rocket.getY() - 10, 9, 13);
        this.setFill(new ImagePattern(
                new Image(Laser.class.getResource("/images/redlaser.png").toExternalForm())));
    }
}
