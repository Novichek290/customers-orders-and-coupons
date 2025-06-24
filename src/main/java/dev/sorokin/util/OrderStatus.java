package dev.sorokin.util;

public enum OrderStatus {
    PENDING,               // Ожидание
    PROCESSING,            // В обработке
    CONFIRMED,             // Подтвержден
    SHIPPED,               // Отправлен
    IN_TRANSIT,            // В пути
    OUT_FOR_DELIVERY,      // Доставляется
    DELIVERED,             // Доставлен
    CANCELLED,             // Отменен
    REFUNDED,              // Возвращен (деньги)
    RETURNED,              // Возвращен (товар)
    FAILED,                // Не удался
    ON_HOLD,               // На удержании
    BACKORDERED,           // Предзаказ
    COMPLETED,             // Завершен
    PARTIALLY_SHIPPED,     // Частично отправлен
    PAYMENT_PENDING,       // Ожидает оплаты
    PAYMENT_FAILED,        // Ошибка оплаты
    PAYMENT_REFUNDED,      // Платеж возвращен
    AWAITING_PICKUP,       // Ожидает самовывоза
    EXCHANGED,             // Обменян
    ARCHIVED               // В архиве
}
