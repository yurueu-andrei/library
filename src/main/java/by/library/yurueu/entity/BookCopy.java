package by.library.yurueu.entity;

public class BookCopy {
    private long id;
    private String registrationDate;
    private int price;
    private int pricePerDay;
    private long bookId;

    public BookCopy(long id, String registrationDate, int price, int pricePerDay, long bookId) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.price = price;
        this.pricePerDay = pricePerDay;
        this.bookId = bookId;
    }

    public BookCopy(String registrationDate, int price, int pricePerDay, long bookId) {
        this.registrationDate = registrationDate;
        this.price = price;
        this.pricePerDay = pricePerDay;
        this.bookId = bookId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
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

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}

        BookCopy aThat = (BookCopy) obj;

        if(getRegistrationDate() == null) {
            if(aThat.getRegistrationDate() != null) {return false;}
        } else if(!getRegistrationDate().equals(aThat.getRegistrationDate())) { return false;}

        if(getPricePerDay() != aThat.getPricePerDay()) {return false;}

        if(getPrice() != aThat.getPrice()) {return false;}

        if(getBookId() != aThat.getBookId()) {return false;}

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = prime * result + price;
        result = prime * result + pricePerDay;
        result = prime * result + (int)bookId;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(getClass().getSimpleName())
                .append("{registrationDate=").append(registrationDate)
                .append(", price=").append(price)
                .append(", pricePerDay=").append(pricePerDay)
                .append(", bookId=").append(bookId)
                .append("}");
        return  sb.toString();
    }
}