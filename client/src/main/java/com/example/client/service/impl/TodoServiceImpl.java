package com.example.client.service.impl;

import com.example.client.service.TodoService;
import com.example.client.service.dto.Todo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final RestTemplate restTemplate;
    private final String resourceServerUri;

    public TodoServiceImpl(RestTemplate restTemplate,
                           @Value("${resource-server.uri}") String resourceServerUri) {
        this.restTemplate = restTemplate;
        this.resourceServerUri = resourceServerUri;
    }

    @Override
    public List<Todo> findAll() {
        ResponseEntity<List<Todo>> responseEntity = restTemplate.exchange(
                resourceServerUri + "/todos", HttpMethod.GET, createHttpEntity(),
                new ParameterizedTypeReference<List<Todo>>() {});
        return responseEntity.getBody();
    }

    @Override
    public void save(Todo todo) {
        HttpEntity<?> httpEntity = createHttpEntity(todo);
        restTemplate.exchange(resourceServerUri + "/todos",
                HttpMethod.POST, httpEntity, Void.class);
    }

    @Override
    public void updateDoneById(Integer id) {
        restTemplate.exchange(resourceServerUri + "/todos/{id}",
                HttpMethod.PATCH, createHttpEntity(), Void.class, id);
    }

    @Override
    public void deleteById(Integer id) {
        restTemplate.exchange(resourceServerUri + "/todos/{id}",
                HttpMethod.DELETE, createHttpEntity(), Void.class, id);
    }

    private HttpEntity<?> createHttpEntity(Object requestBody) {
        // リクエストヘッダーにアクセストークンを設定
        HttpHeaders httpHeaders = new HttpHeaders();
        // HttpEntityを作成
        HttpEntity<Object> httpEntity = new HttpEntity<>(requestBody, httpHeaders);
        return httpEntity;
    }

    private HttpEntity<?> createHttpEntity() {
        return createHttpEntity(null);
    }
}
