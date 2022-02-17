package by.library.yurueu.entity;

public abstract class BaseEntity {
    private Long id;

    public BaseEntity() {
    }

    public BaseEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}

        BaseEntity aThat = (BaseEntity) obj;

        if(getId() == null) {
            return aThat.getId() == null;
        } else return getId().equals(aThat.getId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * (getId() != null ? getId().hashCode() : 0);
    }
}