
/*
 * This file is part of NTNU's IDATA2302 Lab 04.
 *
 * Copyright (C) NTNU 2022
 * All rights reserved.
 *
 */

package no.ntnu.idata2302.lab04;

import java.util.ArrayList;
import java.util.Stack;

public class Tree {
    private Node root;

    public void insert(int item) {
        Node parent = findParentOfItem(item);

        if (parent == null) {
            root = new Node(item, null);
            return;
        }

        // check if item already exists in the tree
        if ((parent.left != null && parent.left.item == item) || (parent.right != null && parent.right.item == item)) {
            throw new RuntimeException("Item already exists");
        } else if (item < parent.item) {
            parent.left = new Node(item, parent);
        } else {
            parent.right = new Node(item, parent);
        }
    }

    public void delete(int item) {
        Node parent = findParentOfItem(item);

        Node node = null;
        Boolean left = null;
        if (parent.left != null && parent.left.item == item) {
            node = parent.left;
            left = true;
        } else if (parent.right != null && parent.right.item == item) {
            node = parent.right;
            left = false;
        } else {
            throw new RuntimeException("Item doesn't exist");
        }

        // node doesn't have children, just delete it
        if (node.left == null && node.right == null) {
            if (left) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        // node has 2 children
        else if (node.left != null && node.right != null) {
            Node successor = findSuccessor(node);

            if (node.right != successor) {
                // since node.right != null, the successor must be in the right subtree
                // as such, it can't have a left child and could only have a right child
                if (successor.right != null) {
                    // the successor must be the left child of its parent
                    successor.parent.left = successor.right;
                    successor.right.parent = successor.parent;
                } else {
                    successor.parent.left = null;
                }
                successor.right = node.right;
                node.right.parent = successor;
            }
            successor.parent = parent;
            if (left) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = node.left;
            successor.left.parent = successor;
        }
        // node only has a left child
        else if (node.left != null) {
            if (left) {
                parent.left = node.left;
                node.left.parent = parent;
            } else {
                parent.right = node.left;
                node.left.parent = parent;
            }
        }
        // node only has a right child
        else {
            if (left) {
                parent.left = node.right;
                node.right.parent = parent;
            } else {
                parent.right = node.right;
                node.right.parent = parent;
            }
        }
    }

    // finds the theoretical or actual parent of an item
    public Node findParentOfItem(int item) {
        Node node = root;
        Node prev_node = null;

        while (node != null) {
            if (item < node.item) {
                prev_node = node;
                node = node.left;
            } else if (item > node.item) {
                prev_node = node;
                node = node.right;
            } else {
                return prev_node;
            }
        }

        return prev_node;
    }

    public Node findSuccessor(Node node) {
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        Node parent = node.parent;
        if (parent == null) {
            throw new RuntimeException("node has no successor");
        }
        while (parent != null && node == parent.right) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }




    public void inOrderTraversal() {

        /* If root is null, there are no children to traverse */
        if (this.root == null) return;
        /* 
         Creates a stack which will contain the nodes 
         we have traversed over  
        */
        Stack<Node> stack = new Stack<>();
        /* 
         Current node is the root before traversing
         can begin.
        */
        Node current = this.root;

        /* Starts traversing the tree */
        while (current != null || !stack.isEmpty()) {

            /* Reach the left most child-node of the current node */
            while (current != null) {
                /* Register the node in the stack */
                stack.push(current);
                /* Go to the left child of this node */
                current = current.left;
            }

            /* Empties the stack after reaching the left most child-node */
            current = stack.pop();
            /* Prints out the item of this node */
            System.out.print(current.item + " ");
            /* Goes to the right node instead */
            current = current.right; 
        }
        System.out.println();
    }





    public void postOrderTraversal() {

        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        Node current;

        if (this.root == null) return;

        s1.push(this.root);

        while (!s1.empty()) {
            current = s1.pop();
            s2.push(current);

            if (current.left != null) {
                s1.push(current.left);
            }
            if (current.right != null) {
                s1.push(current.right);
            }
        }

        while (!s2.empty()) {
            current = s2.pop();
            System.out.print(current.item + " ");
        }
        System.out.println();
    }


    
    public void preOrderTraversal() {
        if (this.root == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(this.root);

        while (!stack.empty()) {

            Node current = stack.peek();
            System.out.print(current.item + " ");
            stack.pop();

            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Tree t = new Tree();
    }

    
    /* function to print level order traversal of tree*/
    public ArrayList<Character> printLevelOrder()
    {
        ArrayList<Character> list = new ArrayList<>();
        int h = height(root);
        int i;
        for (i = 1; i <= h; i++) {
            currentLevel(root, i, list);
        }
        return list;
    }


    /* Compute the "height" of a tree -- the number of
    nodes along the longest path from the root node
    down to the farthest leaf node.*/
    private int height(Node root)
    {
        if (root == null)
            return 0;
        else {
            /* compute  height of each subtree */
            int lheight = height(root.left);
            int rheight = height(root.right);

            /* use the larger one */
            if (lheight > rheight)
                return (lheight + 1);
            else
                return (rheight + 1);
        }
    }

    /* Print nodes at the current level */
    private void currentLevel(Node root, int level, ArrayList<Character> list)
    {
        if (root == null) return;

        if (level == 1) {
            list.add( (char) root.item);
        }
        else if (level > 1) {
            currentLevel(root.left, level - 1, list);
            currentLevel(root.right, level - 1, list);
        }
    }

    public Node getRoot() {
        return this.root;
    }

}

class Node {

    Node parent;
    Node left = null;
    Node right = null;
    int item;

    public Node(int item, Node parent) {
        this.item = item;
        this.parent = parent;
    }
}

