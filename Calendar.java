
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/*
This class handles everything in the Calendar pane which is 500 by 500 in dimension and is set in the left
orientation of the borderPane. The calendar itself is a 7x5 grid of calendarSquares where each square is
80 by 80 pixels in dimension
 */

public class Calendar {

    private double date;
    private double gridNum;
    private double rowNum;
    private double mouseX;
    private double mouseY;
    private int squareIndex;

    private Pane CalendarPane;
    private String month;
    private Button forwards;
    private Button backwards;
    private Label title;
    private CalendarSquare[] squares;
    private CalendarSquare selectedSquare;

    public Calendar(Pane CalendarPane, String Month){

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

        //initializes the calendar grid and creates it in the method below
        this.createGrid();
        this.backwards = this.setButton("back", Constants.BACKWARDS_BUTTON_XCOORD);
        this.forwards = this.setButton("forwards", Constants.FORWARDS_BUTTON_XCOORD);
        this.setMonth(this.month);

        //adds a mouse listener to detect where the mouse clicked on the calendar
        this.CalendarPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new mouseClickDetector());

    }

    public void createGrid() {

        //creates calendar grid in an array of 35 instances of calendar square

        for(int row = 0; row < Constants.CAL_ROW_NUM; row++){

            switch (row){
                case 0:
                    setLabel("Sun",row*Constants.CALENDAR_SQUARE_DIMENSION);
                    break;
                case 1:
                    setLabel("Mon",row*Constants.CALENDAR_SQUARE_DIMENSION);
                    break;
                case 2:
                    setLabel("Teus",row*Constants.CALENDAR_SQUARE_DIMENSION);
                    break;
                case 3:
                    setLabel("Wed",row*Constants.CALENDAR_SQUARE_DIMENSION);
                    break;
                case 4:
                    setLabel("Thurs",row*Constants.CALENDAR_SQUARE_DIMENSION);
                    break;
                case 5:
                    setLabel("Fri",row*Constants.CALENDAR_SQUARE_DIMENSION);
                    break;
                case 6:
                    setLabel("Sat",row*Constants.CALENDAR_SQUARE_DIMENSION);
                    break;
            }

            Rectangle rect = new Rectangle(Constants.CALENDAR_SQUARE_DIMENSION,Constants.CALENDAR_SQUARE_DIMENSION/2, Color.color(0,0,0,0)); //days of the week label
            rect.setX(row*Constants.CALENDAR_SQUARE_DIMENSION);
            rect.setY(70);
            rect.setStroke(Color.BLACK);

            CalendarPane.getChildren().add(rect);

            for(int col = 0; col < 5; col++){

                this.rowNum = col;

                if(gridNum > 0) {

                    date = gridNum - 7; //this is because it starts on a Saturday
                    //TODO: make it changeable depending on the month entered

                } else{

                    date = 0.0;

                }
                CalendarSquare square = new CalendarSquare(Constants.CALENDAR_SQUARE_DIMENSION*row, Constants.CALENDAR_SQUARE_DIMENSION*col+ 100, gridNum, date, CalendarPane, rowNum);
                this.squares[this.squareIndex] = square;
                //moved the y coordinate down by 100 to make room for the days of the week labels and month labels
                CalendarPane.getChildren().addAll(square.getSquare(), square.getDateSquare(), square.getDay());
                square.addCircles(square.getX(), square.getY());

                gridNum++;
                squareIndex = squareIndex + 1;

            }
        }
    }

    private class mouseClickDetector implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e) {

            mouseX = e.getX();
            mouseY = e.getY();

            for (int i = 0; i < squares.length; i++) {

                if (squares[i] != null &&
                        mouseX > squares[i].getX() && mouseX < squares[i].getX() + Constants.CALENDAR_SQUARE_DIMENSION
                && mouseY > squares[i].getY() && mouseY < squares[i].getY() + Constants.CALENDAR_SQUARE_DIMENSION) {

                    if (selectedSquare != squares[i]) {

                        if (selectedSquare != null) {
                            selectedSquare.setStroke(Color.BLACK, 1.0);
                        }
                        selectedSquare = squares[i];
                        selectedSquare.setStroke(Color.RED, 3.2);

                    }
                }
            }
        }
    }

    public void setLabel(String text, int xCoord){
        Label newLabel = new Label(text);
        newLabel.setLayoutX(xCoord + 5);
        newLabel.setLayoutY(70);
        newLabel.setStyle("-fx-font: italic bold 20px arial;-fx-text-fill: rgba(0,0,0,1.00)");
        CalendarPane.getChildren().add(newLabel);
    }

    public void setMonth(String string){
        this.title = new Label(string);
        this.title.setLayoutX(200);
        this.title.setLayoutY(10);
        this.title.setStyle("-fx-font: italic bold 36px arial;-fx-text-fill: rgba(0,0,0,1.00)");
        CalendarPane.getChildren().add(this.title);
    }

    public Button setButton(String label, double XCoord){
        Button button = new Button(label);
        button.setLayoutX(XCoord);
        button.setLayoutY(20);
        CalendarPane.getChildren().add(button);
        return button;
    }

    public Circle[] getDaCircles(){
            return this.selectedSquare.getCircles();
    }

    public CalendarSquare[] getDaSquares(){return this.squares;}

    public CalendarSquare getSelectedSquare(){
        return selectedSquare;
    }

    public Button getForwards(){
        return forwards;
    }
    public Button getBackwards(){
        return backwards;
    }

    public void setTitle(String newTitle){
        this.CalendarPane.getChildren().remove(this.title);
        this.setMonth(newTitle);
    }

    public void clearGrid(){
        for(int i = 0; i < squares.length; i++){
            this.squares[i].clearCircles();
        }
    }

    public void redrawCircles(Circle[] circles){
        for(int j = 0; j < this.selectedSquare.getCircles().length; j++){
            this.CalendarPane.getChildren().remove(this.selectedSquare.getCircles()[j]);
        }
        for(int i = 0; i < circles.length; i++){
            this.CalendarPane.getChildren().add(circles[i]);
        }
    }


}