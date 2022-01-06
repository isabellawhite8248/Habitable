import javafx.scene.paint.Color;

public class Constants {

    //TODO: at the end of the project go through and make sure a. all constants and necessary and b. no integers/doubles exist which could have been constants
    //TODO: make sure that everything has comments and there is a README

    public static double APP_HEIGHT = 500;
    public static double APP_WIDTH = 1500;
    public static double DAILY_WIDTH = 800;
    public static double DAILY_HEIGHT = 500;
    public static double CALENDAR_DIMENSION = 500;
    public static double HABITS_WIDTH = 200;
    public static double HABITS_HEIGHT = 500;
    public static double BACKWARDS_BUTTON_XCOORD = 10;
    public static double FORWARDS_BUTTON_XCOORD = BACKWARDS_BUTTON_XCOORD + 60;
    public static int CAL_ROW_NUM = 7;
    public static int CAL_COL_NUM = 5;
    public static int CALENDAR_SQUARE_ARRAY_LENGTH = CAL_ROW_NUM*CAL_COL_NUM;

    public static String DAILY_COLOR = "black";
    public static String HABITS_COLOR = "grey";
    public static String CALENDAR_COLOR = "white";

    public static Color INVISIBLE = Color.color(0,0,0,0);

    public static int CALENDAR_SQUARE_DIMENSION = 70;

    //things depending on the month
    public static String MONTH = "January";
    public static int NUM_OF_DAYS = 31;
    public static String STARTING_DAY = "Sat";
    //switch month button that sets it to a new thing


}