package com.bluecatch.domain.projection;

public interface  CustomerMetricsProjection {
    Double getAverageAge();
    Double getStandardDeviation();
    Integer getMinAge();
    Integer getMaxAge();
}
