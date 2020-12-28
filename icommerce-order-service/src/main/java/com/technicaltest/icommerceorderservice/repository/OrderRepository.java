package com.technicaltest.icommerceorderservice.repository;

import com.technicaltest.icommerceorderservice.entity.TOrder;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(exported = false)
public interface OrderRepository extends PagingAndSortingRepository<TOrder, UUID> {
    TOrder findByUuid(UUID uuid);
    List<TOrder> findByCustomerId(UUID customerUuid);
}
