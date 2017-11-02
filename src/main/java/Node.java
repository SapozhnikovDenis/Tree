/**
 *  Класс - узел дерева
 */
public class Node {
    /**
     * value - значение узла
     * levl - уровень вложенности
     * leftDescendant - левый потомок
     * rightDescendant - правый потомок
     */
    private int value;
    private int levl;
    private Node leftDescendant;
    private Node rightDescendant;

    public Node(int value, int levl) {
        this.value = value;
        this.levl = levl;
    }

    /**
     * В методе output рекурсивно выводится корневой узел и его потомки, у которых свои потомки, и так далее...
     */
    public void output() {
        for (int i = 0; i < levl; i++)
            System.out.print("   ");
            System.out.println("|" + value + "|");
        if (leftDescendant != null)
            leftDescendant.output();
        if (rightDescendant != null)
            rightDescendant.output();
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
