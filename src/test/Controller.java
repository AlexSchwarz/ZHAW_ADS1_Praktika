package test;

public class Controller {

    Box<?> box;

    Box<Integer> bI = new Box<Integer>();

    Box<Double> bD = new Box<Double>();

    public void start() {
        box = bI;
    }

    public void printBox(Box<?> box) {
        System.out.println(box);
    }
}
