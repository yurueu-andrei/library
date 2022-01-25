package by.library.yurueu.entity;

public class BookDamage {
    private long id;
    private String imagePath;
    private long userId;
    private long orderId;
    private long bookCopyId;

    public BookDamage(long id, String imagePath, long userId, long orderId, long bookCopyId) {
        this.id = id;
        this.imagePath = imagePath;
        this.userId = userId;
        this.orderId = orderId;
        this.bookCopyId = bookCopyId;
    }

    public BookDamage(String imagePath, long userId, long orderId, long bookCopyId) {
        this.imagePath = imagePath;
        this.userId = userId;
        this.orderId = orderId;
        this.bookCopyId = bookCopyId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(long bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}

        BookDamage aThat = (BookDamage) obj;

        if(getUserId() != aThat.getUserId()) {return false;}

        if(getOrderId() != aThat.getOrderId()) {return false;}

        if(getBookCopyId() != aThat.getBookCopyId()) {return false;}

        if(getImagePath() == null) {
            if(aThat.getImagePath() != null) {return false;}
        } else if(!getImagePath().equals(aThat.getImagePath())) { return false;}

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = prime * result + (int)userId;
        result = prime * result + (int)orderId;
        result = prime * result + (int)bookCopyId;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(getClass().getSimpleName())
                .append("{imagePath=").append(imagePath)
                .append(", userId=").append(userId)
                .append(", orderId=").append(orderId)
                .append(", bookCopyId=").append(bookCopyId)
                .append("}");
        return  sb.toString();
    }
}
