package Praktikum05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RankingServer implements CommandExecutor {

    private List<Competitor> readData(String string) {
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
        for(int i = 0; i < competitorList.size(); i++) {
            competitorList.get(i).setRank(i+1);
        }
    }

    @Override
    public String execute(String command) {
        List<Competitor> competitorList = readData(command);
        SortedBinaryTree<Competitor> sortedBinaryTree = new SortedBinaryTree<>();
        for(Competitor competitor : competitorList) {
            sortedBinaryTree.add(competitor);
        }
        System.out.println(sortedBinaryTree.size());
        System.out.println(sortedBinaryTree.height());
        return sortedBinaryTree.printTree();
    }
}
