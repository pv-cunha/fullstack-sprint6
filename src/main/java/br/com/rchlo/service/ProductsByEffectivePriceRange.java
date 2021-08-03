package br.com.rchlo.service;

import br.com.rchlo.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductsByEffectivePriceRange {

    public List<Product> filterProductsList(BigDecimal minimumPrice, BigDecimal maximumPrice, List<Product> productsList) {
        this.validateParameters(minimumPrice, maximumPrice, productsList);

        List<Product> filteredList  = new ArrayList<>();

        for (Product product : productsList) {
            if (this.isBetweenMinimumPriceAndMaximumPrice(minimumPrice, maximumPrice, product)) {
                filteredList.add(product);

                product.showProduct();
            }
        }

        return filteredList ;
    }

    private boolean isBetweenMinimumPriceAndMaximumPrice(BigDecimal minimumPrice, BigDecimal maximumPrice, Product product) {
        BigDecimal finalPrice = product.getFinalPrice();

        return finalPrice.compareTo(minimumPrice) >= 0 && finalPrice.compareTo(maximumPrice) <= 0;
    }

    private void validateParameters(BigDecimal minimumPrice, BigDecimal maximumPrice, List<Product> productsList) {
        if (minimumPrice == null) throw new IllegalArgumentException("minimum price should not be null");
        if (maximumPrice == null) throw new IllegalArgumentException("maximum price should not be null");
        if (productsList == null) throw new IllegalArgumentException("product list should not be null");
    }

}
