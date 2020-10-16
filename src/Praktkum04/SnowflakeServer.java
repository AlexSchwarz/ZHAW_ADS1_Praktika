package Praktkum04;

public class SnowflakeServer implements CommandExecutor {

    private Turtle turtle;

    /**
     * execute -- nimmt eine Kommandozeile, tut irgendetwas gescheites, und
     * berichtet das Resultat.
     *
     * @param command Kommandozeile
     * @return Resultat, ueblicherweise eine oder mehrere Zeilen.
     */
    @Override
    public String execute(String command) throws Exception {
        int  stufe = Integer.parseInt(command);
        turtle = new Turtle();
        turtle.move(0.5);
        turtle.turn(90);
        turtle.move(0.5);
        generateCommand(stufe, 0.5);
        turtle.turn(-120);
        generateCommand(stufe, 0.5);
        turtle.turn(-120);
        generateCommand(stufe, 0.5);
        return turtle.getTrace();
    }

    public void generateCommand(int stufe, double distanz) {
        if (stufe == 0) {
            turtle.move(distanz);
        } else {
            stufe--;
            distanz = distanz / 3;
            generateCommand(stufe, distanz);
            turtle.turn(60);
            generateCommand(stufe, distanz);
            turtle.turn(-120);
            generateCommand(stufe, distanz);
            turtle.turn(60);
            generateCommand(stufe, distanz);
        }
    }
}
