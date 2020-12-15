package com.technicaltest.icommerceorderservice.entity;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface OrderRepository extends PagingAndSortingRepository<TOrder, Long> {
    List<TOrder> findByID(@Param("id") String id);
}
