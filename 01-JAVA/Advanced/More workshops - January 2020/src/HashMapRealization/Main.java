package HashMapRealization;

public class Main {
    public static void main(String[] args) {

        Node alice = new Node("Alice", "Sofia");
        Node zoe = new Node("Zoe", "Brussels");
        Node charlie = new Node("Charlie", "London");
        Node svilen = new Node("Svilen", "Amsterdam");

        NodeList list = new NodeList();
        list.add(alice);
        list.add(zoe);
        list.add(charlie);
        list.add(svilen);

        list.overrideElementWithKey("Richard", "Plovdiv");
        System.out.println(list.toString());

//        NodeMap map = new NodeMap();
//        map.put("Alice", "Sofia");
//        map.put("Bob", "Varna");
//        map.put("Charlie", "Plovdiv");
//        map.put("Zoe", "Brussels");
//        map.put("Zoe", "Plovdiv");
//        map.put("Charlie", "London");
//        map.put("Ivan", "Burgas");
//
//        System.out.println(map.get("Zoe"));


    }
}
