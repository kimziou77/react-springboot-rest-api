package com.su.order.repository;

import com.su.order.common.TestRepositoryConfig;
import com.su.order.order.entity.Order;
import com.su.order.order.entity.OrderProduct;
import com.su.order.orderProduct.repository.OrderProductRepository;
import com.su.order.order.repository.OrderRepository;
import com.su.order.product.entity.Category;
import com.su.order.product.entity.Product;
import com.su.order.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.util.ArrayList;
import java.util.List;

import static com.su.order.common.Helper.getTime;
import static com.su.order.common.Helper.makeOrder;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@SpringJUnitConfig(classes = TestRepositoryConfig.class)
@DisplayName("주문_장바구니_테스트 (OrderProduct)")
class OrderProductNamedJdbcRepositoryTest {
    @Autowired
    OrderProductRepository orderProductRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    @Nested
    class 주문을_하면_주문_상품_테이블을_만들_수_있다{
        Order 주문;
        List<OrderProduct> 장바구니;

        @Test
        void insert() {
            주문 = 주문_한개_생성();
            장바구니.forEach(물건하나 -> orderProductRepository.insert(물건하나));
            var 주문상품들 = orderProductRepository.findAll();

            assertThat(주문상품들)
                    .usingRecursiveFieldByFieldElementComparatorIgnoringFields("orderProducts")
                    .containsAll(장바구니);

        }
        public Order 주문_한개_생성(){
            // 1 주문 (order) - 1 장바구니 (List<OrderProduct>)
            var order = makeOrder(1L);
            orderRepository.insert(order);

            장바구니 = new ArrayList<>();

            var 상품목록 = 상품목록_불러오기();
            장바구니.add(new OrderProduct(상품목록.get(0).getId(), 10L, 상품목록.get(0).getPrice()*10));
            장바구니.add(new OrderProduct(상품목록.get(1).getId(), 10L, 상품목록.get(1).getPrice()*10));
            장바구니.add(new OrderProduct(상품목록.get(2).getId(), 10L, 상품목록.get(2).getPrice()*10));

            장바구니.forEach(주문상품 ->{
                주문상품.setOrderId(order.getId());
                주문상품.setCreatedAt(order.getCreatedAt());
                주문상품.setUpdatedAt(order.getUpdatedAt());
            });

            return order;
        }
        List<Product> 상품목록_불러오기(){
            List<Product> products = new ArrayList<>();
            products.add(new Product("상품0", Category.COFFEE,1000L,1000,"테스트 상품0",getTime(),getTime()));
            products.add(new Product("상품1", Category.BOOK,2000L,1000,"테스트 상품1",getTime(),getTime()));
            products.add(new Product("상품2", Category.CANDY,3000L,1000,"테스트 상품2",getTime(),getTime()));
            products.forEach(product -> productRepository.insert(product));
            return products;
        }
    }
}