package com.technicaltest.icommerceuserservice.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicaltest.icommerceuserservice.dto.LoginResponse;
import com.technicaltest.icommerceuserservice.dto.RegistrationRequest;
import com.technicaltest.icommerceuserservice.dto.RegistrationResponse;
import com.technicaltest.icommerceuserservice.entity.TCustomer;
import com.technicaltest.icommerceuserservice.helpper.JWTHelper;
import com.technicaltest.icommerceuserservice.repository.CustomerRepository;
import com.technicaltest.icommerceuserservice.services.CustomerService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceBeanImpl implements CustomerServiceBean {
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceBeanImpl.class);
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<TCustomer> findByUuid(UUID uuid) {
        return customerRepository.findByUuid(uuid);
    }

    @Override
    public RegistrationResponse registration(RegistrationRequest registrationRequest) {
        logger.info("registration bean: ");
        try {
            TCustomer customer = new TCustomer();
            customer.setUserName(registrationRequest.getUserName());
            customer.setAddress(registrationRequest.getAddress());
            customer.setEmail(registrationRequest.getEmail());
            customer.setFacebookId(registrationRequest.getFacebookId());
            customer.setFacebookToken(registrationRequest.getFacebookToken());
            customer.setFirstName(registrationRequest.getFirstName());
            customer.setLastName(registrationRequest.getLastName());
            customer.setMobile(registrationRequest.getMobile());
            customer.setIsActive(true);
            customer.setUserType("CUSTOMER");
            customer.setStartedDate(new Date());
            customer.setUpdatedAt(new Date());
            TCustomer onboarddingCustomer = customerRepository.save(customer);
            return new RegistrationResponse(0, "Registration success", null, onboarddingCustomer.getFacebookId());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return new RegistrationResponse(0, "Registration failed", null, null);
    }

    @Override
    public LoginResponse loginByFacebook(String facebookID, String facebookToken) throws IOException {
        logger.info("findByFacebookIdAndHashPassword: ");
        TCustomer customer = customerRepository.findByFacebookIdAndFacebookToken(facebookID, facebookToken);
        ObjectMapper mapper = new ObjectMapper();
        logger.info("result: " + mapper.writeValueAsString(customer));
        String jwt = JWTHelper.createJWT(customer.getUuid().toString());
        logger.info("JWT: " + jwt);
        if (customer != null) {
            return new LoginResponse(0, "Loggin success", null, jwt);
        }
        return new LoginResponse(-1, "Loggin false", null, null);
    }
}
