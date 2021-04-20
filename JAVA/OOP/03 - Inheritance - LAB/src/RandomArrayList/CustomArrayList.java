package RandomArrayList;

import java.util.ArrayList;
import java.util.Random;

public class CustomArrayList<T> extends ArrayList<T> {
        public T getRandomElement() {
            int randomIndex = this.getRandomIndex();
            return super.remove(randomIndex);
        }

        private int getRandomIndex() {
            Random random = new Random();
            return random.nextInt(super.size());
        }
}
