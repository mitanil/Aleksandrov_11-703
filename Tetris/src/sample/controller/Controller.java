package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import sample.app.Game;

public class Controller {


    @FXML
    private AnchorPane Pane;

//    @FXML
//    public void initialize(){
//        System.out.println("fff");
//    }

    @FXML
    public void onEnter() {
        Scene scene = Pane.getScene();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (KeyCode.W == event.getCode()) {
                Game.pRotate();
            }
            if (KeyCode.A == event.getCode()) {
                Game.pMoveLeft();
            }
            if (KeyCode.D == event.getCode()) {
                Game.pMoveRight();
            }
            if (KeyCode.S == event.getCode()) {
                Game.pMoveDown();
            }
        });
    }
}
