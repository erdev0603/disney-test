package org.disney.service;

import org.disney.dto.BookResult;
import org.disney.gateway.ProductGateway;
import org.disney.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    private final ProductGateway productGateway;

    public ProductService(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public List<Product> getAllProducts() {
        try {
            return productGateway.getAllProducts();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public Product getProductById(Integer productId) {
        try {
            return productGateway.getProductById(productId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public BookResult bookProduct(Integer inventoryId) {
        BookResult bookResult = new BookResult();
        try {
            productGateway.bookProduct(inventoryId);
            bookResult.setInventoryId(inventoryId);
            bookResult.setBookResult(true);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            bookResult.setInventoryId(inventoryId);
            bookResult.setBookResult(false);
        }
        return bookResult;
    }
}
