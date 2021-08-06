import implementations.BinarySearchTree;
import implementations.BinaryTree;
import implementations.MaxHeap;
import implementations.PriorityQueue;
import interfaces.Heap;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


//        BinaryTree tree = new BinaryTree<>(17,
//                new BinaryTree<>(9, new BinaryTree<>(3, null, null),
//                        new BinaryTree<>(11, null, null)),
//                new BinaryTree<>(25, new BinaryTree<>(20, null, null),
//                        new BinaryTree<>(31, null, null))
//        );
//
//        System.out.println(tree.asIndentedPreOrder(0));
//
//        BinaryTree<Integer> secondCopiedTree = tree.copyTree();
//
//        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
//        bst.insert(12);
//        bst.getLeft();
//        bst.insert(21);
//        bst.insert(5);
//        bst.insert(1);
//        bst.insert(8);
//        bst.insert(18);
//        bst.insert(23);
//
//        bst.print();
//

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        List<Integer> elements = new ArrayList<>(List.of(15, 25, 6, 9, 5, 8, 17));
        for (Integer element : elements) {
            priorityQueue.add(element);
        }

        System.out.println(priorityQueue.peek());

        System.out.println("5".compareTo("8"));
        int a = 5;
    }
}
