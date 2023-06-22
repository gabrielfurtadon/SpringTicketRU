package br.com.Gabriel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TickRUApplication {

	public static void main(String[] args) {
		SpringApplication.run(TickRUApplication.class, args);
		try {
			java.sql.Driver driver = java.sql.DriverManager.getDriver("jdbc:mysql://localhost:3306/");
			System.out.println(driver.getMajorVersion() + "." + driver.getMinorVersion());
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}

	}

}
