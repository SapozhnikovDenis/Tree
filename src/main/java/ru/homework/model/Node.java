package ru.homework.model;

/**
 * Узел дерева
 */
public class Node {
    private int value;
    private Node leftDescendant;
    private Node rightDescendant;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeftDescendant() {
        return leftDescendant;
    }

    public void setLeftDescendant(Node leftDescendant) {
        this.leftDescendant = leftDescendant;
    }

    public Node getRightDescendant() {
        return rightDescendant;
    }

    public void setRightDescendant(Node rightDescendant) {
        this.rightDescendant = rightDescendant;
    }
}
