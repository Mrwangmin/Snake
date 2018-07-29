import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.util.Duration;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;

public class Main extends Application {
    private Snake snake;
    private Food food;
    private Rectangle throwBody = new Rectangle();
    private Pane pane = new Pane();
    Rectangle re;

    @Override
    public void start(Stage priamryStage){
        //qipan(30);
        food = new Food(15,15);
        re = new Rectangle(food.getX()*30,food.getY()*30,30,30);
        re.setFill(Color.BLACK);
        re.setArcHeight(30);
        re.setArcWidth(30);
        pane.getChildren().add(re);
        snake = new Snake();
        snake.eat(new Point(0,0));
        pane.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case UP: snake.setFangxiang(1);break;
                case DOWN: snake.setFangxiang(2);break;
                case LEFT: snake.setFangxiang(3);break;
                case RIGHT: snake.setFangxiang(4);break;
            }
        });
        writesnake(snake);
        Scene scene = new Scene(pane,900,900);
        priamryStage.setTitle("贪吃蛇");
        priamryStage.setScene(scene);
        priamryStage.show();
        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(300),event -> {
                    boolean x = snake.move(food);
                    if (x){
                        writeFood();
                    }
                    throwBody.setArcHeight(0);
                    throwBody.setArcWidth(0);
                    throwBody.setFill(Color.WHITE);
                    writesnake(snake);
                }));
                animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        pane.requestFocus();
    }
    private void writeFood(){
        int x;
        int y;
        do {
            x = (int) (Math.random()*30);
            y = (int) (Math.random()*30);
        }while (!panduan(x,y));
        System.out.println(x+"  "+y);
        food.setX(x);
        food.setY(y);
        re.setX(x*30);
        re.setY(y*30);
    }

    private boolean panduan(int x,int y){
        for (int i = 0;i < snake.body.size(); i++){
            Point point = snake.body.get(i);
            if (point.getX() == x && point.getY() == y){
                return false;
            }
        }
        return true;
    }
    private void writesnake(Snake snake){
        for (int i = 0;i < snake.size()-1;i++){
            Point point = snake.get(i);
            Rectangle rectangle = new Rectangle(point.getX()*30,point.getY()*30,30,30);
            rectangle.setFill(new Color(Math.random(),Math.random(),Math.random(),Math.random()));
            rectangle.setArcHeight(30);
            rectangle.setArcWidth(30);
            pane.getChildren().add(rectangle);
        }
        Point point = snake.body.getLast();
        throwBody = new Rectangle(point.getX()*30,
                point.getY()*30,30,30);
        throwBody.setFill(new Color(Math.random(),Math.random(),Math.random(),Math.random()));
        throwBody.setArcHeight(30);
        throwBody.setArcWidth(30);
        pane.getChildren().add(throwBody);

    }
    private void qipan(int x){
        for (int i = 0;i <= x;i++){
            Line line = new Line(i*30,0,i*30,x*30);
            pane.getChildren().add(line);
        }
        for (int j = 0 ;j <=x; j++){
            Line line = new Line(0,j*30,x*30,j*30);
            pane.getChildren().add(line);
        }
    }
}
