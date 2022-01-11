import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.application.Application;

/*
This class launches the application, titles it schedule and creates a new instance of paneOrganizer
 */

public class App extends Application{
    @Override
    public void start(Stage stage) {

        PaneOrganizer organizer = new PaneOrganizer();
        Scene scene = new Scene(organizer.getRoot(), Constants.APP_WIDTH,
                Constants.APP_HEIGHT);
        stage.setScene(scene);
        stage.setTitle(Constants.APP_TITLE);
        stage.show();


    }
}
//TODO: save circles - maybe build a wrapper class for the circles?
//TODO: change the numbers each flip of the month - maybe save-able interface, then you can flip between save-ables?
//TODO: make the text editable from habits and daily
//TODO: be able to save the information - look into saving and loading
//TODO: look for repeated code and try to condense
//TODO: look into using enums, lots of strings no storage
//TODO: go through the rest of the to dos throughout the code and do them if they're still relevant
//TODO: make it adjustible to the year, right now only works for the year 2022, make it so that you can enter when the year
//starts and whether its a leap year and it will automatically generate the new calendar, more extensible version