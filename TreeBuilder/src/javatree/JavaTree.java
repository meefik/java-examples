package javatree;

public class JavaTree {
    static String[] tree =
        {"       |       ",
         "      /|\\      ",
         "    ///|\\\\\\    ",
         "      /|\\      ",
         "    ///|\\\\\\    ",
         "  /////|\\\\\\\\\\  ",
         "      /|\\      ",
         "    ///|\\\\\\    ",
         "  /////|\\\\\\\\\\  ",
         "///////|\\\\\\\\\\\\\\",
         "      /|\\      "};
    int height;
    public JavaTree() {
        height = tree.length;
    }
    public JavaTree(int height) {
        this.height = height;
        if (height < 1) this.height=1;
        if (height > tree.length) this.height=tree.length;
    }
    void growTree() {
        for (int i = 0; i < height; i++) {
            System.out.println(tree[i]);
        }
    }
    public void drawTree() {
        growTree();
    }
}
