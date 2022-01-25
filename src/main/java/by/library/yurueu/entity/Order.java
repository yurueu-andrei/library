package by.library.yurueu.entity;

import java.time.LocalDate;

public class Order {
    private long id;
    private String orderStatus;
    private String startDate;
    private String endDate;
    private int price;
    private long userId;

    public Order(long id, String orderStatus, String startDate, String endDate, int price, long userId) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.userId = userId;
    }

    public Order(String orderStatus, String startDate, String endDate, int price, long userId) {
        this.orderStatus = orderStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}

        Order aThat = (Order) obj;

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

        if(getUserId() != aThat.getUserId()) {return false;}

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = prime * result + (startDate != null ? startDate.hashCode() : 0);
        result = prime * result + (endDate != null ? endDate.hashCode() : 0);
        result = prime * result + price;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(getClass().getSimpleName())
                .append("{orderStatus=").append(orderStatus)
                .append(", startDate=").append(startDate)
                .append(", endDate=").append(endDate)
                .append(", price=").append(price)
                .append(", userId=").append(userId)
                .append("}");
        return  sb.toString();
    }
}