package by.library.yurueu.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Genre extends BaseEntity {
    private Long id;
    private String genreName;
    @Builder
    public Genre(Long id, String genreName) {
        setId(id);
        this.genreName = genreName;
    }
}
