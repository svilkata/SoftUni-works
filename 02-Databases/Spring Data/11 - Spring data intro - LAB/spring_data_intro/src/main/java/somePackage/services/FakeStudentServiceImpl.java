package somePackage.services;

public class FakeStudentServiceImpl implements StudentService {
    @Override
    public void register() {
        System.out.println("already registered");
    }
}
