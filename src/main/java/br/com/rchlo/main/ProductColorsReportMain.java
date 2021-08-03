package br.com.rchlo.main;

import br.com.rchlo.data.ProductRepository;
import br.com.rchlo.domain.Product;
import br.com.rchlo.service.ProductColorsReport;

import java.util.List;

public class ProductColorsReportMain {
    public static void main(String[] args) {
        ProductColorsReport productColorsReport = new ProductColorsReport();
        List<Product> allProducts = ProductRepository.all();

        productColorsReport.report(allProducts);
    }
}
