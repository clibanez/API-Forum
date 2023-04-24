package br.com.projeto.forum.modelo.dto;

import br.com.projeto.forum.modelo.Curso;
import br.com.projeto.forum.modelo.Topico;
import br.com.projeto.forum.repositories.CursoRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TopicoFormDTO {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String titulo;
    @Length(min = 10)
    private String mensagem;
    @NotNull
    @NotEmpty
    private String nomeCurso;

     public Topico converter(CursoRepository cursoRepository){
         Curso curso = cursoRepository.findByNome(nomeCurso);
         return new Topico(titulo,mensagem, curso);

     }
}
