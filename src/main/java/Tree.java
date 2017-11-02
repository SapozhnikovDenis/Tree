/**
 * Класс - бинарное дерево
 */
public class Tree {
    /**
     * root - корень дерева
     * treeEmpty - пустое ли дерево
     */
    private Node root;
    private boolean treeEmpty = true;

    /**
     * @param inValue - число которое надо вставить в дерево или которые необходимо найти.
     * @param act - действие которое нужно сделать
     * @param root - корень дерева
     */
    private void algorithm(int inValue, Act act, Node root) {this.algorithm(inValue,act,root,0);}

    /**
     * Алгоритм поиска нужного значения
     * @param inValue - число которое нужно обновить
     * @param act - действие которое нужно сделать
     * @param root - корень дерева
     * @param update - число которым нужно обновить
     */
    private void algorithm(int inValue, Act act, Node root, int update){
        Node node;
        int levl = 1;
        while (true) {
            if (inValue == root.getValue())
            {
                if (act == Act.UPDATE) {
                    if ((root.getLeftDescendant() == null || root.getLeftDescendant().getValue() < update)
                            && (root.getRightDescendant() == null || root.getRightDescendant().getValue() > update)) {
                        root.setValue(update);
                    } else System.out.println("C таким числом сломается дерево!");
                }
                else if(act == Act.DELETE) {
                    this.root = null;
                    treeEmpty == true;
                }
                break;
            }
            else if (inValue < root.getValue()) {
                if (root.getLeftDescendant() == null) {
                    node = new Node(inValue,levl);
                    root.setLeftDescendant(node);
                    break;
                } else {
                    if (act == Act.DELETE && root.getLeftDescendant().getValue() == inValue) {
                        root.setLeftDescendant(null);
                        break;
                    }
                    root = root.getLeftDescendant();
                    levl++;
                }
            } else {
                if (root.getRightDescendant() == null) {
                    node = new Node(inValue,levl);
                    root.setRightDescendant(node);
                    break;
                } else {
                    if (act == Act.DELETE && root.getRightDescendant().getValue() == inValue) {
                        root.setRightDescendant(null);
                        break;
                    }
                    root = root.getRightDescendant();
                    levl++;
                }
            }
        }
    }

    /**
     * Если дерево пустое, то первым значением доавиться корень, остальные отпрвятся в алгоритм
     * @param inValue - число которое надо вставить в дерево
     */
    public void insert(int inValue) {
        if (treeEmpty) {
            root = new Node(inValue,0);
            treeEmpty = false;
        }
        else {
            algorithm(inValue, Act.INSERT, root);
        }
    }

    /**
     * Метод вызывающий алгоритм для удаления числа
     * @param inValue - число которое нужно удалить
     */
    public void delete(int inValue){
        algorithm(inValue, Act.DELETE, root);
    }

    /**
     * Метод вызывающий алгоритм для обновления значения
     * @param inValue - число которое нужно обновить
     * @param update - число которым нужно обновить
     */
    public void update(int inValue, int update){
        algorithm(inValue, Act.UPDATE, root, update);
    }

    /**
     * Метод вызывающий рекурсивный алгоритм корня дерева
     */
    public void select() {
        if (root != null) {
            System.out.println(String.format("%45s","Уровень вложенности"));
            System.out.println("| 1| 2| 3| 4| 5| 6| 7| 8| 9| 10| 11| 12| 13| 14| 15| 16| 17| 18| 19| 20|");
            root.output();
        }
        else System.out.println("Дерево пустое!");
    }
}