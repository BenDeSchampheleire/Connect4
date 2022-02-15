package application;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Test extends Application {

    private SimpleObjectProperty<Color> playerColor = new SimpleObjectProperty<>(Color.RED);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        final BorderPane borderPane = new BorderPane();
        final GridPane gridPane = new GridPane();
        primaryStage.setTitle("Connect4");
        primaryStage.setResizable(false);

        Scene scene = new Scene(borderPane, 900, 900, true);
        scene.setFill(Color.BLACK);
        //scene.getStylesheets().add("net/glyphsoft/styles.css");

        gridPane.setTranslateY(50);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.getColumnConstraints().addAll(
                new ColumnConstraints(100,100,Double.MAX_VALUE),
                new ColumnConstraints(100,100,Double.MAX_VALUE),
                new ColumnConstraints(100,100,Double.MAX_VALUE),
                new ColumnConstraints(100,100,Double.MAX_VALUE),
                new ColumnConstraints(100,100,Double.MAX_VALUE),
                new ColumnConstraints(100,100,Double.MAX_VALUE),
                new ColumnConstraints(100,100,Double.MAX_VALUE));
        gridPane.getRowConstraints().addAll(
                new RowConstraints(100,100,Double.MAX_VALUE),
                new RowConstraints(100,100,Double.MAX_VALUE),
                new RowConstraints(100,100,Double.MAX_VALUE),
                new RowConstraints(100,100,Double.MAX_VALUE),
                new RowConstraints(100,100,Double.MAX_VALUE),
                new RowConstraints(100,100,Double.MAX_VALUE));

        createGrids(gridPane);

        borderPane.setCenter(gridPane);

        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    //Create Grids
    private void createGrids(final GridPane gridPane){

        gridPane.getChildren().clear();

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
                DropShadow effect = new DropShadow();
                effect.setSpread(.2);
                effect.setRadius(25);
                effect.setColor(Color.BLUE);
                cell.setEffect(effect);

                final Circle diskPreview = new Circle(40);
                diskPreview.setOpacity(0.5);
                diskPreview.setFill(Color.TRANSPARENT);

                diskPreview.setOnMouseEntered(arg0 -> {
                    if(playerColor.get()==Color.RED){
                        diskPreview.setFill(Color.RED);
                    }else{
                        diskPreview.setFill(Color.YELLOW);
                    }
                });

                diskPreview.setOnMouseExited(arg0 -> diskPreview.setFill(Color.TRANSPARENT));

                final Circle disk = new Circle(40);
                disk.fillProperty().bind(playerColor);
                disk.setOpacity(0.5);
                disk.setTranslateY(-(100*(r+1)));

                final TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300), disk);

                disk.setOnMouseEntered(arg0 -> {
                    if(playerColor.get()==Color.RED){
                        diskPreview.setFill(Color.RED);
                    }else{
                        diskPreview.setFill(Color.YELLOW);
                    }
                });

                disk.setOnMouseExited(arg0 -> diskPreview.setFill(Color.TRANSPARENT));

                disk.setOnMouseClicked(arg0 -> {
                    if(disk.getTranslateY()!=0){
                        translateTransition.setToY(0);
                        translateTransition.play();
                        if(playerColor.get()==Color.RED){
                            playerColor.set(Color.YELLOW);
                            disk.fillProperty().bind(new SimpleObjectProperty<Color>(Color.RED));
                        }else{
                            playerColor.set(Color.RED);
                            disk.fillProperty().bind(new SimpleObjectProperty<Color>(Color.YELLOW));
                        }
                    }
                });

                diskPreview.setOnMouseClicked(arg0 -> {
                    if(disk.getTranslateY()!=0){
                        translateTransition.setToY(0);
                        translateTransition.play();
                        if(playerColor.get()==Color.RED){
                            playerColor.set(Color.YELLOW);
                            disk.fillProperty().bind(new SimpleObjectProperty<Color>(Color.RED));
                        }else{
                            playerColor.set(Color.RED);
                            disk.fillProperty().bind(new SimpleObjectProperty<Color>(Color.YELLOW));
                        }
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
