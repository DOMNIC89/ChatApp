package com.parth.chatapp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {
    private static final SimpleDateFormat CHAT_TIMESTAMP_YEARLY_FORMAT;
    private static final SimpleDateFormat CHAT_TIMESTAMP_DEFAULT_FORMAT;
    private static final SimpleDateFormat CHAT_TIMESTAMP_MINUTES_FORMAT;

    static {
        CHAT_TIMESTAMP_YEARLY_FORMAT = new SimpleDateFormat("d MMM yyyy", Locale.getDefault());
        CHAT_TIMESTAMP_DEFAULT_FORMAT = new SimpleDateFormat("d MMM", Locale.getDefault());
        CHAT_TIMESTAMP_MINUTES_FORMAT = new SimpleDateFormat("h:mm a", Locale.getDefault());
    }

    public static String getTimestampText(long timestamp) {
        long now = System.currentTimeMillis();
        Calendar nowCal = Calendar.getInstance();
        nowCal.setTime(new Date(now));
        Calendar timestampCal = Calendar.getInstance();
        timestampCal.setTime(new Date(timestamp));
        if (now > timestamp) {
            if (nowCal.get(Calendar.YEAR) != timestampCal.get(Calendar.YEAR)) {
                Date date = new Date(timestamp);
                return CHAT_TIMESTAMP_YEARLY_FORMAT.format(date);
            } else if (nowCal.get(Calendar.MONTH) != timestampCal.get(Calendar.MONTH)) {
                Date date = new Date(timestamp);
                return CHAT_TIMESTAMP_DEFAULT_FORMAT.format(date);
            } else if (nowCal.get(Calendar.DAY_OF_MONTH) != timestampCal.get(Calendar.DAY_OF_MONTH)) {
                Date date = new Date(timestamp);
                return CHAT_TIMESTAMP_DEFAULT_FORMAT.format(date);
            } else {
                Date date = new Date(timestamp);
                return CHAT_TIMESTAMP_MINUTES_FORMAT.format(date);
            }
        }
        return "";
    }
}
