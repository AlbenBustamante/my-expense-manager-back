package com.abb.expensemanager.util;

import com.abb.expensemanager.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * This class contains the essential methods for convert a string like '$ 25,00' to a BigDecimal object and likewise the other way around.
 */
@Component
@RequiredArgsConstructor
public class CurrencyConverter {

    private final NumberFormat formatter;
    private final Locale locale;

    /**
     * Sets the BigDecimal value to the default currency format.
     *
     * @param value the value to be formatted.
     * @return the value formatted.
     */
    public String toString(final BigDecimal value) {
        return formatter.format(value);
    }

    /**
     * Removes the currency format and gets the BigDecimal value.
     *
     * @param value the value to be converted.
     * @return the BigDecimal value.
     */
    public BigDecimal toBigDecimal(final String value) {
        if (locale.getLanguage().equals("en")) {
            return new BigDecimal(value.replaceAll("[^\\d.]", ""));
        }

        if (locale.getLanguage().equals("es")) {
            return new BigDecimal(value
                    .replaceAll("[^\\d,]", "")
                    .replace(',', '.'));
        }

        throw new AppException("Language not supported.", HttpStatus.BAD_REQUEST);
    }

}
