package com.example.service.endpoint;

import com.example.service.entity.Role;
import com.example.service.repository.RoleRepository;
import com.microservice.service.GetRoleRequest;
import com.microservice.service.GetRoleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class RoleEndpoint {

    private static final String NAMESPACE_URI = "http://microservice.com/service";

    @Autowired
    private final RoleRepository roleRepository;

    public RoleEndpoint(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRoleRequest")
    @ResponsePayload
    public GetRoleResponse getRoleResponse(@RequestPayload GetRoleRequest request){
        GetRoleResponse response = new GetRoleResponse();
        Role role = roleRepository.findById((long) request.getId())
                .orElseThrow();
        response.setName(role.getName());
        return response;
    }
}
