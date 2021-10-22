package com.kitabisa.app;

import com.kitabisa.app.notification.VendorOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.spi.FeatureProvider;

@SpringBootApplication
public class TestKitabisaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestKitabisaApplication.class, args);
	}

	@Bean
	public FeatureProvider featureProvider() {
		return new EnumBasedFeatureProvider (VendorOptions.class);
	}
}
