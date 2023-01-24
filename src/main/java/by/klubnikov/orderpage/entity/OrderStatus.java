package by.klubnikov.orderpage.entity;

public enum OrderStatus {
    PAID("Заказ оплачен."),
    SEND("Заказ передан в доставку."),

    INDELIVERY("Заказ в пути."),

    RECEIVED("Заказ доставлен.");

    private String statusDescription;

    OrderStatus(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    @Override
    public String toString() {
        return statusDescription;
    }
    }
