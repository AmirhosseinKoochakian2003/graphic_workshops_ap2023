package view;

import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;

public class MainMenuController {
    public void mark(MouseEvent mouseEvent) {
        CheckBox checkBox = (CheckBox) mouseEvent.getSource();
        if (checkBox.isSelected()) {
            System.out.println("task is done");
        }
    }
}
