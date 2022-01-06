
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

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
    private ArrayList<StoreIt> storage;

    private String[] months;
    private Calendar[] monthPages;
    private int calendarIndex;
    private Pane CalendarPane;


    //TODO: make forawrd back and clear for the dailyPane and the CalendarPane
    public PaneOrganizer(){

        this.storage = new ArrayList<>();
        this.selectedMonth = "January";
        this.root = new BorderPane();

        Pane DailyPane = new Pane();
        Pane HabitsPane = new Pane();
        CalendarPane = new Pane();

        this.calendarIndex = 0; //current calendar pane program is flipped to, default is Jan
        this.monthPages = new Calendar[12];
        this.months = new String[12];

        this.months[0] = "January";
        this.months[1] = "Febuary";
        this.months[2] = "March";
        this.months[3] = "April";
        this.months[4] = "May";
        this.months[5] = "June";
        this.months[6] = "July";
        this.months[7] = "August";
        this.months[8] = "September";
        this.months[9] = "October";
        this.months[10] = "November";
        this.months[11] = "December";

        for(int i = 0; i < this.monthPages.length; i++){
            Pane pane = new Pane();
            this.monthPages[i] = new Calendar(pane, this.months[i]);
        }

        this.calendar = new Calendar(CalendarPane, "January");
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

    public void setUpPane(Pane pane, String backgroundColor,Double width, Double height){
        pane.setStyle("-fx-background-color:" + backgroundColor + ";");
        pane.setPrefSize(width, height);
    }

    public void setUpButtonPane(){
        javafx.scene.control.Button quitButton = new Button("QUIT");
        quitButton.setOnAction((ActionEvent e) -> System.exit(0));
        quitButton.setFocusTraversable(false);
        this.buttonPane.getChildren().add(quitButton);
    }


    public BorderPane getRoot(){
        return this.root;
    }

    public void setFlipForwardButton(){
        calendar.getForwards().setOnAction((ActionEvent e) -> forward());
        calendar.getForwards().setFocusTraversable(false);
    }

    public void setFlipBackButton(){
        calendar.getBackwards().setOnAction((ActionEvent e) -> back());
        calendar.getBackwards().setFocusTraversable(false);
    }

    public void forward(){
        this.saveCircles();
        System.out.println("forwards");
        if(this.calendarIndex < 11){

            this.calendar.setTitle(this.months[this.calendarIndex + 1]);
            this.selectedMonth = this.months[this.calendarIndex + 1];
            this.calendarIndex = calendarIndex + 1;

        } else {

            this.calendar.setTitle(this.months[0]);
            this.selectedMonth = this.months[0];
            this.calendarIndex = 0;
            System.out.println("end of the year, looping back to the start");

        }


        habit.clearChecks();
        calendar.clearGrid();

        //figure out how to store the previous circles, loop through circle array and save location as points in an array list and colors too, then redraw, may need to make a circle wrapper class for this, then the array of panes is not needed, should delete afterwards
        //need to put an if statement in calendar that switches the number labels around depending on when the day starts
        //make it more editable to determine when the starting day is
    }

    public void back(){

        this.saveCircles();

        System.out.println("back");

        if(this.calendarIndex > 0){
            this.calendar.setTitle(this.months[this.calendarIndex - 1]);
            this.selectedMonth = this.months[this.calendarIndex - 1];
            this.calendarIndex = calendarIndex - 1;

        } else {
            this.calendar.setTitle(this.months[11]);
            this.selectedMonth = this.months[11];
            this.calendarIndex = 11;
            System.out.println("start of the year, looping back to the end");
        }

        habit.clearChecks();
        calendar.clearGrid();
        //if there is a saved storeIt for that month redraw the circles, else, clear checks and clear the grid

    }


    public void saveCircles(){

        for(int i = 0; i < calendar.getDaSquares().length; i++){

            Circle[] circles = this.calendar.getDaSquares()[i].getCircles();

//            for(int j = 0; j < circles.length; j++){
                StoreIt store = new StoreIt(this.selectedMonth);
                store.addCircle(circles);

                //get month index, this will be the index of storage
                for (int k = 0; k < this.months.length; k++){
                    if(this.months[k] == this.selectedMonth){
                        storage.add(store); //store the storeIt in the storeIt array
                    }
                }
//            }

        }

    }

    public void redrawCircles(){
        //if the month you flipped to and it's corresponding StoreIt is not null in the index of the arraylist...
        //remove all circles from the pane, make a method to do this
        //loop through the circles arraylist in the store it and add them to the pane, make a method in the calendar class that takes in the arraylist of circles, then call it here
        for(int i = 0; i < storage.size(); i++){

        }
    }


    }
