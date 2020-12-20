package com.technicaltest.icommerceuserservice.repository;

import com.technicaltest.icommerceuserservice.entity.TAddressCustomer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface AddressCustomerRepository extends PagingAndSortingRepository<TAddressCustomer, Long> {
    Optional<TAddressCustomer> findById(@Param("id") Long id);
}
