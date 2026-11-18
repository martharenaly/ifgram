package ifgram.ifpb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import ifgram.ifpb.repository.UserRepository;
=======
import ifgram.ifpb.service.UserService;
>>>>>>> 1f5fd9f4ba3dc79fd0f453f5cbf5487cefc4eaa3
import ifgram.ifpb.dto.UserRequest;
import ifgram.ifpb.dto.UserResponse;

@RestController
@RequestMapping("user")
public class UserController {

    public final UserService service;

    public UserController(UserService service){
        this.service = service;
    }


    @GetMapping
    public List<UserResponse> getUser(){
        List<UserResponse> listaUsuarios = service.buscarTodosUsuarios();
        return listaUsuarios;
    }

    @PostMapping
    public UserResponse postUser(UserRequest request) throws Exception {
        UserResponse userResponse = service.criar(request);
        return userResponse;
    }

    @PutMapping
    public UserResponse UpdateUser(UserRequest request) throws Exception {
        return service.atualizarUsuario(request);
    }

    @DeleteMapping
    public void deleteUser(UserRequest request) throws Exception {
        return service.deletarUsuario(request);
    }

}