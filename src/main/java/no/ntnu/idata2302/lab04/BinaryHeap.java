package no.ntnu.idata2302.lab04;

import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<Item extends Comparable<Item>> {
    
    /*
     * FIELDS
     * --------------------------------------------------------------
     */

    /** Array */
    private ArrayList<Item> nodes;



    /*
     * CONSTRUCTORS
     * --------------------------------------------------------------
     */

    public BinaryHeap() {
        this.nodes = new ArrayList<>();
    }


    public BinaryHeap(List<Item> array) {
        this.nodes = (ArrayList<Item>) array;
        heapify();
    }



    /*
     * BUBBLE
     * --------------------------------------------------------------
     */

    private void bubbleUp(int index) {

        while (index > 0) {
            int parentIndex = parentIndex(index);
            int diff = nodes.get(parentIndex).
                        compareTo(nodes.get(index));
            if (diff > 0) {
                swap(parentIndex, index);
            }
            index = parentIndex;
        }
    }

    private void bubbleDown(int parentIndex) {
        while (hasAnyChild(parentIndex)) {
            Integer leastChildIndex = leastChildIndex(parentIndex);
            int diff = nodes.get(parentIndex).
                        compareTo(nodes.get(leastChildIndex));

            if (diff <= 0) break;
            swap(parentIndex, leastChildIndex);
            parentIndex = leastChildIndex;
        }
    }




    /*
     * MOFIDY
     * --------------------------------------------------------------
     */

    public BinaryHeap<Item> insert(Item node) {
        nodes.add(node);
        bubbleUp(nodes.size()-1);
        return this;
    }


    private void heapify() {
        int currentIndex = parentIndex(getSize()-1);
        while (currentIndex >= 0) {
            bubbleDown(currentIndex);
            currentIndex--;
        }
    }

    public Item next() {
        swap(nodes.size()-1, 0);
        Item nextItem = nodes.remove(nodes.size()-1);
        bubbleDown(0);
        return nextItem;
    }


    private void swap(int first, int second) {
        Item temp1 = nodes.get(first);
        Item temp2 = nodes.get(second);

        nodes.set(first, temp2);
        nodes.set(second, temp1);
    }


    public void decreaseKey(int i, int k) {
        swap(i, k);
        bubbleDown(0);
    }




    /*
     * BOOL-METHODS
     * --------------------------------------------------------------
     */  

    private boolean hasAnyChild(int index) {
        Integer left = leftChildIndex(index);
        Integer right = rightChildIndex(index);
        boolean child = true;
        
        if (left == null && right == null) {child = false;}
        return child;
    }




    /*
     * INDEX-GETTERS
     * --------------------------------------------------------------
     */
    private int parentIndex(int childIndex) {
        return (childIndex - 1)/2;
    }

    public Integer leftChildIndex(int parentIndex){
        int i = 2*parentIndex + 1;
        try {
            Item n = nodes.get(i);
            return i;
        } catch (Exception e) {
            return null;
        }
    }

    public Integer rightChildIndex(int parentIndex){
        int i = 2*parentIndex + 2;
        try {
            Item n = nodes.get(i);
            return i;
        } catch (Exception e) {
            return null;
        }
    }

    private Integer leastChildIndex(int parentIndex){
        Integer left = leftChildIndex(parentIndex);
        Integer right = rightChildIndex(parentIndex);

        if (left == null) return right;
        if (right == null) return left;

        if (left.compareTo(right) > 0) return right;
        else return left;
    }



    /*
     * INDEX-GETTERS
     * --------------------------------------------------------------
     */
    public List<Item> getArray() {
        return this.nodes;
    }

    public int getSize() {
        return nodes.size();
    }

    public void printTree() {
        int row = 1;
        int count = getSize()-1;
        int curr = 0;

        while (true) {
            for (int i = 0; i < row; i++) {
                System.out.print(nodes.get(curr) + " ");
                curr++;
                count--;
                if (curr == getSize()) {
                    System.exit(0);
                    System.out.println();
                }
            }
            System.out.println();
            row = row*2;
        }
    }
}