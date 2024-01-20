package com.example.service.endpoint;

import com.example.service.entity.Privilege;
import com.example.service.repository.PrivilegeRepository;
import com.microservice.service.GetPrivilegeRequest;
import com.microservice.service.GetPrivilegeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PrivilegeEndpoint {

    private static final String NAMESPACE_URI = "http://microservice.com/service";

    @Autowired
    private final PrivilegeRepository privilegeRepository;

    public PrivilegeEndpoint(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPrivilegeRequest")
    @ResponsePayload
    public GetPrivilegeResponse getPrivilegeResponse(@RequestPayload GetPrivilegeRequest request){
        GetPrivilegeResponse response = new GetPrivilegeResponse();
        Privilege privilege = privilegeRepository.findById((long) request.getId())
                .orElseThrow();
        response.setName(privilege.getName());
        return response;
    }
}
