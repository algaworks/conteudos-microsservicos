package com.algaworks.example.secure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ProductRestControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void deveFalhar() throws Exception {
        mvc.perform(get("/products"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(value = "joaodasilva", authorities = "ROLE_CUSTOMER")
    void devePermitirUsuarioComRoleAcesseProdutos() throws Exception {
        mvc.perform(get("/products"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("joao")
    void naoDevePermitirQueUmUsuarioDePerfilClienteCadastreUmProduto() throws Exception {
        String requestBody =
                """
                {
                    "name": "Novo produto"
                }
                """;
        mvc.perform(
                    post("/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody)
                )
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("alex")
    void devePermitirQueUmUsuarioDePerfilAdminCadastreUmProduto() throws Exception {
        String requestBody =
                """
                {
                    "name": "Novo produto"
                }
                """;
        mvc.perform(
                    post("/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody)
                )
                .andExpect(status().isCreated());
    }
}