package HashMapRealization;

public class NodeList {
    private Node firstElement = null;
    private int size = 0;

    public void add(Node node) {
        this.size++;
        if (firstElement == null) {
            firstElement = node;
            return;
        }

        Node currentNode = firstElement;
        while (true) {
            if (currentNode.next == null) { //check if this is the last node
                currentNode.next = node;
                return;
            }
            currentNode = currentNode.next; //we go to the next node
        }
    }

    @Override
    public String toString() {
        if (firstElement == null) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder();
            Node currentNode = firstElement;
            while (currentNode != null) {
                sb.append("{");
                sb.append(currentNode.toString());
                sb.append("}");
                sb.append(",");
                currentNode = currentNode.next;
            }
            return sb.toString();
        }
    }


    public Node getFirstElementWithKey(String key) {
        Node currentNode = firstElement;
        while (currentNode != null) { //докато стигнем последния Node
            if (key.equals(currentNode.key)) {
                return currentNode;
            }
            currentNode = currentNode.next; //we go to the next node
        }

        return null;

    }

    //only works if the key exists, otherwise does nothing
    public void overrideElementWithKey(String key, String value) {
        if (firstElement == null) {
//            throw new IndexOutOfBoundsException("You cannot override an empty ");
            return;
        }

        Node nodeToReplace = new Node(key, value);
        //override 1st element
        if (key.equals(firstElement.key)) {
            nodeToReplace.next = firstElement.next;
            firstElement = nodeToReplace;
            return;
        }

        Node currentNode = firstElement;
        while (currentNode != null) {
            if (currentNode.next != null && currentNode.next.key.equals(key)) {
                nodeToReplace.next = currentNode.next.next;
                currentNode.next = nodeToReplace;
                return;
            }

            currentNode = currentNode.next;
        }
    }
}
