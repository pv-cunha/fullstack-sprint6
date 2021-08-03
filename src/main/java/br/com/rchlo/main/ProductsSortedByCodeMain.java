package br.com.rchlo.main;

import br.com.rchlo.data.ProductRepository;
import br.com.rchlo.domain.Product;
import br.com.rchlo.service.ProductsSortedByCode;

import java.util.List;

public class ProductsSortedByCodeMain {
    public static void main(String[] args) {
        ProductsSortedByCode productsSortedByCode = new ProductsSortedByCode();

        List<Product> allProducts = ProductRepository.all();

        productsSortedByCode.sortByCode(allProducts);
    }
}
