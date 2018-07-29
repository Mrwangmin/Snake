import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClockPane extends Pane {
    private int hour;
    private int minute;
    private int second;

     class inClass{

    }

    private double w = 250 ,h = 250;
     public ClockPane(){
         setCurrentTime();
     }

    public ClockPane(int hour,int minute, int second){
         this.hour = hour;
         this.minute = minute;
         this.second = second;
         paintClok();
    }

    private void paintClok() {
         double clockRadius = Math.min(w,h)*0.8*0.5;
         double centerX = w/2;
         double centerY = h/2;

        Circle circle = new Circle(centerX, centerY ,clockRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Text t1 = new Text(centerX-5, centerY - clockRadius + 12,"12");
        Text t2 = new Text(centerX - clockRadius + 3, centerY+5,"9");
        Text t3 = new Text(centerX + clockRadius - 10, centerY+3,"3");
        Text t4 = new Text(centerX - 3, centerY+clockRadius - 3,"6");

        double sLength = clockRadius*0.8;
        double secondx = centerX + sLength*
                Math.sin(second*(2*Math.PI / 60));
        double secondy = centerY - sLength*
                Math.cos(second * (2 * Math.PI / 60));
        Line sLine = new Line(centerX,centerY,secondx,secondy);
        sLine.setStroke(Color.RED);

        double mLength = clockRadius * 0.65;
        double xMintue = centerX + mLength*
                Math.sin(minute*(2*Math.PI / 60));
        double yMintue = centerY - mLength*
                Math.cos(minute * (2 * Math.PI / 60));
        Line mLine = new Line(centerX,centerY,xMintue,yMintue);
        mLine.setStroke(Color.BLUE);

        double hLength = clockRadius * 0.5;
        double hourx = centerX + hLength*
                Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        double houry = centerY - hLength*
                Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        Line hLine = new Line(centerX,centerY,hourx,houry);
        hLine.setStroke(Color.GREEN);

        getChildren().clear();
        getChildren().addAll(circle,t1,t2,t3,t4,sLine,mLine,hLine);
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
        paintClok();
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public void setMinute(int minute) {
        this.minute = minute;
        paintClok();
    }

    public void setSecond(int second) {
        this.second = second;
        paintClok();
    }


    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
        paintClok();
    }

    public void setW(double w) {
        this.w = w;
        paintClok();
    }

    public double getW() {
        return w;
    }
    public void setCurrentTime(){
        Calendar calendar = new GregorianCalendar();
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);
        paintClok();
    }
}
