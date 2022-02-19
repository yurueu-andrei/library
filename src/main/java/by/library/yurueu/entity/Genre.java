package by.library.yurueu.entity;

import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Genre extends BaseEntity {
    private String genreName;

    @Builder
    public Genre(Long id, String genreName) {
        setId(id);
        this.genreName = genreName;
    }
}