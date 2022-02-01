package by.library.yurueu.entity;

import java.time.LocalDate;

public class BookCopy {
    private Long id;
    private BookCopyStatus status;
    private LocalDate registrationDate;
    private int price;
    private int pricePerDay;
    private Long bookId;
    private Long orderId;

    public BookCopy() {}

    public BookCopy(Long id, BookCopyStatus status, LocalDate registrationDate, int price, int pricePerDay, Long bookId) {
        this.id = id;
        this.status = status;
        this.registrationDate = registrationDate;
        this.price = price;
        this.pricePerDay = pricePerDay;
        this.bookId = bookId;
        this.orderId = orderId;
    }

    public BookCopy(BookCopyStatus status, LocalDate registrationDate, int price, int pricePerDay, Long bookId) {
        this.status = status;
        this.registrationDate = registrationDate;
        this.price = price;
        this.pricePerDay = pricePerDay;
        this.bookId = bookId;
        this.orderId = orderId;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BookCopyStatus getStatus() {
        return status;
    }

    public void setStatus(BookCopyStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}

        BookCopy aThat = (BookCopy) obj;

        if(getId() == null) {
            if(aThat.getId() != null) {return false;}
        } else if(!getId().equals(aThat.getId())) { return false;}

        if(getStatus() == null) {
            if(aThat.getStatus() != null) {return false;}
        } else if(!getStatus().equals(aThat.getStatus())) { return false;}

        if(getRegistrationDate() == null) {
            if(aThat.getRegistrationDate() != null) {return false;}
        } else if(!getRegistrationDate().equals(aThat.getRegistrationDate())) { return false;}

        if(getPricePerDay() != aThat.getPricePerDay()) {return false;}

        if(getPrice() != aThat.getPrice()) {return false;}

        if(getOrderId() == null) {
            if(aThat.getOrderId() != null) {return false;}
        } else if(!getOrderId().equals(aThat.getOrderId())) { return false;}

        if(getBookId() == null) {
            return aThat.getBookId() == null;
        } else return getBookId().equals(aThat.getBookId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getId() != null ? getId().hashCode() : 0);
        result = prime * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = prime * result + (getRegistrationDate() != null ? getRegistrationDate().hashCode() : 0);
        result = prime * result + getPrice();
        result = prime * result + getPricePerDay();
        result = prime * result + (getBookId() != null ? getBookId().hashCode() : 0);
        result = prime * result + (getOrderId() != null ? getOrderId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(getClass().getSimpleName())
                .append("{status=").append(getStatus())
                .append(", registrationDate=").append(getRegistrationDate())
                .append(", price=").append(getPrice())
                .append(", pricePerDay=").append(getPricePerDay())
                .append(", bookId=").append(getBookId())
                .append(", orderId=").append(getBookId())
                .append("}");
        return sb.toString();
    }
}