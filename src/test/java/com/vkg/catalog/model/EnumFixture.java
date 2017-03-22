package com.vkg.catalog.model;

import org.apache.commons.lang.math.RandomUtils;

public class EnumFixture {
	public static <E extends Enum<E>> E randomEnum(Class<E> clazz) {
		return randomEnum(clazz.getEnumConstants());
	}
	
	public static <E extends Enum<E>> E randomEnum(E[] values) {
		return values[RandomUtils.nextInt(values.length)];
	}
}
