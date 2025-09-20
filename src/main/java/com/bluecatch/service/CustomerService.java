package com.bluecatch.service;


import com.bluecatch.data.dto.request.CustomerDto;
import com.bluecatch.data.dto.response.CustomerCollectionResponse;
import com.bluecatch.data.dto.response.CustomerResponse;
import com.bluecatch.data.repository.CustomerRepository;
import com.bluecatch.domain.CustomerEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerResponse save(CustomerDto customerDto) {
        Integer calculatedAge = Period.between(customerDto.getBirthDate(), LocalDate.now()).getYears();
        customerDto.setAge(calculatedAge);
        CustomerEntity customer = CustomerEntity.builder().build().from(customerDto);
        return this.customerRepository.save(customer).toDto();
    }

    public CustomerResponse findById(Long id) {
        if (this.customerRepository.findById(id).isPresent()) {
            return this.customerRepository.findById(id).get().toDto();
        }
        return null;
    }

    public CustomerCollectionResponse findAll(Integer page, Integer pageSize) {
        Page<CustomerEntity> entityPage = this.customerRepository.findAll(PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "id")));
        return CustomerCollectionResponse.builder()
                .page(page)
                .totalPages(pageSize)
                .totalPages(entityPage.getTotalPages() - 1)
                .totalElements(entityPage.getTotalElements())
                .customerResponses(entityPage.getContent().stream().map(CustomerEntity::toDto).toList())
                .build();
    }

}
