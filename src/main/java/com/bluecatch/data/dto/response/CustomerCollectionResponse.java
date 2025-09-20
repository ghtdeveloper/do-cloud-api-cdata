package com.bluecatch.data.dto.response;

import lombok.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CustomerCollectionResponse {
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalElements;
    private List<CustomerResponse> customerResponses;
}
