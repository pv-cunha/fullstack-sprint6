package br.com.rchlo.main;

import br.com.rchlo.data.ProductRepository;
import br.com.rchlo.domain.Color;
import br.com.rchlo.domain.Product;
import br.com.rchlo.service.ProductsByColor;

import java.util.List;

public class ProductsByColorMain {
    public static void main(String[] args) {

        ProductsByColor productsByColor = new ProductsByColor();
        List<Product> allProducts = ProductRepository.all();

        productsByColor.filter(allProducts, Color.WHITE);

    }
}
