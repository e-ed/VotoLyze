package com.bytebuilders.VotoLyze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class VotoLyzeApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotoLyzeApplication.class, args);
	}

}


@RestController
class HttpController {
	@GetMapping("/public")
	String publicRoute() {
		return "<h1> Public route! </h1>";
	}

	@GetMapping("/private")
	String privateRoute() {
		return "<h1> Private route! </h1>";
	}
}
