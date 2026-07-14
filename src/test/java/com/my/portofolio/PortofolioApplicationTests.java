package com.my.portofolio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class PortofolioApplicationTests {

	@Autowired
	private com.my.portofolio.repository.UserRepository userRepository;

	@Test
	void printBcryptHash() {
		org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
		System.out.println("BCRYPT_HASH_FOR_ADMIN123: " + encoder.encode("admin123"));
		
		userRepository.findByEmail("admin@mail.com").ifPresent(user -> {
			System.out.println("STORED_PASSWORD_IN_DB: " + user.getPassword());
			System.out.println("PASSWORD_MATCHES: " + encoder.matches("admin123", user.getPassword()));
		});
	}

}
