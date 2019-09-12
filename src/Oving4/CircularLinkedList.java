package Oving4;

public class CircularLinkedList {

    //Declaring head and tail pointer as null.
    public Node head = null;
    public Node tail = null;

    //This function will add the new node at the end of the list.
    public void add(int data) {
        //Create new node
        Node newNode = new Node(data);
        //Checks if the list is empty.
        if (head == null) {
            //If list is empty, both head and tail would point to new node.
            head = newNode;
            tail = newNode;
            newNode.next = head;
        } else {
            //tail will point to new node.
            tail.next = newNode;
            //New node will become new tail.
            tail = newNode;
            //Since, it is circular linked list tail will point to head.
            tail.next = head;
        }
    }
    public void deleteEnd() {
        //Checks whether list is empty
        if(head == null) {
            return;
        }
        else {
            //Checks whether contain only one element
            if(head != tail ) {
                Node current = head;
                //Loop will iterate till the second last element as current.next is pointing to tail
                while(current.next != tail) {
                    current = current.next;
                }
                //Second last element will be new tail
                tail = current;
                //Tail will point to head as it is a circular linked list
                tail.next = head;
            }
            //If the list contains only one element
            //Then it will remove it and both head and tail will point to null
            else {
                head = tail = null;
            }
        }
    }
    public void remove(int key) {
        if (head == null) {
           head = null;
        }

        // Find the required node
        Node curr = head, prev = new Node(0);
        while (curr.data != key) {
            if (curr.next == head) {
                System.out.printf("\nGiven node is not found"
                        + " in the list!!!");
                break;
            }
            prev = curr;
            curr = curr.next;
        }

        // Check if node is only node
        if (head == tail) {
            head = tail = null;
        }

        // If more than one node, check if
        // it is first node
        if (curr == head) {
            prev = head;
            while (prev.next != head)
                prev = prev.next;
            head = curr.next;
            prev.next = head;
        }

        // check if node is last node
        else if (curr.next == head) {
            prev.next = head;
        } else {
            prev.next = curr.next;
        }

        if (curr.next == tail) {
            tail = curr;
        }
    }

    //Displays all the nodes in the list
    public void display() {
        Node current = head;
        if (head == null) {
            System.out.println("List is empty");
        } else {
            System.out.println("Nodes of the circular linked list: ");
            do {
                //Prints each node by incrementing pointer.
                System.out.print(" " + current.data);
                current = current.next;
            } while (current != head);
            System.out.println();
        }
    }

    public void killSoldiers(int intervall) {
        Node current = tail;
        if (head == null) {
            System.out.println("List is empty");
        } else {
            do {
                for (int i = 0; i < intervall; i++) {
                    current = current.next;
                }
                System.out.println("Removing: " + current.getData()+"\n");
                remove(current.getData());
                display();
            } while (sizeOfList() > 1);
        }
    }

    public int sizeOfList() {
        int length = 1;
        if (head == null) {
            return 0;
        }
        Node temp = head.getNext();
        while (temp != head) {
            temp = temp.getNext();
            length++;
        }
        return length;
    }


}


