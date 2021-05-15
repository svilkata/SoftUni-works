package p05_CustomLinkedList;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CustomLinkedListTest<T> {
    private CustomLinkedList<T> list;

    @Before
    public void setUp() {
        this.list = new CustomLinkedList<>();
    }

    @Test
    public void creatingLinkedListShouldHaveZeroCount() throws IllegalAccessException {
        //полето count
        Field field = Arrays.stream(CustomLinkedList.class.getDeclaredFields())
                .filter(f -> f.getName().equals("count"))
                .findFirst()
                .orElse(null);

        assertNotNull("Field count was null! Invalid object state", field);

        field.setAccessible(true);
        int count = (int)field.get(this.list);
        assertEquals("Creating empty LinkedList did not have zero count", 0, count);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getOnEmptyListShouldThrowExceptionWithZeroAsiindex() {
        this.list.get(0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getOnEmptyListShouldThrowExceptionWithNegativeAsiindex() {
        this.list.get(-3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getOnEmptyListShouldThrowExceptionWithPositiveAsiindex() {
        this.list.get(3);
    }
}