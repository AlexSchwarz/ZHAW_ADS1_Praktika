package Praktikum02;

public class Main {
    public static void main(String[] args) {
        MyList myList = new MyList();

        myList.add("first");
        myList.add("second");
        myList.add("third");
        myList.add("fourth");
        myList.add("fifth");
        myList.add("sixth");

        System.out.println(myList.size());

        myList.print();

        System.out.println("***");
        iter(0);
        System.out.println("***");
        pR(0);




    }

    public static void iter(int i) {
        while(i <= 10) {
            System.out.println(i);
            i ++;
        }
    }

    public static void pR(int i) {
        if(i <= 10) {
            System.out.println(i);
            pR(i + 1);
        }
    }
}
