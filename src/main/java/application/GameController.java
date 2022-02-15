package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameController {

    @FXML
    private Button buttonStart;

    @FXML
    private Button buttonQuit;

    @FXML
    protected void quitWindow() {
        Stage stage = (Stage) this.buttonQuit.getScene().getWindow();
        stage.close();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        buttonQuit.getScene().setRoot(pane); // use any resource in the scene (choice of buttonQuit)
    }
}

