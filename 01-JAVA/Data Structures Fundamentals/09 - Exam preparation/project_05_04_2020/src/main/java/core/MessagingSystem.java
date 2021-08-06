package core;

import model.Message;
import shared.DataTransferSystem;

import java.util.ArrayList;
import java.util.List;

public class MessagingSystem implements DataTransferSystem {

    static class Node {
        Message message;
        Node left;
        Node right;

        public Node(Message message) {
            this.message = message;
        }


        int getWeight() {
            return this.message.getWeight();
        }
    }

    Node root;
    int size;

    @Override
    public void add(Message message) {
        if (root == null) {
            root = new Node(message);
        } else {
            add(root, message);
        }

        size++;
    }

    private void add(Node node, Message message) {
        if (node.getWeight() == message.getWeight()) {
            throw new IllegalArgumentException();
        }

        if (message.getWeight() < node.getWeight()) {
            if (node.left == null) {
                node.left = new Node(message);
            } else {
                add(node.left, message);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(message);
            } else {
                add(node.right, message);
            }
        }
    }

    @Override
    public Message getByWeight(int weight) {
        Node result = getByWeight(root, weight);

        if (result == null) {
            throw new IllegalArgumentException();
        }

        return result.message;
    }

    private Node getByWeight(Node node, int weight) {
        if (node == null) {
            return null;
        }

        if (node.getWeight() == weight) {
            return node;
        }

        if (weight < node.getWeight()) {
            return getByWeight(node.left, weight);
        } else {
            return getByWeight(node.right, weight);
        }

    }

    @Override
    public Message getLightest() {
        if (root == null) {
            throw new IllegalArgumentException();
        }

        Node node = root;
        while (node.left != null){
            node = node.left;
        }

        return node.message;
    }

    @Override
    public Message getHeaviest() {
        if (root == null) {
            throw new IllegalArgumentException();
        }

        Node node = root;
        while (node.right != null){
            node = node.right;
        }

        return node.message;
    }

    @Override
    public Message deleteLightest() {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        
        Message deleted;
        if (root.left == null) {
            deleted = root.message;
            root = root.right;
        } else {
            Node parent = root;
            while (parent.left.left != null){
                parent = parent.left;
            }

            deleted = parent.left.message;
            parent.left = parent.left.right;
        }

        size--;
        return deleted;
    }

    @Override
    public Message deleteHeaviest() {
        if (root == null) {
            throw new IllegalArgumentException();
        }

        Message deleted;
        if (root.right == null) {
            deleted = root.message;
            root = root.left;
        } else {
            Node parent = root;
            while (parent.right.right != null){
                parent = parent.right;
            }

            deleted = parent.right.message;
            parent.left = parent.right.left;
        }

        size--;
        return deleted;
    }

    @Override
    public Boolean contains(Message message) {
        return getByWeight(message.getWeight()) != null;
    }

    @Override
    public List<Message> getOrderedByWeight() {
        return getInOrder();
    }

    @Override
    public List<Message> getPostOrder() {
        List<Message> messages = new ArrayList<>();
        fillPostOrder(root, messages);
        return messages;
    }

    private void fillPostOrder(Node node, List<Message> output) {
        if (node == null) {
            return;
        }

        fillPostOrder(node.left, output);
        fillPostOrder(node.right, output);
        output.add(node.message);
    }

    private void fillPreOrder(Node node, List<Message> output) {
        if (node == null) {
            return;
        }

        output.add(node.message);
        fillPreOrder(node.left, output);
        fillPreOrder(node.right, output);
    }

    private void fillInOrder(Node node, List<Message> output) {
        if (node == null) {
            return;
        }

        fillInOrder(node.left, output);
        output.add(node.message);
        fillInOrder(node.right, output);
    }

    @Override
    public List<Message> getPreOrder() {
        List<Message> messages = new ArrayList<>();
        fillPreOrder(root, messages);
        return messages;
    }

    @Override
    public List<Message> getInOrder() {
        List<Message> messages = new ArrayList<>();
        fillInOrder(root, messages);
        return messages;
    }

    @Override
    public int size() {
        return this.size;
    }
}
