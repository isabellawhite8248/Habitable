import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Habits {

    //TODO: eliminate constants and input a method make it more changeable in the future
    private Timeline timeline;
    private Pane HabitsPane;
    private Rectangle habitsRect;
    private double prevWidth; //TODO: this can probally be eliminated as an instance variable
    private Color habitColor;
    private Boolean isClearing;


    private CheckBox[] checkedBoxes;
    private Habit[] habitsArray;

    private Habit currentHabit;

    //TODO: find a way to store the individual habits in enums instead of instance variables
    //TODO: make the habits editable within the program while its running

    private Habit h0;
    private Habit h1;
    private Habit h2;
    private Habit h3;
    private Habit h4;
    private Habit h5;
    private Habit h6;
    private Habit h7;

    private CheckBox currentCheckedBox;

    private String habitMessage;

    private Calendar calendar;
//    to the right of that a 200 wide column for habits list, should have color circles column, make a 2x2 grid

    public Habits(Pane HabitsPane, Calendar calendar){

        this.calendar = calendar;

        this.isClearing = false;
        this.habitsRect = null; //placeholder for creating the habits grid
        this.prevWidth = 110;
        this.habitColor = Color.WHITESMOKE;
        this.habitMessage = "no habit inputed";

        this.setUpTimeline();

        this.currentCheckedBox = null;
        this.currentHabit = null;

        this.checkedBoxes = new CheckBox[8];
        this.habitsArray = new Habit[8];

        this.h0 = new Habit(Color.RED, false,"Brush your teeth");
        this.h1 = new Habit(Color.ORANGE, false, "study cs");
        this.h2 = new Habit(Color.YELLOW, false, "eat healthy");
        this.h3 = new Habit(Color.LIME, false, "habit 4");
        this.h4 = new Habit(Color.DARKGREEN, false, "habit 5");
        this.h5 = new Habit(Color.BLUE, false, "habit 6");
        this.h6 = new Habit(Color.BLUEVIOLET, false, "habit 7");
        this.h7 = new Habit(Color.BLACK, false, "habit 8");

//with or without you U2

        this.HabitsPane = HabitsPane;

//        this.HabitsPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new mouseClickDetector());

        this.HabitsPane.getChildren().add(setTitle());

        for(int row = 0; row < 3; row++){ // for 8 potential habits
            for(int col = 0; col < 8; col++){ //one col for color, the other for text habit, one for frequecey (1x week/month etc)
                //180 wide rows, 20 tall
                switch(row){
                    //col 0 - color, 20x20
                    case (0):
                        //TODO: you switch on the same variable, col twice, could we combine the switch statements or better yet simplify with an enum?
                        switch(col){
                            //TODO: figure out if you can replace this switch statement with an enum
                            case 0:
                                this.habitColor = this.h0.getHabitColor();
                                this.currentHabit = h0;
                                break;
                            case 1:
                                this.habitColor = this.h1.getHabitColor();
                                this.currentHabit = h1;
                                break;
                            case 2:
                                this.habitColor = this.h2.getHabitColor();
                                this.currentHabit = h2;
                                break;
                            case 3:
                                this.habitColor = this.h3.getHabitColor();
                                this.currentHabit = h3;
                                break;
                            case 4:
                                this.habitColor = this.h4.getHabitColor();
                                this.currentHabit = h4;
                                break;
                            case 5:
                                this.habitColor = this.h5.getHabitColor();
                                this.currentHabit = h5;
                                break;
                            case 6:
                                this.habitColor = this.h6.getHabitColor();
                                this.currentHabit = h6;
                                break;
                            default:
                                this.habitColor = this.h7.getHabitColor();
                                this.currentHabit = h7;
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
                                this.habitMessage = this.h0.getHabitLabel();
                                break;
                            case 1:
                                this.habitMessage = this.h1.getHabitLabel();
                                break;
                            case 2:
                                this.habitMessage = this.h2.getHabitLabel();
                                break;
                            case 3:
                                this.habitMessage = this.h3.getHabitLabel();
                            case 4:
                                this.habitMessage = this.h4.getHabitLabel();
                                break;
                            case 5:
                                this.habitMessage = this.h5.getHabitLabel();
                                break;
                            case 6:
                                this.habitMessage = this.h6.getHabitLabel();
                                break;
                            default:
                                this.habitMessage = this.h7.getHabitLabel();
                                break;
                        }

                        this.setHabit(this.habitMessage, 30, col*50 + 60, col);
                        this.habitsArray[col] = this.currentHabit;

                        break;

                    default:

                        break;
                }


            }
        }

        this.HabitsPane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());

    }

    public void setUpTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(0.2), (ActionEvent e) -> updateCheckList());
        this.timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void updateCheckList(){

        for(int i = 0; i < checkedBoxes.length; i++){
            if(calendar.getSelectedSquare() != null) {
                if (checkedBoxes[i].isSelected()) {
                    habitsArray[i].setCheck(true);

                    switch(i+1){
                        case(1):
                            calendar.getDaCircles()[i+1].setFill(Color.RED);
                        break;
                        case(2):
                            calendar.getDaCircles()[i+1].setFill(Color.ORANGE);
                        break;
                        case(3):
                            calendar.getDaCircles()[i+1].setFill(Color.YELLOW);
                        break;
                        case(4):
                            calendar.getDaCircles()[i+1].setFill(Color.LIME);
                        break;
                        case(5):
                            calendar.getDaCircles()[i+1].setFill(Color.DARKGREEN);
                        break;
                        case(6):
                            calendar.getDaCircles()[i+1].setFill(Color.BLUE);
                        break;
                        case(7):
                            calendar.getDaCircles()[i+1].setFill(Color.BLUEVIOLET);
                        break;
                        default:
                            calendar.getDaCircles()[i+1].setFill(Color.BLACK);
                        break;
                    }

                } else {
                    habitsArray[i].setCheck(false);
                    calendar.getDaCircles()[i+1].setFill(Constants.INVISIBLE);
                }
            }
        }

        if(isClearing){
            this.clearChecks();
            isClearing = false;
        }

    }

    public void createHRect(int row, int col, int width, int height, Color stroke, Color fill){

        this.habitsRect = new Rectangle(row*this.prevWidth + 5, col*height + 60, width, height);
        this.habitsRect.setStroke(stroke);
        this.habitsRect.setFill(fill);
        HabitsPane.getChildren().add(habitsRect);

    }

    public void setHabit(String habit, int x, int y, int col){
        CheckBox habitLabel = new CheckBox(habit);
        this.currentCheckedBox = habitLabel;
        //TODO: set max width of the habit label so it doesn't go off screen, set max text too so it doesn't go out of the box
//        Label habitLabel = new Label(habit);
        habitLabel.setTextFill(Color.BLACK);
        habitLabel.setLayoutX(x + 5); //5 is the offset maybe make this a static variable later
        habitLabel.setLayoutY(y + 5);
        HabitsPane.getChildren().add(habitLabel);
        //TODO: add checked box to the checked box array, length of 8
        this.checkedBoxes[col] = this.currentCheckedBox;
    }

    public Label setTitle(){
        Label title = new Label("Habits");
        title.setLayoutX(10);
        title.setLayoutY(10);
        title.setStyle("-fx-font: italic bold 36px arial;-fx-text-fill: rgba(0,0,0,1.00)");
        return title;
    }
//SET THIS ACTION ON KEY INPUT SHIFT - clears the checks in the checkBoxes
    public void clearChecks(){
        for(int i = 0; i < this.checkedBoxes.length; i++){
            this.checkedBoxes[i].setSelected(false);
        }
    }

    private class KeyHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent e){
            if(e.isShiftDown()){
                isClearing = true;
            }else{
                isClearing = false;
            }
        }
    }

}