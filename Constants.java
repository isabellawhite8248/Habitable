import javafx.scene.paint.Color;

/*
This is the constants class, constants grouped by type and class
 */

public class Constants {

    //APP CONSTANTS
    public static double APP_HEIGHT = 500;
    public static double APP_WIDTH = 1500;
    public static String APP_TITLE = "Schedule";

    //PANEORGANIZER CONSTANTS
    public static int NUM_MONTHS = 12;
    public static String MONTH_0 = "January";
    public static String MONTH_1 = "Febuary";
    public static String MONTH_2 = "March";
    public static String MONTH_3 = "April";
    public static String MONTH_4 = "May";
    public static String MONTH_5 = "June";
    public static String MONTH_6 = "July";
    public static String MONTH_7 = "August";
    public static String MONTH_8 = "September";
    public static String MONTH_9 = "October";
    public static String MONTH_10 = "November";
    public static String MONTH_11 = "December";

    //DAILY CONSTANTS
    public static double DAILY_WIDTH = 800;
    public static double DAILY_HEIGHT = 500;
    public static double DAILY_CELL_HEIGHT = 18;

    public static String DAILY_TASK_TIME_0 = "1:00am";
    public static String DAILY_TASK_0 = "task";
    public static String DAILY_TASK_TIME_1 = "2:00am";
    public static String DAILY_TASK_1 = "task";
    public static String DAILY_TASK_TIME_2 = "3:00am";
    public static String DAILY_TASK_2 = "task";
    public static String DAILY_TASK_TIME_3 = "4:00am";
    public static String DAILY_TASK_3 = "task";
    public static String DAILY_TASK_TIME_4 = "5:00am";
    public static String DAILY_TASK_4 = "task";
    public static String DAILY_TASK_TIME_5 = "6:00am";
    public static String DAILY_TASK_5 = "task";
    public static String DAILY_TASK_TIME_6 = "7:00am";
    public static String DAILY_TASK_6 = "task";
    public static String DAILY_TASK_TIME_7 = "8:00am";
    public static String DAILY_TASK_7 = "task";
    public static String DAILY_TASK_TIME_8 = "9:00am";
    public static String DAILY_TASK_8 = "task";
    public static String DAILY_TASK_TIME_9 = "10:00am";
    public static String DAILY_TASK_9 = "task";
    public static String DAILY_TASK_TIME_10 = "11:00am";
    public static String DAILY_TASK_10 = "task";
    public static String DAILY_TASK_TIME_11 = "12:00am";
    public static String DAILY_TASK_11 = "task";
    public static String DAILY_TASK_TIME_12 = "1:00pm";
    public static String DAILY_TASK_12 = "task";
    public static String DAILY_TASK_TIME_13 = "2:00pm";
    public static String DAILY_TASK_13 = "task";
    public static String DAILY_TASK_TIME_14 = "3:00pm";
    public static String DAILY_TASK_14 = "task";
    public static String DAILY_TASK_TIME_15 = "4:00pm";
    public static String DAILY_TASK_15 = "task";
    public static String DAILY_TASK_TIME_16 = "5:00pm";
    public static String DAILY_TASK_16 = "task";
    public static String DAILY_TASK_TIME_17 = "6:00pm";
    public static String DAILY_TASK_17 = "task";
    public static String DAILY_TASK_TIME_18 = "7:00pm";
    public static String DAILY_TASK_18 = "task";
    public static String DAILY_TASK_TIME_19 = "8:00pm";
    public static String DAILY_TASK_19 = "task";
    public static String DAILY_TASK_TIME_20 = "9:00pm";
    public static String DAILY_TASK_20 = "task";
    public static String DAILY_TASK_TIME_21 = "10:00pm";
    public static String DAILY_TASK_21 = "task";
    public static String DAILY_TASK_TIME_22 = "11:00pm";
    public static String DAILY_TASK_22 = "task";
    public static String DAILY_TASK_TIME_23 = "12:00pm";
    public static String DAILY_TASK_23 = "task";

    //CALENDAR CONSTANTS
    public static double CALENDAR_DIMENSION = 500;
    public static double BACKWARDS_BUTTON_XCOORD = 10;
    public static double FORWARDS_BUTTON_XCOORD = BACKWARDS_BUTTON_XCOORD + 60;
    public static double SELECT_STROKE_WIDTH = 3.2;
    public static double CALENDAR_STROKE_WIDTH = 1.0;
    public static double FLIP_BUTTON_Y_COORD = 20;

    public static int CAL_ROW_NUM = 7;
    public static int CAL_COL_NUM = 5;
    public static int CALENDAR_SQUARE_ARRAY_LENGTH = CAL_ROW_NUM*CAL_COL_NUM;
    public static int DAYS_OF_WEEK_YCOORD = 70;
    public static int CALENDAR_SQUARE_DIMENSION = 70;
    public static int SQUARE_YOFFSET = 100;

    public static String DAILY_COLOR = "black";
    public static String HABITS_COLOR = "grey";
    public static String CALENDAR_COLOR = "white";

    public static Color INVISIBLE = Color.color(0,0,0,0);

    //CALENDAR SQUARE CONSTANTS
    public static Color DATE_FILL = Color.WHITESMOKE;

    public static double CIRCLE_OFFSET = 3.5;
    public static double Circle_YOFFSET = 15;

    public static int NUM_CIRCLES = 9;
    public static int CIRCLE_RAD = 8;
    public static int LABEL_OFFSET = 5;

    public static String BACKWARDS_BUTTON_LABEL = "back";
    public static String FORWARDS_BUTTON_LABEL = "forward";
    public static String DAY_0 = "Sun";
    public static String DAY_1 = "Mon";
    public static String DAY_2 = "Teus";
    public static String DAY_3 = "Wed";
    public static String DAY_4 = "Thurs";
    public static String DAY_5 = "Fri";
    public static String DAY_6 = "Sat";

    //HABITS CONSTANTS
    public static double HABITS_WIDTH = 200;
    public static double HABITS_HEIGHT = 500;

    public static int HABIT_COLOR_RECT_WIDTH = 20;
    public static int HABIT_COLOR_RECT_HEIGHT = 50;
    public static int HABIT_RECT_WIDTH = 160;
    public static int HABIT_RECT_HEIGHT = 50;
    public static int NUM_HABITS = 8;
    public static int HABITS_X1 = 10;
    public static int HABITS_X2 = 30;
    public static int COL_STAGGER = 50;
    public static int Y_STAGGER = 60;
    public static int HABITS_X_OFFSET = 5;
    public static int HABITS_Y_OFFSET = 60;
    public static int SETHABIT_OFFSET = 5;

    public static String HABIT_0 = "Brush your teeth";
    public static String HABIT_1 = "study cs";
    public static String HABIT_2 = "eat healthy";
    public static String HABIT_3 = "habit 4";
    public static String HABIT_4 = "habit 5";
    public static String HABIT_5 = "habit 6";
    public static String HABIT_6 = "habit 7";
    public static String HABIT_7 = "habit 8";

    public static String HABIT_TITLE = "Habits";
    public static String HABIT_MESSAGE_DEFAULT = "no habit inputed";

    //misc -- contingent on the month selected
    public static String MONTH = "January";
    public static int NUM_OF_DAYS = 31;
    public static String STARTING_DAY = "Sat";


}