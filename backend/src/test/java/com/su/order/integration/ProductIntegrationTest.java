package com.su.order.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.su.order.common.OrderServiceConfig;
import com.su.order.common.ProductServiceConfig;
import com.su.order.common.TestRepositoryConfig;
import com.su.order.product.api.ProductRestController;
import com.su.order.product.controller.ProductController;
import com.su.order.product.dto.ProductDto;
import com.su.order.product.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.su.order.common.Helper.toJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DisplayName("상품 도메인 통합 테스트")
@Import({OrderServiceConfig.class, ProductServiceConfig.class, TestRepositoryConfig.class})
public class ProductIntegrationTest {
//https://xlffm3.github.io/spring%20&%20spring%20boot/webtestclient/

    @Autowired
    ProductRestController productRestController;
    @Autowired
    ProductController productController;

    MockMvc mockMvc;
    MockMvc restMockMvc;
    WebTestClient client;

    @BeforeEach
    void setUp(ApplicationContext context) {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        restMockMvc = MockMvcBuilders.standaloneSetup(productRestController).build();
        client = WebTestClient.bindToApplicationContext(context).build();
    }

    @Nested
    class 상품_등록{
        @Test
        void 성공() throws Exception {

            ProductDto.Create dto = new ProductDto.Create();
            dto.setName("커피빈");
            dto.setCategory(Category.COFFEE);
            dto.setPrice(1000L);
            dto.setQuantity(1000L);
            dto.setDescription("상품설명");


            String body = toJson(dto);
            mockMvc.perform(post("/products/product")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                        .andExpect(jsonPath("$.name").value("커피빈"))
                        .andExpect(jsonPath("$.category").value(Category.COFFEE))
                        .andExpect(jsonPath("$.price").isNumber())
                        .andExpect(jsonPath("$.quantity").isNumber())
                        .andExpect(jsonPath("$.description").value("상품설명"));
        }
        @Test
        void 실패(){

        }
    }
    @Nested
    class 상품_수정{
        @Test
        void 성공(){

        }
        @Test
        void 실패(){

        }
    }
    @Nested
    class 상품_조회{

        @Test
        void API_조회_테스트(){
            client.get().uri("/products")
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(ProductDto.Result.class);
        }

        @Test
        void 성공(){

        }
        @Test
        void 실패(){

        }
    }

}