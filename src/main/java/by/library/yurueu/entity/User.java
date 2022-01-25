package by.library.yurueu.entity;

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String passportNumber;
    private String email;
    private String address;
    private String birthDate;

    public User(long id, String firstName, String lastName, String passportNumber, String email, String address, String birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
    }

    public User(String firstName, String lastName, String passportNumber, String email, String address, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
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

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}

        User aThat = (User) obj;

        if(getFirstName() == null) {
            if(aThat.getFirstName() != null) {return false;}
        } else if(!getFirstName().equals(aThat.getFirstName())) { return false;}

        if(getLastName() == null) {
            if(aThat.getLastName() != null) {return false;}
        } else if(!getLastName().equals(aThat.getLastName())) { return false;}

        if(getPassportNumber() == null) {
            if(aThat.getPassportNumber() != null) {return false;}
        } else if(!getPassportNumber().equals(aThat.getPassportNumber())) { return false;}

        if(getEmail() == null) {
            if(aThat.getEmail() != null) {return false;}
        } else if(!getEmail().equals(aThat.getEmail())) { return false;}

        if(getAddress() == null) {
            if(aThat.getAddress() != null) {return false;}
        } else if(!getAddress().equals(aThat.getAddress())) { return false;}

        if(getBirthDate() == null) {
            if(aThat.getBirthDate() != null) {return false;}
        } else if(!getBirthDate().equals(aThat.getBirthDate())) { return false;}

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (firstName != null ? firstName.hashCode() : 0);
        result = prime * result + (lastName != null ? lastName.hashCode() : 0);
        result = prime * result + (passportNumber != null ? passportNumber.hashCode() : 0);
        result = prime * result + (email != null ? email.hashCode() : 0);
        result = prime * result + (address != null ? address.hashCode() : 0);
        result = prime * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(getClass().getSimpleName())
                .append("{firstName=").append(firstName)
                .append(", lastName=").append(lastName)
                .append(", birthDate=").append(birthDate)
                .append(", passport=").append(passportNumber)
                .append(", email=").append(email)
                .append(", address=").append(address)
                .append("}");
        return  sb.toString();
    }
}