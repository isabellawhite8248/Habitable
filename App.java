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
        stage.setTitle("Schedule");
        stage.show();


    }
}

//TODO: make it so it changes the numbers and days starting of the month with the flipping of pages and that it retains the circles
//TODO: make the text editable from habits and daily
//TODO: be able to save the information - look into saving and loading
//info to save: dots, text entered in daily and habits, don't think checked boxes need to be preserved
//TODO: look for repeated code and try to condense
//TODO: look into using enums
//TODO: go through the rest of the to dos throughout the code and do them if they're still relevant
//TODO: comments and read me