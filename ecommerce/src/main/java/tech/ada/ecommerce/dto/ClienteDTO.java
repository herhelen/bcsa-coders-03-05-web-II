package tech.ada.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import tech.ada.ecommerce.model.Compra;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class ClienteDTO {

    private Long id;

    private String nomeCompleto;

    private String dataNascimento;

    private String cpf;

    private String email;

    private String senha;

    private boolean ativo;

    @JsonIgnore
    private Date dataDesativacao;

//    public String getNomeCompleto() {
//        return nomeCompleto;
//    }
//
//    public void setNomeCompleto(String nomeCompleto) {
//        this.nomeCompleto = nomeCompleto;
//    }
//
//    public String getDataNascimento() {
//        return dataNascimento;
//    }
//
//    public void setDataNascimento(String dataNascimento) {
//        this.dataNascimento = dataNascimento;
//    }
//
//    public String getCpf() {
//        return cpf;
//    }
//
//    public void setCpf(String cpf) {
//        this.cpf = cpf;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getSenha() {
//        return senha;
//    }
//
//    public void setSenha(String senha) {
//        this.senha = senha;
//    }
//
//    public List<Compra> getCompras() {
//        return compras;
//    }
//
//    public void setCompras(List<Compra> compras) {
//        this.compras = compras;
//    }
//
//    public boolean isAtivo() {
//        return ativo;
//    }
//
//    public void setAtivo(boolean ativo) {
//        this.ativo = ativo;
//    }
}
