import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

//TODO: need to be able to save and load the images from before
// - the habits, the text from the daily pane, the marked circles on the calendar and each of the 12 panes, the month the calendar was flipped to

public class PaneOrganizer {

    private BorderPane root;
    private VBox buttonPane;
    private Calendar calendar;
    private Habits habit;
    private Daily daily;

//TODO: make forawrd back and clear for the dailyPane and the CalendarPane
    public PaneOrganizer(){

        this.root = new BorderPane();
        Pane DailyPane = new Pane();
        Pane HabitsPane = new Pane();
        Pane CalendarPane = new Pane();

        this.calendar = new Calendar(CalendarPane);
        this.habit = new Habits(HabitsPane);
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
}
