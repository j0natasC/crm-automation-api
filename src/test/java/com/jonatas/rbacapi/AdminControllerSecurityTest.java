package com.jonatas.rbacapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("1. Deve permitir acesso (200 OK) para ADMIN")
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void devePermitirAcessoAdmin() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("2. Deve negar acesso (403 Forbidden) para usuário comum")
    @WithMockUser(username = "user", roles = { "USER" })
    void deveNegarAcessoUsuarioComum() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("3. Deve negar acesso (401 Unauthorized) para usuário deslogado")
    void deveNegarAcessoAnonimo() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isUnauthorized());
    }
}