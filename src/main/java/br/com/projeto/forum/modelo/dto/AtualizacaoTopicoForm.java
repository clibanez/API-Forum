package br.com.projeto.forum.modelo.dto;


import br.com.projeto.forum.modelo.Topico;
import br.com.projeto.forum.repositories.TopicoRepository;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class AtualizacaoTopicoForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String titulo;

    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String mensagem;

    public Topico atualizar(Long id, TopicoRepository topicoRepository){
        Topico topico = topicoRepository.findById(id).orElseThrow(null);
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);
        return topico;
    }
}
