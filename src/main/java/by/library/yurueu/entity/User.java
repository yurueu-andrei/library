package by.library.yurueu.entity;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class User extends BaseEntity {
    private Long id;
    private String firstName;
    private String lastName;
    private String passportNumber;
    private String email;
    private String address;
    private LocalDate birthDate;

    @Builder
    public User(Long id, String firstName, String lastName, String passportNumber, String email, String address, LocalDate birthDate) {
        setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
    }
}