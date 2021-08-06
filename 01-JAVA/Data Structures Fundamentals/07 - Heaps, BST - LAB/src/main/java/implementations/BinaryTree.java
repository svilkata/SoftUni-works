package implementations;

import interfaces.AbstractBinarySearchTree;
import interfaces.AbstractBinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTree<E> implements AbstractBinaryTree<E> {
    private E key;
    private BinaryTree<E> left;
    private BinaryTree<E> right;

    public BinaryTree(E key, BinaryTree<E> left, BinaryTree<E> right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }


    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return this.left;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return this.right;
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    @Override
    public String asIndentedPreOrder(int indent) {
        String out = createPadding(indent) + getKey();
        if (this.getLeft() != null) {
            out += "\r\n" + getLeft().asIndentedPreOrder(indent + 2);
        }
        if (this.getRight() != null) {
            out += "\r\n" + getRight().asIndentedPreOrder(indent + 2);
        }
        return out;

    }

    private String createPadding(int indent) {
        StringBuilder padding = new StringBuilder("");
        for (int i = 0; i < indent; i++) {
            padding.append(" ");
        }

        return padding.toString();
    }

    private void printRecursive(AbstractBinaryTree<E> node, int level){
        if (node == null) {
            return;
        }

        StringBuilder padding = new StringBuilder();
        for (int i = 0; i < level; i++) {
            padding.append("  ");
        }

        printRecursive(node.getLeft(), level + 1);
        System.out.println(padding.append(node.getKey()));
        printRecursive(node.getRight(), level + 1);
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        List<AbstractBinaryTree<E>> listOfNodes = new ArrayList<>();
        preOrderDFS(this, listOfNodes);

        return listOfNodes;
    }

    private void preOrderDFS(BinaryTree<E> root, List<AbstractBinaryTree<E>> listOfNodes) {
        if (root == null) {
            return;
        }

        listOfNodes.add(root);
        preOrderDFS((BinaryTree<E>) root.getLeft(), listOfNodes);
        preOrderDFS((BinaryTree<E>) root.getRight(), listOfNodes);
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        List<AbstractBinaryTree<E>> listOfNodes = new ArrayList<>();
        inOrderDFS(this, listOfNodes);

        return listOfNodes;
    }

    private void inOrderDFS(BinaryTree<E> root, List<AbstractBinaryTree<E>> listOfNodes) {
        if (root == null) {
            return;
        }

        inOrderDFS((BinaryTree<E>) root.getLeft(), listOfNodes);
        listOfNodes.add(root);
        inOrderDFS((BinaryTree<E>) root.getRight(), listOfNodes);
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        List<AbstractBinaryTree<E>> listOfNodes = new ArrayList<>();
        postOrderDFS(this, listOfNodes);

        return listOfNodes;
    }

    private void postOrderDFS(BinaryTree<E> root, List<AbstractBinaryTree<E>> listOfNodes) {
        if (root == null) {
            return;
        }

        postOrderDFS((BinaryTree<E>) root.getLeft(), listOfNodes);
        postOrderDFS((BinaryTree<E>) root.getRight(), listOfNodes);
        listOfNodes.add(root);
    }

    public BinaryTree<E> copyTree() {
        return copy(this); //това е корена
    }

    private BinaryTree<E> copy(BinaryTree<E> root) {
        if (root == null) {
            return null;
        }

        BinaryTree<E> copiedTree = new BinaryTree<E>(root.getKey(), null, null);
        copiedTree.left = copy((BinaryTree<E>) root.getLeft());
        copiedTree.right = copy((BinaryTree<E>) root.getRight());

        return copiedTree;
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {
        if (this.getLeft() != null) {
            this.getLeft().forEachInOrder(consumer);
        }
        consumer.accept(this.getKey());

        if (this.getRight() != null) {
            this.getRight().forEachInOrder(consumer);
        }
    }
}
