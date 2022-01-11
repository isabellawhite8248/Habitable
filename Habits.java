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

/*
this class sets up the habit pane and stores instances of type habit in it, it also contains the check boxes
and the colors of the habits alongside the strings that tell which habit is which.
 */

public class Habits {

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

    public Habits(Pane HabitsPane, Calendar calendar){

        this.calendar = calendar;
        this.isClearing = false;
        this.habitsRect = null; //placeholder for creating the habits grid
        this.prevWidth = 110;
        this.habitColor = Color.WHITESMOKE;
        this.habitMessage = Constants.HABIT_MESSAGE_DEFAULT;

        //timeline needed to detect when the boxes are checked
        this.setUpTimeline();

        //initial settings
        this.currentCheckedBox = null;
        this.currentHabit = null;

        this.checkedBoxes = new CheckBox[Constants.NUM_HABITS];
        this.habitsArray = new Habit[Constants.NUM_HABITS];

        //creates each of the 8 habits
        this.h0 = new Habit(Color.RED, false,Constants.HABIT_0);
        this.h1 = new Habit(Color.ORANGE, false, Constants.HABIT_1);
        this.h2 = new Habit(Color.YELLOW, false, Constants.HABIT_2);
        this.h3 = new Habit(Color.LIME, false, Constants.HABIT_3);
        this.h4 = new Habit(Color.DARKGREEN, false, Constants.HABIT_4);
        this.h5 = new Habit(Color.BLUE, false, Constants.HABIT_5);
        this.h6 = new Habit(Color.BLUEVIOLET, false, Constants.HABIT_6);
        this.h7 = new Habit(Color.BLACK, false, Constants.HABIT_7);

        this.HabitsPane = HabitsPane;
        this.HabitsPane.getChildren().add(setTitle());

        for(int row = 0; row < 3; row++){ //one row for color, the other for text habit
            for(int col = 0; col < 8; col++){ // 8 cols for 8 potential habits
                switch(row){
                    case (0):

                        //in the first column create the color columnw
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

                        //creates a habit rectangle
                        this.createHRect(row, col, Constants.HABIT_COLOR_RECT_WIDTH, Constants.HABIT_COLOR_RECT_HEIGHT, Color.BLACK, this.habitColor);
                        this.habitsRect.setX(Constants.HABITS_X1);
                        break;

                    //in the 2nd column create the habit label rectangles
                    case (1):

                        this.createHRect(row, col, Constants.HABIT_RECT_WIDTH, Constants.HABIT_RECT_HEIGHT, Color.BLACK, Color.LIGHTGREY);
                        this.habitsRect.setX(Constants.HABITS_X2);

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

                        this.setHabit(this.habitMessage, Constants.HABITS_X2, col*Constants.COL_STAGGER + Constants.Y_STAGGER, col);
                        this.habitsArray[col] = this.currentHabit;

                        break;

                    default:

                        //no default case, only two columns needed
                        break;
                }

            }
        }

        //add a key handler to the pane
        this.HabitsPane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());

    }

    //sets up a timeline and updates the checklist with the timeline
    public void setUpTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(0.2), (ActionEvent e) -> updateCheckList());
        this.timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    //update checklist, called at each second long key frame of the timeline
    public void updateCheckList(){
        for(int i = 0; i < checkedBoxes.length; i++){
            if(calendar.getSelectedSquare() != null) {
                if (checkedBoxes[i].isSelected()) { //if the checkbox is selected...
                    habitsArray[i].setCheck(true); //update the habits array to reflect that
                    //switch statement fills in specific circles corresponding to the habits
                    //setFill command located in the calendar square class updates the fill represented in the int array for storage purposes
                    switch(i+1){
                        case(1):
                            calendar.getDaCircles()[i+1].setFill(Color.RED);
                            calendar.getSelectedSquare().changeFill(1,i);
                        break;
                        case(2):
                            calendar.getDaCircles()[i+1].setFill(Color.ORANGE);
                            calendar.getSelectedSquare().changeFill(1,i);
                        break;
                        case(3):
                            calendar.getDaCircles()[i+1].setFill(Color.YELLOW);
                            calendar.getSelectedSquare().changeFill(1,i);
                        break;
                        case(4):
                            calendar.getDaCircles()[i+1].setFill(Color.LIME);
                            calendar.getSelectedSquare().changeFill(1,i);
                            break;
                        case(5):
                            calendar.getDaCircles()[i+1].setFill(Color.DARKGREEN);
                            calendar.getSelectedSquare().changeFill(1,i);
                            break;
                        case(6):
                            calendar.getDaCircles()[i+1].setFill(Color.BLUE);
                            calendar.getSelectedSquare().changeFill(1,i);
                            break;
                        case(7):
                            calendar.getDaCircles()[i+1].setFill(Color.BLUEVIOLET);
                            calendar.getSelectedSquare().changeFill(1,i);
                            break;
                        default:
                            calendar.getDaCircles()[i+1].setFill(Color.BLACK);
                            calendar.getSelectedSquare().changeFill(1,i);
                            break;
                    }

                } else {

                    //if the box is not checked then set the setting to false and set the circle fill to invisible
                    habitsArray[i].setCheck(false);
                    calendar.getDaCircles()[i+1].setFill(Constants.INVISIBLE);
                    calendar.getSelectedSquare().changeFill(0,i);
                }
            }
        }

        //if the boolean isClearing is true, only during shift pressed, then eliminate the circles from the cell
        if(isClearing){
            this.clearChecks();
            isClearing = false; //set isclearing to false again after the checks are cleared
        }

    }

    //create the habit rectangle, a cell in the habit grid
    public void createHRect(int row, int col, int width, int height, Color stroke, Color fill){
        this.habitsRect = new Rectangle(row*this.prevWidth + Constants.HABITS_X_OFFSET, col*height + Constants.HABITS_Y_OFFSET, width, height);
        this.habitsRect.setStroke(stroke);
        this.habitsRect.setFill(fill);
        HabitsPane.getChildren().add(habitsRect);

    }

    //set the habit by creating the checkbox and adding it to the main pane
    public void setHabit(String habit, int x, int y, int col){
        CheckBox habitLabel = new CheckBox(habit);
        this.currentCheckedBox = habitLabel;
        habitLabel.setTextFill(Color.BLACK);
        habitLabel.setLayoutX(x + Constants.SETHABIT_OFFSET);
        habitLabel.setLayoutY(y + Constants.SETHABIT_OFFSET);
        HabitsPane.getChildren().add(habitLabel);
        this.checkedBoxes[col] = this.currentCheckedBox;
    }

    //sets the title for the habits list: "Habits"
    public Label setTitle(){
        Label title = new Label(Constants.HABIT_TITLE);
        title.setLayoutX(10);
        title.setLayoutY(10);
        title.setStyle("-fx-font: italic bold 36px arial;-fx-text-fill: rgba(0,0,0,1.00)");
        return title;
    }
//SET THIS ACTION ON KEY INPUT SHIFT - clears the checks in the checkBoxes
    public void clearChecks(){
        for(int i = 0; i < this.checkedBoxes.length; i++){
            this.checkedBoxes[i].setSelected(false);

            //update the fill array for the checked box accordingly
            for(int k = 0; k < calendar.getDaCircles().length; k++){
                calendar.getSelectedSquare().changeFill(0,k);
            }
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