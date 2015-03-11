package com.finances.personal.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class FloatUtils {
	
	public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;
	public static final int DEFAULT_SCALE = 2;

	private FloatUtils() {}
	
	public static final float round(float value) {
		return round(value, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
	}
	
	public static final float round(float value, int scale) {
		return round(value, scale, DEFAULT_ROUNDING_MODE);
	}
	
	public static final float round(float value, RoundingMode roundingMode) {
		return round(value, DEFAULT_SCALE, roundingMode);
	}

	public static final float round(float value, int scale, RoundingMode roundingMode) {
		BigDecimal bigDecimal = new BigDecimal(value);
		
		return bigDecimal.setScale(scale, roundingMode).floatValue();
	}

}
