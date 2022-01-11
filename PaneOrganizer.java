
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.ArrayList;

/*
PaneOrganizer organizes the three main sections of the app and deals with interactions between the calendar and habit circles
 */

//TODO: need to be able to save and load the images from before
// - the habits, the text from the daily pane, the marked circles on the calendar and each of the 12 panes, the month the calendar was flipped to

public class PaneOrganizer {

    private BorderPane root;
    private VBox buttonPane;
    private Calendar calendar;
    private Habits habit;
    private Daily daily;
//    private ArrayList<Circle> circlz;
    private String selectedMonth;
//    private StoreIt[] storage;

    private String[] months;
    private Calendar[] monthPages;
    private int calendarIndex;
    private Pane CalendarPane;


    //TODO: make forawrd back and clear for the dailyPane and the CalendarPane
    public PaneOrganizer(){

//        this.storage = new StoreIt[Constants.NUM_MONTHS];
        this.selectedMonth = Constants.MONTH_0;
        this.root = new BorderPane();

        Pane DailyPane = new Pane();
        Pane HabitsPane = new Pane();
        CalendarPane = new Pane();

        this.calendarIndex = 0; //current calendar pane program is flipped to, default is Jan
        this.monthPages = new Calendar[Constants.NUM_MONTHS];
        this.months = new String[Constants.NUM_MONTHS];

        this.months[0] = Constants.MONTH_0;
        this.months[1] = Constants.MONTH_1;
        this.months[2] = Constants.MONTH_2;
        this.months[3] = Constants.MONTH_3;
        this.months[4] = Constants.MONTH_4;
        this.months[5] = Constants.MONTH_5;
        this.months[6] = Constants.MONTH_6;
        this.months[7] = Constants.MONTH_7;
        this.months[8] = Constants.MONTH_8;
        this.months[9] = Constants.MONTH_9;
        this.months[10] = Constants.MONTH_10;
        this.months[11] = Constants.MONTH_11;

//        for(int i = 0; i < this.monthPages.length; i++){
//            Pane pane = new Pane();
//            this.monthPages[i] = new Calendar(pane, this.months[i]);
//        }

        this.calendar = new Calendar(CalendarPane, Constants.MONTH_0);
        this.habit = new Habits(HabitsPane, this.calendar);
        this.daily = new Daily(DailyPane);

        this.buttonPane = new VBox();
        this.setUpButtonPane();
        root.setBottom(buttonPane);

        root.setCenter(DailyPane);
        root.setRight(HabitsPane);
        root.setLeft(CalendarPane);

        this.setUpPane(DailyPane, Constants.DAILY_COLOR, Constants.DAILY_WIDTH, Constants.DAILY_HEIGHT);
        this.setUpPane(HabitsPane, Constants.HABITS_COLOR, Constants.HABITS_WIDTH, Constants.HABITS_HEIGHT);
        this.setUpPane(CalendarPane, Constants.CALENDAR_COLOR, Constants.CALENDAR_DIMENSION, Constants.CALENDAR_DIMENSION);

        this.setFlipBackButton();
        this.setFlipForwardButton();

    }

    //method which sets up the pane
    public void setUpPane(Pane pane, String backgroundColor,Double width, Double height){
        pane.setStyle("-fx-background-color:" + backgroundColor + ";");
        pane.setPrefSize(width, height);
    }

    //sets up the quit button
    public void setUpButtonPane(){
        javafx.scene.control.Button quitButton = new Button("QUIT");
        quitButton.setOnAction((ActionEvent e) -> System.exit(0));
        quitButton.setFocusTraversable(false);
        this.buttonPane.getChildren().add(quitButton);
    }

    //gets the root, a borderpane which all the other panes are added to
    public BorderPane getRoot(){
        return this.root;
    }

    //forward button set to the flip forward method, called when pressed
    public void setFlipForwardButton(){
        calendar.getForwards().setOnAction((ActionEvent e) -> forward());
        calendar.getForwards().setFocusTraversable(false);
    }

    //back button set to the flip forward method, called when pressed
    public void setFlipBackButton(){
        calendar.getBackwards().setOnAction((ActionEvent e) -> back());
        calendar.getBackwards().setFocusTraversable(false);
    }

    //foward method, flips the calendar forward by switching the title
    public void forward(){

        //saves the circle
//        this.saveCircles();

        //sets the title according to the calendar index
        if(this.calendarIndex < 11){
            this.calendar.setTitle(this.months[this.calendarIndex + 1]);
            this.selectedMonth = this.months[this.calendarIndex + 1];
            this.calendarIndex = calendarIndex + 1;
        } else { //edge case is the month of December where it needs to flip to Jan
            this.calendar.setTitle(this.months[0]);
            this.selectedMonth = this.months[0];
            this.calendarIndex = 0;
        }

        //clears the circles and the grid in preparation for a new month
//        if(this.storage[this.calendarIndex] == null) {
//            habit.clearChecks();
//            calendar.clearGrid();
//        } else {
//            this.redrawCircles();
//        }

        //TODO: finish writing the forward method
        //figure out how to store the previous circles, loop through circle array and save location as points in an array list and colors too, then redraw, may need to make a circle wrapper class for this, then the array of panes is not needed, should delete afterwards
        //need to put an if statement in calendar that switches the number labels around depending on when the day starts
        //make it more editable to determine when the starting day is
    }

    public void back(){

        //saves the circle
//        this.saveCircles();

        //sets the title according to the calendar index
        if(this.calendarIndex > 0){
            this.calendar.setTitle(this.months[this.calendarIndex - 1]);
            this.selectedMonth = this.months[this.calendarIndex - 1];
            this.calendarIndex = calendarIndex - 1;
        //edge case is Jan, which it needs to flip back to December
        } else {
            this.calendar.setTitle(this.months[11]);
            this.selectedMonth = this.months[11];
            this.calendarIndex = 11;
            System.out.println("start of the year, looping back to the end");
        }

//        clears the circles and the grid in preparation for a new month
//        if(this.storage[this.calendarIndex] == null) {
//            habit.clearChecks();
//            calendar.clearGrid();
//        } else {
//            this.redrawCircles();
//        }
//        if there is a saved storeIt for that month redraw the circles, else, clear checks and clear the grid

    }


    //method which saves all the circles on the board in a new instance of store it
//    public void saveCircles(){
//        StoreIt store = new StoreIt(this.selectedMonth, calendar.getDaSquares()); //store it takes in the selectedMonth to label which month it's storing
//        for(int i = 0; i < calendar.getDaSquares().length; i++){
//            Circle[] circles = this.calendar.getDaSquares()[i].getCircles();
//            store.addCircle(circles); //loops through the circle array and stores it in the arraylist in the corresponding StoreIt instance
//            storage[this.calendarIndex] = store; //stores the store it in the store it array
//        }
//    }

//    public void redrawCircles(){
////        this.calendar.takeOffCircles(); //removes all circles from the calendar pane
//        for(int i = 0; i < storage.length; i++){
//            if(this.storage[i] != null && i == this.calendarIndex){ //if the storage index in the array has a store it saved, and it is of the same month...
//                //add the circles from the store it to the pane
//                this.storage[i].color();
////                for(int f = 0; f < this.storage[i].getAllCircles().size(); f++){
////                    Circle circle = this.storage[i].getAllCircles().get(f);
////                    System.out.println(circle);
////                    circle.setFill(Color.BLACK);
//////                    CalendarPane.getChildren().add(circle);
////                }
//            }
//        }
//
//    }
}


