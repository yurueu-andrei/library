package by.library.yurueu.entity;

import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Book extends BaseEntity {
    private Long id;
    private String title;
    private int pagesNumber;
    private String imagePath;

    @Builder
    public Book(Long id, String title, int pagesNumber, String imagePath) {
        setId(id);
        this.title = title;
        this.pagesNumber = pagesNumber;
        this.imagePath = imagePath;
    }
}