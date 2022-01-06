import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Daily {
//    daily class, daily schedule, can edit with text
//4 columns, text, habit - if applicable and time, and check box
//if the box is checked a color is added to the calendar visualization
    //500/24 = 20.83333, each section should be 19 tall

    private Pane DailyPane;

    public Daily(Pane DailyPane){

        this.DailyPane = DailyPane;

        for(int row = 0; row < 24; row++){
            //TODO: remove constants
            Rectangle rect = new Rectangle(0,row*18,Constants.DAILY_WIDTH,18);
            rect.setStroke(Color.WHITE);
            DailyPane.getChildren().add(rect);
            switch(row){

                case 0:
                    this.setDailyTask("1:00am", "task", row*18);
                    break;

                case 1:
                    this.setDailyTask("2:00am", "task", row*18);
                    break;

                case 2:
                    this.setDailyTask("3:00am", "task", row*18);
                    break;

                case 3:
                    this.setDailyTask("4:00am", "task", row*18);
                    break;

                case 4:
                    this.setDailyTask("5:00am", "task", row*18);
                    break;

                case 5:
                    this.setDailyTask("6:00am", "task", row*18);
                    break;

                case 6:
                    this.setDailyTask("7:00am", "task", row*18);
                    break;

                case 7:
                    this.setDailyTask("8:00am", "task", row*18);
                    break;

                case 8:
                    this.setDailyTask("9:00am", "task", row*18);
                    break;

                case 9:
                    this.setDailyTask("10:00am", "task", row*18);
                    break;

                case 10:
                    this.setDailyTask("11:00am", "task", row*18);
                    break;

                case 11:
                    this.setDailyTask("12:00am", "task", row*18);
                    break;

                case 12:
                    this.setDailyTask("1:00pm", "task", row*18);
                    break;

                case 13:
                    this.setDailyTask("2:00pm", "task", row*18);
                    break;

                case 14:
                    this.setDailyTask("3:00pm", "task", row*18);
                    break;

                case 15:
                    this.setDailyTask("4:00pm", "task", row*18);
                    break;

                case 16:
                    this.setDailyTask("5:00pm", "task", row*18);
                    break;

                case 17:
                    this.setDailyTask("6:00pm", "task", row*18);
                    break;

                case 18:
                    this.setDailyTask("7:00pm", "task", row*18);
                    break;

                case 19:
                    this.setDailyTask("8:00pm", "task", row*18);
                    break;

                case 20:
                    this.setDailyTask("9:00pm", "task", row*18);
                    break;

                case 21:
                    this.setDailyTask("10:00pm", "task", row*18);
                    break;

                case 22:
                    this.setDailyTask("11:00pm", "task", row*18);
                    break;

                case 23:
                    this.setDailyTask("12:00pm", "task", row*18);
                    break;

            }
        }
    }

    public void setDailyTask(String time, String dailyTask, int y){
        //TODO: set label parameters like width and height to make sure it doesn't go off the screen
        //TODO: make the user able to edit the labels

        Label label = new Label(time + ":" + dailyTask);
        label.setTextFill(Color.WHITE);
        label.setLayoutY(y);
        DailyPane.getChildren().add(label);
    }

}
