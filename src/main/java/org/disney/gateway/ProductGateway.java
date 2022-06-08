package org.disney.gateway;

import org.disney.dto.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductGateway {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl;

    public ProductGateway(@Value("${product.gateway.host}") String host, @Value("${product.gateway.port}") int port) {
        this.baseUrl = "http://" + host + ":" + port;
    }

    public List<Product> getAllProducts() {
        return getList(baseUrl + "/products", HttpMethod.GET);
    }

    public Product getProductById(Integer productId) {
        return restTemplate.getForObject(baseUrl + "/product?id=" + productId, Product.class);
    }

    public void bookProduct(Integer inventoryId) {
        restTemplate.postForObject(baseUrl + "/product/book?inventoryId=" + inventoryId, inventoryId, Void.class);
    }

    public <T> List<T> getList(final String path, final HttpMethod method) {
        final ResponseEntity<List<T>> response = restTemplate.exchange(
                path,
                method,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }
}
