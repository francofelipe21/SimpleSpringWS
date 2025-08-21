package com.sonda.prueba_tecnica;

import com.sonda.prueba_tecnica.controllers.MainController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PruebaTecnicaApplicationTests {

	@Autowired
	private MainController controller;
	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	@Autowired
	private TestRestTemplate restTemplate;
	@Test
	void testPostRequest() {
		testTuple(200, 1571);
		testTuple(162920, 1577);
		testTuple(398940, 486);
		testTuple(1688835, 3970);
		testTuple(2101065, 1858);
		testTuple(2672860, 2481);
		testTuple(3390940, 1289);
		testTuple(3936140, 1477);
		testTuple(4414840, 1690);
		testTuple(5145145, 3912);

	}

	private void testTuple(int record, int operation) {
		Map<String, Object> request = new HashMap<>();
		request.put("numero de registro", record);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

		ResponseEntity<Map> response = restTemplate.exchange(
				"http://localhost:8080/",
				HttpMethod.POST,
				entity,
				Map.class
		);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).containsEntry("numero de registro", record);
		assertThat(response.getBody()).containsEntry("numero de operacion", operation);
	}

}


