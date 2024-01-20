package com.example.service.endpoint;

import com.example.service.entity.User;
import com.example.service.repository.UserRepository;
import com.microservice.service.GetUserRequest;
import com.microservice.service.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://microservice.com/service";

    @Autowired
    private final UserRepository userRepository;

    public UserEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUserResponse(@RequestPayload GetUserRequest request){
        GetUserResponse response = new GetUserResponse();
        User user = userRepository.findById((long) request.getId())
                .orElseThrow();
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        return response;
    }
}
