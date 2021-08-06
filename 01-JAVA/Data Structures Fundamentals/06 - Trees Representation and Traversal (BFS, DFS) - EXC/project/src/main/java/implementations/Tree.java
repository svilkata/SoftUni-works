package implementations;

import interfaces.AbstractTree;

import java.util.*;
import java.util.stream.Collectors;

public class Tree<E> implements AbstractTree<E> {
    private class DeepestNode<E> {
        private Tree<E> node;
        private int depth;

        public DeepestNode(Tree<E> node, int depth) {
            this.node = node;
            this.depth = depth;
        }

        public Tree<E> getNode() {
            return node;
        }

        public void setNode(Tree<E> node) {
            this.node = node;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }
    }

    private E value;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E value, Tree<E>... children) {
        this.value = value;
        this.children = this.initChildren(children);
    }

    private List<Tree<E>> initChildren(Tree<E>[] children) {
        List<Tree<E>> treeChildren = new ArrayList<>();

        for (Tree<E> child : children) {
            child.setParent(this);
            treeChildren.add(child);
        }
        return treeChildren;
    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;

    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public E getKey() {
        return this.value;
    }

    public List<Tree<E>> getChildren() {
        return this.children;
    }

    private String getAsStringDFS(Tree<E> node, int indent) {
        StringBuilder result = new StringBuilder(getIndent(indent) + node.getKey());

        for (Tree<E> child : node.getChildren()) {
            result.append(System.lineSeparator()).append(getAsStringDFS(child, indent + 2));
        }

        return result.toString();
    }

    private String getIndent(int indent) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < indent; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }

    @Override
    public String getAsString() {
        return getAsStringDFS(this, 0);
    }

    @Override
    public List<E> getLeafKeys() {
        List<E> leafsList = new ArrayList<>();
        Queue<Tree<E>> treeQueue = new ArrayDeque<>();
        treeQueue.offer(this);

        while (!treeQueue.isEmpty()) {
            Tree<E> currentNode = treeQueue.poll();

            //do String magic
            if (currentNode.getChildren().isEmpty()) {
                leafsList.add(currentNode.getKey());

            } else {
                for (Tree<E> childNode : currentNode.getChildren()) {
                    treeQueue.offer(childNode);
                }
            }


        }

        return leafsList;
    }

    @Override
    public List<E> getMiddleKeys() {
        List<E> leafsList = new ArrayList<>();
        Queue<Tree<E>> treeQueue = new ArrayDeque<>();
        treeQueue.offer(this);

        while (!treeQueue.isEmpty()) {
            Tree<E> currentNode = treeQueue.poll();

            //do String magic
            if (!currentNode.getChildren().isEmpty() && currentNode.getParent() != null) {
                leafsList.add(currentNode.getKey());
            } else {
                for (Tree<E> childNode : currentNode.getChildren()) {
                    treeQueue.offer(childNode);
                }
            }
        }
        return leafsList;
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
        Stack<Tree<E>> longestPath = new Stack<>();
        Stack<Tree<E>> currentPath = new Stack<>();
        currentPath.push(this);

        getLongestPathDFS(this, longestPath, currentPath);

        List<Tree<E>> resultPath = new ArrayList<>();
        return longestPath.pop();
    }


    @Override
    public List<E> getLongestPath() {
        Stack<Tree<E>> longestPath = new Stack<>();
        Stack<Tree<E>> currentPath = new Stack<>();
        currentPath.push(this);

        getLongestPathDFS(this, longestPath, currentPath);

        List<E> resultPath = new ArrayList<>();

        while (!longestPath.isEmpty()) {
            resultPath.add(longestPath.pop().getKey());
        }

        Collections.reverse(resultPath);

        return resultPath;
    }

    private void getLongestPathDFS(Tree<E> node, Stack<Tree<E>> longestPath, Stack<Tree<E>> currentPath) {
        if (node.getChildren().isEmpty()) {
            if (longestPath.size() < currentPath.size()) {
                longestPath.clear();

                for (Tree<E> currentPathNode : currentPath) {
                    longestPath.push(currentPathNode);
                }
            }
        } else {
            for (Tree<E> childNode : node.getChildren()) {
                currentPath.push(childNode);
                getLongestPathDFS(childNode, longestPath, currentPath);
                currentPath.pop();
            }

        }

    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        List<List<E>> paths = new ArrayList<>();
        Stack<Tree<E>> currentPath = new Stack<>();
        currentPath.push(this);

        getAllPathsWithGivenSum(this, paths, currentPath, sum);

        return paths;
    }

    private void getAllPathsWithGivenSum(Tree<E> node, List<List<E>> paths, Stack<Tree<E>> currentPath,
                                         int targetSum) {
        if (node.getChildren().isEmpty()) {
            if (currentPath.stream().mapToInt(x-> (int) x.getKey()).sum() == targetSum) {
                paths.add(new ArrayList<>(currentPath.stream().map(x -> x.getKey()).collect(Collectors.toList())));
            }
        } else {
            for (Tree<E> childNode : node.getChildren()) {
                currentPath.push(childNode);
                getAllPathsWithGivenSum(childNode, paths, currentPath, targetSum);
                currentPath.pop();
            }
        }
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        return null;
    }
}



