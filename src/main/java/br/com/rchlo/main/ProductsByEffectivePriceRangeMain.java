package br.com.rchlo.main;


import br.com.rchlo.data.ProductRepository;
import br.com.rchlo.domain.Product;
import br.com.rchlo.service.ProductsByEffectivePriceRange;

import java.math.BigDecimal;
import java.util.List;

public class ProductsByEffectivePriceRangeMain {

    public static void main(String[] args) {

        var productsByEffectivePriceRangeFilter = new ProductsByEffectivePriceRange();
        List<Product> allProducts = ProductRepository.all();
        BigDecimal minimumPrice = new BigDecimal("30.00");
        BigDecimal maximumPrice = new BigDecimal("50.00");

        List<Product> filteredProducts = productsByEffectivePriceRangeFilter.filterProductsList(minimumPrice, maximumPrice, allProducts);

        for (Product product : filteredProducts) {
            boolean hasDiscount = product.getDiscount() != null;
            BigDecimal effectivePrice;
            if (hasDiscount) {
                effectivePrice = product.getPrice().subtract(product.getDiscount());
            } else {
                effectivePrice = product.getPrice();
            }
            System.out.printf("%s - %s - R$ %.2f %n", product.getCode(), product.getName(), effectivePrice);
        }

    }

}
