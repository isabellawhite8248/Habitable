import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
The daily class handles the center daily pane and has a 24 hour schedule which certain events can be entered
eventually hopefully the used can edit it on the screen
 */

public class Daily {

    private Pane DailyPane;

    public Daily(Pane DailyPane){

        this.DailyPane = DailyPane;

        for(int row = 0; row < 24; row++){
            //TODO: remove constants

            //loops through the task grid and adds the appropriate task per each cell
            Rectangle rect = new Rectangle(0,row*Constants.DAILY_CELL_HEIGHT,Constants.DAILY_WIDTH,Constants.DAILY_CELL_HEIGHT);
            rect.setStroke(Color.WHITE);
            DailyPane.getChildren().add(rect);
            switch(row){

                case 0:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_0, Constants.DAILY_TASK_0, row);
                    break;

                case 1:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_1, Constants.DAILY_TASK_1, row);
                    break;

                case 2:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_2, Constants.DAILY_TASK_2, row);
                    break;

                case 3:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_3, Constants.DAILY_TASK_3, row);
                    break;

                case 4:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_4, Constants.DAILY_TASK_4, row);
                    break;

                case 5:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_5, Constants.DAILY_TASK_5, row);
                    break;

                case 6:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_6, Constants.DAILY_TASK_6, row);
                    break;

                case 7:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_7, Constants.DAILY_TASK_7, row);
                    break;

                case 8:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_8, Constants.DAILY_TASK_8, row);
                    break;

                case 9:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_9, Constants.DAILY_TASK_9, row);
                    break;

                case 10:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_10, Constants.DAILY_TASK_10, row);
                    break;

                case 11:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_11, Constants.DAILY_TASK_11, row);
                    break;

                case 12:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_12, Constants.DAILY_TASK_12, row);
                    break;

                case 13:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_13, Constants.DAILY_TASK_13, row);
                    break;

                case 14:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_14, Constants.DAILY_TASK_14, row);
                    break;

                case 15:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_15, Constants.DAILY_TASK_15, row);
                    break;

                case 16:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_16, Constants.DAILY_TASK_16, row);
                    break;

                case 17:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_17, Constants.DAILY_TASK_17, row);
                    break;

                case 18:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_18, Constants.DAILY_TASK_18, row);
                    break;

                case 19:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_19, Constants.DAILY_TASK_19, row);
                    break;

                case 20:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_20, Constants.DAILY_TASK_20, row);
                    break;

                case 21:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_21, Constants.DAILY_TASK_21, row);
                    break;

                case 22:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_22, Constants.DAILY_TASK_22, row);
                    break;

                case 23:
                    this.setDailyTask(Constants.DAILY_TASK_TIME_23, Constants.DAILY_TASK_23, row);
                    break;

            }
        }
    }

    //sets up the task label
    public void setDailyTask(String time, String dailyTask, int y){

        //TODO: set label parameters like width and height to make sure it doesn't go off the screen
        //TODO: make the user able to edit the labels

        Label label = new Label(time + ":" + dailyTask);
        label.setTextFill(Color.WHITE);
        label.setLayoutY(y*Constants.DAILY_CELL_HEIGHT);
        DailyPane.getChildren().add(label);
    }

}
