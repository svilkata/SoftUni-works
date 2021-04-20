package p04_BubbleSortTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BubbleTest {


    @Test
    public void bubbleSortShouldSortElements() {
        int[] arrOne = {42, 13, 69, 27};
        int[] arrExpected = {42, 13, 69, 27};

        Bubble.sort(arrOne);
        Arrays.sort(arrExpected);

        Assert.assertArrayEquals(arrExpected, arrOne);
    }
}