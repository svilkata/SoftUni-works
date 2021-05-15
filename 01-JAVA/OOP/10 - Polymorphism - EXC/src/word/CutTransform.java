package word;

import java.util.ArrayDeque;
import java.util.Deque;

public class CutTransform implements TextTransform {
    private Deque<String> memory;

    public CutTransform(Deque<String> memory) {
        this.memory = memory;
    }

    @Override
    public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
        this.memory.clear();
        String removedText = text.toString().substring(startIndex, endIndex);
        if (!removedText.isEmpty()) {
            this.memory.push(removedText);
        }
        text.replace(startIndex, endIndex, "");
    }
}
