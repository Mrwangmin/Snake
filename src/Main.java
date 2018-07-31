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
import java.util.ArrayList;

public class Main extends Application {
    private Snake snake;
    private Food food;
    private Pane pane = new Pane();
    private Circle re;
    private Timeline animation;
    private ArrayList<Circle> rec = new ArrayList<>();

    @Override
    public void start(Stage priamryStage){
        qipan(30);
        food = new Food(15,15);
        re = new Circle(food.getX()*30+15,food.getY()*30+15,15);
        re.setFill(Color.BLACK);
        pane.getChildren().add(re);
        snake = new Snake();
        snake.eat(new Point(0,0));
        pane.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case UP: if (snake.getFangxiang()!=2) {
                    snake.setFangxiang(1);
                }break;
                case DOWN: if (snake.getFangxiang()!=1) {
                    snake.setFangxiang(2);
                }break;
                case LEFT: if (snake.getFangxiang()!=4) {
                    snake.setFangxiang(3);
                }break;
                case RIGHT:if (snake.getFangxiang()!=3) {
                    snake.setFangxiang(4);
                }break;
            }
        });
        writesnake(snake);
        Scene scene = new Scene(pane,900,900);
        priamryStage.setTitle("贪吃蛇");
        priamryStage.setScene(scene);
        priamryStage.show();
        animation = new Timeline(
                new KeyFrame(Duration.millis(300),event -> {
                    if (snake.move(food)){
                        writeFood();
                    }
                    if (snake.gameOver()){
                        ganmOver();
                    }
                    System.out.println(snake.body.getFirst().getX()+" 动画 "+snake.body.getFirst().getY());
                    writesnake(snake);
                }));
                animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        pane.requestFocus();
    }

    private void ganmOver(){
        animation.stop();
        System.out.println(snake.body.getFirst().getX()+" 停止 "+snake.body.getFirst().getY());
        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(11,12,13,14));
        flowPane.setVgap(5);
        flowPane.setHgap(10);
        Button button = new Button("再来一次");
        flowPane.getChildren().add(new Label("游戏结束"));
        flowPane.getChildren().add(button);
        button.setOnMouseClicked(event -> {
            snake = new Snake();
            snake.eat(new Point(0,0));
            animation.play();
        });

        pane.getChildren().add(flowPane);

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
        re.setCenterX(x*30+15);
        re.setCenterY(y*30+15);
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
        for (int i = 0;i < rec.size();i++){
            Circle circle = rec.get(i);
            circle.setCenterY(910);
            circle.setCenterX(910);
        }

        for (int i = 0;i < snake.size();i++){
            Point point = snake.get(i);
            Circle circle = new Circle(point.getX()*30+15,point.getY()*30+15,15);
            circle.setFill(new Color(Math.random(),Math.random(),Math.random(),Math.random()));
            circle.setCenterX(point.getX()*30+15);
            circle.setCenterY(point.getY()*30+15);
            rec.add(circle);
            pane.getChildren().add(circle);
        }

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
