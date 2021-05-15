package RandomArrayList;

public class Main {
    public static void main(String[] args) {
        CustomArrayList<Integer> arrayList = new CustomArrayList<>();

        arrayList.add(5);
        arrayList.add(7);
        arrayList.add(2);
        arrayList.add(10);
        arrayList.add(8);

        System.out.println(arrayList.getRandomElement());
        System.out.println(arrayList.size());
    }
}
