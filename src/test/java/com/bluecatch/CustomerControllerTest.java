package com.bluecatch;

import com.bluecatch.controller.CustomerController;
import com.bluecatch.data.dto.request.CustomerDto;
import com.bluecatch.data.dto.response.CustomerCollectionResponse;
import com.bluecatch.data.dto.response.CustomerMetricsResponse;
import com.bluecatch.data.dto.response.CustomerResponse;
import com.bluecatch.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    private MockMvc mockMvc;
    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController controller;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    private CustomerDto createValidCustomerDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("Juan");
        customerDto.setPaternalName("Perez");
        customerDto.setMaternalName("Garcia");
        customerDto.setBirthDate(LocalDate.of(1990, 5, 15));
        customerDto.setAge(33);
        return customerDto;
    }

    private CustomerResponse createCustomerResponse() {
        return CustomerResponse.builder()
                .id(1L)
                .firstName("Juan")
                .paternalName("Perez")
                .maternalName("Garcia")
                .age(33)
                .birthDate(LocalDate.of(1990, 5, 15))
                .lifeExpectancy(75)
                .build();
    }

    private CustomerCollectionResponse createCustomerCollectionResponse() {
        List<CustomerResponse> customers = Arrays.asList(
                createCustomerResponse(),
                CustomerResponse.builder()
                        .id(2L)
                        .firstName("Maria")
                        .paternalName("Lopez")
                        .maternalName("Martinez")
                        .age(28)
                        .birthDate(LocalDate.of(1995, 3, 20))
                        .lifeExpectancy(80)
                        .build()
        );

        return CustomerCollectionResponse.builder()
                .page(0)
                .pageSize(10)
                .totalPages(1)
                .totalElements(2L)
                .customerResponses(customers)
                .build();
    }

    private CustomerMetricsResponse createCustomerMetricsResponse() {
        return CustomerMetricsResponse.builder()
                .averageAge(30.5)
                .standardDeviation(5.2)
                .totalCustomers(10L)
                .minAge(18)
                .maxAge(65)
                .build();
    }

    @Test
    void saveCustomer_NullFirstName_BadRequest() throws Exception {
        CustomerDto customerDto = createValidCustomerDto();
        customerDto.setFirstName(null);
        mockMvc.perform(post("/api/v1.0/bluecatch/customer/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void saveCustomer_FutureBirthDate_BadRequest() throws Exception {
        CustomerDto customerDto = createValidCustomerDto();
        customerDto.setBirthDate(LocalDate.now().plusYears(1));
        mockMvc.perform(post("/api/v1.0/bluecatch/customer/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void saveCustomer_NullBirthDate_BadRequest() throws Exception {
        CustomerDto customerDto = createValidCustomerDto();
        customerDto.setBirthDate(null);
        mockMvc.perform(post("/api/v1.0/bluecatch/customer/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void saveCustomer_InvalidRequestBody_BadRequest() throws Exception {
        mockMvc.perform(post("/api/v1.0/bluecatch/customer/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("invalid json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findById_ExistingCustomer_Success() throws Exception {
        Long customerId = 1L;
        CustomerResponse expectedResponse = createCustomerResponse();
        when(customerService.findById(customerId)).thenReturn(expectedResponse);
        mockMvc.perform(get("/api/v1.0/bluecatch/customer/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Juan"))
                .andExpect(jsonPath("$.paternalName").value("Perez"))
                .andExpect(jsonPath("$.maternalName").value("Garcia"))
                .andExpect(jsonPath("$.age").value(33));
    }

    @Test
    void findById_NonExistingCustomer_ReturnsNull() throws Exception {
        Long customerId = 999L;
        when(customerService.findById(customerId)).thenReturn(null);
        mockMvc.perform(get("/api/v1.0/bluecatch/customer/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void findAll_WithDefaultParameters_Success() throws Exception {
        CustomerCollectionResponse expectedResponse = createCustomerCollectionResponse();
        when(customerService.findAll(anyInt(), anyInt())).thenReturn(expectedResponse);
        mockMvc.perform(get("/api/v1.0/bluecatch/customer/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.page").value(0))
                .andExpect(jsonPath("$.pageSize").value(10))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(2))
                .andExpect(jsonPath("$.customerResponses").isArray())
                .andExpect(jsonPath("$.customerResponses.length()").value(2));
    }

    @Test
    void findAll_WithCustomParameters_Success() throws Exception {
        int page = 1;
        int pageSize = 5;
        CustomerCollectionResponse expectedResponse = createCustomerCollectionResponse();
        expectedResponse.setPage(page);
        expectedResponse.setPageSize(pageSize);
        when(customerService.findAll(page, pageSize)).thenReturn(expectedResponse);
        mockMvc.perform(get("/api/v1.0/bluecatch/customer/all")
                        .param("page", String.valueOf(page))
                        .param("pageSize", String.valueOf(pageSize)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.page").value(page))
                .andExpect(jsonPath("$.pageSize").value(pageSize));
    }

    @Test
    void findMetrics_Success() throws Exception {
        CustomerMetricsResponse expectedResponse = createCustomerMetricsResponse();
        when(customerService.getDetailAgeData()).thenReturn(expectedResponse);
        mockMvc.perform(get("/api/v1.0/bluecatch/customer/metrics"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.averageAge").value(30.5))
                .andExpect(jsonPath("$.standardDeviation").value(5.2))
                .andExpect(jsonPath("$.totalCustomers").value(10))
                .andExpect(jsonPath("$.minAge").value(18))
                .andExpect(jsonPath("$.maxAge").value(65));
    }

    @Test
    void findMetrics_NoDataAvailable_ReturnsNull() throws Exception {
        when(customerService.getDetailAgeData()).thenReturn(null);
        mockMvc.perform(get("/api/v1.0/bluecatch/customer/metrics"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void allEndpoints_ProduceJsonContent() throws Exception {
        when(customerService.save(any(CustomerDto.class))).thenReturn(createCustomerResponse());
        when(customerService.findById(anyLong())).thenReturn(createCustomerResponse());
        when(customerService.findAll(anyInt(), anyInt())).thenReturn(createCustomerCollectionResponse());
        when(customerService.getDetailAgeData()).thenReturn(createCustomerMetricsResponse());

        CustomerDto customerDto = createValidCustomerDto();

        mockMvc.perform(post("/api/v1.0/bluecatch/customer/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(get("/api/v1.0/bluecatch/customer/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(get("/api/v1.0/bluecatch/customer/all"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(get("/api/v1.0/bluecatch/customer/metrics"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}