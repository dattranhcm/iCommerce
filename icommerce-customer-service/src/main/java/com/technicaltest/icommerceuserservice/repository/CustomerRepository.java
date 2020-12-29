package com.technicaltest.icommerceuserservice.repository;

import com.technicaltest.icommerceuserservice.entity.TCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(exported = false)
public interface CustomerRepository extends JpaRepository<TCustomer, UUID> {
    TCustomer findByUuid(UUID uuid);
    TCustomer findByFacebookId(String facebookID);
    TCustomer findByFacebookIdAndFacebookToken(String facebookId, String hashPassword);
}
