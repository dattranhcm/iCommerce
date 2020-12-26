package com.technicaltest.icommerceproductservice.repository;

import com.technicaltest.icommerceproductservice.entity.TProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(exported = false)
public interface ProductRepository extends PagingAndSortingRepository<TProduct, UUID> {
    public TProduct findByUuid(String uuid);
    public List<TProduct> findByProductCodeIn(List<String> productCode);
    public List<TProduct> findAll();
}
