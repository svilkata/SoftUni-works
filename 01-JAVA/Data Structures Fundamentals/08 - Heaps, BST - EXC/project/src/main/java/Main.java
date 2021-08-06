import model.Product;
import solutions.BinaryTree;
import solutions.MinHeap;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        MinHeap<Product> minHeap = new MinHeap<>();
//        List<Integer> elements = new ArrayList<>(List.of(15, 25, 6, 9, 5, 8, 17, 16));
//        for (Integer element : elements) {
//            minHeap.add(new Product(element));
//        }
//
//        minHeap.decrease(new Product(8));
//
//        int a = 5;

        BinaryTree binaryTree =
                new BinaryTree(1,
                        new BinaryTree(2,
                                new BinaryTree(4, null, null),
                                new BinaryTree(5, null, null)),
                        new BinaryTree(3,
                                new BinaryTree(6, null, null),
                                new BinaryTree(7, null, null)));

        List<Integer> list = binaryTree.topView();

    }
}
