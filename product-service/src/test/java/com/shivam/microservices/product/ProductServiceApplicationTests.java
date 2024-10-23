package com.shivam.microservices.product;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:7.0.5"));

	@LocalServerPort
	private Integer port;


	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mongoDBContainer.start();
	}


	@Test
	void shouldCreateProduct() {
		String requestBody = """
				{
				  "name": "iphone 15",
				  "description": "smart phone from apple",
				  "price": 19.99
				}
				""";

		RestAssured.given().contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/products")
				.then()
				.statusCode(201)
				.body("id", org.hamcrest.Matchers.notNullValue())
				.body("name", org.hamcrest.Matchers.equalTo("iphone 15"))
				.body("description", org.hamcrest.Matchers.equalTo("smart phone from apple"))
				.body("price", org.hamcrest.Matchers.equalTo(19.99F));

	}


}
