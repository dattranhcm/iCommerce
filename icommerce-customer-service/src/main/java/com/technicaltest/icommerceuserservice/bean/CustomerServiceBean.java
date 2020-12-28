package com.technicaltest.icommerceuserservice.bean;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.technicaltest.icommerceuserservice.dto.CustomerResponse;
import com.technicaltest.icommerceuserservice.dto.LoginResponse;
import com.technicaltest.icommerceuserservice.dto.RegistrationRequest;
import com.technicaltest.icommerceuserservice.dto.RegistrationResponse;
import com.technicaltest.icommerceuserservice.entity.TCustomer;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface CustomerServiceBean {
    CustomerResponse checkCustomerUUID(UUID customerUUID);
    RegistrationResponse registration(RegistrationRequest registrationRequest);
    LoginResponse loginByFacebook(String facebookID, String facebookToken) throws IOException;
}
