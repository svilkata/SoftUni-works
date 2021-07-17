import implementations.DoublyLinkedList;
import implementations.DoublyLinkedList.Node;

import java.util.Iterator;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<String> builtInLinkedList = new LinkedList<>();

        DoublyLinkedList<String> people = new DoublyLinkedList<>();
        builtInLinkedList.addLast("joro");
        builtInLinkedList.addLast("pesho");
        builtInLinkedList.addLast("misho");
        builtInLinkedList.addLast("grisho");

//        for (String lrr : builtInLinkedList) {
//            System.out.println(lrr);
//        }

        Iterator<String> iterator = builtInLinkedList.iterator();
        while (iterator.hasNext()) {
            String person = iterator.next(); //връща стринг
            if (person.equals("pesho")) {
                iterator.remove();
            } else {
                System.out.println(person);
            }
        }

        Node<String> node = people.getHead();//първия елемент от списъка
        while (node != null) {
            if (node.getElement().equals("pesho")) {
                people.remove(node);
            } else {
                System.out.println(node.getElement());
            }

            node = node.getNext();
        }


    }
}
