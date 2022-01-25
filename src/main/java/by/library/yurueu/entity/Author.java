package by.library.yurueu.entity;

public class Author {
    private long id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String imagePath;

    public Author(long id, String firstName, String lastName, String birthDate, String imagePath) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.imagePath = imagePath;
    }

    public Author(String firstName, String lastName, String birthDate, String imagePath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.imagePath = imagePath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
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
            if(aThat.getImagePath() != null) {return false;}
        } else if(!getImagePath().equals(aThat.getImagePath())) { return false;}

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (firstName != null ? firstName.hashCode() : 0);
        result = prime * result + (lastName != null ? lastName.hashCode() : 0);
        result = prime * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = prime * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(getClass().getSimpleName())
                .append("{firstName=").append(firstName)
                .append(", lastName=").append(lastName)
                .append(", birthDate=").append(birthDate)
                .append(", imagePath=").append(imagePath)
                .append("}");
        return  sb.toString();
    }
}