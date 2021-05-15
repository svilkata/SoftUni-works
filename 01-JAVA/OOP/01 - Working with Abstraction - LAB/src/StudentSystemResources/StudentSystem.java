package StudentSystemResources;

import java.util.HashMap;
import java.util.Map;

public class StudentSystem {
    private Map<String, Student> store;

    public StudentSystem() {
        this.store = new HashMap<>();
    }

    public Map<String, Student> getStore() {
        return this.store;
    }

    public void executeCommand(String[] args) {
        String commandName = args[0];
        String studentName = args[1];

        if (commandName.equals("Create")) {
            executeCreateCommand(args, studentName);
        } else if (commandName.equals("Show")) {
            executeShowCommand(studentName);
        }
    }

    private void executeShowCommand(String studentName) {
        if (!this.store.containsKey(studentName)) {
            return;
        }
        Student student = store.get(studentName);
        System.out.println(student);
    }

    private void executeCreateCommand(String[] args, String studentName) {
        int age = Integer.parseInt(args[2]);
        double grade = Double.parseDouble(args[3]);

        if (!store.containsKey(studentName)) {
            Student student = new Student(studentName, age, grade);
            this.store.put(studentName, student);
        }
    }
}
