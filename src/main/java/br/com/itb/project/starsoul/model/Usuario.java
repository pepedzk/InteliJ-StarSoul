package br.com.itb.project.starsoul.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")

public class Usuario {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=true, length=100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 25)
    private String senha;

    @Column(nullable=false)
    private boolean status;


    @Transient
    private String mensagemErro = "teste";

    @Transient
    private boolean isValid = true;


    // Getters And Setters


    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


    public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}


    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


    public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public String getMensagemErro() {
        return mensagemErro;
    }

    public boolean isValid() {
        return isValid;
    }

    public boolean validarUsuario() {
        if (senha == null || senha.isEmpty()) {
            isValid = false;
            mensagemErro += "Você precisa inserir uma senha! \n";
        }
        if (email == null || email.isEmpty()) {
            isValid = false;
            mensagemErro += "Você precisa inserir um email! \n";
        }
        return isValid;
    }

}