package Oving4.Opg1;

public class Josepheus {

    public static void main(String[] args) {

        int soldiers = 41;
        int interval = 3;

        CircularLinkedList cll = new CircularLinkedList();

        //Fill the list with data
        for (int i = 1; i <= soldiers; i++) {
            cll.add(i);
        }

        //Display the original
        cll.display();

        System.out.println("Killing soldiers");
        cll.killSoldiers(interval);

        //Display the new list
        cll.display();


    }
}
