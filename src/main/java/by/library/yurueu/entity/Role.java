package by.library.yurueu.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {
    private Long id;
    private String roleName;

    @Builder
    public Role(Long id, String roleName) {
        setId(id);
        this.roleName = roleName;
    }
}