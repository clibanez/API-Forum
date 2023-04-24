package br.com.projeto.forum.modelo;


import br.com.projeto.forum.modelo.dto.TopicoDTO;
import br.com.projeto.forum.modelo.dto.TopicoFormDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Topico {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.NAO_RESPONDIDO;

    @ManyToOne
    private Usuario autor;
    @ManyToOne
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private List<Resposta> respostas = new ArrayList<>();

    public Topico(TopicoDTO topicoDTO) {
        this.id = topicoDTO.getId();
        this.titulo = topicoDTO.getTitulo();
        this.mensagem = topicoDTO.getMensagem();
        this.dataCriacao = topicoDTO.getDataCriacao();

    }

    public Topico(String titulo, String mensagem, Curso curso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.curso = curso;
    }
}
