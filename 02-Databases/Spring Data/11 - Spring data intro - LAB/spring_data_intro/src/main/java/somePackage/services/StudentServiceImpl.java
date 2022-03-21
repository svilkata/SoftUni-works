package somePackage.services;

import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public void register() {
//        studentRepository.register(); //блаагодарение на Спринг, пропускаме този ред да го пишем
    }
}
