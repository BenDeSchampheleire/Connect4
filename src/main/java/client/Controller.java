package client;
import servPattern.Game;
import servPattern.Grid;
import java.io.IOException;
import java.util.Objects;

import servPattern.Checker;
import servPattern.Column;
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

/**
 * This class represents the graphical interface
 * And handles the unfolding of the game
 *
 */
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

        drawBoard(stage, grid);
        window.centerOnScreen();
    }

    /**
     *
     * @param scene: scene where the screen is displayed
     *        winner: color of the winner's team
     *
     */
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
                playerName.setText("Red wins !");
                playerName.setTextFill(Color.RED);
            }
            case "yellow" -> {
                playerName.setText("Yellow wins !");
                playerName.setTextFill(Color.YELLOW);
            }
            case "nobody" -> playerName.setText("It's a draw !");
        }

    }

    /**
     * This method draws the board on which the game takes place
     *
     * @param stage: stage on which the board is displayed
     *        grid: the grid object, used to know the number of rows and columns to draw
     *
     */
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

        Scene scene = new Scene(borderPane, (game.grid.getWidth()+1)*100, (game.grid.getHeight()+2)*100, true);
        scene.setFill(Color.TRANSPARENT);

        gridPane.setAlignment(Pos.CENTER);

        // creation of columns
        for (int i = 0; i < game.grid.getWidth(); i++) {
            gridPane.getColumnConstraints().addAll(new ColumnConstraints(100,100, Double.MAX_VALUE));
        }

        // creation of rows
        for (int j = 0; j < game.grid.getHeight(); j++) {
            gridPane.getRowConstraints().addAll(new RowConstraints(100,100, Double.MAX_VALUE));
        }

        // creation of the grid
        drawGrid(gridPane, game, game.player1);
        borderPane.setCenter(gridPane);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    /**
     * This method handles the unfolding of the game and the behavior of the grid's cases
     *
     * @param gridPane: the visual grid
     *        grid: the grid object
     *
     */
    private void drawGrid(final GridPane gridPane, Game game, Player player){

        // SimpleObjectProperty<Color> playerColor = new SimpleObjectProperty<>(Color.RED);

        gridPane.getChildren().clear();

            // creation of the visual grid
            for (int col = 0; col < game.grid.getWidth(); col++) {

                Column column = game.grid.getGrid().get(col);

                for (int row = 0; row < game.grid.getHeight(); row++) {

                    Checker checker = column.getColumn().get( game.grid.getHeight() - 1 - row );

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
                        // if it's his turn to play
                        if (Objects.equals(game.getTurn(), player.getColor())) {
                            // red team
                            if (Objects.equals(player.getColor(), "red")) {
                                checkerPreview.setFill(Color.RED);
                            }
                            // yellow team
                            else {
                                checkerPreview.setFill(Color.YELLOW);
                            }
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

                    // when the player clicks on a case
                    checkerPreview.setOnMouseClicked(arg0 -> {
                        translateTransition.setToY(0);
                        translateTransition.play();
                        // if it's the player's turn to play
                        if (Objects.equals(game.getTurn(), player.getColor())) {
                            // ask the server to put a checker at this position
                            String demande = player.monClientTCP.transmettreChaine("jouer " + checker.getPosition());
                            // red team
                            if (Objects.equals(player.getColor(), "red")) {
                                checker_circle.fillProperty().bind(new SimpleObjectProperty<>(Color.RED));
                                checker.setColor("red");
                                // change turn
                                playerColor.set(Color.YELLOW);
                            }
                            // yellow team
                            else {
                                // ask the server to put a checker at this position
                                checker_circle.fillProperty().bind(new SimpleObjectProperty<>(Color.YELLOW));
                                checker.setColor("yellow");
                            }

                        }

                        switch ( game.grid.EndOfGame() ) {
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
