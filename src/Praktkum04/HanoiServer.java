package Praktkum04;

public class HanoiServer implements CommandExecutor {

    StringBuilder sb = new StringBuilder();

    public String moveDisk(int n, char from, char to, char help) {
        if(n > 0) {
            moveDisk(n-1, from, help, to);
            sb.append("bewege " + from + " nach " + to + "\n");
            moveDisk(n - 1, help, to, from);
        }
        return sb.toString();
    }

    /**
     * execute -- nimmt eine Kommandozeile, tut irgendetwas gescheites, und
     * berichtet das Resultat.
     *
     * @param command Kommandozeile
     * @return Resultat, ueblicherweise eine oder mehrere Zeilen.
     */
    @Override
    public String execute(String command) throws Exception {
        return moveDisk(Integer.parseInt(command), 'A', 'B', 'C');
    }
}
