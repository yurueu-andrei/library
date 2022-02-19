package by.library.yurueu.entity;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Author extends BaseEntity {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String imagePath;

    @Builder
    public Author(Long id, String firstName, String lastName, LocalDate birthDate, String imagePath) {
        setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.imagePath = imagePath;
    }
}