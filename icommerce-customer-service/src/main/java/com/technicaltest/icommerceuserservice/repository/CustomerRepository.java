package com.technicaltest.icommerceuserservice.repository;

import com.technicaltest.icommerceuserservice.entity.TCustomer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(exported = false)
public interface CustomerRepository extends PagingAndSortingRepository<TCustomer, UUID> {
    List<TCustomer> findByUuid(UUID uuid);
    TCustomer findByFacebookIdAndFacebookToken(String facebookId, String hashPassword);
}
