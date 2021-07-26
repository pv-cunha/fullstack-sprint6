package br.com.rchlo.service;

import br.com.rchlo.domain.Color;
import br.com.rchlo.domain.Product;
import br.com.rchlo.domain.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProductsByEffectivePriceRangeTest {

    private ProductsByEffectivePriceRange productsByEffectivePriceRange;

    @BeforeEach
    void setUp() {
        productsByEffectivePriceRange = new ProductsByEffectivePriceRange();
    }

    @Test
    void shouldReturnOnlyProductsInTheRange() {
        List<Product> products = List.of(aDiscountedTShirt(), anExpensiveJacket(), aCheapTankTop());
        BigDecimal minimumPrice = new BigDecimal("30.0");
        BigDecimal maximumPrice = new BigDecimal("40.0");

        List<Product> filteredProducts = productsByEffectivePriceRange.filterProductsList(minimumPrice, maximumPrice, products);

        assertEquals(1, filteredProducts.size());

        Product product = filteredProducts.get(0);
        assertEquals(14124998L, product.getCode());
        assertEquals("Camiseta Infantil Manga Curta Super Mario", product.getName());
    }

    @Test
    void shouldWorkWithAnExactPrice() {
        List<Product> products = List.of(aCheapTankTop());
        BigDecimal minimumPrice = new BigDecimal("29.90");
        BigDecimal maximumPrice = new BigDecimal("29.90");

        List<Product> filteredProducts = productsByEffectivePriceRange.filterProductsList(minimumPrice, maximumPrice, products);

        assertEquals(1, filteredProducts.size());

        Product product = filteredProducts.get(0);
        assertEquals(14040174L, product.getCode());
        assertEquals("Regata Infantil Mario Bros", product.getName());
    }

    @Test
    void shouldWorkWithAnDiscounts() {
        List<Product> products = List.of(aDiscountedTShirt());
        BigDecimal minimumPrice = new BigDecimal("34.90");
        BigDecimal maximumPrice = new BigDecimal("34.90");

        List<Product> filteredProducts = productsByEffectivePriceRange.filterProductsList(minimumPrice, maximumPrice, products);

        assertEquals(1, filteredProducts.size());

        Product product = filteredProducts.get(0);
        assertEquals(14124998L, product.getCode());
        assertEquals("Camiseta Infantil Manga Curta Super Mario", product.getName());
    }

    private Product aDiscountedTShirt() {
        return new Product(14124998L,
                "Camiseta Infantil Manga Curta Super Mario",
                "A Camiseta Infantil Manga Curta Super Mario é confeccionada em malha macia e possui decote careca, mangas curtas e padronagem do Super Mario. Aposte na peça na hora de compor visuais geek divertidos.",
                "camiseta-infantil-manga-curta-super-mario-14124998_sku",
                "Nintendo",
                new BigDecimal("39.90"),
                new BigDecimal("5.0"),
                Color.BLUE,
                116,
                "https://static.riachuelo.com.br/RCHLO/14124998004/portrait/cd948d80fe8a1fdc873f8dca1f3c4c468253bf1d.jpg",
                Set.of(Size.SMALL, Size.MEDIUM));
    }

    private Product anExpensiveJacket() {
        return new Product(13834193L,
                "Jaqueta Puffer Juvenil Com Capuz Super Mario",
                "A Jaqueta Puffer Juvenil Com Capuz Super Mario é confeccionada em material sintético. Possui estrutura ampla e modelo puffer, com capuz em pelúcia e bolsos frontais. Ideal para compor looks de inverno, mas sem perder o estilo. Combine com uma camiseta, calça jeans e tênis colorido.",
                "jaqueta-puffer-juvenil-com-capuz-super-mario-13834193_sku",
                "Nintendo",
                new BigDecimal("199.90"),
                null,
                Color.WHITE,
                147,
                "https://static.riachuelo.com.br/RCHLO/13834193003/portrait/3107b7473df334c6ff206cd78d16dec86d7dfe9a.jpg",
                Set.of(Size.LARGE, Size.EXTRA_LARGE));
    }

    private Product aCheapTankTop() {
        return new Product(14040174L,
                "Regata Infantil Mario Bros",
                "A Regata Infantil Mario Bros é confeccionada em fibra natural, possui decote redondo e modelagem regular. As peças temáticas com os personagens preferidos da criançada são indispensáveis no guarda-roupa. Divertidas e cheias de personalidade, os modelos são uma forma dos pequenos se expressarem em relação aos seus gostos pessoais, que já começam desde cedo. Aposte!",
                "regata-infantil-mario-bros-14040174_sku",
                "Nintendo",
                new BigDecimal("29.90"),
                null,
                Color.WHITE,
                98,
                "https://static.riachuelo.com.br/RCHLO/14040174004/portrait/f10a3e016dd974dbdc7dfaefa41a47599557a58a.jpg",
                Set.of(Size.SMALL, Size.MEDIUM, Size.LARGE));
    }

}
