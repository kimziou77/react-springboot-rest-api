package com.su.order.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.su.order.common.Helper;
import com.su.order.order.api.OrderRestController;
import com.su.order.order.controller.dto.OrderDto;
import com.su.order.order.entity.OrderProduct;
import com.su.order.product.entity.Category;
import com.su.order.product.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static com.su.order.common.Helper.toJson;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DisplayName("주문 도메인 통합 테스트")
public class OrderIntegrationTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Nested
    class 상품_주문{
        @Test
        void 성공() throws Exception {
            var given = createUserRequest();

            mockMvc.perform(MockMvcRequestBuilders
                    .post("/orders")
                    .content(toJson(given))
                    .accept(MediaType.APPLICATION_JSON)); // TODO: 이걸 어떻게 검증해야할까?
//                        .andExpect(jsonPath("$.email").value("이메일"))
//                        .andExpect(jsonPath("$.address").value("주소"));
        }
        @Test
        void 실패() throws Exception{
            var given = new OrderDto.UserOrderRequest();

            mockMvc.perform(MockMvcRequestBuilders
                    .post("/orders")
                    .content(toJson(given))
                    .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound()); // ERROR : not supported 에러??

        }
    }

    @Nested
    class 주문_내역_조회{

        @Test
        void 성공(){

        }
        @Test
        void 실패(){

        }
    }

    @Nested
    class 주문_취소{
        @Test
        void 성공(){

        }
        @Test
        void 실패(){

        }
    }

    List<Product> getProducts(){
        List<Product> products = new ArrayList<>();
        products.add(new Product(0L,"상품0", Category.COFFEE,1000L,1000,"테스트 상품0"));
        products.add(new Product(1L,"상품1", Category.BOOK,2000L,1000,"테스트 상품1"));
        products.add(new Product(2L,"상품2", Category.CANDY,3000L,1000,"테스트 상품2"));
        return products;
    }

    OrderDto.UserOrderRequest createUserRequest(){
        var 상품리스트 = getProducts();

        List<OrderProduct> 장바구니 = new ArrayList<>();
        장바구니.add(new OrderProduct(상품리스트.get(0).getId(), 10L, 상품리스트.get(0).getPrice()*10));
        장바구니.add(new OrderProduct(상품리스트.get(1).getId(), 10L, 상품리스트.get(1).getPrice()*10));
        장바구니.add(new OrderProduct(상품리스트.get(2).getId(), 10L, 상품리스트.get(2).getPrice()*10));

        var request = new OrderDto.UserOrderRequest("이메일","주소", 장바구니,10000L);
        return request;
    }
}
