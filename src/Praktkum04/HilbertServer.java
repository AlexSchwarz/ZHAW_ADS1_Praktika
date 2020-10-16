package Praktkum04;

public class HilbertServer implements CommandExecutor {
    private Turtle turtle;

    @Override
    public String execute(String command) throws Exception {
        int depth = Integer.parseInt(command);
        double dist = 0.8 / (Math.pow(2,depth+1)-1);
        turtle = new Turtle(0.1, 0.1);
        hilbert(depth, dist, -90);
        return turtle.getTrace();
    }

    private void hilbert(int depth, double dist, double angle) {
        if(depth < 0) return;
        turtle.turn(-angle); //left
        hilbert(depth -1, dist, -angle);
        turtle.move(dist);
        turtle.turn(angle); //right
        hilbert(depth -1, dist, angle);
        turtle.move(dist);
        hilbert(depth -1, dist, angle);
        turtle.turn(angle); //right
        turtle.move(dist);
        hilbert(depth -1, dist, -angle);
        turtle.turn(-angle); //left
    }
}
