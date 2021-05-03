package com.pigdroid.util;

import java.util.Calendar;
import java.util.Date;

public final class DateUtils {

	private DateUtils() {

	}

	public static Date getDate(Date date, Date time) {
		Calendar calendar = Calendar.getInstance();
		Calendar timeCalendar = Calendar.getInstance();
		timeCalendar.setTime(time);
		calendar.set(Calendar.HOUR, timeCalendar.get(Calendar.HOUR));
		calendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, timeCalendar.get(Calendar.SECOND));
		//TODO millis?
		return calendar.getTime();
	}

}
