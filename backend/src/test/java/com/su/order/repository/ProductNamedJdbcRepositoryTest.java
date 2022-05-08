package com.su.order.repository;

import com.su.order.common.TestRepositoryConfig;
import com.su.order.product.entity.Category;
import com.su.order.product.entity.Product;
import com.su.order.product.repository.ProductRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

import static com.su.order.common.Helper.getTime;
import static com.su.order.common.Helper.makeProduct;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@SpringJUnitConfig(classes = TestRepositoryConfig.class)
class ProductNamedJdbcRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void insert(){
        var products = 상품목록_불러오기();
        products.forEach(product -> productRepository.insert(product));

        assertThat(productRepository.count()).isEqualTo(products.size());
        assertThat(productRepository.findAll())
                .usingRecursiveFieldByFieldElementComparator()
                .containsAll(products);
    }
    @Nested
    class findById{
        @Test
        void 찾는_Id가_존재하면_상품을_조회할_수_있다(){
            var product = productRepository.insert(makeProduct("1"));

            var findProduct = productRepository.findById(product.getId());
            assertThat(findProduct.isPresent()).isTrue();
            assertThat(findProduct.get())
                    .usingRecursiveComparison()
                    .isEqualTo(product);
        }
        @Test
        void 찾는_Id가_없으면_상품을_조회할_수_없다(){
            var findProduct = productRepository.findById(1L);
            assertThat(findProduct.isEmpty()).isTrue();
        }
    }

    @Test
    void findAll(){
        System.out.println("초기상태");
        System.out.println(productRepository.findAll());
        var product1 = productRepository.insert(makeProduct("1"));
        var product2 = productRepository.insert(makeProduct("2"));
        var product3 = productRepository.insert(makeProduct("3"));
        var products = productRepository.findAll();



        assertThat(products)
                .usingRecursiveFieldByFieldElementComparator()
                .contains(product1, product2, product3);
    }

    @Test
    void deleteById(){
        var product1 = productRepository.insert(makeProduct("1"));
        var product2 = productRepository.insert(makeProduct("2"));
        var product3 = productRepository.insert(makeProduct("3"));

        productRepository.deleteById(product2.getId());
        var products = productRepository.findAll();
        assertThat(products)
                .usingRecursiveFieldByFieldElementComparator()
                .doesNotContain(product2);
    }

    @Test
    void deleteAll(){
        productRepository.insert(makeProduct("1"));
        productRepository.insert(makeProduct("2"));
        productRepository.insert(makeProduct("3"));
        assertThat(productRepository.count()).isEqualTo(3);

        productRepository.deleteAll();
        assertThat(productRepository.count()).isEqualTo(0);
    }

    List<Product> 상품목록_불러오기(){
        List<Product> products = new ArrayList<>();
        products.add(new Product("상품0", Category.COFFEE,1000L,1000,"테스트 상품0",getTime(),getTime()));
        products.add(new Product("상품1", Category.BOOK,2000L,1000,"테스트 상품1",getTime(),getTime()));
        products.add(new Product("상품2", Category.CANDY,3000L,1000,"테스트 상품2",getTime(),getTime()));
        return products;
    }
}