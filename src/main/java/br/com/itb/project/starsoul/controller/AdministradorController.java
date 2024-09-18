package br.com.itb.project.starsoul.controller;

import br.com.itb.project.starsoul.model.Administrador;
import br.com.itb.project.starsoul.service.AdministradorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/admnistrador")
public class AdministradorController {

    final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }
	
	@PostMapping
    public ResponseEntity<Administrador> criarAdministrador(@RequestBody Administrador administrador) {
        Administrador novoAdministrador = administradorService.criarAdministrador(administrador);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAdministrador);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> listarAdministrador(@PathVariable Long id) {
        Administrador administrador = administradorService.listarAdministrador(id);
        return ResponseEntity.ok(administrador);
    }

    @GetMapping
    public ResponseEntity<List<Administrador>> listarTodosAdministradores() {
        List<Administrador> administradores = administradorService.listarTodosAdministradores();
        return ResponseEntity.ok(administradores);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> atualizarAdministrador(@PathVariable Long id, @RequestBody Administrador administradorAtualizado) {
        Administrador administrador = administradorService.atualizarAdministrador(id, administradorAtualizado);
        return ResponseEntity.ok(administrador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAdministrador(@PathVariable Long id) {
        administradorService.deletarAdministrador(id);
        return ResponseEntity.noContent().build();
    }

}