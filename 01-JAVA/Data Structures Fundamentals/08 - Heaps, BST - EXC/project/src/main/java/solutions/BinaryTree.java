package solutions;

import java.util.*;
import java.util.stream.Collectors;

public class BinaryTree {
    private int value;
    private BinaryTree left;
    private BinaryTree right;
    private BinaryTree parent;


    public BinaryTree(int key, BinaryTree left, BinaryTree right) {
        this.value = key;
        this.left = left;
        this.right = right;
        this.setParent(null);
        if (this.left != null) {
            this.left.setParent(this);
        }
        if (this.right != null) {
            this.right.setParent(this);
        }
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }

    public void setParent(BinaryTree parent) {
        this.parent = parent;
    }

    public int getValue() {
        return value;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public BinaryTree getParent() {
        return parent;
    }

    private BinaryTree findNode(BinaryTree current, int value) {
        if (current == null) {
            return null;
        }

        if (current.getValue() == value) {
            return current;
        } else {
            BinaryTree foundNode = this.findNode(current.getLeft(), value);

            if (foundNode == null) {
                foundNode = this.findNode(current.getRight(), value);
            }

            return foundNode;
        }
    }

    private List<BinaryTree> findAncestors(int value) {
        List<BinaryTree> result = new ArrayList<>();
        BinaryTree foundNode = this.findNode(this, value);

        while (foundNode.getParent() != null) {
            foundNode = foundNode.getParent();
            result.add(foundNode);
        }

        return result;
    }

    public Integer findLowestCommonAncestor(int first, int second) {
        List<BinaryTree> firstAncestors = this.findAncestors(first);
        List<BinaryTree> secondAncestors = this.findAncestors(second);

        for (int i = 0; i < firstAncestors.size(); i++) {
            if (secondAncestors.contains(firstAncestors.get(i))) {
                return firstAncestors.get(i).getValue();
            }
        }

        return null;
    }

    public List<Integer> topView() {
        //първият елемент е offset – заема стойности от -3 до +3
//вторият параметър е стойността на върха
//третият елемент е нивото на което се намира върха
        Map<Integer, Pair<Integer, Integer>> offsetToValueLevel = new TreeMap<>();

        traverseTree(this, 0, 1, offsetToValueLevel);

        List<Integer> collect = offsetToValueLevel.values()
                .stream()
                .map(e -> e.getKey())
                .collect(Collectors.toList());

        return collect;
    }

    private void traverseTree(BinaryTree tree, int offset, int level, Map<Integer,
            Pair<Integer, Integer>> offsetToValueLevel) {
        if (tree == null) {
            return;
        }

        Pair<Integer, Integer> currentValueLevel = offsetToValueLevel.get(offset);
        if (currentValueLevel == null || level < currentValueLevel.getValue()) {
            offsetToValueLevel.put(offset, new Pair<>(tree.value, level));
        }

        traverseTree(tree.left, offset - 1, level + 1, offsetToValueLevel);
        traverseTree(tree.right, offset + 1, level + 1, offsetToValueLevel);
    }

}
