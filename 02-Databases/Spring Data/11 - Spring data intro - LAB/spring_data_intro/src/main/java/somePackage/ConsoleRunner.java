package somePackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import somePackage.services.MajorService;
import somePackage.services.StudentService;

@Component
public class ConsoleRunner implements CommandLineRunner {
//    @Autowired
    private StudentService studentService;

//    @Autowired
    private MajorService majorService;

    @Autowired
    public ConsoleRunner(StudentService studentService, MajorService majorService) {
        this.studentService = studentService;
        this.majorService = majorService;
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("ha ha ha");
        this.studentService.register();
        this.majorService.register();
//        Major major = new Major("Java DB Fundamentals");
//        Student student = new Student("John",new Date(), major);
//        majorService.create(major);
//        studentService.register(student);
    }
}

