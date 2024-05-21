package springwebflux.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import springwebflux.entity.User;
import springwebflux.mapper.UserMapper;
import springwebflux.model.request.UserRequest;
import springwebflux.repository.UserRepository;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserService service;



    @Test
    void save() {
        UserRequest request = new UserRequest("Ademir Junior", "email@email.com", "123");
        User entity = User.builder().build();

        when(mapper.toEntity(any())).thenReturn(entity);
        when(repository.save(any(User.class))).thenReturn(Mono.just(User.builder().build()));

        Mono<User> result = service.save(request);

        StepVerifier.create(result)
                .expectNextMatches(user -> user.getClass() == User.class)
                .expectComplete()
                .verify();

        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void findById() {
        when(repository.findById(anyString())).thenReturn(Mono.just(User.builder()
                .id("1234")
                .build()));

        Mono<User> result = service.findById("1234");

        StepVerifier.create(result)
                .expectNextMatches(user -> user.getClass() == User.class)
                .expectComplete()
                .verify();

        Mockito.verify(repository, times(1)).findById(anyString());

    }

    @Test
    void findAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}