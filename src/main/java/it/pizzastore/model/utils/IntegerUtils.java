package it.pizzastore.model.utils;

import java.math.BigDecimal;

public class IntegerUtils {

	public static boolean isIntegerValue(BigDecimal bd) {
		return bd.stripTrailingZeros().scale() <= 0;
	}

}
