package com.conexa.saude.api.desafiotecnicobackendconexa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "SINTOMA")
@AllArgsConstructor
@NoArgsConstructor
public class Sintoma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sintoma")
    private Integer idSintoma;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "detalhes")
    private String detalhes;

    @Column(name = "id_atendimento_fk")
    private Integer idAtendimento;

}
