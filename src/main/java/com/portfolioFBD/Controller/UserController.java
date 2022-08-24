package com.portfolioFBD.Controller;

import com.portfolioFBD.Dto.Mensaje;
import com.portfolioFBD.Dto.UserDto;
import com.portfolioFBD.Entity.Admin;
import com.portfolioFBD.Entity.User;
import com.portfolioFBD.Exceptions.UserNoEncontradoException;
import com.portfolioFBD.Service.AdminService;
import com.portfolioFBD.Service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@RestController 
public class UserController {
    @Autowired
    private UserService userService;

    //Muestro todos los user (GET /user)
    @GetMapping("/")
    ResponseEntity<List<Admin>> mostrarTodos() {
        List<User> listaUser= userService.buscarTodos();
        return new ResponseEntity(listaUser, HttpStatus.OK);
    }

    //Muestro un user según id (GET /user/id )
    @GetMapping("/{id}")
    ResponseEntity<?> mostrarUno(@PathVariable("id") Integer id) {
        User user;
        try {
          user = userService.buscarPorId(id);  
        } catch (UserNoEncontradoException e) {
           return new ResponseEntity(new Mensaje ("El user no fue encontrado."), HttpStatus.NOT_FOUND);
        }
     return new ResponseEntity(user, HttpStatus.OK);
    }

    //Creo un user nuevo (POST /user)
    @PostMapping("/") //Tomo los datos del POST con RequestParam
    User crearUser(@RequestBody UserDto user) {
        return userService.crear(user);
    }

    //Modifico un user según id (PUT /user/id + los datos del PUT)
    @PutMapping("/{id}") //El id llega como PathVariable, el resto como parámetros dentro del PUT (como un POST)
    User modificarAdmin(@PathVariable Integer id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam Long dni)
            throws UserNoEncontradoException {
        return userService.modificar(id, nombre, apellido, dni);
    }

    //DELETE /user/id -> elimina un user según id
    @DeleteMapping("/{id}")
    ResponseEntity<Mensaje> eliminarUser(@PathVariable Integer id) {
        userService.eliminar(id);
        return new ResponseEntity<Mensaje>(new Mensaje("El user fue eliminado."), HttpStatus.OK);
    }
}
