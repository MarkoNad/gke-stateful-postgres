package com.gkedemo.service;

import com.gkedemo.domain.User;
import com.gkedemo.domain.UserRepository;
import com.gkedemo.exception.RemoteInvocationFailedException;
import com.gkedemo.exception.UserNotFoundException;
import com.gkedemo.service.dto.GetUserItemsRequest;
import com.gkedemo.service.dto.GetUserItemsResponse;
import com.gkedemo.service.dto.ItemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final RestTemplateBuilder restTemplateBuilder;

    private final String itemServiceUrl;

    public UserService(UserRepository userRepository, RestTemplateBuilder restTemplateBuilder, @Value("${item-service.url}") final String itemServiceUrl) {
        this.userRepository = userRepository;
        this.restTemplateBuilder = restTemplateBuilder;
        this.itemServiceUrl = itemServiceUrl;
    }

    public Set<String> getUserItems(final UUID userId) {
        final Optional<User> maybeUser = userRepository.findById(userId);
        if (maybeUser.isEmpty()) {
            throw new UserNotFoundException("User " + userId + " not found.");
        }
        final RestTemplate restTemplate = restTemplateBuilder.rootUri(itemServiceUrl).build();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(GetUserItemsRequest.MEDIA_TYPE);
        headers.setAccept(List.of(GetUserItemsResponse.MEDIA_TYPE));
        final HttpEntity<GetUserItemsRequest> request = new HttpEntity<>(new GetUserItemsRequest(userId), headers);
        LOGGER.info("Getting user's items from remote service: {}", request);
        final ResponseEntity<GetUserItemsResponse> response = restTemplate.exchange(
                "/items",
                HttpMethod.POST,
                request,
                GetUserItemsResponse.class
        );
        LOGGER.info("Got response: {}", response);
        if (response.getBody() == null) {
            throw new RemoteInvocationFailedException("The Item service responded with an empty body.");
        }
        return response.getBody()
                .items()
                .stream()
                .map(ItemDto::name)
                .collect(Collectors.toSet());
    }

}
