package springwebflux.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import springwebflux.entity.User;
import springwebflux.mapper.UserMapper;
import springwebflux.model.request.UserRequest;
import springwebflux.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public Mono<User> save(final UserRequest request){
        return repository.save(mapper.toEntity(request));
    }

}
