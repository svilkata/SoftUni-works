import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Class reflectionClass = Reflection.class;

        /* Task 1
        System.out.println(reflectionClass);
        System.out.println(reflectionClass.getSuperclass());
        Class[] interfaces = reflectionClass.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println(anInterface);
        }
        Object o = reflectionClass.getDeclaredConstructor().newInstance();
        System.out.println(o.toString());**/

        Subject annotation = (Subject) reflectionClass.getDeclaredAnnotation(Subject.class);
        System.out.println(annotation.categories());

//        Author annot = (Author) reflectionClass.getDeclaredAnnotation(Subject.class);
//        System.out.println(annot.name());

        Arrays.stream(reflectionClass.getDeclaredFields())
                .filter(f -> !Modifier.isPrivate(f.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .forEach(f -> System.out.println(f.getName() + " must be private!"));


        Method[] allMethods = reflectionClass.getDeclaredMethods();
        List<Method> setters = new ArrayList<>();
        List<Method> getters = new ArrayList<>();

        for (Method method : allMethods) {
            int methodModifierNumber = method.getModifiers();
            if (isSetter(method) && !Modifier.isPrivate(methodModifierNumber)) {
                setters.add(method);
            } else if (isGetter(method) && !Modifier.isPublic(methodModifierNumber)) {
                getters.add(method);
            }

            Author author = method.getAnnotation(Author.class);
            if (author != null) {
                System.out.println(String.format("%s is written by %s",
                        method.getName(), author.name()));
            }
        }

        getters.sort((l, r) -> l.getName().compareTo(r.getName()));
        setters.sort((l, r) -> l.getName().compareTo(r.getName()));

        getters.forEach(g -> System.out.println(formatGetter(g)));
        setters.forEach(s -> System.out.println(formatSetter(s)));
    }

    private static String formatGetter(Method g) {
        return String.format("%s have to be public!",
                g.getName());
    }

    private static String formatSetter(Method s) {
        return String.format("%s have to be private!",
                s.getName());
    }

    private static boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")) {
            return false;
        }

        if (method.getReturnType() == void.class) {
            return false;
        }

        if (method.getParameterCount() != 0) {
            return false;
        }

        return true;
    }

    private static boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) {
            return false;
        }

        if (method.getReturnType() != void.class) {
            return false;
        }

        if (method.getParameterCount() != 1) {
            return false;
        }

        return true;
    }
}
