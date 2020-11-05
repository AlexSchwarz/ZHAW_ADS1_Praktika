package Praktikum08;

import java.awt.*;

public class TestGraphicServer implements CommandExecutor {

    private static final ServerGraphics serverGraphics = new ServerGraphics();

    public String execute(String command) {
        serverGraphics.setColor(Color.red);
        serverGraphics.drawRect(0,0,0.5,0.5);
        serverGraphics.setColor(Color.black);
        serverGraphics.drawLine(0,0,1,0);
        double height = Math.sqrt(3)/2;
        serverGraphics.drawLine(0,0, 0.5, height);
        serverGraphics.drawLine(0.5, height, 1, 0);
        return serverGraphics.getTrace();
    }

}
