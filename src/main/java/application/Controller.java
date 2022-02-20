package application;

import java.io.IOException;

import game.Checker;
import game.Column;
import game.Grid;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

public class Controller {

    @FXML
    private Button buttonStart;

    @FXML
    private Button buttonQuit;

    @FXML
    protected void quitWindow() {
        Stage stage = (Stage)this.buttonQuit.getScene().getWindow();
        stage.close();
    }

    public void changeScene() {
        Scene scene = this.buttonQuit.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;

        Grid grid = new Grid(6, 6);

        drawBoard(stage,grid);
        window.centerOnScreen();
    }

    public void winScreen(Scene scene, String winner) throws IOException {

        final VBox vbox = new VBox();
        vbox.setSpacing(30);
        vbox.setAlignment(Pos.CENTER);

        Label congrats = new Label("Congratulations");
        congrats.setFont(new Font("Arial",48));
        vbox.getChildren().add(congrats);

        Label playerName = new Label();
        playerName.setFont(new Font("Arial",48));
        vbox.getChildren().add(playerName);

        scene.setRoot(vbox);


        switch (winner) {
            case "red" -> {
                playerName.setText("Red wins");
                playerName.setTextFill(Color.RED);
            }
            case "yellow" -> {
                playerName.setText("Yellow wins");
                playerName.setTextFill(Color.YELLOW);
            }
            case "nobody" -> playerName.setText("It's a draw");
        }

    }

    public void drawBoard(Stage stage, Grid grid) {

        /* asks the user how many columns he wants
        int columnNumber;
        do {
            System.out.println("How many columns? (Has to be between 4 and 8) ");
            Scanner Csc = new Scanner(System.in);
            columnNumber = Csc.nextInt();
        } while (columnNumber < 4 || columnNumber > 8);

        // asks the user how many rows he wants
        int rowNumber;
        do {
            System.out.println("How many rows? (Has to be between 4 and 8) ");
            Scanner Rsc = new Scanner(System.in);
            rowNumber = Rsc.nextInt();
        } while (rowNumber < 4 || rowNumber > 8);*/

        final BorderPane borderPane = new BorderPane();
        final GridPane gridPane = new GridPane();
        stage.setTitle("Connect4");
        stage.setResizable(true);

        Scene scene = new Scene(borderPane, (grid.getWidth()+1)*100, (grid.getHeight()+2)*100, true);
        scene.setFill(Color.TRANSPARENT);

        gridPane.setAlignment(Pos.CENTER);

        // creation of columns
        for (int i = 0; i < grid.getWidth(); i++) {
            gridPane.getColumnConstraints().addAll(new ColumnConstraints(100,100, Double.MAX_VALUE));
        }

        // creation of rows
        for (int j = 0; j < grid.getHeight(); j++) {
            gridPane.getRowConstraints().addAll(new RowConstraints(100,100, Double.MAX_VALUE));
        }

        // creation of the grid
        drawGrid(gridPane, grid);
        borderPane.setCenter(gridPane);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    private void drawGrid(final GridPane gridPane, Grid grid){

        SimpleObjectProperty<Color> playerColor = new SimpleObjectProperty<>(Color.RED);

        gridPane.getChildren().clear();

            // creation of the visual grid
            for (int col = 0; col < grid.getWidth(); col++) {

                Column column = grid.getGrid().get(col);

                for (int row = 0; row < grid.getHeight(); row++) {

                    Checker checker = column.getColumn().get( grid.getHeight() - 1 - row );

                    // create the blue cell
                    Rectangle rect = new Rectangle(100, 100);
                    Circle circ = new Circle(45);
                    circ.centerXProperty().set(50);
                    circ.centerYProperty().set(50);
                    Shape cell = Path.subtract(rect, circ);
                    cell.setFill(Color.BLUE);
                    cell.setStroke(Color.BLUE);
                    cell.setOpacity(0.8);

                    // preview checkers
                    final Circle checkerPreview = new Circle(40);
                    checkerPreview.setOpacity(0.5);
                    checkerPreview.setFill(Color.TRANSPARENT);

                    // displays a red or yellow preview checker whenever the player puts the mouse on a playable cell
                    checkerPreview.setOnMouseEntered(arg0 -> {
                        if (playerColor.get() == Color.RED) {
                            checkerPreview.setFill(Color.RED);
                        } else {
                            checkerPreview.setFill(Color.YELLOW);
                        }
                    });

                    // removes the preview checker whenever the player takes the mouse off the cell
                    checkerPreview.setOnMouseExited(arg0 -> checkerPreview.setFill(Color.TRANSPARENT));

                    // checkers
                    final Circle checker_circle = new Circle(40);
                    switch (checker.getColor()) {
                        case "red" -> checker_circle.setFill(Color.RED);
                        case "yellow" -> checker_circle.setFill(Color.YELLOW);
                        case "blank" -> {
                            checker_circle.setFill(Color.TRANSPARENT);
                            checker_circle.setTranslateY(-100 * (row + 1));
                        }
                    }

                    final TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200), checker_circle);

                    checkerPreview.setOnMouseClicked(arg0 -> {
                        translateTransition.setToY(0);
                        translateTransition.play();
                        if (playerColor.get() == Color.RED) {
                            checker_circle.fillProperty().bind(new SimpleObjectProperty<>(Color.RED));
                            checker.setColor("red");
                            playerColor.set(Color.YELLOW);
                        } else {
                            checker_circle.fillProperty().bind(new SimpleObjectProperty<>(Color.YELLOW));
                            checker.setColor("yellow");
                            playerColor.set(Color.RED);
                        }

                        switch ( grid.EndOfGame() ) {
                            case "red" -> {
                                try {
                                    winScreen(gridPane.getScene(),"red");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            case "yellow" -> {
                                try {
                                    winScreen(gridPane.getScene(),"yellow");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            case "nobody" -> {
                                try {
                                    winScreen(gridPane.getScene(),"nobody");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            case "ongoing" -> {}
                        }
                    });

                    StackPane stack = new StackPane();

                    stack.getChildren().addAll(cell, checkerPreview, checker_circle);

                    gridPane.add(stack, col, row);

                }

            }

    }
}
