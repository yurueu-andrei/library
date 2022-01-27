package by.library.yurueu.entity;

import java.time.LocalDate;

public class BookCopy {
    private Long id;
    private LocalDate registrationDate;
    private int price;
    private int pricePerDay;
    private Long bookId;

    public BookCopy() {}

    public BookCopy(Long id, LocalDate registrationDate, int price, int pricePerDay, Long bookId) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.price = price;
        this.pricePerDay = pricePerDay;
        this.bookId = bookId;
    }

    public BookCopy(LocalDate registrationDate, int price, int pricePerDay, Long bookId) {
        this.registrationDate = registrationDate;
        this.price = price;
        this.pricePerDay = pricePerDay;
        this.bookId = bookId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}

        BookCopy aThat = (BookCopy) obj;

        if(getId() == null) {
            if(aThat.getRegistrationDate() != null) {return false;}
        } else if(!getId().equals(aThat.getId())) { return false;}

        if(getRegistrationDate() == null) {
            if(aThat.getRegistrationDate() != null) {return false;}
        } else if(!getRegistrationDate().equals(aThat.getRegistrationDate())) { return false;}

        if(getPricePerDay() != aThat.getPricePerDay()) {return false;}

        if(getPrice() != aThat.getPrice()) {return false;}

        if(getBookId() == null) {
            if(aThat.getBookId() != null) {return false;}
        } else if(!getBookId().equals(aThat.getBookId())) { return false;}

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getId() != null ? getId().hashCode() : 0);
        result = prime * result + (getRegistrationDate() != null ? getRegistrationDate().hashCode() : 0);
        result = prime * result + getPrice();
        result = prime * result + getPricePerDay();
        result = prime * result + (getBookId() != null ? getBookId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(getClass().getSimpleName())
                .append("{registrationDate=").append(getRegistrationDate())
                .append(", price=").append(getPrice())
                .append(", pricePerDay=").append(getPricePerDay())
                .append(", bookId=").append(getBookId())
                .append("}");
        return sb.toString();
    }
}