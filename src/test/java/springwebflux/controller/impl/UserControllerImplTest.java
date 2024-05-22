package springwebflux.controller.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import springwebflux.entity.User;
import springwebflux.model.request.UserRequest;
import springwebflux.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static reactor.core.publisher.Mono.just;
import static reactor.core.publisher.Mono.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebClient
class UserControllerImplTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService service;


    @Test
    @DisplayName("Test andpoint save with success")
    void save() {
        final var request = new UserRequest("Ademir", "email@email.com","123");

        when(service.save(any(UserRequest.class))).thenReturn(just(User.builder().build()));

        webTestClient.post().uri("/users")
                .contentType(APPLICATION_JSON)
                .body(fromValue(request))
                .exchange()
                .expectStatus().isCreated();

        verify(service).save(any(UserRequest.class));
    }

    @Test
    @DisplayName("Test andpoint save with bad request")
    void saveBadRequest() {
        final var request = new UserRequest(" Ademir", "email@email.com","123");

        webTestClient.post().uri("/users")
                .contentType(APPLICATION_JSON)
                .body(fromValue(request))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.path").isEqualTo("/users")
                .jsonPath("$.path").isEqualTo(BAD_REQUEST.value())
                .jsonPath("$.path").isEqualTo("Validation error")
                .jsonPath("$.path").isEqualTo("Error on validation attributes")
                .jsonPath("$.errors[0].fieldName").isEqualTo("name")
                .jsonPath("$.errors[0].message").isEqualTo("field cannot have blank spaces at the beginning or at end");
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void updata() {
    }

    @Test
    void delete() {
    }
}