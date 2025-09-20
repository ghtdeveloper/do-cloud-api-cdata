package com.bluecatch.domain.entities;


import com.bluecatch.data.dto.request.CustomerDto;
import com.bluecatch.data.dto.response.CustomerResponse;
import com.bluecatch.utils.ToDTO;
import com.bluecatch.utils.TransformFrom;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "tb_customer", schema = "bluecatch")
public class CustomerEntity implements TransformFrom<CustomerDto, CustomerEntity>, ToDTO<CustomerResponse> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "first_name", updatable = false)
    private String firstName;

    @Column(name = "paternal_name", updatable = false)
    private String paternalName;

    @Column(name = "maternal_name", updatable = false)
    private String maternalName;

    @Column(name = "age", updatable = false)
    private Integer age;

    @Column(name = "birthdate", updatable = false)
    private LocalDate birthdate;

    @Transient
    private Integer lifeExpectancy;

    @Override
    public CustomerResponse toDto() {
        return CustomerResponse.builder()
                .id(this.id)
                .firstName(this.firstName)
                .paternalName(this.paternalName)
                .maternalName(this.maternalName)
                .age(this.age)
                .birthDate(this.birthdate)
                .lifeExpectancy(this.lifeExpectancy)
                .build();
    }

    @Override
    public CustomerEntity from(CustomerDto customerDto) {
        return CustomerEntity.builder()
                .firstName(customerDto.getFirstName())
                .paternalName(customerDto.getPaternalName())
                .maternalName(customerDto.getMaternalName())
                .birthdate(customerDto.getBirthDate())
                .age(customerDto.getAge())
                .build();
    }
}
