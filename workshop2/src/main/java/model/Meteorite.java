package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.MeteoriteAnimation;

public class Meteorite extends Rectangle {
    private int health;
    private int damage;

    private MeteoriteAnimation meteoriteAnimation;

    public MeteoriteAnimation getMeteoriteAnimation() {
        return meteoriteAnimation;
    }

    public void setMeteoriteAnimation(MeteoriteAnimation meteoriteAnimation) {
        this.meteoriteAnimation = meteoriteAnimation;
    }

    public Meteorite(double v, double v1, int health, int damage) {
        super(v, v1, 70, 60);
        this.health = health;
        this.damage = damage;


        this.setFill(new ImagePattern(new Image
                (Rocket.class.getResource("/images/rock1.png").toExternalForm())));
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int damage) {
        this.health -= damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void stopTheAnimation() {
        meteoriteAnimation.stop();
    }
}
