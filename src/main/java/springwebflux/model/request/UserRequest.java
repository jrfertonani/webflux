package springwebflux.model.request;


public record UserRequest(

        String name,
        String email,
        String password

) { }
