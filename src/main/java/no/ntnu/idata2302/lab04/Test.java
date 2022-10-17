package no.ntnu.idata2302.lab04;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Test {
    
    static Integer[] items1 = new Integer[]{8,3,6,1,9,4,12,45,17};
    static Integer[] items2 = new Integer[]{(int) 'A', (int) 'L', (int) 'P', (int) 'D', (int) 'R', (int) 'E', (int) 'I', (int) 'X'};
    static ArrayList<Integer> nodes = new ArrayList<>(Arrays.asList(items1));
    static ArrayList<Integer> nodes2 = new ArrayList<>(Arrays.asList(items2));
    static BinaryHeap<Integer> heap = new BinaryHeap<>(nodes);
    static BinaryHeap<Integer> heap2 = new BinaryHeap<>(nodes2);
    static ArrayList<Integer> heapList = (ArrayList<Integer>) heap.getArray();
    static ArrayList<Integer> heapList2 = (ArrayList<Integer>) heap2.getArray();
    
    public static void main(String[] args) {

        //trident(heapList);
        //popper(heap);
        //findLayer(7);
        //testLetters();
        //treeTest();
        //distribution();
        testDecrease();
    }


    private static void testDecrease() {
        BinaryHeap<Integer> decreaseHeap = new BinaryHeap<>(nodes);
        ArrayList<Integer> decreaseList = (ArrayList<Integer>) decreaseHeap.getArray();
        trident(decreaseList);
        decreaseHeap.decreaseKey(4, 2);
        decreaseList = (ArrayList<Integer>) decreaseHeap.getArray();
        trident(decreaseList);
    }


    private static void treeTest() {
        Tree tree = new Tree();

        for (int i : items1) {
            tree.insert(i);
        }

        tree.inOrderTraversal();
        tree.postOrderTraversal();
        tree.preOrderTraversal();

    }


    private static void distribution() {

        
        ArrayList<Integer> ad = new ArrayList<>();
        ad.add((int) 'A');
        ad.add((int) 'B');
        ad.add((int) 'C');
        ad.add((int) 'D');

        //ad.add((int) 'E');
        //ad.add((int) 'F');
        //ad.add((int) 'G');
        //ad.add((int) 'H');
        //ad.add((int) 'I');
        //ad.add((int) 'J');

        System.out.println("\nN: " + ad.size());
        System.out.println("Permuations: " + factorial(ad.size()));
        List<List<Integer>> perm = listPermutations(ad);

        List<List<Character>> trees = new ArrayList<List<Character>>();

        for (List<Integer> p : perm) {
            Tree tree = new Tree();
            for (Integer i : p) {
                tree.insert((char) (int) i);
            }
            trees.add(tree.printLevelOrder());
        }

        System.out.println("With duplicates: " + trees.size());
        Set<List<Character>> set = new LinkedHashSet<>();
        // Add the elements to set 
        set.addAll(trees); 
  
        // Clear the list 
        trees.clear(); 
  
        // add the elements of set 
        // with no duplicates to the list 
        trees.addAll(set);
        
        System.out.println("Without duplicates: " + trees.size());

        
        for (List<Character> p : trees) {
            System.out.println();
            for (Character c : p) {
                System.out.print(c + " ");
            }
        }
        

       
        
    }

    public static List<List<Integer>> listPermutations(ArrayList<Integer> list) {


        if (list.size() == 0) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            result.add(new ArrayList<Integer>());
            return result;
        }
    
        List<List<Integer>> returnMe = new ArrayList<List<Integer>>();
    
        //Integer firstElement = list.remove(0);
        Integer firstElement = list.get(0);
        list.remove((Integer) list.get(0));
    
        List<List<Integer>> recursiveReturn = listPermutations(list);
        for (List<Integer> li : recursiveReturn) {
    
            for (int index = 0; index <= li.size(); index++) {
                List<Integer> temp = new ArrayList<Integer>(li);
                temp.add(index, firstElement);
                returnMe.add(temp);
            }
    
        }
        return returnMe;
    }



    private static int factorial(int n) {
        int s = 1;
        for (int i = n; i > 0; i--) {
            s*=i;
        }
        return s;
    }

   

    private static void testLetters() {
        ArrayList<Integer> testing = new ArrayList<>();
        BinaryHeap<Integer> bh = new BinaryHeap<>();

        
        for(int i=0; i<items2.length; i++) {
            System.out.println("\nInserting: " + (char)(int) items2[i]);
            bh.insert(items2[i]);
            alphabetPrint((ArrayList<Integer>) bh.getArray());
        } 
    }







    private static int findLayer(int k) {
        double h = 1;
        boolean found = false;

        // Minst antall nodes for høyden h: Math.pow(2, h-1)
        // Maks antall nodes for høydem h: Math.pow(2,h)-1)

        while (!found) {
            if (k >= Math.pow(2, h-1) && k <= Math.pow(2,h)-1) {
                 found = true;
            }
            else {
                h++;
            }
        }
        return (int) h;
    }




    private static void popper(BinaryHeap<Integer> heap) {
        System.out.println("\n");
        while (heap.getSize() > 0) {
            System.out.print(heap.next() + " ");
        }
        System.out.println("\n");
    }


    private static void trident(ArrayList<Integer> LIST) {
        System.out.println("\n");
        // There is always one X in the first row, therfore:
        StringBuilder sb = new StringBuilder();
        int height = 4;
        int numOfSpaces = 2*(height +1);
        int numOfXes = 1;
        int count = 0;
        int index = 0;

        // For each row...
        for (int i = 1; i <= height; i++) {

            numOfXes = (int) Math.pow(2, count);
            count++;
            

            // Add (height - numOfXes) amount of spaces
            for (int k = 1; k <= numOfSpaces; k++) {
                sb.append(" ");
            }

            // Then, add (1 + 2*row-index) amount of Xes
            for (int z = 1; z <= numOfXes; z++) {
                if (index < LIST.size()) {
                    sb.append(LIST.get(index) + " ");
                    index++;
                }
            }
            sb.append("\n");

            // Xes rises with 2 for each row
            numOfSpaces -= (numOfXes);
            if (numOfSpaces < 0) {numOfSpaces = 0;}
        }

        System.out.println(sb.toString());

     }


     private static void alphabetPrint(ArrayList<Integer> LIST) {
        System.out.println("\n");
        // There is always one X in the first row, therfore:
        StringBuilder sb = new StringBuilder();
        int height = 4;
        int numOfSpaces = 2*(height +1);
        int numOfXes = 1;
        int count = 0;
        int index = 0;

        // For each row...
        for (int i = 1; i <= height; i++) {

            numOfXes = (int) Math.pow(2, count);
            count++;
            

            // Add (height - numOfXes) amount of spaces
            for (int k = 1; k <= numOfSpaces; k++) {
                sb.append(" ");
            }

            // Then, add (1 + 2*row-index) amount of Xes
            for (int z = 1; z <= numOfXes; z++) {
                if (index < LIST.size()) {
                    int num = LIST.get(index);
                    char c = (char) num;
                    sb.append(c + " ");
                    index++;
                }
            }
            sb.append("\n");

            // Xes rises with 2 for each row
            numOfSpaces -= (numOfXes);
            if (numOfSpaces < 0) {numOfSpaces = 0;}
        }

        System.out.println(sb.toString());

     }
    
}
