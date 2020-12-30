package com.technicaltest.icommerceorderservice.repository;

import com.technicaltest.icommerceorderservice.entity.TOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(exported = false)
public interface OrderRepository extends JpaRepository<TOrder, UUID> {
    TOrder findByUuid(UUID uuid);
    List<TOrder> findByCustomerId(UUID customerUuid);
}
