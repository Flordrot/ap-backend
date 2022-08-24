package com.portfolioFBD.Service;

import com.portfolioFBD.Dto.UserDto;
import com.portfolioFBD.Entity.User;
import com.portfolioFBD.Exceptions.UserNoEncontradoException;
import com.portfolioFBD.Repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User crear(UserDto userDto) {
        User user =new User();
        user.setAlta(true);
        user.setApellido(userDto.getApellido());
        user.setNombre(userDto.getNombre());
        user.setDni(userDto.getDni());
        user.setModificacion(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User modificar(Integer id, String nombre, String apellido, Long dni) throws UserNoEncontradoException {
        //Validaciones...

        //Busco al user que me llega por parámetro
        User user = buscarPorId(id); //Este método está más abajo, si no lo encuentra lanza una excepción
        //Seteo los nuevos datos que me llegan por parámetro
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setDni(dni);
        //Guardo el empleado y retorno el resultado
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> buscarTodos() {
        return userRepository.findAll(); //Devuelvo todos los User
    }

    @Transactional(readOnly = true)
    public User buscarPorId(Integer id) throws UserNoEncontradoException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNoEncontradoException(id)); //Busca el User por id, si no lo encuentra, lanza excepción
    }

    public void eliminar(Integer id) {
        userRepository.deleteById(id); //Soft delete (ver entidad User)
    }

}
