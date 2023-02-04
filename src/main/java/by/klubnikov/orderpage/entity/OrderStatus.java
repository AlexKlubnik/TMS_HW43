package by.klubnikov.orderpage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum OrderStatus {
    PAID("Заказ оплачен."),
    SEND("Заказ передан в доставку."),

    INDELIVERY("Заказ в пути."),

    RECEIVED("Заказ доставлен.");

    private String statusDescription;

    @Override
    public String toString() {
        return statusDescription;
    }
    }
