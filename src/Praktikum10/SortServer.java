package Praktikum10;

import java.util.*;

public class SortServer implements CommandExecutor{

    private final Random random = new Random();
    private static final int MAX_NUMBER = 1000000;

    @Override
    public String execute(String command) throws Exception {
        String[] commands = command.split(" ");
        String sortMethod = commands[0].toUpperCase();
        String result = null;
        if(sortMethod.equals("COMPARE")) {
            int arraySizeUpperBound = Integer.parseInt(commands[1]);
            int stepSize = Integer.parseInt(commands[2]);
            compareSortingMethods(arraySizeUpperBound, stepSize);
        }else {
            int sizeOfList = Integer.parseInt(commands[1]);
            int[] nbrArray= generateArray(sizeOfList);
            double time;
            switch (sortMethod) {
                case "BUBBLE" -> {
                    time = measureSortTime(nbrArray, "BUBBLE");
                    bubbleSort(nbrArray);
                }
                case "SELECTION" -> {
                    time = measureSortTime(nbrArray, "SELECTION");
                    selectionSort(nbrArray);
                }
                case "INSERTION" -> {
                    time = measureSortTime(nbrArray, "INSERTION");
                    insertionSort(nbrArray);
                }
                default -> {
                    time = 0;
                    sortMethod = "NOT AVAILABLE";
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Selected Sort was ").append(sortMethod).append("\n");
            sb.append("Array size: ").append(sizeOfList).append("\n");
            sb.append(time).append("\n");
            sb.append("Resulting array: ").append(Arrays.toString(nbrArray)).append("\n");
            sb.append("Sorted correctly: ").append(checkSort(nbrArray)).append("\n");
            result = sb.toString();
        }
        return result + "\n";
    }

    private void compareSortingMethods(int arraySizeUpperBound, int stepSize) {
        for(int i = 0; i <= arraySizeUpperBound; i+=stepSize) {
            int[] nbrArray= generateArray(i);
            double time1 = measureSortTime(nbrArray, "BUBBLE");
            double time2 = measureSortTime(nbrArray, "SELECTION");
            double time3 = measureSortTime(nbrArray, "INSERTION");
            System.out.println(i + ";" + time1 + ";" + time2 + ";" + time3);
        }
    }

    private int[] generateArray(int sizeOfList) {
        int[] nbrArray = new int[sizeOfList];
        for(int i = 0; i < sizeOfList; i++) {
            nbrArray[i] = (random.nextInt(MAX_NUMBER + 1));
        }
        return nbrArray;
    }

    private double measureSortTime(int[] nbrArray, String sortMethod) {
        long end, start = System.currentTimeMillis();
        int count = 0;
        do {
            int[] copyArray = new int[nbrArray.length];
            System.arraycopy(nbrArray, 0, copyArray, 0, nbrArray.length);
            switch (sortMethod) {
                case "BUBBLE" -> bubbleSort(copyArray);
                case "SELECTION" -> selectionSort(copyArray);
                case "INSERTION" -> insertionSort(copyArray);
            }
            count++;
            end = System.currentTimeMillis();
        } while (end - start < 1000);
        return (double)(end-start)/count;
    }

    private void bubbleSort(int[] nbrArray) {
        for(int k = nbrArray.length -1; k > 0; k --) {
            boolean noSwap = true;
            for (int i = 0; i < k; i ++) {
                if ( nbrArray[i] > nbrArray[i+1]) {
                    swap(nbrArray, i, i+1);
                    noSwap = false;
                }
            }
            if(noSwap) break;
        }
    }

    private void selectionSort(int[] nbrArray) {
        for(int k = 0; k < nbrArray.length; k++) {
            int min = k;
            for(int i = k+1; i < nbrArray.length; i++) {
                if(nbrArray[i] < nbrArray[min]) min = i;
            }
            if (min != k) {
                swap(nbrArray, min , k);
            }
        }
    }

    private void insertionSort(int[] nbrArray) {
        for(int k = 1; k < nbrArray.length; k++) {
            int temp = nbrArray[k];
            int i;
            for(i = k; ((i > 0) && (nbrArray[i-1] > temp)); i--) {
                nbrArray[i] = nbrArray[i-1];
            }
            nbrArray[i] = temp;
        }
    }

    private void swap(int[] nbrArray, int i, int k) {
        int temp = nbrArray[i];
        nbrArray[i] = nbrArray[k];
        nbrArray[k] = temp;
    }

    private boolean checkSort(int[] nbrArray) {
        boolean sorted = true;
        for(int i = 0; i < nbrArray.length -1; i++) {
            if (nbrArray[i] > nbrArray[i+1]) {
                sorted = false;
                break;
            }
        }
        return sorted;
    }
}
