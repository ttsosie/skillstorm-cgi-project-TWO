package com.skillSTORM.warehouse_api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skillSTORM.warehouse_api.models.Products;
import com.skillSTORM.warehouse_api.models.Warehouses;
import com.skillSTORM.warehouse_api.repositories.ProductsRepository;
import com.skillSTORM.warehouse_api.repositories.WarehousesRepository;
import com.skillSTORM.warehouse_api.services.ProductsService;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

class ProductServicesTest {
	
	//Creating the mock environment for testing Product Services class
	@Mock
    private ProductsRepository productRepo;
    @Mock
    private WarehousesRepository warehouseRepo;
    @InjectMocks
    private ProductsService service;

    private AutoCloseable closeable;
    private Warehouses goldenLeaf;
    private List<Products> sailorMoonProducts;

    @BeforeAll
    static void beforeAll() {
        System.out.println("=== BEFORE ALL TESTS ===");
    }

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        Warehouses goldenLeaf = new Warehouses();
        goldenLeaf.setId(9);
        goldenLeaf.setWarehouseName("Silver Comic PowerHouse");
        goldenLeaf.setState("WA");

        sailorMoonProducts = Arrays.asList(
                new Products(80, "Moon Tiara Tea", 5.99, "Bright lemon with stardust", goldenLeaf, 50),
                new Products(82, "Mercury Mint Blend", 6.49, "Cool mint & calming herbs", goldenLeaf, 30),
                new Products(83, "Mars Cinnamon Chai", 6.99, "Spicy chai with fiery kick", goldenLeaf, 40),
                new Products(84, "Jupiter Jasmine Green", 7.25, "Floral green tea with lightning energy", goldenLeaf, 25),
                new Products(85, "Venus Vanilla Rooibos", 6.75, "Sweet vanilla & gentle rooibos", goldenLeaf, 35)
        );

        // Default mock behavior
        when(productRepo.findAll()).thenReturn(sailorMoonProducts);
        when(warehouseRepo.findById(1)).thenReturn(Optional.of(goldenLeaf));
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @AfterAll
    static void afterAll() {
        System.out.println("=== AFTER ALL TESTS ===");
    }

    @Test
    void findAll_returnsAllSailorMoonProducts() {
        Iterable<Products> results = service.findAll().getBody();

        assertNotNull(results);
        assertEquals(5, ((List<Products>) results).size());
        verify(productRepo, times(1)).findAll();
    }

    @Test
    void findById_returnsCorrectProduct() {
        when(productRepo.findById(3)).thenReturn(Optional.of(sailorMoonProducts.get(2)));

        Products marsTea = service.findById(3).getBody();

        assertNotNull(marsTea);
        assertEquals("Mars Cinnamon Chai", marsTea.getProductName());
    }

    @Test
    void deleteById_removesProduct() {
        doNothing().when(productRepo).deleteById(2);

        service.deleteById(2);

        verify(productRepo, times(1)).deleteById(2);
    }
	/*@Test
	void test() {
		fail("Not yet implemented");
	}*/

}
