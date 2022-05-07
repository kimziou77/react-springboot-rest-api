package com.su.order.orderProduct.repository;

import com.su.order.order.entity.OrderProduct;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class HelperOrderProductRepository {
    static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : LocalDateTime.MIN.truncatedTo(ChronoUnit.MILLIS);
    }

    static SqlParameterSource toOrderProductParamMap(OrderProduct orderProduct){
        var paramMap = new MapSqlParameterSource();
        paramMap.addValue("orderId", orderProduct.getOrderId());
        paramMap.addValue("productId", orderProduct.getProductId());
        paramMap.addValue("count", orderProduct.getCount());
        paramMap.addValue("totalPrice", orderProduct.getTotalPrice());
        paramMap.addValue("createdAt", orderProduct.getCreatedAt());
        paramMap.addValue("updatedAt", orderProduct.getUpdatedAt());
        return paramMap;
    }
    static final RowMapper<OrderProduct> orderProductRowMapper = (resultSet, i) -> {
        var id = resultSet.getLong("id");
        var orderId = resultSet.getLong("order_id");
        var productId = resultSet.getLong("product_id");

        var count = resultSet.getLong("count");
        var totalPrice = resultSet.getLong("total_price");

        var createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        var updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));

        return new OrderProduct(id, orderId, productId, count, totalPrice, createdAt, updatedAt);
    };

}
