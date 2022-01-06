import javafx.scene.shape.Circle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StoreIt {

    //an array of instances of this class in the pane organizer to store necessary circle information

    private String month;

    private ArrayList<Circle> allCirclesInGrid;

    public StoreIt(String month){

        this.month = month;

        this.allCirclesInGrid = new ArrayList<>();


    }
//
    public void addCircle(Circle[] circles){
        for(int i = 0; i < circles.length; i++){
            this.allCirclesInGrid.add(circles[i]);
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
