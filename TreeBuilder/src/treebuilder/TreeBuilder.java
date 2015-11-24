package treebuilder;

import java.util.Scanner;
import javatree.JavaTree;

public class TreeBuilder {
    public static void main(String[] args) {
        // ввод данных с клавиатуры
        Scanner in = new Scanner(System.in);
        System.out.print("Введите высоту дерева: ");
        String h = in.nextLine();
        in.close();
        // использоваение класса JavaTree из пакета javatree
        JavaTree tree = new JavaTree(Integer.parseInt(h));
        tree.drawTree();
    }
}
