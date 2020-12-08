package Praktikum12;

import javax.naming.PartialResultException;

public class NaiverParallelQuicksort extends Thread {

    private final int SPLIT_THRESHOLD = 100000;
    private final int left;
    private final int right;
    private final int[] array;

    public NaiverParallelQuicksort(int left, int right, int[] array) {
        super();
        this.left = left;
        this.right = right;
        this.array = array;
    }

    public static void sort(int[] array) {
        Thread root = new NaiverParallelQuicksort(0, array.length-1, array);
        root.start();
        try {
            root.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void run() {
        int mid = 0;
        Thread thread1 = null;
        Thread thread2 = null;

        if(left < right) {
            mid = partition(array, left, right);
            if( mid -left > SPLIT_THRESHOLD) {
                thread1 = new NaiverParallelQuicksort(left, mid - 1, array);
                thread1.start();
            }else {
                quickSort(array, left, mid-1);
            }
            if(right -mid > SPLIT_THRESHOLD) {
                thread2 = new NaiverParallelQuicksort(mid, right, array);
                thread2.start();
            }else {
                quickSort(array, mid, right);
            }
            try {
                if (thread1 != null) thread1.join();
                if (thread2 != null) thread2.join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
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

    private static void swap(int[] nbrArray, int i, int k) {
        int temp = nbrArray[i];
        nbrArray[i] = nbrArray[k];
        nbrArray[k] = temp;
    }
}
