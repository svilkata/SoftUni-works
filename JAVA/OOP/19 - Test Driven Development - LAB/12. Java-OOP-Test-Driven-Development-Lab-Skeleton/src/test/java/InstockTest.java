import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

public class InstockTest {
    private static final String[] LABELS = {"G", "C", "B", "F", "E", "D", "A"};
    private static final String[] LABELS_TWO = {"G", "C", "B", "F", "E", "D", "A"};
    private static final int INITIAL_QUANTITY = 42;
    private static final Product PRODUCT = new Product("Cosmos", 3.14, INITIAL_QUANTITY);
    private Instock instock;

    @Before
    public void setUP() {
        this.instock = new Instock();
    }

    @Test
    public void instockGetCountShouldReturnZeroOrEmptyInstock() {
        int count = instock.getCount();
        assertEquals(0, count);
    }

    @Test
    public void addingProductShouldIncreaseCount() {
        this.instock.add(PRODUCT);
        assertEquals(1, this.instock.getCount());
    }

    @Test
    public void containsShouldReturnTrueWhenProductIsPresent() {
        this.instock.add(PRODUCT);
        assertTrue(this.instock.contains(PRODUCT));
    }

    @Test
    public void containsShouldReturnFalseWhenProductIsAbsent() {
        this.instock.add(PRODUCT);
        Product testProduct = new Product("Pesho", 3.14, 10);
        assertFalse(this.instock.contains(testProduct));
    }

    @Test
    public void findShouldReturnCorrectProductAccordingToInsertionOrder() {
        fillWithProducts();
        int order = LABELS.length / 2;
        Product returnedFoundProduct = this.instock.find(order);

        assertEquals(LABELS[order], returnedFoundProduct.getLabel());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void findWithInivalidIndexShouldThrowException() {
        fillWithProducts();
//        this.instock.find(LABELS.length);
        this.instock.find(-1);
    }

    @Test
    public void changeQuantityShouldActuallyReplaceOldQuantity() {
        this.instock.add(PRODUCT);
        this.instock.changeQuantity(PRODUCT.getLabel(), INITIAL_QUANTITY + 25);
        assertEquals(INITIAL_QUANTITY + 25, PRODUCT.getQuantity());

    }

    @Test(expected = IllegalArgumentException.class)
    public void changeQuantityWithInvalidProductLabelShouldThrowException() {
        this.instock.add(PRODUCT);
        this.instock.changeQuantity(PRODUCT.getLabel() + "123", INITIAL_QUANTITY + 13);
    }

    @Test
    public void findByLabelShouldReturnCorrectLabel() {
        this.instock.add(PRODUCT);
        Product byLabel = this.instock.findByLabel(PRODUCT.getLabel());
//        Product product = new Product(PRODUCT.getLabel(), 12, 12);
        assertEquals(PRODUCT, byLabel);

    }

    @Test(expected = IllegalArgumentException.class)
    public void findByLabelWhenNosuchLabeledProductExistShouldThrowException() {
        this.instock.add(PRODUCT);
        this.instock.findByLabel(PRODUCT.getLabel() + "123");
    }

    @Test
    public void findFirstByAlphabeticalOrderShouldReturnEmptyCollectionWithOutOFRangeArgument() {
        fillWithProducts();
        Iterable<Product> products = this.instock.findFirstByAlphabeticalOrder(LABELS.length + 1);

        int count = countsElementsInCollection(products);

        assertEquals(0, count);
    }

    @Test
    public void findFirstByAlphabeticalOrderShouldReturnCorrectCountOfProducts() {
        fillWithProducts();
        Iterable<Product> products = this.instock.findFirstByAlphabeticalOrder(LABELS.length);

        int count = countsElementsInCollection(products);
        assertEquals(LABELS.length, count);
    }

    @Test
    public void findFirstByAlphabeticalOrderShouldReturnCorrectCountOfProductsWhenCountIsLessThanSize() {
        fillWithProducts();
        int count = LABELS.length / 2;
        Iterable<Product> products = this.instock.findFirstByAlphabeticalOrder(count);

        int counter = countsElementsInCollection(products);
        assertEquals(count, counter);
    }

    @Test
    public void findFirstByAlphabeticalOrderShouldReturnElementsInCorrectOrder() {
        fillWithProducts();
        Iterable<Product> products = this.instock.findFirstByAlphabeticalOrder(LABELS.length);

        String[] arr1 = LABELS.clone();
        Arrays.sort(arr1);

        String[] arr = new String[LABELS.length];
        int index = 0;
        for (Product product : products) {
            arr[index++] = product.getLabel();
        }

        assertArrayEquals(arr1, arr);
    }

    @Test
    public void getIterableShouldReturnAllElements() {
        for (int j = 0; j < LABELS_TWO.length; j++) {
            this.instock.add(new Product(LABELS_TWO[j], 12.5, j));
        }

        Iterator<Product> iterator = this.instock.iterator();

        String[] resNames = new String[LABELS_TWO.length];

        int index = 0;
        for (; iterator.hasNext(); ) {
            resNames[index++] = iterator.next().getLabel();
        }

        assertArrayEquals(LABELS, resNames);
    }

    @Test
    public void findAllInPriceRangeWhenNoFoundProductsInPriceRange() {
        fillWithProducts();
        Iterable<Product> products = this.instock.findAllInPriceRange(0.001, 0.009);

        int sizeOfProduct = 0;
        if (products != null) {
            for (Product product : products) {
                sizeOfProduct++;
            }
        }

        assertEquals(0, sizeOfProduct);
    }

    @Test
    public void findAllInPriceRangeWhenThereAreProductsInPriceRange() {
        fillWithProducts();
        Iterable<Product> products = this.instock.findAllInPriceRange(0, 2.3);

        String[] arrExpected = new String[3];
        int index = 0;
        //First 3 items are in the search
        for (String label : LABELS.clone()) {
            arrExpected[index++] = label;
            if (index == 3) {
                break;
            }
        }

        int z = 4;
        arrExpected = reverseArray(arrExpected);
        int y = 4;


        String[] arrResult = new String[3];
        int indexArrResult = 0;
        if (products != null) {
            for (Product product : products) {
                arrResult[indexArrResult] = product.getLabel();
                indexArrResult++;
            }
        }
        int h = 4;

        assertArrayEquals(arrExpected, arrResult);
    }

    private String[] reverseArray(String[] arrToReverse) {
        String[] resultArr = new String[3];

        for (int i = arrToReverse.length - 1; i >= 0; i--) {
            resultArr[3-1-i] = arrToReverse[i];
        }

        return resultArr;
    }


    private int countsElementsInCollection(Iterable<Product> products) {
        int counter = 0;
        for (Product product : products) {
            counter++;
        }
        return counter;
    }


    private void fillWithProducts() {
        for (int j = 0; j < LABELS.length; j++) {
            this.instock.add(new Product(LABELS[j], j + 0.01, j));
        }
    }


}