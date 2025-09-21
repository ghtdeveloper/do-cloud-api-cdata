package com.bluecatch.utils;

public class CalculateAgeUtils {
    private static final int AVG_PROM = 89;

    public static Integer calculateTime(Integer currentAge) {
        if (currentAge == null) {
            return 0;
        }
        int value = AVG_PROM - currentAge;
        return Math.max(value, 0);
    }

}
