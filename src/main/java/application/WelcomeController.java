package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class WelcomeController {

    @FXML
    private Button buttonStart;

    @FXML
    private Button buttonQuit;

    @FXML
    protected void quitWindow() {
        Stage stage = (Stage) buttonQuit.getScene().getWindow();
        stage.close();
    }

    public void changeScene() throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Board.fxml")));
        Scene scene = buttonQuit.getScene(); // use any resource in the scene (choice of buttonQuit)
        Window window = scene.getWindow();
        Stage stage = (Stage) window;
        stage.setWidth(447);
        window.centerOnScreen();
        scene.setRoot(pane);
    }
}

