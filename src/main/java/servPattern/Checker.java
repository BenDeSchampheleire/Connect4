package servPattern;

/**
 * This class represents the checkers
 *
 */
public class Checker {


    private int position;
    private String color;

    /**
     * Creates a checker object
     * A checker is white during the creation of a column
     * And it becomes red or yellow during the game
     *
     * @param position: index of the checker in the column
     *        color: color of the checker
     *
     */
    public Checker(int position, String color) {

        this.position = position;
        this.color = color;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void display_checker() {
            if (this.getColor().equals("yellow")) {
                System.out.print("y");
            }
            else if (this.getColor().equals("red")) {
                System.out.print("r");
            }
            else {
                System.out.print(".");
            }
    }
}


