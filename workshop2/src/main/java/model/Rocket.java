package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Rocket extends Rectangle {
    private int health;
    private int combat;

    public Rocket(int health, int combat) {
        super(367, 530, 66, 50);
        this.health = health;
        this.combat = combat;

        this.setFill(new ImagePattern(
                new Image(Rocket.class.getResource("/images/player.png").toExternalForm())));
    }

    public int getHealth() {
        return health;
    }

    public int getCombat() {
        return combat;
    }

    public void setHealth(int damage) {
        this.health -= damage;
    }
}
