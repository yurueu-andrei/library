package by.library.yurueu.entity;

import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Role extends BaseEntity {
    private Long id;
    private String roleName;

    @Builder
    public Role(Long id, String roleName) {
        setId(id);
        this.roleName = roleName;
    }
}