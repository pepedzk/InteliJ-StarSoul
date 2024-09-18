package br.com.itb.project.starsoul.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Administrador")

public class Administrador {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=6)
    private int codAdmin;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 25)
    private String senha;

    @Column(nullable=false)
    private boolean status;

    @Transient
    private String mensagemErro = "";

    @Transient
    private boolean isValid = true;


    // Getters And Setters


    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


    public int getCodAdmin() {
		return codAdmin;
	}
	public void setCodAdmin(int codAdmin) {
		this.codAdmin = codAdmin;
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

    public boolean validarAdmin() {
        if (codAdmin == 0) {
            isValid = false;
            mensagemErro += "Você precisa inserir um Código para o Admin! \n";
        }
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