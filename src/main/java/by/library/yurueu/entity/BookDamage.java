package by.library.yurueu.entity;

public class BookDamage {
    private Long id;
    private String imagePath;
    private Long userId;
    private Long orderId;
    private Long bookCopyId;

    public BookDamage() {}

    public BookDamage(Long id, String imagePath, Long userId, Long orderId, Long bookCopyId) {
        this.id = id;
        this.imagePath = imagePath;
        this.userId = userId;
        this.orderId = orderId;
        this.bookCopyId = bookCopyId;
    }

    public BookDamage(String imagePath, Long userId, Long orderId, Long bookCopyId) {
        this.imagePath = imagePath;
        this.userId = userId;
        this.orderId = orderId;
        this.bookCopyId = bookCopyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(Long bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}

        BookDamage aThat = (BookDamage) obj;

        if(getId() == null) {
            if(aThat.getId() != null) {return false;}
        } else if(!getId().equals(aThat.getId())) { return false;}

        if(getUserId() == null) {
            if(aThat.getUserId() != null) {return false;}
        } else if(!getUserId().equals(aThat.getUserId())) { return false;}

        if(getOrderId() == null) {
            if(aThat.getOrderId() != null) {return false;}
        } else if(!getOrderId().equals(aThat.getOrderId())) { return false;}

        if(getBookCopyId() == null) {
            if(aThat.getBookCopyId() != null) {return false;}
        } else if(!getBookCopyId().equals(aThat.getBookCopyId())) { return false;}

        if(getImagePath() == null) {
            return aThat.getImagePath() == null;
        } else return getImagePath().equals(aThat.getImagePath());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getImagePath() != null ? getImagePath().hashCode() : 0);
        result = prime * result + (getBookCopyId() != null ? getBookCopyId().hashCode() : 0);
        result = prime * result + (getOrderId() != null ? getOrderId().hashCode() : 0);
        result = prime * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = prime * result + (getId() != null ? getId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(getClass().getSimpleName())
                .append("{id=").append(getId())
                .append(", imagePath=").append(getImagePath())
                .append(", userId=").append(getUserId())
                .append(", orderId=").append(getOrderId())
                .append(", bookCopyId=").append(getBookCopyId())
                .append("}");
        return sb.toString();
    }
}
