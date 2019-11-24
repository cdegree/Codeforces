package algorithms;


public class BinarySearchTree {
    public class Node {
        int data;
        Node left, right;
        Node(int d) {
            data = d;
            left = right = null;
        }
    }
    Node root;
    public int len;
    int result;

    public BinarySearchTree() {
        root = null;
    }

    public void Insert(int data) {
        this.root = this.InsertRec(this.root, data);
        ++len;
    }

    Node InsertRec(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
        if (data < node.data) {
            node.left = this.InsertRec(node.left, data);
        } else {
            node.right = this.InsertRec(node.right, data);
        }
        return node;
    }

    public class count {
        int c = 0;
    }

    void KthLargestElement(Node node, int k, count C)
    {
        if (node == null || C.c >= k)
            return ;
        this.KthLargestElement(node.right, k, C);
        C.c++;
        if (C.c == k) {
            result = node.data;
            return ;
        }
        this.KthLargestElement(node.left, k, C);
    }

    public int KthLargest(int k)
    {
        count c = new count();
        result = -1;
        this.KthLargestElement(this.root, k, c);
        return result;
    }

    public static void main(String[] args)
    {
        Integer arr[] = new Integer[]{12, 3, 5, 7, 4, 19, 26};
        BinarySearchTree bst =new BinarySearchTree();
        for(int item:arr ){
            bst.Insert(item);
        }
        for(int k=1;k<=bst.len;++k) {
            System.out.println(k+"'th smallest element is " + bst.KthLargest(k));
        }
    }
}
