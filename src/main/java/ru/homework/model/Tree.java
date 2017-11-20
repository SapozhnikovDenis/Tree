package ru.homework.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

/**
 * Дерево
 */
public class Tree {
    private Node root;
    private int size;

    public Tree() {root = null;}

    /**
     * Метод вставки значения
     * @param value значение которое нужно вставить
     * @return true or false в зависимости от успеха операции
     */
    public boolean insert(int value) {
        Node find = find(value);
        if (root == null) {
            root = new Node(value);
            size++;
            return true;
        }
        else if (find != null) return false;
        else {
            Node newNode = new Node(value);
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (value < current.getValue()) {
                    current = current.getLeftDescendant();
                    if (current == null) {
                        parent.setLeftDescendant(newNode);
                        size++;
                        return true;
                    }
                } else if (value > current.getValue()){
                    current = current.getRightDescendant();
                    if (current == null) {
                        parent.setRightDescendant(newNode);
                        size++;
                        return true;
                    }
                }
                else return false;
            }
        }
    }

    /**
     * Метод вставки значения между двумя узлами
     * @param descendantValue значения узла перед которым нужно вставить
     * @param insertValue значение которое надо вставить
     * @return true or false в зависимости от успеха операции
     */
    public boolean insertBefore(int descendantValue, int insertValue) {
        if (root == null) return false;

        Node nodeInsert = find(insertValue);
        if (nodeInsert != null) { return false; }
        nodeInsert = new Node(insertValue);

        Node nodeDescendant = find(descendantValue);
        if (nodeDescendant == null) { return false; }

        Node nodeParent = findParent(descendantValue);
        if (nodeParent == null) {
            if (root.getValue() > insertValue) {
                nodeInsert.setRightDescendant(root);
            }
            else {
                nodeInsert.setLeftDescendant(root);
            }
            root = nodeInsert;
            size++;
            return true;
        }

        if (nodeDescendant == nodeParent.getLeftDescendant() && insertValue < nodeParent.getValue()) {
            nodeParent.setLeftDescendant(nodeInsert);
        }
        else if (nodeDescendant == nodeParent.getRightDescendant() && insertValue > nodeParent.getValue()) {
            nodeParent.setRightDescendant(nodeInsert);
        }
        else { return false; }

        if (nodeDescendant.getValue() > insertValue) {
            nodeInsert.setRightDescendant(nodeDescendant);
        }
        else { nodeInsert.setLeftDescendant(nodeDescendant);}

        return true;
    }

    /**
     * Метод обнавляющий значение
     * @param inValue число которое изменяем
     * @param update число которым заменяем
     * @return true or false в зависимости от успеха операции
     */
    public boolean update(int inValue, int update) {
        Node check = find(update);
        if (check != null) return false;

        Node current = find(inValue);
        if (current == null) return false;

        if (current != root) {
            current.setValue(update);
            if (find(update) == null) {
                current.setValue(inValue);
                return false;
            }
        }
        else {
            if ((current.getLeftDescendant() == null || current.getLeftDescendant().getValue() < update)
                    && (current.getRightDescendant() == null || current.getRightDescendant().getValue() > update)) {
                current.setValue(update);
            }
            else { return false; }
        }
        return true;
    }

    /**
     * Псевдо-балансировка дерева
     * @return сбаласированое дерево
     */
    public Tree balance() {
        Tree tree = new Tree();
        ArrayList<Integer> array = new ArrayList<Integer>();

        String in = toString();
        String split[] = in.split(" ");

        for (String s: split) {
            if (s.matches("[0-9]+|-[0-9]+")) array.add(Integer.parseInt(s));
        }

        ArrayList<Integer> copyArray = new ArrayList<Integer>(array);
        copyArray.sort(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2) return -1;
                else if (o1 == o2) return 0;
                else return 1;
            }
        });

        tree.insert(copyArray.get(copyArray.size() / 2));
        for (Integer i: array) { tree.insert(i); }
        return tree;
    }

    /**
     * Метод удаления значения
     * @param value значение которое нужно удалить
     * @return true or false в зависимости от успеха операции
     */
    public boolean delete(int value) {
        Node node = find(value);
        if (node != null) {
            recursiveDelete(node);
            Node nodeParent = findParent(value);

            if (nodeParent == null) root = null;
            else if (nodeParent.getRightDescendant() != null
                    && nodeParent.getRightDescendant().getValue() == value) {
                nodeParent.setRightDescendant(null);
            }
            else if (nodeParent.getLeftDescendant() != null
                    && nodeParent.getLeftDescendant().getValue() == value) {
                nodeParent.setLeftDescendant(null);
            }

            size++;
            return true;
        }
        else { return false; }
    }

    /**
     * Метод рекурсивного удаления всех узлов вниз по иерархии
     * @param node узел от которого нужно начать удалять
     */
    private void recursiveDelete(Node node) {
        if (node.getRightDescendant() != null) {
            recursiveDelete(node.getRightDescendant());
            node.setRightDescendant(null);
        }
        if (node.getLeftDescendant() != null) {
            recursiveDelete(node.getLeftDescendant());
            node.setLeftDescendant(null);
        }
    }

    /**
     * Поиск узла в дереве
     * @param value значение которое нужно найти
     * @return найденый узел. !!WARNING!! узел может оказаться пустым.
     */
    private Node find(int value) {
        if (root == null) return null;
        Node node = root;
        while (node.getValue() != value) {
            if (value < node.getValue()) {
                node = node.getLeftDescendant();
            } else { node = node.getRightDescendant(); }

            if (node == null) { break; }
        }
        return node;
    }

    /**
     * Поиск родителя узла в дереве
     * @param value значение у которого нужно найти родителя
     * @return найденый узел. !!WARNING!! узел может оказаться пустым.
     */
    private Node findParent(int value) {
        Node node = root;
        while (true) {
            if (node.getValue() == value) {
                node = null;
                break;
            }
            else if (node.getLeftDescendant() != null
                    && node.getLeftDescendant().getValue() == value) { break; }
            else if (node.getRightDescendant() != null
                    && node.getRightDescendant().getValue() == value) { break; }
            else {
                if (value < node.getValue()) {
                    node = node.getLeftDescendant();
                }
                else { node = node.getRightDescendant(); }

                if (node == null) { break; }
            }
        }
        return node;
    }

    /**
     * Строчное представление дерева
     * @return строку в которой расположенно все дерево
     */
    @Override
    public String toString() {
        String output = "___________________________________________________________________________________________________________________________________________\n";

        Stack<Node> globalStack = new Stack<Node>();
        globalStack.push(root);
        int nBlanks = 5 * size;
        boolean isRowEmpty = false;

        while (!isRowEmpty) {
            Stack<Node> localStack = new Stack<Node>();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++) {
                output += " ";
            }

            while (!globalStack.isEmpty()) {
                Node node = globalStack.pop();
                if (node != null) {
                    output += node.getValue();
                    localStack.push(node.getLeftDescendant());
                    localStack.push(node.getRightDescendant());

                    if (node.getLeftDescendant() != null || node.getRightDescendant() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    output += "-";
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2; j++) {
                    output += " ";
                }
            }

            output += "\n";
            nBlanks /= 2;

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }
        }

        return output + "___________________________________________________________________________________________________________________________________________\n";
    }

}
