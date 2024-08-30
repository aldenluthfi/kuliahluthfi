package id.ac.ui.cs.advprog.eshop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

@AutoConfigureJsonTesters
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @Autowired
    private JacksonTester<Product> jsonProduct;

    @Test
    void canGetCreateProductPage() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                        get("/product/create"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.SC_OK, response.getStatus());
    }

    @Test
    void canCreateProduct() throws Exception {
        Product product = new Product();

        String json = jsonProduct.write(product).getJson();
        MockHttpServletResponse response = mockMvc.perform(
                        post("/product/create")
                                .contentType("application/json")
                                .content(json != null ? json : ""))
                .andReturn().getResponse();
        assertEquals(HttpStatus.SC_MOVED_TEMPORARILY, response.getStatus());
    }

    @Test
    void canGetProductListPage() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                        get("/product/list"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.SC_OK, response.getStatus());
    }

    @Test
    void canGetEditProductPage() throws Exception {
        Product product = new Product();
        String json = jsonProduct.write(product).getJson();
        mockMvc.perform(
                        post("/product/create")
                                .contentType("application/json")
                                .content(json != null ? json : ""))
                .andReturn().getResponse();

        Mockito.when(service.findById("1")).thenReturn(product);

        MockHttpServletResponse response = mockMvc.perform(
                        get("/product/edit/1"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.SC_OK, response.getStatus());

    }

    @Test
    void canEditProduct() throws Exception {
        Product existingProduct = new Product();
        String jsonExistingProduct = jsonProduct.write(existingProduct).getJson();
        mockMvc.perform(
                        post("/product/create")
                                .contentType("application/json")
                                .content(jsonExistingProduct != null ? jsonExistingProduct : ""))
                .andReturn().getResponse();

        Product updatedProduct = new Product();

        String json = jsonProduct.write(updatedProduct).getJson();
        MockHttpServletResponse response = mockMvc.perform(
                        post("/product/edit/1")
                                .contentType("application/json")
                                .content(json != null ? json : ""))
                .andReturn().getResponse();
        assertEquals(HttpStatus.SC_MOVED_TEMPORARILY, response.getStatus());
    }

    @Test
    void canRedirectWhenEditNotFound() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                        get("/product/edit/69"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.SC_MOVED_TEMPORARILY, response.getStatus());
    }

    @Test
    void canDeleteProduct() throws Exception {
        Product product = new Product();
        String json = jsonProduct.write(product).getJson();

        mockMvc.perform(
                        post("/product/create")
                                .contentType("application/json")
                                .content(json != null ? json : ""))
                .andReturn().getResponse();

        MockHttpServletResponse response = mockMvc.perform(
                        get("/product/delete/1"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.SC_MOVED_TEMPORARILY, response.getStatus());
    }
}