import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.ArrayList;

public class StoreIt {

    //an array of instances of this class in the pane organizer to store necessary circle information

    private String month;

    private ArrayList<Circle> allCirclesInGrid;
    private CalendarSquare[] squares;

    public StoreIt(String month, CalendarSquare[] squares){

        this.squares = squares;
        //things to store to recolor circles in a grid:
        //array of calendar squares
        //string of month
        this.month = month;
        this.allCirclesInGrid = new ArrayList<>();

    }
    public String getMonth(){
        return month;
    }
//
    public void addCircle(Circle[] circles){
        for(int i = 0; i < circles.length; i++){
            this.allCirclesInGrid.add(circles[i]);
        }
    }

    public ArrayList<Circle> getAllCircles(){
        return allCirclesInGrid;
    }
    public void color(){
        for(int s = 0; s < squares.length; s++) {

            int[] fills = this.squares[s].getCircleFills();
//            for(int i = 0; i < fills.length; i++){
//                System.out.println(fills[i]);
//            }
            //TODO: issue - the fills are all zero when they should not be, check how the fill array is updated
            for (int i = 0; i < this.squares[s].getCircles().length; i++) {
                Circle[] circle = this.squares[s].getCircles();
                switch (i) {
                    case 0: //this circle will always be invisible no matter what because there are only 8 habits
//                    if(fills[0] == 1){
//
//                    } else{
//
//                    }
                        circle[i].setFill(Constants.INVISIBLE);
                        break;
                    case 1:
                        if (fills[1] == 1) {
                            circle[i].setFill(Color.RED);
                            System.out.println("circle filled red");

                        } else {
                            circle[i].setFill(Constants.INVISIBLE);
                        }
                        break;
                    case 2:
                        if (fills[2] == 1) {
                            circle[i].setFill(Color.ORANGE);
                        } else {
                            circle[i].setFill(Constants.INVISIBLE);
                        }
                        break;
                    case 3:
                        if (fills[3] == 1) {
                            circle[i].setFill(Color.YELLOW);
                        } else {
                            circle[i].setFill(Constants.INVISIBLE);
                        }
                        break;
                    case 4:
                        if (fills[4] == 1) {
                            circle[i].setFill(Color.LIME);
                        } else {
                            circle[i].setFill(Constants.INVISIBLE);
                        }
                        break;
                    case 5:
                        if (fills[5] == 1) {
                            circle[i].setFill(Color.DARKGREEN);
                        } else {
                            circle[i].setFill(Constants.INVISIBLE);
                        }
                        break;
                    case 6:
                        if (fills[6] == 1) {
                            circle[i].setFill(Color.BLUE);
                        } else {
                            circle[i].setFill(Constants.INVISIBLE);
                        }
                        break;
                    case 7:
                        if (fills[7] == 1) {
                            circle[i].setFill(Color.BLUEVIOLET);
                        } else {
                            circle[i].setFill(Constants.INVISIBLE);
                        }
                        break;
                    case 8:
                        if (fills[8] == 1) {
                            circle[i].setFill(Color.BLACK);
                        } else {
                            circle[i].setFill(Constants.INVISIBLE);
                        }
                        break;
                }
            }
        }

    }
//
//    public void setMonth(){
//
//    }
//
//
//    public void getCircles(){
//
//    }
}
