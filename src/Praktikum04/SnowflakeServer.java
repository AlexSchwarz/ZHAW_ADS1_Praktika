package Praktikum04;

public class SnowflakeServer implements CommandExecutor{

    private Turtle turtle;
    @Override
    public String execute(String command) {
        turtle = new Turtle();
        turtle.move(0.5);
        turtle.turn(90);
        turtle.move(0.5);
        generateCommands(3,0.4);
        turtle.turn(240);
        generateCommands(3,0.4);
        turtle.turn(240);
        generateCommands(3,0.4);
        String trace = turtle.getTrace();
        System.out.println(trace);
        return trace;
    }

    public void generateCommands(int stufe, double dist) {
        if( stufe == 0) {
            turtle.move(dist);
        } else {
            stufe--;
            dist = dist/3;
            generateCommands(stufe, dist);
            turtle.turn(60);
            generateCommands(stufe, dist);
            turtle.turn(-120);
            generateCommands(stufe, dist);
            turtle.turn(60);
            generateCommands(stufe, dist);
        }
    }
}
