package springwebflux.model.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import springwebflux.validator.TrimString;

public record UserRequest(

        @TrimString
        @Size(min = 3, max = 50, message = "must be between 3 and 50 caracterers")
        @NotBlank(message = "must not be null or empty")
        String name,

        @TrimString
        @Email(message = "invalid email")
        @NotBlank(message = "must not be null or empty")
        String email,

        @TrimString
        @Size(min = 3, max = 50, message = "must be between 3 and 50 caracterers")
        @NotBlank(message = "must not be null or empty")
        String password

) { }
