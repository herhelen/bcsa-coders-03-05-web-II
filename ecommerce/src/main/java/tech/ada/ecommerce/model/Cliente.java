package tech.ada.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

//@Getter
//@Setter
//@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
//@Table(name = "usuario")
public class Cliente implements Serializable {

    @Id // indica que é PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // gerar valor automático do Id
    private Long id;

    @Column(length = 50, nullable = false)
    private String nomeCompleto;

    @Column(nullable = false)
    private Date dataNascimento;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(unique = true, nullable = false)
    private String email; // login

    @Column(nullable = false)
    private String senha;

//    @OneToMany(fetch = FetchType.LAZY)
    @OneToMany
    private List<Compra> compras;

    @Column(nullable = false)
    private boolean ativo;

    @Column
    private Date dataDesativacao;

    public Cliente() {
    }

    public Cliente(Long id, String nomeCompleto, Date dataNascimento, String cpf, String email, String senha, boolean ativo) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
    }

    public Cliente(String nomeCompleto, Date dataNascimento, String cpf, String email, String senha,
                   boolean ativo) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataDesativacao() {
        return dataDesativacao;
    }

    public void setDataDesativacao(Date dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }
}
