package com.technicaltest.icommerceorderservice.repository;

import com.technicaltest.icommerceorderservice.entity.TOrder;
import com.technicaltest.icommerceorderservice.entity.TOrderItems;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface OrderItemRepository extends PagingAndSortingRepository<TOrderItems, Long> {
    List<TOrderItems> findById(@Param("id") String id);
}
