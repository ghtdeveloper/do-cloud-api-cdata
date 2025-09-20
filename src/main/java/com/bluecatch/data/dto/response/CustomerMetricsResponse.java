package com.bluecatch.data.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Setter
@Getter
@ToString
public class CustomerMetricsResponse {
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "0.00")
    private Double averageAge;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "0.00")
    private Double standardDeviation;
    private Long totalCustomers;
    private Integer minAge;
    private Integer maxAge;
}
