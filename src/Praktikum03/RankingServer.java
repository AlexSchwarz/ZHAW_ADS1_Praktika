package Praktikum03;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RankingServer implements CommandExecutor {

    private List<Competitor> readData(String string) {
        System.out.println("Starting readData...");
        List<Competitor> competitorList = new ArrayList<>();
        Reader inputString = new StringReader(string);
        BufferedReader bufferedReader = new BufferedReader(inputString);
        try {
            String sourceLine = bufferedReader.readLine();
            while (sourceLine != null) {
                competitorList.add(parseStringToCompetitor(sourceLine));
                sourceLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("End readData...");
        return competitorList;
    }

    private Competitor parseStringToCompetitor(String competitorCSV) {
        String[] arr = competitorCSV.split(";");
        Competitor competitor = new Competitor(Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]), arr[3], arr[4]);
        return competitor;
    }

    private void printList(List<Competitor> competitorList) {
        for(Competitor competitor : competitorList) {
            System.out.println(competitor);
        }
    }

    private String listToString(List<Competitor> competitorList) {
        StringBuilder sb = new StringBuilder();
        for(Competitor competitor : competitorList) {
            sb.append(competitor.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    private void iterateAndSetRank(List<Competitor> competitorList) {
        for(int i = 0; i < competitorList.size(); i ++) {
            competitorList.get(i).setRank(i);
        }
    }

    @Override
    public String execute(String command) {
        List<Competitor> competitorList = readData(command);
        Collections.sort(competitorList, new ComparatorSortByTime());
        iterateAndSetRank(competitorList);
        Collections.sort(competitorList, new ComparatorSortByName());
        //printList(competitorList);
        return listToString(competitorList);
    }
}
