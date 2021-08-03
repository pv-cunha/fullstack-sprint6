package br.com.rchlo.service;

import br.com.rchlo.domain.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductsSortedByCode {
    public List<Product> sortByCode(List<Product> productsList) {
        this.validateParameters(productsList);

        List<Product> sortedProducts = new ArrayList<>(productsList);

        Collections.sort(sortedProducts);

        for (Product product : sortedProducts) {
            product.showProduct();
        }

        return sortedProducts;
    }

    private void validateParameters(List<Product> productsList) {
        if (productsList == null) throw new IllegalArgumentException("product list should not be null");
    }
}
