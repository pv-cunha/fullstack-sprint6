package br.com.rchlo.service;

import br.com.rchlo.domain.Product;
import br.com.rchlo.domain.Size;

import java.util.*;
import java.util.stream.Collectors;

public class ProductSizesReport {

    private Map<Size, List<Product>> productsPerSize = new HashMap<>();

    public Map<Size, List<Product>> report(List<Product> productList) {
        this.validateParameters(productList);

        for (Product product : productList) {
           Set<Size> availableSizes = product.getAvailableSizes();

            availableSizes.forEach(size -> {
                List<Product> sizeProducts = productsPerSize.get(size);

                if (sizeProducts != null) {
                    sizeProducts.add(product);
                } else {
                    sizeProducts = new ArrayList<>();
                    sizeProducts.add(product);

                    productsPerSize.put(size, sizeProducts);
                }

            });
        }

        showProductsPerSize();

        return productsPerSize;
    }

    private void showProductsPerSize() {
        this.productsPerSize.forEach((key, value) -> System.out.println("\n" + key + ":\n" + value.stream().map(Product::getName).collect(Collectors.joining("\n"))));
    }

    private void validateParameters(List<Product> products) {
        if (products == null) throw new IllegalArgumentException("product list should not be null");
    }
}
