package com.psd.filter.resources;

import com.psd.filter.config.RestAssureConf;
import com.psd.filter.entity.ProductEntity;
import com.psd.filter.factory.ProductFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;

class ProductResourcesTest extends RestAssureConf {

    @Autowired
    private ProductFactory productFactory;

    private static ProductEntity product;

    @BeforeAll
    void setUpClass(){
        product = productFactory.createProductBase();
    }

    @Test
    void shouldReturnProductById() {
        final ProductEntity productResponse =
                given()
                    .log().all()
                    .spec(specification)
                .when()
                    .get("products/"+product.getId())
                .then()
                    .statusCode(200)
                    .extract()
                    .as(ProductEntity.class);

        Assertions.assertEquals(productResponse.getId(), product.getId());
    }


}