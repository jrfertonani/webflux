package springwebflux.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import springwebflux.entity.User;
import springwebflux.model.request.UserRequest;
import springwebflux.model.response.UserResponse;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(final UserRequest request);

    @Mapping(target = "id", ignore = true)
    User toEntity(final UserRequest request, @MappingTarget final User entity);

    UserResponse toResponse(final User entity);
}