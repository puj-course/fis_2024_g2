package org.musify.controller;

import jakarta.persistence.EntityNotFoundException;
import org.musify.model.Usuario;
import org.musify.model.UsuarioFactory;
import org.musify.model.UsuarioGratuito;
import org.musify.model.UsuarioRegistroDTO;
import org.musify.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{nickname}")
    public ResponseEntity<?> getUsuarioByNickname(@PathVariable String nickname) {
        try {
            Usuario usuario = usuarioService.getUsuarioByNickname(nickname);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }
            return ResponseEntity.ok(usuario); // Retorna el usuario encontrado
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable String id) {
        try {
            Usuario usuario = usuarioService.getUsuarioById(id);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }
            return ResponseEntity.ok(usuario); // Retorna el usuario encontrado
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }

    @PostMapping
    public ResponseEntity<?> postUsuario(@RequestBody UsuarioRegistroDTO u) {
        try {
            // Llama al método en service
            Usuario nuevoUsuario = UsuarioFactory.crearUsuarioPorRol(u.getRol());
            nuevoUsuario=usuarioService.crearUsuario(u, nuevoUsuario);

            // Crear la URI para retornar la ubicación del nuevo usuario
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(nuevoUsuario.getId_usuario())
                    .toUri();

            return ResponseEntity.created(location).body(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el usuario: " + e.getMessage());
        }
    }

}

