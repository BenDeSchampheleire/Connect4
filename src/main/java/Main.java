public class Main {
    public static void main(String[] args) {

        Grid g1 = new Grid(7, 6);

        g1.display_grid();

        g1.play_checker(4,"red");
        g1.play_checker(4,"yellow");
        g1.play_checker(4,"yellow");
        g1.play_checker(4,"yellow");
        g1.play_checker(1,"red");
        g1.play_checker(2,"red");
        g1.play_checker(3,"red");
        g1.play_checker(3,"yellow");
        g1.play_checker(3,"yellow");
        g1.play_checker(2,"yellow");
        g1.play_checker(2,"yellow");
        //g1.play_checker(2,"yellow");
        g1.play_checker(2,"yellow");
        g1.play_checker(2,"yellow");
        g1.play_checker(5,"yellow");

        g1.display_grid();

        System.out.println(g1.checkWin("red"));
        System.out.println(g1.checkWin("yellow"));

        System.out.println(g1.getGrid().get(1).check_full());

        System.out.println(g1.getGrid().get(0).getColumn().get(0).getColor());
        System.out.println(g1.getGrid().get(0).getColumn().get(0).getPosition());
    }
}
