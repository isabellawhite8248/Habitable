import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
//TO FINISH THE PROJECT
//if a mouse intersects the calendar it is the selected square and habits circle will appear if box is checked, if not no
//add the array of months and add a clear button for each month which resets everything and unchecks the habits
//TODO: do the thing below -- its annoying if you don't fix that
//could make it so that when you start a new day it automatically unchecks the checkboxes

public class CalendarSquare {

    private Rectangle rect;
    private int xCoord;
    private int yCoord;
    private Double gridNum;
    private Double date;
    private Pane CalendarPane;
    private Rectangle dateSquare;
    private Label day;
    private Double rowNum;
    private int dateLabel;
    private int circleNum;
    private Circle[] circles;
    private int[] circleFills; //array composed of 1s and 0s, if its a 1 that means it is colored in, if it is zero, color of circle index is invisible

    public CalendarSquare(int xCoord, int yCoord, Double gridNum, Double date, Pane CalendarPane, Double rowNum) {

        this.circleFills = new int[9];
        this.circles = new Circle[9];
        this.circleNum = 0;
        this.dateLabel = 0;
        this.rowNum = rowNum;
        this.gridNum = gridNum;
        this.date = date;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.rect = new Rectangle(xCoord, yCoord, Constants.CALENDAR_SQUARE_DIMENSION, Constants.CALENDAR_SQUARE_DIMENSION);
        this.rect.setFill(Color.LIGHTBLUE);
        this.rect.setStroke(Color.BLACK);

        this.CalendarPane = CalendarPane;

        this.dateSquare = new Rectangle(xCoord, yCoord, Constants.CALENDAR_SQUARE_DIMENSION / 3, Constants.CALENDAR_SQUARE_DIMENSION / 5);
        this.dateSquare.setFill(Color.WHITESMOKE);
        this.dateLabel = ((int) Math.round(((xCoord / Constants.CALENDAR_SQUARE_DIMENSION) + 1) + (7 * this.rowNum)) - 6);
        this.day = new Label(" " + this.dateLabel); //plus 7 times the row number
        this.day.setLayoutX(xCoord);
        this.day.setLayoutY(yCoord);

        if(this.dateLabel == -5) {
            this.dateLabel = 30;
            this.day.setText("30");
            this.day.setTextFill(Color.DARKBLUE);
            this.dateSquare.setFill(Constants.INVISIBLE);

        }else if(this.dateLabel == -4){

            this.dateLabel = 31;
            this.day.setText("31");
            this.day.setTextFill(Color.DARKBLUE);
            this.dateSquare.setFill(Constants.INVISIBLE);
            //TODO: only make this possible if the month has 31 days

        }else if (this.dateLabel < 1 || this.dateLabel > 31){
            this.day.setTextFill(Constants.INVISIBLE);
            this.dateSquare.setFill(Constants.INVISIBLE);
        }
    }

    public Rectangle getSquare(){
        return rect;
    }

    public Rectangle getDateSquare(){
        return dateSquare;
    }

    public Label getDay(){
        return day;
    }

    public double getX(){
        return rect.getX();
    }
    public double getY(){
        return rect.getY();
    }
    public void setStroke(Color color, Double width){
        rect.setStroke(color);
        rect.setStrokeWidth(width);
    }

    public void addCircles(double rectX, double rectY){

        for(int row = 0; row < 3; row++){
            for(int col = 0; col <3; col++){

                //take out first dot out of grid of 9
                Circle circle = new Circle();
                circle.setCenterX(rectX + (Constants.CALENDAR_SQUARE_DIMENSION/3.5)*row + 15);
                circle.setCenterY(rectY + (Constants.CALENDAR_SQUARE_DIMENSION/3.5)*col + 15);
                circle.setRadius(8);
                circle.setFill(Constants.INVISIBLE);
                //default color to make them invisible
                circleFills[this.circleNum] = 0;
                circles[this.circleNum] = circle;
                CalendarPane.getChildren().add(circle);

                this.circleNum = circleNum + 1;
            }
        }
    }

    public Circle[] getCircles(){
        return circles;
    }

    public int[] getCircleFills(){
        return circleFills;
    }

    public void clearCircles(){
        for(int i = 0; i < circles.length; i++){
            this.circles[i].setFill(Constants.INVISIBLE);
        }
        for(int i = 0; i < circleFills.length; i++){
            this.circleFills[i] = 0; //set all fills back to invisible
        }
    }

}