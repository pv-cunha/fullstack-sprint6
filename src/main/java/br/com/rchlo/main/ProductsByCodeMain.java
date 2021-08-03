package br.com.rchlo.main;

import br.com.rchlo.data.ProductRepository;
import br.com.rchlo.domain.Product;
import br.com.rchlo.service.ProductsByCode;

import java.util.List;

public class ProductsByCodeMain {

    public static void main(String[] args) {

        var productsByCodeFilter = new ProductsByCode();
        List<Product> allProducts = ProductRepository.all();

        List<Product> filteredProducts = productsByCodeFilter.filter(14040174L, allProducts);

    }

}
