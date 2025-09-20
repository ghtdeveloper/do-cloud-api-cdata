package com.bluecatch.data.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Setter
@Getter
@ToString
public class CustomerResponse {
    private Long id;
    private String firstName;
    private String paternalName;
    private String maternalName;
    private Integer age;
    private LocalDate birthDate;
}
