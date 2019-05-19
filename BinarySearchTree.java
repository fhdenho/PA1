
/**
 * Fahad Alhazzaa
 * 05/19/2019
 * Binary Search Tree program / CSCI 232 PA1
 * This assignment should show how to print the BST with some functions like the traversal order (Inorder, Preorder, Postorder)
 * This assignment should show how th Delete a node or search for a node in the BST
 * This assignment has an input.txt file with values for the tree
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BinarySearchTree { // setting the name of the code Binary Search tree to represnt BST

    private class Node {

        int data; // have the data to be in intger
        Node left, right; // the direction for the node would be left or right

        Node(int data) { // have the node to be in intger
            this.data = data;
        }
    }

    private Node root; // definding the root to be in the tree

    
    public void insert(int data) {
        root = insert(root, data); // Insert a node value to the tree
    }

    
    private Node insert(Node n, int data) { // a method for the node to be insert
        if (n == null) { 
            n = new Node(data); // n will represnt the node
        } else if (n.data > data) { // compare the node to the data
            n.left = insert(n.left, data); // if the node less than the data will go to the left
        } else if (n.data < data) { // compare the node to the data
            n.right = insert(n.right, data); // if the node bigger than the data will go to the right
        }
        return n;
    }

    
    public void delete(int data) {
        root = delete(root, data); // Delete a node value to the tree
    }

    
    private Node delete(Node n, int data) { // a method for the node to be insert
        if (n == null) {
            return null; // if the value nit found return nothing
        }
        if (n.data > data) {
            n.left = delete(n.left, data);
        } else if (n.data < data) {
            n.right = delete(n.right, data);
        } else if (n.left != null && n.right != null) // p has two children
        {
            int minKey = getMinData(n.right);
            n.data = minKey;
            n.right = delete(n.right, n.data);
        } else {
            n = (n.left != null) ? n.left : n.right;
        }
        return n;
    }

    // Return minimum data in a given sub tree
    private int getMinData(Node p) {
        while (p.left != null) {
            p = p.left;
        }
        return p.data;
    }

    // Return true if data is found, false otherwise
    public boolean find(int data) {
        return find(root, data);
    }

    // Helper method for find
    private boolean find(Node p, int data) {
        if (p == null) {
            return false;
        } else if (p.data > data) {
            return find(p.left, data);
        } else if (p.data < data) {
            return find(p.right, data);
        }
        return true;
    }

    // Print tree in human readable way
    public void print() {
        int height = height(root);
        for (int level = 1; level <= height; level++) {
            System.out.println(" ");
            printLevel(root, level);
            System.out.println();
        }
    }

    // Return height of the given subtree
    private int height(Node p) {
        if (p == null) {
            return 0;
        }
        int lheight = height(p.left);
        int rheight = height(p.right);
        if (lheight > rheight) {
            return lheight + 1;
        } else {
            return rheight + 1;
        }
    }

    // Print nodes at the given level
    private void printLevel(Node p, int level) {
        if (p != null) {
            if (level == 1) {
                System.out.print(p.data + " ");
            } else {
                printLevel(p.left, level - 1);
                printLevel(p.right, level - 1);
            }
        }
    }

    // Print tree in three traversal functions: inorder, preorder, and postorder
    public void traversal() {
        System.out.println("Tree traversal");
        System.out.print("  inorder:");
        inorder(root);
        System.out.println();

        System.out.print("  preorder:");
        preorder(root);
        System.out.println();

        System.out.print("  postorder:");
        postorder(root);
        System.out.println();
    }

    private void inorder(Node p) {
        if (p != null) {
            inorder(p.left);
            System.out.print(" " + p.data);
            inorder(p.right);
        }
    }

    private void preorder(Node p) {
        if (p != null) {
            System.out.print(" " + p.data);
            preorder(p.left);
            preorder(p.right);
        }
    }

    private void postorder(Node p) {
        if (p != null) {
            postorder(p.left);
            postorder(p.right);
            System.out.print(" " + p.data);
        }
    }

    private static Scanner keyboard = new Scanner(System.in);
    private static BinarySearchTree tree;

    // Display menu and return user's choice
    static int menu() {
        System.out.println("--- Binary Search Tree ---");
        System.out.println("1.  Delete a node");
        System.out.println("2.  Search a node");
        System.out.println("3.  Print tree");
        System.out.println("4.  Tree traversal");
        System.out.println("5.  Quit");
        System.out.print("Enter your choice: ");
        int choice = keyboard.nextInt();
        return choice;
    }

    // Read a file to build and return the tree
    static void readTree(String fileName) throws IOException {
        tree = new BinarySearchTree();
        Scanner infile = new Scanner(new File(fileName));
        infile.useDelimiter(",");
        while (infile.hasNextInt()) {
            tree.insert(infile.nextInt());
        }
        infile.close();
    }

    static void delete() {
        System.out.print("Enter a key to delete: ");
        int data = keyboard.nextInt();
        tree.delete(data);
    }

    static void search() {
        System.out.print("Enter a key to search for: ");
        int data = keyboard.nextInt();
        if (tree.find(data)) {
            System.out.println(data + " is found");
        } else {
            System.out.println(data + " is not found");
        }
    }

    static void printTree() {
        tree.print();
    }

    static void treeTraversal() {
        tree.traversal();
    }

    public static void main(String[] args) throws IOException {
        int choice;

        readTree("input.txt");

        do {
            choice = menu();
            switch (choice) {
                case 1:
                    delete();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    printTree();
                    break;
                case 4:
                    treeTraversal();
                    break;
                case 5:
                    System.out.println("DONE.");
                    break;
                default:
                    System.out.println("not a correct input!");
                    break;
            }
            System.out.println();
        } while (choice != 5);
    }

}
