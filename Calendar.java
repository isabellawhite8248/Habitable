
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
//Dimensions = 500x500
//grid is 7 wide and 5 tall, need room at the top for a label
//400/5 = 80 tall and 80 wide, 80 by 80 squares

public class Calendar {

    private double date;
    private Pane CalendarPane;
    private double gridNum;
    private double rowNum;

    public Calendar(Pane CalendarPane){

        this.rowNum = 0;
        this.date = 0; //intially
        this.gridNum = 1; //intially
        this.CalendarPane = CalendarPane;
        this.createGrid();
        this.setButton("back", 10);
        this.setButton("forwards", 70);
        this.setMonth();

    }

    public void createGrid() {
        //created grid in row major switched to column major to better label the dates
        for(int row = 0; row < 7; row++){

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
                //moved the y coordinate down by 100 to make room for the days of the week labels and month labels
                CalendarPane.getChildren().addAll(square.getSquare(), square.getDateSquare(), square.getDay());

                gridNum++;

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

    public void setMonth(){
        Label newLabel = new Label(Constants.MONTH);
        newLabel.setLayoutX(200);
        newLabel.setLayoutY(10);
        newLabel.setStyle("-fx-font: italic bold 36px arial;-fx-text-fill: rgba(0,0,0,1.00)");
        CalendarPane.getChildren().add(newLabel);
    }

    public void setButton(String label, double XCoord){
        Button button = new Button(label);
        button.setLayoutX(XCoord);
        button.setLayoutY(20);
        CalendarPane.getChildren().add(button);
    }

}
