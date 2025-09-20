package com.bluecatch.data.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import static com.bluecatch.utils.RegexPattern.REGEX_ONLY_LETTERS;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDto {

    @Pattern(regexp = REGEX_ONLY_LETTERS, message = "firstName is invalid")
    private String firstName;

    @Pattern(regexp = REGEX_ONLY_LETTERS, message = "paternalName is invalid")
    private String paternalName;

    @Pattern(regexp = REGEX_ONLY_LETTERS, message = "maternalName is invalid")
    private String maternalName;

    @Min(value = 0, message = "Age must be greater than or equal to 0")
    @Max(value = 150, message = "Age must be less than or equal to 150")
    @Digits(integer = 3, fraction = 0, message = "Age must be a valid integer number")
    private Integer age;

    @Past(message = "birthDate must be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @NotNull(message = "birthDate is required")
    private LocalDate birthDate;

}
