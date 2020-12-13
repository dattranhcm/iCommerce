package com.technicaltest.icommerceuserservice.entity;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface UserRepository extends PagingAndSortingRepository<TUser, Long> {
    List<TUser> findByName(@Param("fullName") String name);
}
