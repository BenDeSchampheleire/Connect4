package application;
import game.Grid;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Scanner;

public class Test extends Application {

    private SimpleObjectProperty<Color> playerColor = new SimpleObjectProperty<>(Color.RED);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // asks the user how many columns he wants
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
        } while (rowNumber < 4 || rowNumber > 8);

        final BorderPane borderPane = new BorderPane();
        final GridPane gridPane = new GridPane();
        primaryStage.setTitle("Connect4");
        primaryStage.setResizable(true);

        Scene scene = new Scene(borderPane, 700, 700, true);
        scene.setFill(Color.TRANSPARENT);

        gridPane.setAlignment(Pos.CENTER);

        // creation of columns
        for (int i=0; i<columnNumber; i++) {
            gridPane.getColumnConstraints().addAll(new ColumnConstraints(100,100, Double.MAX_VALUE));
        }

        // creation of rows
        for (int j=0; j<rowNumber; j++) {
            gridPane.getRowConstraints().addAll(new RowConstraints(100,100, Double.MAX_VALUE));
        }

        // creation of the "real" grid (not the graphical one)
        Grid grid = new Grid(rowNumber, columnNumber);

        // creation of the grid
        createGrids(gridPane, grid);
        borderPane.setCenter(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    //Create Grids
    private void createGrids(final GridPane gridPane, Grid grid){

        gridPane.getChildren().clear();

        // creation of the visual grid
        for(int r = 0; r < gridPane.getRowConstraints().size(); r++){
            for(int c = 0; c < gridPane.getColumnConstraints().size(); c++){

                Rectangle rect = new Rectangle(100,100);
                Circle circ = new Circle(45);
                circ.centerXProperty().set(50);
                circ.centerYProperty().set(50);
                Shape cell = Path.subtract(rect, circ);
                cell.setFill(Color.BLUE);
                cell.setStroke(Color.BLUE);
                cell.setOpacity(0.8);

                // empty cells
                final Circle diskPreview = new Circle(40);
                diskPreview.setOpacity(0.5);
                diskPreview.setFill(Color.TRANSPARENT);

                // displays a red or yellow circle whenever the player puts the mouse on a playable cell
                diskPreview.setOnMouseEntered(arg0 -> {
                    if(playerColor.get() == Color.RED){
                        diskPreview.setFill(Color.RED);
                    }else{
                        diskPreview.setFill(Color.YELLOW);
                    }
                });

                // removes the circle whenever the player takes the mouse off the cell
                diskPreview.setOnMouseExited(arg0 -> diskPreview.setFill(Color.TRANSPARENT));

                final Circle disk = new Circle(40);
                disk.fillProperty().bind(playerColor);
                disk.setStroke(Color.BLACK);
                disk.setTranslateY(-(100*(r+1)));

                final TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200), disk);

                diskPreview.setOnMouseClicked(arg0 -> {
                        translateTransition.setToY(0);
                        translateTransition.play();
                        if(playerColor.get() == Color.RED){
                            playerColor.set(Color.YELLOW);
                            disk.fillProperty().bind(new SimpleObjectProperty<>(Color.RED));
                        }else{
                            playerColor.set(Color.RED);
                            disk.fillProperty().bind(new SimpleObjectProperty<>(Color.YELLOW));
                        }

                });

                StackPane stack = new StackPane();

                stack.getChildren().addAll(cell, diskPreview, disk);

                gridPane.add(stack, c, r);

                /*if(r == gridPane.getColumnConstraints().size()-1){
                    stack.setEffect(new Reflection());
                }*/
            }

        }
    }
}
