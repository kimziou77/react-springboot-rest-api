package com.su.order.product.repository;

import com.su.order.order.entity.Order;
import com.su.order.product.entity.Category;
import com.su.order.product.entity.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class HelperProductRepository {
    static final RowMapper<Product> productRowMapper = (resultSet, i) -> {
        var id = resultSet.getLong("id");
        var name = resultSet.getString("name");

        var category = Category.valueOf(resultSet.getString("category"));
        var price = resultSet.getLong("price");
        var quantity = resultSet.getLong("quantity");
        var description = resultSet.getString("description");
        var imagePath = resultSet.getString("image_path");

        var createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        var updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));

        return new Product(id, name, category, price, quantity, description, imagePath, createdAt, updatedAt);
    };

    static SqlParameterSource toProductParamMap(Product product) {
        var paramMap = new MapSqlParameterSource();
        paramMap.addValue("name", product.getName());
        paramMap.addValue("category", product.getCategory().toString());
        paramMap.addValue("price", product.getPrice());
        paramMap.addValue("quantity", product.getQuantity());
        paramMap.addValue("imagePath", product.getImagePath());
        paramMap.addValue("description", product.getDescription());
        paramMap.addValue("createdAt", product.getCreatedAt());
        paramMap.addValue("updatedAt", product.getUpdatedAt());
        return paramMap;
    }

    static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : LocalDateTime.MIN.truncatedTo(ChronoUnit.MILLIS);
    }
}
