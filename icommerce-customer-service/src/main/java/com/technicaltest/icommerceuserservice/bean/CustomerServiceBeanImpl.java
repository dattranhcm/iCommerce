package com.technicaltest.icommerceuserservice.bean;

import com.technicaltest.icommerceuserservice.dto.RegistrationRequest;
import com.technicaltest.icommerceuserservice.entity.TCustomer;
import com.technicaltest.icommerceuserservice.repository.CustomerRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import java.util.Date;
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

    @Override
    public Object registration(RegistrationRequest registrationRequest) {
        TCustomer customer = new TCustomer();
        customer.setAddress(registrationRequest.getAddress());
        customer.setEmail(registrationRequest.getEmail());
        customer.setFacebookId(registrationRequest.getFacebookId());
        customer.setFacebookToken(registrationRequest.getFacebookToken());
        customer.setFirstName(registrationRequest.getFirstName());
        customer.setLastName(registrationRequest.getLastName());
        customer.setMobile(registrationRequest.getMobile());
        customer.setIsActive("ACTIVE");
        customer.setUserType("CUSTOMER");
        customer.setStartedDate(new Date());
        customer.setUpdatedAt(new Date());
        return customerRepository.save(customer);
    }

    @Override
    public Object login(String userName, String password) {
        String hashPassword = DigestUtils
                .md5Hex(password);
        TCustomer customer = customerRepository.findByFacebookIdAndHashPassword(userName, hashPassword);
        return customer;
    }
}
