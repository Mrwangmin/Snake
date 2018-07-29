import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Snake{
    LinkedList<Point> body = new LinkedList<>();
    Point tou ;
    int fangxiang = 2;


    boolean move(Food food){
        Point point = new Point();
        switch (fangxiang){
            case 1://上
                tou = body.getFirst();
                point.setX(tou.getX());
                point.setY(tou.getY()-1);
                body.addFirst(point);
                break;
            case 2://下
                tou = body.getFirst();
                point.setX(tou.getX());
                point.setY(tou.getY()+1);
                body.addFirst(point);
                break;
            case 3://左
                tou = body.getFirst();
                point.setX(tou.getX()-1);
                point.setY(tou.getY());
                body.addFirst(point);
                break;
            case 4://右
                tou = body.getFirst();
                point.setX(tou.getX()+1);
                point.setY(tou.getY());
                body.addFirst(point);
                break;
        }
        Point x = body.getFirst();
        Point x1 = body.getLast();
        body.removeLast();
        if (x.getY()==food.getY()&&x.getX()==food.getX()){
            body.addLast(x1);
            return true;
        }else{

            return false;
        }
    }

    public void setFangxiang(int fangxiang){
        this.fangxiang = fangxiang;
    }

    void eat(Point point){
        body.addFirst(point);
    }

    int size(){
        return body.size();
    }

    Point get(int x){
        return body.get(x);
    }

}
