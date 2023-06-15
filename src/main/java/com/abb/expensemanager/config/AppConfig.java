package com.abb.expensemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Define all the beans here.
 */
@Configuration
public class AppConfig {

    /**
     * Set the default password encoder for users.
     *
     * @return the encoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Set the default currency format.
     *
     * @return the formatter.
     */
    @Bean
    public NumberFormat numberFormat() {
        return NumberFormat.getCurrencyInstance();
    }

    /**
     * Set the default locale.
     *
     * @return the locale.
     */
    @Bean
    public Locale locale() {
        return Locale.getDefault(Locale.Category.FORMAT);
    }

}
