package implementations;

import interfaces.AbstractBinarySearchTree;
import interfaces.AbstractBinaryTree;

public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {
    private Node<E> root;
    private Node<E> leftChild;
    private Node<E> rightChild;

    public BinarySearchTree() {
    }

    @Override
    public void insert(E key) {
        Node<E> node = new Node<>(key, null, null);

        if (this.getRoot() == null) {
            this.root = node;
        } else {
            // Find the place to insert
            insertRecursive(key, this.root);
        }
    }

    private void insertRecursive(E key, Node<E> node) {
        int compareResult = key.compareTo(node.value);

        if (compareResult == 0) {
            return;
        }

        if (compareResult < 0) {
            if (node.leftChild == null) {
                node.leftChild = new Node<>(key, null, null);
            } else {
                insertRecursive(key, node.leftChild);
            }

        } else {
            if (node.rightChild == null) {
                node.rightChild = new Node<>(key, null, null);
            } else {
                insertRecursive(key, node.rightChild);
            }
        }
    }

    @Override
    public boolean contains(E element) {
        Node<E> current = this.root;
        while (current != null) {
            if (element.compareTo(current.value) < 0) {
                current = current.leftChild;
            } else if (element.compareTo(current.value) > 0) {
                current = current.rightChild;
            } else {
                break;
            }
        }
        return current != null;
    }

    @Override
    public AbstractBinarySearchTree<E> search(E searchedElement) {
        AbstractBinarySearchTree<E> result = new BinarySearchTree<>();
        Node<E> current = this.root;
        while (current != null){
            if(searchedElement.compareTo(current.value) < 0){
                current = current.leftChild;
            } else if(searchedElement.compareTo(current.value) > 0){
                current = current.rightChild;
            } else { // ако елемента съвпада
                return new BinarySearchTree<E>(current);
            }
        }
        return result;
    }

    //генерирай дърво с метода insert с определен корен
    private BinarySearchTree(Node<E> root) {
        this.copy(root);
    }

    //генерирай дърво с метода insert с определен корен
    private void copy(Node<E> node) {
        if(node!=null) {
            this.insert(node.value);
            this.copy(node.leftChild);
            this.copy(node.rightChild);
        }
    }


    @Override
    public Node<E> getRoot() {
        return this.root;
    }

    @Override
    public Node<E> getLeft() {
        return this.leftChild;
    }

    @Override
    public Node<E> getRight() {
        return this.rightChild;
    }

    @Override
    public E getValue() {
        return this.root.value;
    }

    public void print() {
        printRecursive(this.root, 0);
    }

    private void printRecursive(Node<E> node, int level) {
        if (node == null) {
            return;
        }

        StringBuilder padding = new StringBuilder();
        for (int i = 0; i < level; i++) {
            padding.append("  ");
        }

        printRecursive(node.leftChild, level + 1);
        System.out.println(padding.append(node.getValue()));
        printRecursive(node.rightChild, level + 1);
    }
}
