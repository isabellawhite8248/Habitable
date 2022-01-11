import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/*
This class is a wrapper class for the java fx class rectangle and stores the necessary
information for the rectangle and holds all the circles inside
 */

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
    private int dayOffset;
    private String currMonth;
//    private Timeline timeline;

    private int[] circleFills;
    //array composed of 1s and 0s, if its a 1 that means it is colored in, if it is zero, color of circle index is invisible

    public CalendarSquare(int xCoord, int yCoord, Double gridNum, Double date, Pane CalendarPane, Double rowNum, String currentMonth) {

        this.dayOffset = 6; //if it were 0 the day labels would start on Sunday
        this.circleFills = new int[Constants.NUM_CIRCLES];
        this.circles = new Circle[Constants.NUM_CIRCLES];

        this.currMonth = currentMonth;

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
        this.dateSquare.setFill(Constants.DATE_FILL);

        //the date number is determined by the (Column # + 1) + (7 * row minus 6)
        //the minus 6, day offset is because the month starts on Saturday
        this.dateLabel = ((int) Math.round(((xCoord / Constants.CALENDAR_SQUARE_DIMENSION) + 1) + (7 * this.rowNum)) - this.dayOffset);

        //labels the white square with the date number and sets the location
        this.day = new Label(" " + this.dateLabel);
        this.day.setLayoutX(xCoord);
        this.day.setLayoutY(yCoord);

        //TODO: this needs to be changed its only specific to Jan
        //adds the 30 and 31 squares at the beginning because there wasn't room for them at the end
//        if(this.dateLabel == -5) {
//            this.dateLabel = 30;
//            this.day.setText("30");
//            this.day.setTextFill(Color.DARKBLUE);
//            this.dateSquare.setFill(Constants.INVISIBLE);
//        }else if(this.dateLabel == -4){
//            this.dateLabel = 31;
//            this.day.setText("31");
//            this.day.setTextFill(Color.DARKBLUE);
//            this.dateSquare.setFill(Constants.INVISIBLE);
//            //TODO: only make this possible if the month has 31 days
//        }else if (this.dateLabel < 1 || this.dateLabel > 31){
//            this.day.setTextFill(Constants.INVISIBLE);
//            this.dateSquare.setFill(Constants.INVISIBLE);
//        }

//        this.setUpTimeline();
    }

    //gets the rectangle from the calendar square
    public Rectangle getSquare(){
        return rect;
    }

    //gets the white rectangle that houses the date label
    public Rectangle getDateSquare(){
        return dateSquare;
    }

    //returns the label with the date number
    public Label getDay(){ return day; }

    //gets the x and y coordinate of the big calendar square
    public double getX(){ return rect.getX();}
    public double getY(){ return rect.getY(); }

    //sets the stroke of the calendar square, used when selecting a square in the Calendar class
    public void setStroke(Color color, Double width){
        rect.setStroke(color);
        rect.setStrokeWidth(width);
    }

    //adds the habit circles to the square
    public void addCircles(double rectX, double rectY){

        //for loop sets up a 3 x 3 grid to space out the circles evenly
        for(int row = 0; row < 3; row++){
            for(int col = 0; col <3; col++){

                Circle circle = new Circle();
                circle.setCenterX(rectX + (Constants.CALENDAR_SQUARE_DIMENSION/Constants.CIRCLE_OFFSET)*row + Constants.Circle_YOFFSET);
                circle.setCenterY(rectY + (Constants.CALENDAR_SQUARE_DIMENSION/Constants.CIRCLE_OFFSET)*col + Constants.Circle_YOFFSET);
                circle.setRadius(Constants.CIRCLE_RAD);
                circle.setFill(Constants.INVISIBLE);

                //circle num is just used to count each circle throughout the loop/assign a num to each one
                //when initialized circle num starts at 0
                circleFills[this.circleNum] = 0;

                //fills the circle fills array with 0 at index circleNum because they are all unfilled when created
                circles[this.circleNum] = circle;

                //adds the created circle to the pane
                CalendarPane.getChildren().add(circle);

                //increments circleNum up in preparation for creating a new circle
                this.circleNum = circleNum + 1;
            }
        }
    }

    //method which returns the circles in the calendar square
    public Circle[] getCircles(){
        return circles;
    }

    //method which returns the integer array to keep track of the filled circles, 0 for unfilled, 1 for filled
    public int[] getCircleFills(){
        return circleFills;
    }

    //goes through all circles and sets the fill back to clear, then updates the circleFill array to reflect this
    public void clearCircles(){
        for(int i = 0; i < circles.length; i++){
            this.circles[i].setFill(Constants.INVISIBLE);
        }
        this.updateAllFillToInvisible();
    }

    public void updateAllFillToInvisible(){
        for(int i = 0; i < circleFills.length; i++){
            this.circleFills[i] = 0; //set all fills back to invisible
        }
    }

    public void changeFill(int i, int index){
        //i can be 1 or 0, 1 represents it is filled and 0 represents the invisible color
        this.circleFills[index] = i;
    }

    public void removeCirclesFromPane(){
        for(int i = 0; i < circles.length; i++){
            this.CalendarPane.getChildren().remove(this.circles[i]);
        }
        this.updateAllFillToInvisible();
    }

    //takes in a new day offset depending on the month and updates the labels
    //boolean special case is if the grid isn't big enough to support the 30 or 31 boxes like in jan, in which case
    //it overflows to the first row
    public void updateSquare(int newOffset, int cap, Boolean specialCase, String currMonth){
        this.dateLabel = ((int) Math.round(((xCoord / Constants.CALENDAR_SQUARE_DIMENSION) + 1) + (7 * this.rowNum)) - newOffset);
        this.day.setText(" " + this.dateLabel);
        if(this.dateLabel > cap || this.dateLabel <= 0){
            this.day.setTextFill(Constants.INVISIBLE);
            this.dateSquare.setFill(Constants.INVISIBLE);
        } else {
            this.day.setTextFill(Color.BLACK);
            this.dateSquare.setFill(Color.WHITE);
        }
        //special cases:------------- overflow days
        if(specialCase){
            if(currMonth == "January" || currMonth == "October"){
                if(this.dateLabel == -4){
                    this.day.setText(" " + 30);
                    this.day.setTextFill(Color.BLACK);
                    this.dateSquare.setFill(Color.WHITE);
                } else if (this.dateLabel == -5){
                    this.day.setText(" " + 31);
                    this.day.setTextFill(Color.BLACK);
                    this.dateSquare.setFill(Color.WHITE);
                }
            } else if(currMonth == "July"){ //only other time boolean is set to true is in July (edge case)
                if(this.dateLabel == -4) {
                    this.day.setText(" " + 31);
                    this.day.setTextFill(Color.BLACK);
                    this.dateSquare.setFill(Color.WHITE);
                }
            }
        }
    }

//    public void setUpTimeline() {
//        KeyFrame kf = new KeyFrame(Duration.seconds(0.2), (ActionEvent e) -> updateSquare());
//        this.timeline = new Timeline(kf);
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
//    }

//    public void updateSquare(){
//        if(this.currMonth != "January"){
//            System.out.println("currentMonth != Jan)" + "currMonth=" + this.currMonth);
//        }
//    }

}