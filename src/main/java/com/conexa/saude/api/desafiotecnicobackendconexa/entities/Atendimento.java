package com.conexa.saude.api.desafiotecnicobackendconexa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.support.MessageSourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "ATENDIMENTO")
@AllArgsConstructor
@NoArgsConstructor
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atendimento")
    private Integer idAtendimento;

    @NotNull(message = "Data/hora do atendimento não pode estar nula")
    @Column(name = "dataHora")
    private LocalDateTime dataHora;

    @NotBlank(message = "O atendimento precisa de um paciente para acontecer")
    @Column(name = "idPaciente")
    private String idPaciente;

    @NotBlank(message = "O atendimento precisa de um medico para acontecer")
    @Column(name = "idMedicoCriador")
    private String idMedicoCriador;

    @OneToMany(mappedBy = "idAtendimento", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Sintoma> sintomas = new HashSet<>();

    @NotNull(message = "O campo status está inválido")
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;
}
