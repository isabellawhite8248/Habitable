import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Habits {

    //TODO: eliminate constants and input a method make it more changeable in the future

    private Pane HabitsPane;
    private Rectangle habitsRect;
    private double prevWidth; //TODO: this can probally be eliminated as an instance variable
    private Color habitColor;

    //TODO: find a way to store the individual habits in enums instead of instance variables
    //TODO: make the habits editable within the program while its running

    private String h0;
    private String h1;
    private String h2;
    private String h3;
    private String h4;
    private String h5;
    private String h6;
    private String h7;

    private String habitMessage;

//    to the right of that a 200 wide column for habits list, should have color circles column, make a 2x2 grid

    public Habits(Pane HabitsPane){

        this.habitsRect = null; //placeholder for creating the habits grid
        this.prevWidth = 110;
        this.habitColor = Color.WHITESMOKE;

        this.h0 = "habit 1";
        this.h1 = "habit 2";
        this.h2 = "habit 3";
        this.h3 = "habit 4";
        this.h4 = "habit 5";
        this.h5 = "habit 6";
        this.h6 = "habit 7";
        this.h7 = "habit 8";

        this.habitMessage = "no message inputed";
        this.HabitsPane = HabitsPane;

        this.HabitsPane.getChildren().add(setTitle());

        for(int row = 0; row < 3; row++){ // for 8 potential habits
            for(int col = 0; col < 8; col++){ //one col for color, the other for text habit, one for frequecey (1x week/month etc)
                //180 wide rows, 20 tall
                switch(row){
                    //col 0 - color, 20x20
                    case (0):
                        switch(col){
                          //TODO: figure out if you can replace this switch statement with an enum
                            case 0:
                                this.habitColor = Color.RED;
                            break;
                            case 1:
                                this.habitColor = Color.ORANGE;
                            break;
                            case 2:
                                this.habitColor = Color.YELLOW;
                            break;
                            case 3:
                                this.habitColor = Color.LIME;
                            break;
                            case 4:
                                this.habitColor = Color.DARKGREEN;
                            break;
                            case 5:
                                this.habitColor = Color.BLUE;
                            break;
                            case 6:
                                this.habitColor = Color.BLUEVIOLET;
                            break;
                            default:
                                this.habitColor = Color.BLACK;
                            break;
                        }
                        this.createHRect(row, col, 20, 50, Color.BLACK, this.habitColor);
                        //TODO: redundant code, eliminate previous set x in method parameter
                        this.habitsRect.setX(10);
                    break;
                    //col 1 - text, habit 110
                    case (1):
                        this.createHRect(row, col, 160, 50, Color.BLACK, Color.LIGHTGREY);
                        //TODO: since height and stroke color remain fixed eliminate the parameter and put it as a constant in the method
                        this.habitsRect.setX(30);

                        switch(col){
                            //TODO: figure out if you can replace this switch statement with an enum
                            case 0:
                                this.habitMessage = this.h0;
                                break;
                            case 1:
                                this.habitMessage = this.h1;
                                break;
                            case 2:
                                this.habitMessage = this.h2;
                                break;
                            case 3:
                                this.habitMessage = this.h3;
                                break;
                            case 4:
                                this.habitMessage = this.h4;
                                break;
                            case 5:
                                this.habitMessage = this.h5;
                                break;
                            case 6:
                                this.habitMessage = this.h6;
                                break;
                            default:
                                this.habitMessage = this.h7;
                                break;
                        }

                        this.setHabit(this.habitMessage, 30, col*50 + 60);

                    break;
                    default: //col 2 - text, frequency, width: 50
//                        this.createHRect(row, col, 50, 50, Color.BLACK, Color.BLACK);
//                        this.prevWidth = 20;
//                        this.habitsRect.setX(140);

                    break;
                }

            }
        }
    }

    public void createHRect(int row, int col, int width, int height, Color stroke, Color fill){

        this.habitsRect = new Rectangle(row*this.prevWidth + 5, col*height + 60, width, height);
        this.habitsRect.setStroke(stroke);
        this.habitsRect.setFill(fill);
        HabitsPane.getChildren().add(habitsRect);

    }

    public void setHabit(String habit, int x, int y){

        CheckBox habitLabel = new CheckBox(habit);
        //TODO: set max width of the habit label so it doesn't go off screen, set max text too so it doesn't go out of the box
//        Label habitLabel = new Label(habit);
        habitLabel.setTextFill(Color.BLACK);
        habitLabel.setLayoutX(x + 5); //5 is the offset maybe make this a static variable later
        habitLabel.setLayoutY(y + 5);
        HabitsPane.getChildren().add(habitLabel);

    }

    public Label setTitle(){
        Label title = new Label("Habits");
        title.setLayoutX(10);
        title.setLayoutY(10);
        title.setStyle("-fx-font: italic bold 36px arial;-fx-text-fill: rgba(0,0,0,1.00)");
        return title;
    }

}
