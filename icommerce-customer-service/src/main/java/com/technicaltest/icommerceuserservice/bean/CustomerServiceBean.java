package com.technicaltest.icommerceuserservice.bean;


import com.technicaltest.icommerceuserservice.dto.CustomerResponse;
import com.technicaltest.icommerceuserservice.dto.LoginResponse;
import com.technicaltest.icommerceuserservice.dto.RegistrationRequest;
import com.technicaltest.icommerceuserservice.dto.RegistrationResponse;

import java.io.IOException;
import java.util.UUID;

public interface CustomerServiceBean {
    CustomerResponse checkCustomerUUID(UUID customerUUID);
    RegistrationResponse registration(RegistrationRequest registrationRequest);
    LoginResponse loginByFacebook(String facebookID, String facebookToken) throws IOException;
}
