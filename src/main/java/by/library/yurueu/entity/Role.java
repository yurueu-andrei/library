package by.library.yurueu.entity;

public class Role {
    private long id;
    private String roleName;

    public Role(long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}

        Role aThat = (Role) obj;

        if(getRoleName() == null) {
            if(aThat.getRoleName() != null) {return false;}
        } else if(!getRoleName().equals(aThat.getRoleName())) { return false;}

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(getClass().getSimpleName())
                .append("{roleName=").append(roleName)
                .append("}");
        return sb.toString();
    }
}