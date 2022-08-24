package com.portfolioFBD.Service;

import com.portfolioFBD.Dto.AdminDto;
import com.portfolioFBD.Entity.Admin;
import com.portfolioFBD.Exceptions.AdminNoEncontradoException;
import com.portfolioFBD.Repository.AdminRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin crear(AdminDto adminDto) {
        Admin admin =new Admin();
        admin.setAlta(true);
        admin.setApellido(adminDto.getApellido());
        admin.setNombre(adminDto.getNombre());
        admin.setDni(adminDto.getDni());
        admin.setModificacion(LocalDateTime.now());
        return adminRepository.save(admin);
    }

    public Admin modificar(Integer id, String nombre, String apellido, Long dni) throws AdminNoEncontradoException {
        //Validaciones...

        //Busco al admin que me llega por parámetro
        Admin admin = buscarPorId(id); //Este método está más abajo, si no lo encuentra lanza una excepción
        //Seteo los nuevos datos que me llegan por parámetro
        admin.setNombre(nombre);
        admin.setApellido(apellido);
        admin.setDni(dni);
        //Guardo el empleado y retorno el resultado
        return adminRepository.save(admin);
    }

    @Transactional(readOnly = true)
    public List<Admin> buscarTodos() {
        return adminRepository.findAll(); //Devuelvo todos los admin
    }

    @Transactional(readOnly = true)
    public Admin buscarPorId(Integer id) throws AdminNoEncontradoException {
        return adminRepository.findById(id)
                .orElseThrow(() -> new AdminNoEncontradoException(id)); //Busca el admin por id, si no lo encuentra, lanza excepción
    }

    public void eliminar(Integer id) {
        adminRepository.deleteById(id); //Soft delete (ver entidad Admin)
    }

}
