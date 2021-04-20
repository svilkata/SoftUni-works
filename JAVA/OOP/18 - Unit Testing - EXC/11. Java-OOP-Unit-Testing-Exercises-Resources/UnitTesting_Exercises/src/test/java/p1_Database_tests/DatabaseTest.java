package p1_Database_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p01_Database.Database;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    private Integer[] numbers;
    private Database database;

    @Before
    public void setDatabaseFiled() throws OperationNotSupportedException {
        this.numbers = initNumbers(6);
        this.database = new Database(this.numbers);
    }

    private Integer[] initNumbers(int size) {
        Integer[] arr = new Integer[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        return arr;

    }

    @Test
    public void databaseCreationTestShouldCreateObjectWithValidParameters() {
        Integer[] elements = this.database.getElements();

        Assert.assertEquals(numbers.length, elements.length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void databaseCreationTestShouldThrowOperationNotSupportedExceptionWithNullVarArgsArguments() throws OperationNotSupportedException {
        new Database();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void databaseCreationTestShouldThrowOperationNotSupportedExceptionWithMoreThanSixteenElements() throws OperationNotSupportedException {
        Integer[] nums = new Integer[17];

        for (int i = 0; i < 17; i++) {
            nums[i] = i;
        }

        new Database(nums);

    }

    @Test
    public void databaseCreationTestShouldSetElementsInCorrectOrderAccordingToInitialParameters() {
        Integer[] elements = this.database.getElements();

        Assert.assertArrayEquals(numbers, elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void databaseFunctionalTestWhenAddingNullElementShouldThrowException() throws OperationNotSupportedException {
        this.database.add(null);
    }

    @Test
    public void databaseFunctionalTestWhenAddingCorrectElementShouldInsertElementAtFirstEmptyIndex() throws OperationNotSupportedException {
        this.database.add(42);

        Integer[] elements = this.database.getElements();

        int lastElement = elements[elements.length - 1];

        Assert.assertEquals(42, lastElement);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void databaseFunctionalTestWhenRemovingOnEmptyDatabaseShouldThrowException() throws OperationNotSupportedException {
        for (int i = 0; i < this.numbers.length; i++) {
            this.database.remove();
        }

        this.database.remove();
    }

    @Test
    public void databaseFunctionalTestWhenRemovingShouldRemoveElement() throws OperationNotSupportedException {
        this.database.remove();
        Integer[] elements = this.database.getElements();

        Integer lastElement = elements[elements.length - 1];

        Assert.assertEquals(this.numbers[this.numbers.length-2], lastElement);
    }


}
