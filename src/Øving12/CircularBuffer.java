package Øving12;

/**
 * * Java Program to implement Circular Buffer
 **/

import java.util.ArrayList;

/** Class Circular Buffer **/
class CircularBuffer {
    private int maxSize;
    private int front = 0;
    private int rear = 0;
    private int bufLen = 0;
    private char[] buf;

    /** constructor **/
    public CircularBuffer(int size) {
        maxSize = size;
        front = rear = 0;
        rear = 0;
        bufLen = 0;
        buf = new char[maxSize];
    }

    /** function to get size of buffer **/
    public int getSize() {
        return bufLen;
    }

    /** function to clear buffer **/
    public void clear() {
        front = rear = 0;
        rear = 0;
        bufLen = 0;
        buf = new char[maxSize];
    }

    /** function to check if buffer is empty **/
    public boolean isEmpty() {
        return bufLen == 0;
    }

    /** function to check if buffer is full **/
    public boolean isFull() {
        return bufLen == maxSize;
    }

    /** insert an element **/
    public void insert(char c) {
        if (isFull()) {
            delete();
        }
        bufLen++;
        rear = (rear + 1) % maxSize;
        buf[rear] = c;

    }

    /** delete an element **/
    public char delete() {
        if (!isEmpty()) {
            bufLen--;
            front = (front + 1) % maxSize;
            return buf[front];
        } else {
            System.out.println("Error : Underflow Exception");
            return ' ';
        }
    }

    /** function to print buffer **/
    public void display() {
        System.out.print("\nBuffer : ");
        for (int i = 0; i < maxSize; i++)
            System.out.print(buf[i] + " ");
        System.out.println();
    }

    public ArrayList<Integer> findLetterAll(char search) {
        ArrayList<Integer> posisjoner = new ArrayList<Integer>();
        for (int i = 0; i < maxSize; i++) {
            if (buf[i] == search) posisjoner.add(i);
        }
        return posisjoner;
    }

    /*
    public ArrayList<Integer> findLetter(char search, ArrayList<Integer> positions) {
        ArrayList<Integer> posNew = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            if (buf[(positions.get(i) + 1) % maxSize] == search) posNew.add(positions.get(i) + 1);
        }
        return posNew;
    }
     */

    public ArrayList<Integer> findLetter(char search, ArrayList<Integer> positions) {
        ArrayList<Integer> posNew = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            if (buf[(positions.get(i) + 1) % maxSize] == search) posNew.add(positions.get(i) + 1);
        }
        return posNew;
    }

    public int returnPosition(int length, ArrayList<Integer> positions) {
        int pos = positions.get(0);
        int rear2 = rear % maxSize;
        if (pos - length > 0) {
            pos = pos - length;
        } else {
            pos = maxSize + pos - length;
        }
        if (rear2 - pos > 0) {
            return rear2 - pos;
        } else {
            return rear2 + maxSize - pos;
        }
    }
}

