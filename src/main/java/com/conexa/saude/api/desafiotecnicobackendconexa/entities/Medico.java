package com.conexa.saude.api.desafiotecnicobackendconexa.entities;

import com.conexa.saude.api.desafiotecnicobackendconexa.entities.security.Perfil;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "MEDICO")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private Integer idMedico;

    @NotBlank(message = "O campo email é obrigatório")
    @Email(message = "O email está inválido")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "O campo senha é obrigatório")
    @Column(name = "senha")
    private String senha;

    @Column(name = "confirmacao_senha")
    private String confirmacaoSenha;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private Set<Integer> perfis = new HashSet<>();

    @Column(name = "especialidade")
    private String especialidade;

    @NotBlank(message = "O campo cpf é obrigatório")
    @CPF(message = "O cpf está inválido")
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "idade")
    private String idade;

    @NotBlank(message = "O campo telefone é obrigatório")
    @Column(name = "telefone")
    private String telefone;

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        perfis.add(perfil.getCod());
    }
}
