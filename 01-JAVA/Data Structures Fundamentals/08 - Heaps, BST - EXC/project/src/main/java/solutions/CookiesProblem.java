package solutions;

import model.Product;

import java.util.ArrayDeque;
import java.util.Queue;

public class CookiesProblem {
    public Integer solve(int requiredSweetness, int[] cookiesSweetness) {
        MinHeap<Product> cookiesPriorityQueue = new MinHeap<Product>();

        for (int sweetness : cookiesSweetness) {
            cookiesPriorityQueue.add(new Product(sweetness));
        }

        int steps = 0;

        int currentMinSweetness = cookiesPriorityQueue.peek().getPrice();
        while (currentMinSweetness < requiredSweetness && cookiesPriorityQueue.size() > 1) {
            int leastSweetCookie = cookiesPriorityQueue.poll().getPrice();
            int secondLeastSweetCookie = cookiesPriorityQueue.poll().getPrice();

            int combinedSweetness = leastSweetCookie + 2 * secondLeastSweetCookie;
            cookiesPriorityQueue.add(new Product(combinedSweetness));

            currentMinSweetness = cookiesPriorityQueue.peek().getPrice();
            steps++;
        }

        return currentMinSweetness > requiredSweetness ? steps : -1;

    }


}

