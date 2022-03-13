package com.conexa.saude.api.desafiotecnicobackendconexa.entities;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "PACIENTE")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Integer idPaciente;

    @NotBlank(message = "O campo email é obrigatório")
    @Email(message = "O email está inválido")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "O campo nome é obrigatório")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "O campo cpf é obrigatório")
    @CPF(message = "O cpf está inválido")
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "idade")
    private String idade;

    @NotBlank(message = "O campo telefone é obrigatório")
    @Column(name = "telefone")
    private String telefone;
}
