package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.naming.OperationNotSupportedException;
import java.util.Random;

public class DatabaseTest {
    private Person[] elements;
    private Database database;


    @Before
    public void setDatabaseFields() throws OperationNotSupportedException {
        this.elements = initPeople(15); //database start with 15 elements
        this.database = new Database(this.elements);
    }

    private Person[] initPeople(int size) {
        Person[] elementsToCreate = new Person[size];
        Person personToAdd;
        String[] possibleNames = generateNames();
        for (int i = 0; i < size; i++) {
//            personToAdd = new Person(new Random().nextInt(50), possibleNames[new Random().nextInt(size)]);
            personToAdd = new Person(i + 1, possibleNames[new Random().nextInt(size)]);
            elementsToCreate[i] = personToAdd;
        }

        return elementsToCreate;
    }

    private String[] generateNames() {
        return new String[]{"Ivan", "Dragan", "Petkan", "Kolyo", "Ivanichka", "Petkanka", "Svilen",
                "Milen", "Sasho", "Ivo", "Penka", "Chapai", "Petka", "Kiro", "Petko", "Sirma", "Plamen",
                "Vlado", "Delyo", "Zdravka", "Denitsa", "Miroslava"};
    }

    @Test
    public void databaseCreationTestShouldCreateObjectWithValidParameters() {
        Person[] elementsToCheck = this.database.getElements();

        Assert.assertEquals(this.elements.length, elementsToCheck.length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void databaseCreationTestShouldThrowOperationNotSupportedExceptionWithNullVarArgsArguments() throws OperationNotSupportedException {
        new Database();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void databaseCreationTestShouldThrowOperationNotSupportedExceptionWithMoreThanSixteenElements() throws OperationNotSupportedException {
        Person[] seventeenPersons = initPeople(17);

        new Database(seventeenPersons);
    }

    @Test
    public void databaseCreationTestShouldSetElementsInCorrectOrderAccordingToInitialParameters() {
        Person[] elementsToChek = this.database.getElements();

        Assert.assertArrayEquals(this.elements, elementsToChek);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void databaseFunctionalTestWhenAddingNullElementShouldThrowException() throws OperationNotSupportedException {
        this.database.add(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void databaseFunctionalTestWhenAddingElementWithNegativeIDShouldThrowException() throws OperationNotSupportedException {
        Person personToAdd = new Person(-5, "Nikolinka");
        this.database.add(personToAdd);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void databaseFunctionalTestWhenAddingCorrectElementButIDalreadyExistsShouldThrowException() throws OperationNotSupportedException {
        Person personToAdd = new Person(8, "SameID name");
        this.database.add(personToAdd);
    }

    @Test
    public void databaseFunctionalTestWhenAddingCorrectElement_16_ShouldInsertElementAtFirstEmptyIndex() throws OperationNotSupportedException {
        Person personToAdd = new Person(16, "Next ID name");
        this.database.add(personToAdd); //we add here real element to the database

        Person[] elementsToCheck = this.database.getElements();
        Person lastElement = elementsToCheck[elementsToCheck.length - 1];
        Assert.assertEquals(personToAdd, lastElement);
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void databaseFunctionalTestWhenAddingCorrectElement_17_ShouldThrowException() throws ArrayIndexOutOfBoundsException, OperationNotSupportedException {
        Person personToAdd16 = new Person(16, "Next ID name");
        this.database.add(personToAdd16);

        Person personToAdd17 = new Person(17, "17th name person");
        this.database.add(personToAdd17); //we are trying to add here 17th element
    }


    @Test(expected = OperationNotSupportedException.class)
    public void databaseFunctionalTestWhenRemovingOnEmptyDatabaseShouldThrowException() throws OperationNotSupportedException {
        for (int i = 0; i < this.elements.length; i++) {
            this.database.remove();
        }

        this.database.remove();
    }

    @Test
    public void databaseFunctionalTestWhenRemovingShouldRemoveElement() throws OperationNotSupportedException {
        this.database.remove();
        Person[] elementsToCheck = this.database.getElements();

        //Посленият останал елемент от базата данни
        Person lastElement = elementsToCheck[elementsToCheck.length - 1];

        // сравняваме дали последния елемент е същия
        Assert.assertEquals(this.elements[this.elements.length-2], lastElement);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void databaseFunctionalTestFindByIdNotExistingIDShouldThrowException() throws OperationNotSupportedException {
        this.database.findById(-5);
    }

    @Test
    public void databaseFunctionalTestFindByIdExistingID() throws OperationNotSupportedException {
        Person personToCheck = null;
        for (Person element : elements) {
            if (element.getId() == 5) {
                personToCheck = element;
                break;
            }
        }

        Assert.assertEquals(personToCheck, this.database.findById(5));
    }

    @Test (expected = OperationNotSupportedException.class)
    public void findByUsernameWhenUsernameIsNull_ShouldThrowException() throws OperationNotSupportedException {
             this.database.findByUsername(null);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void findByUsernameWhenNoUserIsPresent_ShouldThrowException() throws OperationNotSupportedException {
        this.database.findByUsername("ZAZAZA");
    }

    @Test
    public void findByUsernameWhenUserIsPresent() throws OperationNotSupportedException {
        Person[] elementsToSearch = this.database.getElements();
        String usernameInTheList = elementsToSearch[0].getUsername();

        Person foundUsername = this.database.findByUsername(usernameInTheList);
        Assert.assertEquals(elementsToSearch[0], foundUsername);
    }
}