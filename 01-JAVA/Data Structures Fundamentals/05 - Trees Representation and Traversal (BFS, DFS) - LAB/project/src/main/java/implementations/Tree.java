package implementations;

import interfaces.AbstractTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree() {
        this.key = null;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    public Tree(E key, Tree<E>... children) {
        this.key = key;
        this.children = new ArrayList<>();
        for (Tree<E> child : children) {
            this.children.add(child);
            child.parent = this;
        }
    }

    @Override
    public List<E> orderBfs() {
        List<E> result = new ArrayList<>();
        if (this.key == null) {
            return result;
        }
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);

        while (queue.size() > 0) {
            Tree<E> current = queue.poll();
            result.add(current.key);

            for (Tree<E> child : current.children)
                queue.offer(child);
        }

        return result;
    }

//    @Override
//    public List<E> orderDfs() {
//        List<E> result = new ArrayList<>();
//        Deque<Tree<E>> stack = new ArrayDeque<>();
//        stack.push(this);
//
//        while (stack.size() > 0) {
//            Tree<E> current = stack.pop();
//            result.add(current.key);
//
//            for (Tree<E> child : current.children)
//                stack.push(child);
//        }
//
//        return result;
//    }

    @Override
    public List<E> orderDfs() {
        List<E> order = new ArrayList<>();
        this.dfs(this, order);
        return order;
    }

    private void dfs(Tree<E> tree, List<E> order) {
        for (Tree<E> child : tree.children) {
            this.dfs(child, order);
        }
        order.add(tree.key);
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {
        Deque<Tree<E>> queue = new ArrayDeque<>();
        if (this.key == parentKey) {
            child.parent = this;
            this.children.add(child);
            return;
        }

        queue.offer(this);

        while (queue.size() > 0) {
            Tree<E> current = queue.poll();

            for (Tree<E> ch : current.children) {
                if (ch.key == parentKey) {
                    child.parent = ch;
                    ch.children.add(child);
                    return;

                }
                queue.offer(ch);
            }
        }
    }

    @Override
    public void removeNode(E nodeKey) {
        if (this.key == nodeKey && this.parent == null) {
            this.children = new ArrayList<>();
            this.key = null;
            return;
        }

        Tree<E> nodeToDelete = getNode(nodeKey);
        if (nodeToDelete == null) {
            return;
        }

        inTheParentListDeleteThatLeaf(nodeToDelete);
    }

    private void inTheParentListDeleteThatLeaf(Tree<E> nodeToDelete) {
        nodeToDelete.parent.children.remove(nodeToDelete); //ако е листо
        nodeToDelete.parent = null; //ако е листо
    }


    //returns a subtree with root nodeKey
    public Tree<E> getNode(E nodeKey) {
        if (this.key.equals(nodeKey)) {
            return this;
        }

        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);

        while (queue.size() > 0) {
            Tree<E> current = queue.poll();

            for (Tree<E> ch : current.children) {
                if (ch.key.equals(nodeKey)) {
                    return ch;
                }
                queue.offer(ch);
            }
        }

        return null;
    }

    @Override
    public void swap(E firstKey, E secondKey) {
        if (firstKey.equals(secondKey)) {
            return;
        }

        Tree<E> firstSubtree = getNode(firstKey);
        Tree<E> secondSubtree = getNode(secondKey);

        if (firstSubtree.parent == null) {
            if (secondSubtree.children.isEmpty()) { //ако е листо
                inTheParentListDeleteThatLeaf(secondSubtree);
                secondSubtree.children.add(firstSubtree);
                firstSubtree.parent = secondSubtree;
            } else { //ако е среден връх

            }

            return;
        }

        if (secondSubtree.parent == null) {
            if (firstSubtree.children.isEmpty()) { //ако е листо
                inTheParentListDeleteThatLeaf(firstSubtree);
                firstSubtree.children.add(secondSubtree);
                secondSubtree.parent = firstSubtree;
            }

            return;
        }

        switchNodes(firstSubtree, secondSubtree);


//            if (firstSubtree.children.isEmpty() && secondSubtree.children.isEmpty()) { //разменяме две листа
//        {
//
//        }
//        } else if (secondSubtree.parent != null && firstSubtree.children.isEmpty()) {
//            switchLeaves(firstSubtree, secondSubtree);
//        }


    }

    private void switchNodes(Tree<E> subtree1, Tree<E> subtree2) {
        Tree<E> parent1 = subtree1.parent;
        Tree<E> parent2 = subtree2.parent;
        int indexSubtree1 = parent1.children.indexOf(subtree1);
        int indexSubtree2 = parent2.children.indexOf(subtree2);

        parent1.children.set(indexSubtree1, subtree2);
        parent2.children.set(indexSubtree2, subtree1);
        subtree1.parent = parent2;
        subtree2.parent = parent1;
    }
}



