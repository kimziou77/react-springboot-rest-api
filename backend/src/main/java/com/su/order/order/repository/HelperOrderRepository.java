package com.su.order.order.repository;

import com.su.order.order.entity.Order;
import com.su.order.order.entity.OrderProduct;
import com.su.order.order.entity.OrderStatus;
import com.su.order.product.entity.Category;
import com.su.order.product.entity.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class HelperOrderRepository {
    static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : LocalDateTime.MIN.truncatedTo(ChronoUnit.MILLIS);
    }

    static final RowMapper<Order> orderRowMapper = (resultSet, i) -> {
        var id = resultSet.getLong("id");
        var email = resultSet.getString("email");
        var address = resultSet.getString("address");
        var totalPrice = resultSet.getLong("total_price");
        var orderStatus = OrderStatus.valueOf(resultSet.getString("order_status"));
        var createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        var updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));
        return new Order(id, email, address,totalPrice, orderStatus, createdAt, updatedAt);
    };

    static SqlParameterSource toOrderParamMap(Order order){
        var paramMap = new MapSqlParameterSource();
        paramMap.addValue("email", order.getEmail());
        paramMap.addValue("address", order.getAddress());
        paramMap.addValue("totalPrice", order.getTotalPrice());
        paramMap.addValue("orderStatus", order.getOrderStatus().toString());
        paramMap.addValue("createdAt", order.getCreatedAt());
        paramMap.addValue("updatedAt", order.getUpdatedAt());
        return paramMap;
    }
    static SqlParameterSource cancelOrderParamMap(Order order){
        var paramMap = new MapSqlParameterSource();
        paramMap.addValue("id", order.getId());
        paramMap.addValue("orderStatus", OrderStatus.CANCELED.toString());
        paramMap.addValue("updatedAt", LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        return paramMap;
    }

}
