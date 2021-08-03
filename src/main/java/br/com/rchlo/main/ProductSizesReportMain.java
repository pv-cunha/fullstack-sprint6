package br.com.rchlo.main;

import br.com.rchlo.data.ProductRepository;
import br.com.rchlo.domain.Product;
import br.com.rchlo.service.ProductSizesReport;

import java.util.List;

public class ProductSizesReportMain {
    public static void main(String[] args) {
        ProductSizesReport productSizesReport = new ProductSizesReport();

        List<Product> allProducts = ProductRepository.all();

        productSizesReport.report(allProducts);
    }
}
