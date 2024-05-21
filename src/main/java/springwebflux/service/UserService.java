package springwebflux.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springwebflux.entity.User;
import springwebflux.mapper.UserMapper;
import springwebflux.model.request.UserRequest;
import springwebflux.repository.UserRepository;
import springwebflux.service.exception.ObjectNotFoundException;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public Mono<User> save(final UserRequest request){
        return repository.save(mapper.toEntity(request));
    }

    public Mono<User> findById(final String id){
        return handleNotFound(repository.findAndRemove(id), id);
    }

    public Flux<User> findAll(){
        return repository.findAll();
    }

    public Mono<User> update(final String id, final UserRequest request){
        return findById(id)
                .map(entity -> mapper.toEntity(request, entity))
                .flatMap(repository::save);
    }

    public Mono<User> delete (final String id){
        return handleNotFound(repository.findAndRemove(id), id);
    }


    //generic of ObjectNotFoundException
    private <T> Mono <T> handleNotFound (Mono<T> mono, String id){
        return mono.switchIfEmpty(Mono.error(
                new ObjectNotFoundException(
                        format("Object not found. Id: %s, Type: %s", id,User.class.getSimpleName())
                )
        ));
    }

}
