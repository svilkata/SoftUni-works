import entities.Address;
import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        MyConnector.createConnection("root", "", "custom-orm");
        Connection connection = MyConnector.getConnection();

        EntityManager<User> userEntityManager = new EntityManager<>(connection);
//
//        User user = new User("Ala bala", 36, LocalDate.now());
//        user.setUsername("pesho_new_finalv2");
//        userEntityManager.persist(user);

//        User userToDelete = userEntityManager.findFirst(User.class, "id=6");
//        System.out.println(userToDelete);
//        userEntityManager.delete(userToDelete);
//
        Iterable<User> testDisplayingSpecificColumns = userEntityManager.find(User.class, "age>27",
                "age", "registration_date");
        System.out.println(testDisplayingSpecificColumns);
//        user.setId(2);

//        userEntityManager.doAlter(User.class);

//        EntityManager<Address> addressEntityManager = new EntityManager<>(connection);
//        addressEntityManager.doCreate(Address.class);

        connection.close();

    }
}
