package com.technicaltest.icommerceuserservice.bean;


import com.technicaltest.icommerceuserservice.dto.RegistrationRequest;
import com.technicaltest.icommerceuserservice.entity.TCustomer;

import java.util.List;
import java.util.UUID;

public interface CustomerServiceBean {
    List<TCustomer> findByUuid(UUID uuid);
    Object registration(RegistrationRequest registrationRequest);
    Object login(String userName, String password);
}
