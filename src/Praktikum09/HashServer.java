package Praktikum09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.*;

public class HashServer implements CommandExecutor {

    private static final Map<String, Competitor> map = new HashMap<>();

    @Override
    public String execute(String command) throws Exception {

        //todo: command or not?
        //todo: if not command read file
        //todo: else if command execute command

        String message = "No competitor found";
        if(command.toUpperCase().startsWith("GET")) {
            System.out.println(map);
            String string = command.replace("GET", " ").trim();
            Competitor competitor = map.get(string);
            if(competitor == null) {
                message = "No competitor found matching: " + string;
                System.out.println(message);
            }else {
                message = competitor.getKeyString() + " -> " + competitor.toString();
            }
        }else {
            int entryCounter = 0;
            try (Scanner scanner = new Scanner(command)) {
                while (scanner.hasNext()) {
                    String[] args = scanner.nextLine().split(";");
                    Competitor competitor = new Competitor(Integer.parseInt(args[0]), args[1], Integer.parseInt(args[2]), args[3], args[4]);
                    map.put(competitor.getName() + ";" + competitor.getJg(), competitor);
                    entryCounter++;
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            System.out.println(map);
            message = "Loaded -> " + entryCounter;

        }
        return message + "\n";
    }
}
