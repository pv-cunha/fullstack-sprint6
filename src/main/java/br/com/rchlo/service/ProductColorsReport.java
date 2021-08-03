package br.com.rchlo.service;

import br.com.rchlo.domain.Color;
import br.com.rchlo.domain.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductColorsReport {
    private final Map<Color, Integer> quantityPerColor = new HashMap<>();

    public Map<Color, Integer> report(List<Product> productList) {
        this.validateParameters(productList);

        Color[] colorsArray = Color.values();
//
//        for (Color color : colorsArray) {
//            this.quantityPerColor.put(color, 0);
//        }
//
//        productList.forEach(product -> {
//            Integer quantity = this.quantityPerColor.get(product.getColor());
//
//            Color color = product.getColor();
//
//            this.quantityPerColor.put(color, quantity + 1);
//        });
//
//        this.showQuantityPerColor();

        for (Color color : colorsArray) {
            Long quantity = this.getQuantityProductsPerColor(productList, color);

            quantityPerColor.put(color, quantity.intValue());
        }

        showQuantityPerColor();

        return quantityPerColor;
    }

    private void showQuantityPerColor() {
        this.quantityPerColor.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    private Long getQuantityProductsPerColor(List<Product> productsList, Color color) {
        return productsList.stream().filter(product -> product.getColor().equals(color)).count();
    }

    private void validateParameters(List<Product> products) {
        if (products == null) throw new IllegalArgumentException("product list should not be null");
    }
}
