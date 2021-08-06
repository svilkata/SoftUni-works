import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.function.Consumer;

import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;

    public static class Node<E> {
        private E value;
        private Node<E> leftChild;
        private Node<E> rightChild;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> leftChild, Node<E> rightChild) {
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public void setLeftChild(Node<E> leftChild) {
            this.leftChild = leftChild;
        }

        public void setRightChild(Node<E> rightChild) {
            this.rightChild = rightChild;
        }

        public Node<E> getLeft() {
            return this.leftChild;
        }

        public Node<E> getRight() {
            return this.rightChild;
        }

        public E getValue() {
            return this.value;
        }

    }

    public BinarySearchTree() {
    }

    public BinarySearchTree(E value) {
        this.root = new Node<E>(value);
    }

    public void eachInOrder(Consumer<E> consumer) {
        this.internalEachInOrder(this.root, consumer);
    }

    private void internalEachInOrder(Node<E> node, Consumer<E> consumer) {
        if (node == null) {
            return;
        }

        this.internalEachInOrder(node.getLeft(), consumer);
        consumer.accept(node.getValue());
        this.internalEachInOrder(node.getRight(), consumer);
    }

    public Node<E> getRoot() {
        return this.root;
    }

    public void insert(E element) {
        if (this.getRoot() == null) {
            this.root = new Node<E>(element);
        } else {
            //Find the place to insert
            insertRecursive(element, this.root);
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

    public boolean contains(E element) {
        return this.internalSearch(this.root, element);
    }

    private boolean internalSearch(Node<E> root, E element) {
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

    private Node<E> internalSearchReturnNodeRecursive(Node<E> node, E element) {
        if (node == null) {
            return null;
        }

        if (node.getValue().compareTo(element) < 0) {
            return this.internalSearchReturnNodeRecursive(node.getRight(), element);
        } else if (node.getValue().compareTo(element) > 0) {
            return this.internalSearchReturnNodeRecursive(node.getLeft(), element);
        }

        return node;
    }

//    public BinarySearchTree<E> search(E searchedElement) {
//        Node<E> current = internalSearchReturnNodeRecursive(this.root, searchedElement);
//
//        return current == null ? null :
//                new BinarySearchTree<E>(current.getValue());
//
//    }


    public BinarySearchTree<E> search(E searchedElement) {
        BinarySearchTree<E> result = new BinarySearchTree<>();
        Node<E> current = this.root;
        while (current != null) {
            if (searchedElement.compareTo(current.value) < 0) {
                current = current.leftChild;
            } else if (searchedElement.compareTo(current.value) > 0) {
                current = current.rightChild;
            } else { // ако елемента съвпада
                return new BinarySearchTree<E>(current.getValue());
            }
        }

        return result;
    }

    public List<E> range(E first, E second) {
        List<E> result = new ArrayList<>();
        this.internalEachInOrder(this.root, (element) -> {
            if (element.compareTo(first) >= 0 && element.compareTo(second) <= 0) {
                result.add(element);
            }
        });

        return result;
    }

    public void deleteMin() {
        isEmptyTree();

        if (this.root.getLeft() == null) {
            this.root = this.root.getRight();
        } else {
            Node<E> current = this.root;

            while (current.getLeft().getLeft() != null) {
                current = current.getLeft();
            }

            current.setLeftChild(current.getLeft().getRight());
        }
    }

    private void isEmptyTree() {
        if (this.root == null) {
            throw new IllegalArgumentException("The tree is empty");
        }
    }

    public void deleteMax() {
        isEmptyTree();

        if (this.root.getRight() == null) {
            this.root = this.root.getLeft();
        } else {
            Node<E> current = this.root;

            while (current.getRight().getRight() != null) {
                current = current.getRight();
            }

            current.setRightChild(current.getRight().getLeft());
        }
    }

    public int count() {
        return this.internalCount(this.root);
    }

    private int internalCount(Node<E> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + (node.getLeft() == null ? 0 : this.internalCount(node.getLeft()))
                    + (node.getRight() == null ? 0 : this.internalCount(node.getRight()));
        }
    }

    public int rank(E somevalue) {
        if (this.root == null) {
            return 0;
        }
        int result = 0;
        
//        E element = this.ceil(somevalue);

        Node<E> current = this.root;

        Queue<Node<E>> queue = new ArrayDeque<>();
        queue.offer(current);
        

        while (!queue.isEmpty()) {
            current = queue.poll();

            if (current.getValue().compareTo(somevalue) < 0) {
                result++;
            }

            if (current.getLeft() != null) {
                queue.offer(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.offer(current.getRight());
            }
        }

        return result;
    }

    public E ceil(E element) {
        if (this.root == null) {
            return null;
        } else {
            Node<E> current = this.root;
            Node<E> highestNode = null;

            while (current != null) {
                if (current.getValue().compareTo(element) < 0) {
                    current = current.getRight();
                } else if (current.getValue().compareTo(element) > 0) {
                    highestNode = current;
                    current = current.getLeft();
                } else {
                    break;
                }
            }

            return highestNode == null ? null : highestNode.getValue();
        }

    }

    public E floor(E element) {
        if (this.root == null) {
            return null;
        } else {
            Node<E> current = this.root;
            Node<E> lowestNode = null;

            while (current != null) {
                if (current.getValue().compareTo(element) > 0) {

                    current = current.getLeft();
                } else if (current.getValue().compareTo(element) < 0) {
                    lowestNode = current;
                    current = current.getRight();
                } else {
                    if (current.getLeft() != null) {
                        lowestNode = current.getLeft();
                        current = current.getLeft().getRight();
                    } else {
                        break;
                    }
                }
            }

            return lowestNode == null ? null : lowestNode.getValue();
        }
    }
}
