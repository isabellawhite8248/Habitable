
import javafx.scene.paint.Color;

public class Habit {

    private Color color;
    private Boolean isChecked;
    private String habitLabel;

    public Habit(Color color, Boolean isChecked, String habitLabel){

        this.color = color;
        this.isChecked = isChecked;
        this.habitLabel = habitLabel;

    }

    //TODO: check if any of these methods are extraneous and delete if they are
    public String getHabitLabel(){
        return habitLabel;
    }
    public Color getHabitColor(){return color;}
    public Boolean getCheck(){
        return isChecked;
    }
    public void setCheck(Boolean check){
        isChecked = check;
    }
}
