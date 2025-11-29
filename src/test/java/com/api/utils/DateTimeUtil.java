package com.api.utils;

import static java.time.temporal.ChronoUnit.*;

import java.time.temporal.ChronoUnit;

import org.joda.time.Instant;

public class DateTimeUtil {

	
	private DateTimeUtil() {
		
	}
	
	public static void getTimeWithDaysAgo(int days) {
		   Instant.now().minus(days,ChronoUnit.DAYS);	
		   }
}
