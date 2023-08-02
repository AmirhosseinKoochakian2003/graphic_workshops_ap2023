package controller;

import javafx.scene.layout.Pane;
import model.Laser;
import model.Meteorite;
import model.Rocket;
import view.ShootingAnimation;

public class GameController {

    private String username;

    private Meteorite meteorite;
    public GameController(String username) {
        this.username = username;
    }

    public void moveLeft(Rocket rocket) {
        if (rocket.getX() > 20)
            rocket.setX(rocket.getX() - 15);
    }

    public void moveRight(Rocket rocket) {
        if (rocket.getX() < 718)
            rocket.setX(rocket.getX() + 15);
    }

    public void moveUp(Rocket rocket) {
        if (rocket.getY() > 20)
            rocket.setY(rocket.getY() - 15);
    }

    public void moveDown(Rocket rocket) {
        if (rocket.getY() < 530)
            rocket.setY(rocket.getY() + 15);
    }

    public void shoot(Rocket rocket, Pane gamePane) {
        Laser laser = new Laser(rocket);
        gamePane.getChildren().add(laser);
        ShootingAnimation shootingAnimation = new ShootingAnimation(gamePane, rocket, laser);
        shootingAnimation.play();
    }

    public String getUsername() {
        return username;
    }

    public void setMeteorite(Meteorite meteorite) {
        this.meteorite = meteorite;
    }

    public Meteorite getMeteorite() {
        return meteorite;
    }

    public void decreaseHealth(Rocket rocket, Meteorite meteorite) {
            rocket.setHealth(meteorite.getDamage());
        }

    public void decreaseMeteoriteHealth(Meteorite meteorite, Rocket rocket) {
        meteorite.setHealth(rocket.getCombat());
    }
}
