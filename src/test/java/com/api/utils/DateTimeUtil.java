package com.api.utils;

import static java.time.temporal.ChronoUnit.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.joda.time.Instant;

public class DateTimeUtil {

	private DateTimeUtil() {

	}

	public static String getTimeWithDaysAgo(long days) {
		return Instant.now().minus(days).toString();
	}

}
