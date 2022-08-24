package com.portfolioFBD.Controller;

import com.portfolioFBD.Dto.AdminDto;
import com.portfolioFBD.Dto.Mensaje;
import com.portfolioFBD.Entity.Admin;
import com.portfolioFBD.Service.AdminService;
import com.portfolioFBD.Exceptions.AdminNoEncontradoException;
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

@RequestMapping("/admin")
@RestController 
public class AdminController {
    @Autowired
    private AdminService adminService;

    //Muestro todos los Admin (GET /Admin)
    @GetMapping("/")
    ResponseEntity<List<Admin>> mostrarTodos() {
        List<Admin> listaAdmin= adminService.buscarTodos();
        return new ResponseEntity(listaAdmin, HttpStatus.OK);
    }

    //Muestro un Admin según id (GET /Admin/id )
    @GetMapping("/{id}")
    ResponseEntity<?> mostrarUno(@PathVariable("id") Integer id) {
        Admin admin;
        try {
          admin = adminService.buscarPorId(id);  
        } catch (AdminNoEncontradoException e) {
           return new ResponseEntity(new Mensaje ("El admin no fue encontrado."), HttpStatus.NOT_FOUND);
        }
     return new ResponseEntity(admin, HttpStatus.OK);
    }

    //Creo un Admin nuevo (POST /admin)
    @PostMapping("/") //Tomo los datos del POST con RequestParam
    Admin crearAdmin(@RequestBody AdminDto admin) {
        return adminService.crear(admin);
    }

    //Modifico un Admin según id (PUT /Admin/id + los datos del PUT)
    @PutMapping("/{id}") //El id llega como PathVariable, el resto como parámetros dentro del PUT (como un POST)
    Admin modificarAdmin(@PathVariable Integer id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam Long dni)
            throws AdminNoEncontradoException {
        return adminService.modificar(id, nombre, apellido, dni);
    }

    //DELETE /admin/id -> elimina un Admin según id
    @DeleteMapping("/{id}")
    ResponseEntity<Mensaje> eliminarAdmin(@PathVariable Integer id) {
        adminService.eliminar(id);
        return new ResponseEntity<Mensaje>(new Mensaje("El admin fue eliminado."), HttpStatus.OK);
    }
}
