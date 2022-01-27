package by.library.yurueu.entity;

import java.time.LocalDate;

public class Order {
    private Long id;
    private String orderStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    private int price;
    private Long userId;

    public Order() {}

    public Order(Long id, String orderStatus, LocalDate startDate, LocalDate endDate, int price, Long userId) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.userId = userId;
    }

    public Order(String orderStatus, LocalDate startDate, LocalDate endDate, int price, Long userId) {
        this.orderStatus = orderStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}

        Order aThat = (Order) obj;

        if(getId() == null) {
            if(aThat.getId() != null) {return false;}
        } else if(!getId().equals(aThat.getId())) { return false;}

        if(getOrderStatus() == null) {
            if(aThat.getOrderStatus() != null) {return false;}
        } else if(!getOrderStatus().equals(aThat.getOrderStatus())) { return false;}

        if(getStartDate() == null) {
            if(aThat.getStartDate() != null) {return false;}
        } else if(!getStartDate().equals(aThat.getStartDate())) { return false;}

        if(getEndDate() == null) {
            if(aThat.getEndDate() != null) {return false;}
        } else if(!getEndDate().equals(aThat.getEndDate())) { return false;}

        if(getPrice() != aThat.getPrice()) {return false;}

        if(getUserId() == null) {
            if(aThat.getUserId() != null) {return false;}
        } else if(!getUserId().equals(aThat.getUserId())) { return false;}

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getId() != null ? getId().hashCode() : 0);
        result = prime * result + (getOrderStatus() != null ? getOrderStatus().hashCode() : 0);
        result = prime * result + (getStartDate() != null ? getStartDate().hashCode() : 0);
        result = prime * result + (getEndDate() != null ? getEndDate().hashCode() : 0);
        result = prime * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = prime * result + getPrice();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(getClass().getSimpleName())
                .append("{id=").append(getId())
                .append(", orderStatus=").append(getOrderStatus())
                .append(", startDate=").append(getStartDate())
                .append(", endDate=").append(getEndDate())
                .append(", price=").append(getPrice())
                .append(", userId=").append(getUserId())
                .append("}");
        return sb.toString();
    }
}