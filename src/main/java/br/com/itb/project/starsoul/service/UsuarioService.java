package br.com.itb.project.starsoul.service;

import br.com.itb.project.starsoul.exceptions.BadRequest;
import br.com.itb.project.starsoul.exceptions.NotFound;
import br.com.itb.project.starsoul.model.Usuario;
import br.com.itb.project.starsoul.model.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public Usuario criarUsuario(Usuario usuario) {
        if( !usuario.validarUsuario()) {
            throw new BadRequest(usuario.getMensagemErro());
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario listarUsuario(Long id) {
        try{
            return  usuarioRepository.findById(id).get();
        } catch (Exception ex){
            throw  new NotFound("Usuario não encontrado com o id " + id);
        }
    }

    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }


    @Transactional
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        try{
            if (!usuarioAtualizado.validarUsuario()) {
                throw  new BadRequest(usuarioAtualizado.getMensagemErro());
            }
            Usuario usuarioDb = usuarioRepository.findById(id).get();
            usuarioDb.setNome(usuarioAtualizado.getNome());
            usuarioDb.setEmail(usuarioAtualizado.getEmail());
            usuarioDb.setSenha(usuarioAtualizado.getSenha());
            usuarioDb.setStatus(usuarioAtualizado.isStatus());
            return usuarioRepository.save(usuarioDb);

        } catch (Exception ex){
            throw new NotFound("Usuario não encontrado com o id " + id);
        }
    }

    @Transactional
    public void deletarUsuario(Long id) {
        if(usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new NotFound("Usuario não encontrado com o id " + id);
        }
    }
}