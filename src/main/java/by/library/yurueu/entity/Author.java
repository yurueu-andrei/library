package by.library.yurueu.entity;

import java.time.LocalDate;

public class Author {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String imagePath;

    public Author() {}

    public Author(Long id, String firstName, String lastName, LocalDate birthDate, String imagePath) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.imagePath = imagePath;
    }

    public Author(String firstName, String lastName, LocalDate birthDate, String imagePath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}

        Author aThat = (Author) obj;

        if(getId() == null) {
            if(aThat.getId() != null) {return false;}
        } else if(!getId().equals(aThat.getId())) { return false;}

        if(getFirstName() == null) {
            if(aThat.getFirstName() != null) {return false;}
        } else if(!getFirstName().equals(aThat.getFirstName())) { return false;}

        if(getLastName() == null) {
            if(aThat.getLastName() != null) {return false;}
        } else if(!getLastName().equals(aThat.getLastName())) { return false;}

        if(getBirthDate() == null) {
            if(aThat.getBirthDate() != null) {return false;}
        } else if(!getBirthDate().equals(aThat.getBirthDate())) { return false;}

        if(getImagePath() == null) {
            return aThat.getImagePath() == null;
        } else return getImagePath().equals(aThat.getImagePath());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getId() != null ? getId().hashCode() : 0);
        result = prime * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = prime * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = prime * result + (getBirthDate() != null ? getBirthDate().hashCode() : 0);
        result = prime * result + (getImagePath() != null ? getImagePath().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(getClass().getSimpleName())
                .append("{id=").append(getId())
                .append(", firstName=").append(getFirstName())
                .append(", lastName=").append(getLastName())
                .append(", birthDate=").append(getBirthDate())
                .append(", imagePath=").append(getImagePath())
                .append("}");
        return  sb.toString();
    }
}