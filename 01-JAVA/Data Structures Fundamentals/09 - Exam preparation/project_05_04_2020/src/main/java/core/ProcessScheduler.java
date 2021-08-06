package core;

import model.Task;
import shared.Scheduler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProcessScheduler implements Scheduler {
    Node first;
    Node last;
    int size;

    //SinglyLinkedList
    static class Node {
        Task task;
        Node next;
//        private Node head;

        public Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    @Override
    public void add(Task task) { //offer
        if (first == null) {
            first = new Node(task);
            last = first;
        } else {
            attachAfter(last, task);
        }

        this.size++;

    }

    void attachAfter(Node destination, Task task) {
        Node node = new Node(task);

        if (destination == last) {
            //last
            last = node;
        } else {
            //middle
            node.next = destination.next;
        }

        destination.next = node;
    }

    @Override
    public Task process() { //poll
        if (this.size() == 0) {
            return null;
        }
        
        Task task = peek();
        remove(task.getId());
        return task;
    }

    @Override
    public Task peek() {
        if (this.size() == 0) {
            return null;
        }

        return first.task;
    }

    @Override
    public Boolean contains(Task task) {
        for (Node node = first; node != null; node = node.next) {
            if (node.task.getId() == task.getId()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Boolean remove(Task task) {
        return remove(task.getId());
    }

    @Override
    public Boolean remove(int id) {
        NodeAndPrevious result = getNodeAndPreviousByTaskID(id);

        if (result.node == first) {
//            first.next = null;
            first = result.node.next;
        } else {
            result.previous.next = result.node.next;
            if (last == result.node) {
                last = result.previous;
            }
        }

        size--;

        return true;
    }

    @Override
    public void insertBefore(int id, Task task) {
        NodeAndPrevious result = getNodeAndPreviousByTaskID(id);

        if (result.node == first) {
            Node node = new Node(task);
            node.next = first;
            first = node;
        } else {
            attachAfter(result.previous, task);
        }

        size++;
    }

    @Override
    public void insertAfter(int id, Task task) {
        Node node = getNodeByTaskID(id);
        attachAfter(node, task);
        size++;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;

    }

    @Override
    public Task[] toArray() {
        return this.toList().toArray(new Task[size]);
    }

    @Override
    public void reschedule(Task first, Task second) {
        Node a = getNodeByTaskID(first.getId());
        Node b = getNodeByTaskID(second.getId());

        Task aTask = a.task;
        a.task = b.task;
        b.task = aTask;
    }

    @Override
    public List<Task> toList() {
        List<Task> tasks = new ArrayList<>();
        for (Node node = first; node != null; node = node.next) {
            tasks.add(node.task);
        }
        return tasks;
    }

    @Override
    public void reverse() {
        List<Task> tasks = this.toList();
        this.clear();
        for (int i = tasks.size() - 1; i >= 0; i--) {
            add(tasks.get(i));
        }

    }

    @Override
    public Task find(int id) {
        return getNodeByTaskID(id).task;
    }

    @Override
    public Task find(Task task) {
        return find(task.getId());
    }

    Node getNodeByTaskID(int taskID) {
        for (Node node = first; node != null; node = node.next) {
            if (node.task.getId() == taskID) {
                return node;
            }
        }

        throw new IllegalArgumentException();
    }

    class NodeAndPrevious {
        Node node;
        Node previous;

        public NodeAndPrevious(Node node, Node previous) {
            this.node = node;
            this.previous = previous;
        }
    }

    NodeAndPrevious getNodeAndPreviousByTaskID(int taskID) {
        Node previous = null;
        for (Node node = first; node != null; node = node.next) {
            if (node.task.getId() == taskID) {
                return new NodeAndPrevious(node, previous);
            }
            previous = node;
        }

        throw new IllegalArgumentException();
    }
}
