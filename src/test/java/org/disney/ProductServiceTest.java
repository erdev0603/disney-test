package org.disney;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.disney.dto.BookResult;
import org.disney.gateway.ProductGateway;
import org.disney.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class ProductServiceTest {

    private ProductService productService;
    private WireMockServer wireMockServer;

    private final String HOST = "localhost";
    private final int PORT = 8080;

    @BeforeEach
    void setup() {
        wireMockServer = new WireMockServer();
        configureFor(HOST, PORT);
        wireMockServer.start();

        ProductGateway productGateway = new ProductGateway(HOST, PORT);
        productService = new ProductService(productGateway);
    }

    @AfterEach
    void down() {
        wireMockServer.stop();
    }

    @Test
    void testGetAllProducts() {
        // given
        stubFor(any((anyUrl())).willReturn(ok()));

        // when
        productService.getAllProducts();

        // verify
        verify(getRequestedFor(urlEqualTo("/products")));
    }

    @Test
    void testGetProductById() {
        int productId = 1;
        // given
        stubFor(any((anyUrl())).willReturn(ok()));

        // when
        productService.getProductById(productId);

        // verify
        verify(getRequestedFor(urlEqualTo("/product?id=" + productId)));
    }

    @Test
    void testBookByInventoryId() {
        int inventoryId = 100;
        // given
        stubFor(any((anyUrl())).willReturn(ok()));

        // when
        BookResult bookResult = productService.bookProduct(inventoryId);

        // then
        Assertions.assertTrue(bookResult.getBookResult());

        // verify
        verify(postRequestedFor(urlEqualTo("/product/book?inventoryId=" + inventoryId)));
    }
}
