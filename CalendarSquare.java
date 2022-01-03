import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
//TO FINISH THE PROJECT
//if a mouse intersects the calendar it is the selected square and habits circle will appear if box is checked, if not no
//add the array of months and add a clear button for each month which resets everything and unchecks the habits
//TODO: fix the bug where if the month starts on a Saturday it can't fit more than 29 days AKA the month of januraury

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

    public CalendarSquare(int xCoord, int yCoord, Double gridNum, Double date, Pane CalendarPane, Double rowNum) {

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
            this.dateSquare.setFill(Color.color(0,0,0,0));

        }else if(this.dateLabel == -4){

            this.dateLabel = 31;
            this.day.setText("31");
            this.day.setTextFill(Color.DARKBLUE);
            this.dateSquare.setFill(Color.color(0,0,0,0));
            //TODO: only make this possible if the month has 31 days

         }else if (this.dateLabel < 1 || this.dateLabel > 31){
            this.day.setTextFill(Color.color(0,0,0,0));
            this.dateSquare.setFill(Color.color(0,0,0,0));
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

}
