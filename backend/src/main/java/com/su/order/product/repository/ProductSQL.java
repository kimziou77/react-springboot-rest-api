package com.su.order.product.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductSQL {
    DELETE_ALL("DELETE FROM product"),
    FIND_ALL("select * from product"),
    DELETE_BY_ID("DELETE FROM product WHERE id = ?"),
    INSERT("INSERT INTO product(name, category, price, quantity, description, image_path, created_at, updated_at) " +
            "VALUES(:name, :category, :price, :quantity, :description, :imagePath, :createdAt, :updatedAt)"),
    UPDATE("UPDATE product SET product_name=:productName, category=:category, price=:price, quantity=:quantity ,description=:description, " +
            "created_at=:createdAt, updated_at=:updatedAt WHERE id = :id"),
    COUNT("select count(*) from product"),

    FIND_BY_ID("SELECT * from product where id = :productId");

    private final String sql;
}
