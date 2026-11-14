package com.campusfood.express.service;

import com.campusfood.express.repository.CategoryRepository;
import com.campusfood.express.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock ProductRepository productRepository;
    @Mock CategoryRepository categoryRepository;
    @InjectMocks ProductService productService;

    private Sort sortFor(String sortBy) {
        productService.getProducts(null, null, sortBy);
        ArgumentCaptor<Sort> c = ArgumentCaptor.forClass(Sort.class);
        verify(productRepository).searchAndFilter(any(), any(), c.capture());
        return c.getValue();
    }

    @Test
    void defaultSortsById() {
        assertEquals(Sort.by(Sort.Direction.ASC, "id"), sortFor(null));
    }

    @Test
    void ascSortsByPriceAscending() {
        assertEquals(Sort.by(Sort.Direction.ASC, "price"), sortFor("asc"));
    }

    @Test
    void descSortsByPriceDescending() {
        assertEquals(Sort.by(Sort.Direction.DESC, "price"), sortFor("DESC"));
    }
}
