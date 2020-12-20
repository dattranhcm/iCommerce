package com.technicaltest.icommerceuserservice.bean;

import com.technicaltest.icommerceuserservice.entity.TCustomer;
import com.technicaltest.icommerceuserservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceBeanImpl implements CustomerServiceBean {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<TCustomer> findByUuid(UUID uuid) {
        return customerRepository.findByUuid(uuid);
    }
}
