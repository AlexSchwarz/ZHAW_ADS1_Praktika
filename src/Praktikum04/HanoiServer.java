package Praktikum04;

public class HanoiServer implements CommandExecutor{

    void moveDisc(int n, char from, char to, char help) {
        if( n > 0) {
            moveDisc(n-1, from, help, to);
            System.out.println("bewege " + from + " nach " + to);
            moveDisc(n-1, help, to, from);
        }
    }

    @Override
    public String execute(String command) throws Exception {
        System.out.println("*****************");
        moveDisc(Integer.parseInt(command), 'A', 'C', 'B');
        return null;
    }
}
