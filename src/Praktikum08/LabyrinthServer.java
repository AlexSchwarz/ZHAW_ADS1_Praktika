package Praktikum08;


import java.awt.*;
import java.util.Scanner;

public class LabyrinthServer implements CommandExecutor{

    private static final double SCALE = 11;

    private void drawPath(ServerGraphics g, String from, String to, boolean mouse) {
        double xh0 = from.charAt(0) - '0';
        double yh0 = from.charAt(2) - '0';
        double xh1 = to.charAt(0) - '0';
        double yh1 = to.charAt(2) - '0';
        double x0 = Math.min(xh0,xh1)/SCALE;
        double y0 = Math.min(yh0,yh1)/SCALE;
        double x1 = Math.max(xh0,xh1)/SCALE;
        double y1 = Math.max(yh0,yh1)/SCALE;
        double w = 1/SCALE;
        if (mouse) g.drawLine(x0+w/2,y0+w/2,x1+w/2,y1+w/2);
        else {
            if (y0 == y1)
                g.fillRect(x0,y0,x1-x0+w,w);
            else
                g.fillRect(x0,y0,w,y1-y0+w);
        }
    }

    @Override
    public String execute(String command) throws Exception {
        ServerGraphics serverGraphics = new ServerGraphics();
        serverGraphics.setColor(Color.white);
        try(Scanner scanner = new Scanner(command)) {
            while(scanner.hasNext()) {
                String[] arguments = scanner.nextLine().split(" ");
                drawPath(serverGraphics, arguments[0], arguments[1], false);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return serverGraphics.getTrace();
    }
}
