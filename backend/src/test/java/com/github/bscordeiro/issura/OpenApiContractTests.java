package com.github.bscordeiro.issura;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class OpenApiContractTests {

	@Autowired
	private MockMvc mockMvc;

	@ParameterizedTest
	@ValueSource(strings = { "/v3/api-docs", "/v3/api-docs.yaml" })
	void requiresAuthenticationToReadOpenApiContract(String contractPath) throws Exception {
		mockMvc.perform(get(contractPath).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser
	void publishesOpenApiContractAsJson() throws Exception {
		mockMvc.perform(get("/v3/api-docs"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.openapi").isString());
	}

	@Test
	@WithMockUser
	void publishesOpenApiContractAsYaml() throws Exception {
		mockMvc.perform(get("/v3/api-docs.yaml"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("openapi:")));
	}

}
