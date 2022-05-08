package com.su.order.repository;

import com.su.order.common.TestRepositoryConfig;
import com.su.order.order.entity.Order;
import com.su.order.order.entity.OrderProduct;
import com.su.order.order.entity.OrderStatus;
import com.su.order.order.repository.OrderRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@JdbcTest
@SpringJUnitConfig(classes = TestRepositoryConfig.class)
class OrderJdbcRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    void insert() {
        var order = makeOrder();

        orderRepository.insert(order);

        assertThat(orderRepository.count()).isEqualTo(1);
        assertThat(orderRepository.findById(order.getId()).get())
                .usingRecursiveComparison()
                .ignoringFields("orderProducts")
                .isEqualTo(order);
    }

    @Nested
    class findById{
        @Test
        void 찾는_Id가_있으면_주문을_조회할_수_있다(){
            var order = makeOrder();
            orderRepository.insert(order);
            var findOrder = orderRepository.findById(order.getId()).get();
            assertThat(findOrder)
                    .usingRecursiveComparison()
                    .ignoringFields("orderProducts")
                    .isEqualTo(order);
        }
        @Test
        void 찾는_Id가_없으면_주문은_EMPTY(){
            var order = makeOrder();
            var findOrder = orderRepository.findById(order.getId());

            assertThat(findOrder.isEmpty()).isTrue();
        }
    }

    @Test
    void findAll(){
        var order1 = orderRepository.insert(makeOrder());
        var order2 = orderRepository.insert(makeOrder());
        var order3 = orderRepository.insert(makeOrder());
        var orders = orderRepository.findAll();

        assertThat(orders)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("orderProducts")
                .contains(order1, order2, order3);
    }

    @Test
    void cancel(){
        var order = orderRepository.insert(makeOrder());
        assertThat(order.getOrderStatus())
                .isEqualTo(OrderStatus.ACCEPTED);

        orderRepository.cancel(order);

        var findOrder = orderRepository.findById(order.getId()).get();
        assertThat(findOrder.getOrderStatus())
                .isEqualTo(OrderStatus.CANCELED);
    }

    @Test
    void deleteById(){
        var order1 = makeOrder();
        var order2 = makeOrder();
        var order3 = makeOrder();
        orderRepository.insert(order1);
        orderRepository.insert(order2);
        orderRepository.insert(order3);

        orderRepository.deleteById(order2.getId());

        var orders = orderRepository.findAll();
        assertThat(orders)
                .usingRecursiveFieldByFieldElementComparator()
                .doesNotContain(order2);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,100000})
    void count(int n){
        for (int i = 0; i < n; i++) {
            orderRepository.insert(makeOrder());
        }
        assertThat(orderRepository.count()).isEqualTo(n);
    }

    public Order makeOrder() {
        List<OrderProduct> 장바구니 = new ArrayList<>();
        var order = new Order("이메일", "주소",1000L);
        order.setOrderProducts(장바구니);
        return order;
    }

}