package Praktikum12;

import java.util.Arrays;
import java.util.Random;

public class QuickerSort{

    private static final int MAX_ARRAY_VALUE = 1000000;
    private static final int MAX_THRESHOLD = 200;
    private static final int DEFAULT_THRESHOLD = 50;
    private static final int ARRAY_SIZE = 10000;
    private static final Random random = new Random();
    private static int threshold;

    public static void main(String[] args) {
        //Aufgabe 1:
            sort();

        //Aufgabe 2:
            //int bestThreshold = compareSortingTimeBasedOnThresholdSize(ARRAY_SIZE, MAX_THRESHOLD);
            //System.out.println("The threshold with the shortest sort-time is: " + bestThreshold);

            //Optimal bei Threshold = 26 (Siehe .PNG)

        //Aufgabe 3:
            sortWithThread();
    }

    private static void sortWithThread() {
        int[] array = generateArray(ARRAY_SIZE);
        NaiverParallelQuicksort.sort(array);
        System.out.println("Is sorted: " + checkSort(array));
    }

    private static void sort() {
        int[] array = generateArray(ARRAY_SIZE);
        int leftIndex = 0;
        int rightIndex = array.length -1;
        threshold = DEFAULT_THRESHOLD;
        quickerSort(array, leftIndex, rightIndex);
        System.out.println("Is sorted: " + checkSort(array));
    }

    private static void quickerSort(int[] array, int left, int right) {
        if(right - left < threshold) {
            insertionSort(array, left, right);
        }else {
            int l = partition(array, left, right);
            quickSort(array, left,  l- 1);
            quickSort(array, l, right);
        }
    }

    private static void quickSort(int[] a, int left, int right) {
        if(left < right) {
            int mid = partition(a, left, right);
            quickSort(a, left,  mid- 1);
            quickSort(a, mid, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[(left + right)/2];
        while(left <= right) {
            while (arr[left] < pivot) left++;
            while(arr[right] > pivot) right --;
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    private static int compareSortingTimeBasedOnThresholdSize(int arraySize, int maxThreshold) {
        int thresholdWithShortestTime = -1;
        double shortestTime = 100000;
        for(int i = 0; i <= maxThreshold; i++) {
            int[] nbrArray= generateArray(arraySize);
            threshold = i;
            double time1 = measureSortTime(nbrArray);
            if(time1 <= shortestTime) {
                shortestTime = time1;
                thresholdWithShortestTime = i;
            }
            System.out.println(i + ";" + time1);
        }
        return thresholdWithShortestTime;
    }

    private static void insertionSort(int[] nbrArray, int left, int right) {
        int[] copy = Arrays.copyOfRange(nbrArray, left, right);
        for(int k = 1; k <= copy.length-1; k++) {
            int temp = copy[k];
            int i;
            for(i = k; ((i > 0) && (copy[i-1] > temp)); i--) {
                copy[i] = copy[i-1];
            }
            copy[i] = temp;
        }
        System.arraycopy(copy, 0, nbrArray, left, right-left+1);
    }

    private static double measureSortTime(int[] nbrArray) {
        long end, start = System.currentTimeMillis();
        int count = 0;
        do {
            int[] copyArray = new int[nbrArray.length];
            System.arraycopy(nbrArray, 0, copyArray, 0, nbrArray.length);
            quickerSort(copyArray, 0, copyArray.length - 1);
            count++;
            end = System.currentTimeMillis();
        } while (end - start < 1000);
        return (double)(end-start)/count;
    }

    private static int[] generateArray(int sizeOfList) {
        int[] nbrArray = new int[sizeOfList];
        for(int i = 0; i < sizeOfList; i++) {
            nbrArray[i] = (random.nextInt(MAX_ARRAY_VALUE + 1));
        }
        return nbrArray;
    }

    private static void swap(int[] nbrArray, int i, int k) {
        int temp = nbrArray[i];
        nbrArray[i] = nbrArray[k];
        nbrArray[k] = temp;
    }

    private static boolean checkSort(int[] nbrArray) {
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
