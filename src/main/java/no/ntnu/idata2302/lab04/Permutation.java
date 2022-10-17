package no.ntnu.idata2302.lab04;

import java.util.ArrayList;

class Permutation {
    

    private static ArrayList<Integer> origin = new ArrayList<>();



    public static void main(String[] args) {

        /* Number of keys */
        int n = 3;

        /* Number of permutations */
        int p = factorial(n);

        /* Number of times a particular key can be located at a particular index */
        int q = factorial(n-1);

        fillBase(n);

        int[][] permutations = create(n,p,q);

        System.out.println("\nNumber of permutations: " + permutations.length);

        for (int[] permutation : permutations) {
            for (int number : permutation) {
                System.out.print(number + " ");
            }
            System.out.println();
        }

    }


    static int[][] create(int n, int p, int q) {

        int[][] permutations = new int[p][n];    

        for (int i = 0; i < n; i++) {
            permutations[i][0] = origin.get((i+0)%3);
            permutations[i][1] = origin.get((i+1)%3);
            permutations[i][2] = origin.get((i+2)%3);
        }
        //for (int i = n; i < )

        return permutations;
    }

    static int factorial(int k) {
        int product = 1;
        for (int factor = k; factor > 1; factor--) {
            product *= factor;
        }
        return product;
    }


    static void fillBase(int n) {
        if (!origin.isEmpty()) origin.clear();
        for (int key = 1; key <= n; key++) {
            origin.add(key);
        }
    }



}
