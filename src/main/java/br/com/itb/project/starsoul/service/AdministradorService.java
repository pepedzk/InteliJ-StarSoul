package br.com.itb.project.starsoul.service;

import br.com.itb.project.starsoul.exceptions.BadRequest;
import br.com.itb.project.starsoul.exceptions.NotFound;
import br.com.itb.project.starsoul.model.Administrador;
import br.com.itb.project.starsoul.model.AdministradorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdministradorService {

    private final AdministradorRepository administradorRepository;

    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }


    public Administrador criarAdministrador(Administrador administrador) {
        if (!administrador.validarAdmin()) {
            throw new BadRequest(administrador.getMensagemErro());
        }
        return administradorRepository.save(administrador);
    }


    public Administrador listarAdministrador(Long id) {
        try{
            return  administradorRepository.findById(id).get();
        } catch (Exception ex){
            throw  new NotFound("Admnistrador não encontrado com o id " + id);
        }
    }


    public List<Administrador> listarTodosAdministradores() {
        return administradorRepository.findAll();
    }


    @Transactional
    public Administrador atualizarAdministrador(Long id, Administrador administradorAtualizado) {
        try{
            if (!administradorAtualizado.validarAdmin()) {
                throw  new BadRequest(administradorAtualizado.getMensagemErro());
            }
            Administrador administradorDb = administradorRepository.findById(id).get();
            administradorDb.setCodAdmin(administradorAtualizado.getCodAdmin());
            administradorDb.setEmail(administradorAtualizado.getEmail());
            administradorDb.setSenha(administradorAtualizado.getSenha());
            administradorDb.setStatus(administradorAtualizado.isStatus());
            return administradorRepository.save(administradorDb);

        } catch (Exception ex){
            throw new NotFound("Administrador não encontrado com o id " + id);
        }
    }

    @Transactional
    public void deletarAdministrador(Long id) {
        if(administradorRepository.existsById(id)) {
            administradorRepository.deleteById(id);
        } else {
            throw new NotFound("Administrador não encontrado com o id " + id);
        }
    }
}