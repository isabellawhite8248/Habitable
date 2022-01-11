
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/*
This class handles everything in the Calendar pane which is 500 by 500 in dimension and is set in the left
orientation of the borderPane. The calendar itself is a 7x5 grid of calendarSquares where each square is
80 by 80 pixels in dimension
 */

public class Calendar {

    private Timeline timeline;

    private double date;
    private double gridNum;
    private double rowNum;
    private double mouseX;
    private double mouseY;
    private int squareIndex;
    private int dayOffset;

    private Pane CalendarPane;
    private String month;
    private Button forwards;
    private Button backwards;
    private Label title;
    private CalendarSquare[] squares;
    private CalendarSquare selectedSquare;

    public Calendar(Pane CalendarPane, String Month){

        this.dayOffset = 7; //Jan starts on a Saturday, therefore, the number 1 starts counting the days in the 7th grid square
        this.month = Month;
        this.mouseX = 0;
        this.mouseY = 0;
        this.selectedSquare = null;
        this.rowNum = 0;
        this.date = 0;
        this.gridNum = 1;
        this.squareIndex = 0;
        this.CalendarPane = CalendarPane;
        this.squares = new CalendarSquare[Constants.CALENDAR_SQUARE_ARRAY_LENGTH];
//        this.totalCircles = new ArrayList<Circle>();

        //initializes the calendar grid and creates it in the method below
        this.createGrid();
        this.backwards = this.setButton(Constants.BACKWARDS_BUTTON_LABEL, Constants.BACKWARDS_BUTTON_XCOORD);
        this.forwards = this.setButton(Constants.FORWARDS_BUTTON_LABEL, Constants.FORWARDS_BUTTON_XCOORD);
        this.setMonth(this.month);

        this.setUpTimeline();

        //adds a mouse listener to detect where the mouse clicked on the calendar
        this.CalendarPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new mouseClickDetector());

    }

    public void createGrid() {

        //creates calendar grid in an array of 35 instances of calendar square

        for(int row = 0; row < Constants.CAL_ROW_NUM; row++){

            //uses the setLabel method to make labels for each day at the top
            switch (row){
                case 0:
                    setLabel(Constants.DAY_0,row);
                    break;
                case 1:
                    setLabel(Constants.DAY_1,row);
                    break;
                case 2:
                    setLabel(Constants.DAY_2,row);
                    break;
                case 3:
                    setLabel(Constants.DAY_3,row);
                    break;
                case 4:
                    setLabel(Constants.DAY_4,row);
                    break;
                case 5:
                    setLabel(Constants.DAY_5,row);
                    break;
                case 6:
                    setLabel(Constants.DAY_6,row);
                    break;
            }

            //creates a new rectangle to outline the days of the week labels
            Rectangle rect = new Rectangle(Constants.CALENDAR_SQUARE_DIMENSION,Constants.CALENDAR_SQUARE_DIMENSION/2, Constants.INVISIBLE);
            rect.setX(row*Constants.CALENDAR_SQUARE_DIMENSION);
            rect.setY(Constants.DAYS_OF_WEEK_YCOORD);
            rect.setStroke(Color.BLACK);

            //adds the rectangle for the corresponding day of the week graphically
            CalendarPane.getChildren().add(rect);

            //loops down the rows (5) of them
            for(int col = 0; col < Constants.CAL_COL_NUM; col++){

                this.rowNum = col;

                //when ititialized the grid number starts at 1 and counts up at the end of the loop
                if(gridNum > 0) {
                    date = gridNum - this.dayOffset; //this is because Jan starts on a Saturday
                    //TODO: make it changeable depending on the month entered
                }
//                else{
//                    date = 0.0;
//                }

                //creates a new calendar square
                CalendarSquare square = new CalendarSquare(Constants.CALENDAR_SQUARE_DIMENSION*row, Constants.CALENDAR_SQUARE_DIMENSION*col+ Constants.SQUARE_YOFFSET, gridNum, date, CalendarPane, rowNum, this.month);

                //adds the calendar square to the squares array
                this.squares[this.squareIndex] = square;

                //moved the y coordinate down by 100 to make room for the days of the week labels and month labels
                CalendarPane.getChildren().addAll(square.getSquare(), square.getDateSquare(), square.getDay());
                square.addCircles(square.getX(), square.getY());

                gridNum++;
                squareIndex = squareIndex + 1;

            }
        }
    }

        public void setUpTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(0.2), (ActionEvent e) -> updateGrid());
        this.timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void updateGrid(){
        System.out.println(this.month);
        switch(month){
            case("January"):
                this.changeOffset(6, 31, true);
                //starts on Saturday, has 31 days
                //TODO: make an algorithm which predicts next month based on the last one
                //that way you don't have to manually enter this data, cleaner and easier to use
                break;
            case("Febuary"):
                this.changeOffset(2, 28, false);
                //starts on Teusday, has 28 days
                break;
            case("March"):
                this.changeOffset(2, 31, false);
                //starts on Teusday, has 31 days
                break;
            case("April"):
                this.changeOffset(5, 30, false);
                //starts on Friday, 30 days
                break;
            case("May"):
                this.changeOffset(0, 31, false);
                break;
                //starts on Sunday, 31 days
            case("June"):
                this.changeOffset(3, 30, false);
                break;
                //starts on Wed, 30 days;
            case("July"):
                this.changeOffset(5, 31, true);
                //starts on fri, 31 days;
                break;
            case("August"):
                this.changeOffset(1, 31, false);
                //starts on mon, 31 days;
                break;
            case("September"):
                this.changeOffset(4, 30, false);
                //starts on thurs, 30 days;
                break;
            case("October"):
                this.changeOffset(6, 31, true);
                //TODO: duplicate branch, combine switch branch with Jan
                //starts on sat, 31 days;
                break;
            case("November"):
                this.changeOffset(2, 30, false);
                //starts on teus, 30 days;
                break;
            case("December"):
                this.changeOffset(4, 31, false);
                //starts on thurs, 31 days;
                break;
        }
    }

    public void changeOffset(int offset, int cap, Boolean overflow){
        for(int i = 0; i <this.squares.length; i++){
            this.squares[i].updateSquare(offset, cap, overflow, this.month);
        }
    }

    private class mouseClickDetector implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {

            //stores the mouse x and y in instance variables when the mouse is clicked
            mouseX = e.getX();
            mouseY = e.getY();

            //when the mouse is clicked it loops through the array of calendar squares
            for (int i = 0; i < squares.length; i++) {

                //if the square is not null, and the mouse is within the bounds...
                if (squares[i] != null &&
                mouseX > squares[i].getX() && mouseX < squares[i].getX() + Constants.CALENDAR_SQUARE_DIMENSION
                && mouseY > squares[i].getY() && mouseY < squares[i].getY() + Constants.CALENDAR_SQUARE_DIMENSION) {

                    //and if the square is not the same as the one selected before...
                    if (selectedSquare != squares[i]) {

                        //and if the current selected square is not null -- it's set to null at the program launch
                        if (selectedSquare != null) {

                            //set the previous selected square's stroke back to black (deselect it)
                            selectedSquare.setStroke(Color.BLACK, Constants.CALENDAR_STROKE_WIDTH);
                        }

                        //set the square you clicked into as the selected square and designate it with red stroke
                        selectedSquare = squares[i];
                        selectedSquare.setStroke(Color.RED, Constants.SELECT_STROKE_WIDTH);

                    }
                }
            }
        }
    }

    //sets the labels for the days of the week
    public void setLabel(String text, int xCoord){
        Label newLabel = new Label(text);
        newLabel.setLayoutX(xCoord*Constants.CALENDAR_SQUARE_DIMENSION + Constants.LABEL_OFFSET);
        newLabel.setLayoutY(70);
        newLabel.setStyle("-fx-font: italic bold 20px arial;-fx-text-fill: rgba(0,0,0,1.00)");
        CalendarPane.getChildren().add(newLabel);
    }

    //sets the title label for the month, default is Jan
    public void setMonth(String string){
        this.title = new Label(string);
        this.title.setLayoutX(200);
        this.title.setLayoutY(10);
        this.title.setStyle("-fx-font: italic bold 36px arial;-fx-text-fill: rgba(0,0,0,1.00)");
        CalendarPane.getChildren().add(this.title);
    }

    //sets up buttons, used to add the flip forward and flip backward buttons
    public Button setButton(String label, double XCoord){
        Button button = new Button(label);
        button.setLayoutX(XCoord);
        button.setLayoutY(Constants.FLIP_BUTTON_Y_COORD);
        CalendarPane.getChildren().add(button);
        return button;
    }

    //gets the circles from the selected square, stored in an array
    public Circle[] getDaCircles(){ return this.selectedSquare.getCircles(); }

    //gets the array of calendar squares in an array of type CalendarSquare
    public CalendarSquare[] getDaSquares(){return this.squares;}

    //gets the square currently selected
    public CalendarSquare getSelectedSquare(){ return selectedSquare; }

    //returns the forwards/backwards button
    public Button getForwards(){ return forwards; }
    public Button getBackwards(){ return backwards;}

    //switches the month to another string, designated in the paneOrganizer
    public void setTitle(String newTitle){
        this.CalendarPane.getChildren().remove(this.title);
        this.setMonth(newTitle);
        this.month = newTitle;
    }

    //sets all circles back to the invisible color, giving a clean slate
    public void clearGrid(){
        for(int i = 0; i < squares.length; i++){
            this.squares[i].clearCircles();
        }
    }

    public void takeOffCircles(){
        for(int i = 0; i < squares.length; i++){
            this.squares[i].clearCircles();
        }
    }

    //loop through each square, get the collor fill array and color it accordingly
//    public void color(){
//        for(int s = 0; s < squares.length; s++) {
//            int[] fills = this.squares[s].getCircleFills();
//            for (int i = 0; i < this.squares[s].getCircles().length; i++) {
//                Circle[] circle = this.squares[s].getCircles();
//                switch (i) {
//                    case 0: //this circle will always be invisible no matter what because there are only 8 habits
////                    if(fills[0] == 1){
////
////                    } else{
////
////                    }
//                        circle[i].setFill(Constants.INVISIBLE);
//                        break;
//                    case 1:
//                        if (fills[1] == 1) {
//                            circle[i].setFill(Color.RED);
//                        } else {
//                            circle[i].setFill(Constants.INVISIBLE);
//                        }
//                        break;
//                    case 2:
//                        if (fills[2] == 1) {
//                            circle[i].setFill(Color.ORANGE);
//                        } else {
//                            circle[i].setFill(Constants.INVISIBLE);
//                        }
//                        break;
//                    case 3:
//                        if (fills[3] == 1) {
//                            circle[i].setFill(Color.YELLOW);
//                        } else {
//                            circle[i].setFill(Constants.INVISIBLE);
//                        }
//                        break;
//                    case 4:
//                        if (fills[4] == 1) {
//                            circle[i].setFill(Color.LIME);
//                        } else {
//                            circle[i].setFill(Constants.INVISIBLE);
//                        }
//                        break;
//                    case 5:
//                        if (fills[5] == 1) {
//                            circle[i].setFill(Color.DARKGREEN);
//                        } else {
//                            circle[i].setFill(Constants.INVISIBLE);
//                        }
//                        break;
//                    case 6:
//                        if (fills[6] == 1) {
//                            circle[i].setFill(Color.BLUE);
//                        } else {
//                            circle[i].setFill(Constants.INVISIBLE);
//                        }
//                        break;
//                    case 7:
//                        if (fills[7] == 1) {
//                            circle[i].setFill(Color.BLUEVIOLET);
//                        } else {
//                            circle[i].setFill(Constants.INVISIBLE);
//                        }
//                        break;
//                    case 8:
//                        if (fills[8] == 1) {
//                            circle[i].setFill(Color.BLACK);
//                        } else {
//                            circle[i].setFill(Constants.INVISIBLE);
//                        }
//                        break;
//                }
//            }
//        }
//
//    }

//    public void redrawCircles(Circle[] circles){
//
//        for(int j = 0; j < this.selectedSquare.getCircles().length; j++){
//            this.CalendarPane.getChildren().remove(this.selectedSquare.getCircles()[j]);
//        }
//
//        for(int i = 0; i < circles.length; i++){
//            this.CalendarPane.getChildren().add(circles[i]);
//        }
//    }


}