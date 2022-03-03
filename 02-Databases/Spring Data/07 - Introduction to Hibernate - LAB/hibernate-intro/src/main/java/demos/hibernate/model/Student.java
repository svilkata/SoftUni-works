package demos.hibernate.model;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Student {
    private int id;

    @NonNull                 //птрави полето name като required argument на конструктора
    private String name;

    private Date registrationDate = new Date();
}
