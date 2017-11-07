package ru.homework.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Stack;

/**
 * Дерево
 */
public class Tree {
    /**
     * Корень дерева
     */
    private Node root;

    public Tree() {
        root = null;
    }

    /**
     * Метод вставки значения
     * @param value значение которое нужно вставить
     * @return true or false в зависимости от успеха операции
     */
    public boolean insert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (value < current.getValue()) {
                    current = current.getLeftDescendant();
                    if (current == null) {
                        parent.setLeftDescendant(newNode);
                        return true;
                    }
                } else if (value > current.getValue()){
                    current = current.getRightDescendant();
                    if (current == null) {
                        parent.setRightDescendant(newNode);
                        return true;
                    }
                }
                else return false;
            }
        }
        return false;
    }

    /**
     * Метод вставки значения между двумя узлами
     * @param parentValue значение узла предка
     * @param descendantValue значения узла потомка
     * @param insertValue значение которое надо вставить
     * @return true or false в зависимости от успеха операции
     */
    public boolean insertPoint(int parentValue, int descendantValue, int insertValue) {
        Node nodeParent = find(parentValue);
        Node nodeDescendant = find(descendantValue);
        Node nodeInsert = new Node(insertValue);

        if (nodeDescendant == nodeParent.getLeftDescendant()) {
            nodeParent.setLeftDescendant(nodeInsert);
            nodeInsert.setLeftDescendant(nodeDescendant);
            return true;
        }
        else if (nodeDescendant == nodeParent.getRightDescendant()) {
            nodeParent.setRightDescendant(nodeInsert);
            nodeInsert.setRightDescendant(nodeDescendant);
            return true;
        }
        else return false;
    }

    /**
     * Метод обнавляющий значение
     * @param inValue число которое изменяем
     * @param update число которым заменяем
     * @return true or false в зависимости от успеха операции
     */
    public boolean update(int inValue, int update) {
        Node node = find(inValue);
        if (node != null && (node.getLeftDescendant() == null || node.getLeftDescendant().getValue() < update)
                && (node.getRightDescendant() == null || node.getRightDescendant().getValue() > update)) {
            node.setValue(update);
            return true;
        }
        else return false;
    }

    /**
     * Псевдо-балансировка дерева
     * @return псевдо-сбаласированое дерево
     */
    public Tree balance() {
        Tree tree = new Tree();
        Random random = new Random();
        ArrayList<Integer> array = new ArrayList<>();

        String in = toString();
        String split[] = in.split(" ");

        for (String s: split) {
            if (s.matches("[0-9]+")) array.add(Integer.parseInt(s));
        }
        array.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return random.nextInt(2) - 1;
            }
        });

        ArrayList<Integer> copyArray = new ArrayList<>(array);
        copyArray.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2) return -1;
                else if (o1 == o2) return 0;
                else return 1;
            }
        });

        tree.insert(copyArray.get(copyArray.size() / 2));
        for (Integer i: array) tree.insert(i);
        return tree;
    }

    /**
     * Метод удаления значения
     * @param value значение которое нужно удалить
     * @return true or false в зависимости от успеха операции
     */
    public boolean delete(int value) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;

        while (current.getValue() != value) {
            parent = current;
            if (value < current.getValue()) {
                isLeftChild = true;
                current = current.getLeftDescendant();
            } else {
                isLeftChild = false;
                current = current.getRightDescendant();
            }
            if (current == null) {
                return false;
            }
        }
        if (current.getLeftDescendant() == null && current.getRightDescendant() == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.setLeftDescendant(null);
            } else {
                parent.setRightDescendant(null);
            }
        } else if (current.getRightDescendant() == null) {
            if (current == root) {
                root = current.getLeftDescendant();
            } else if (isLeftChild) {
                parent.setLeftDescendant(current.getLeftDescendant());
            } else {
                parent.setRightDescendant(current.getLeftDescendant());
            }
        } else if (current.getLeftDescendant() == null) {
            if (current == root) {
                root = current.getRightDescendant();
            } else if (isLeftChild) {
                parent.setLeftDescendant(current.getRightDescendant());
            } else {
                parent.setRightDescendant(current.getRightDescendant());
            }
        } else {
            Node successor = getSuccessor(current);
            if (current == root) {
                root = successor;
                successor.setLeftDescendant(current.getLeftDescendant());
            } else if(isLeftChild) {
                successor.setLeftDescendant(current.getLeftDescendant());
                parent.setLeftDescendant(successor);
            } else {
                successor.setLeftDescendant(current.getLeftDescendant());
                parent.setRightDescendant(successor);
            }
        }
        return true;
    }

    /**
     * Метод помощник методу delete, поиск наименьшего значения от правой ветки
     * @param node узел который собираемся удалять
     * @return узел которым можно будет заменить пустое место
     */
    private Node getSuccessor(Node node) {
        Node successorParent = node;
        Node successor = node;
        Node current = node.getRightDescendant();
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftDescendant();
        }

        if (successor != node.getRightDescendant()) {
            successorParent.setLeftDescendant(successor.getRightDescendant());
            successor.setRightDescendant(node.getRightDescendant());
        }

        return successor;
    }

    /**
     * Поиск узла в дереве
     * @param value значение которое нужно найти
     * @return найденый узел. !!WARNING!! узел может оказаться пустым.
     */
    private Node find(int value) {
        Node node = root;
        while (node.getValue() != value) {
            if (value < node.getValue()) {
                node = node.getLeftDescendant();
            } else {
                node = node.getRightDescendant();
            }
            if (node == null) {
                break;
            }
        }
        return node;
    }


    @Override
    public String toString() {
        String output = "";

        Stack<Node> globalStack = new Stack<Node>();
        globalStack.push(root);
        int nBlanks = 50;
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
                    output += "_";
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
