package br.com.projeto.forum.config.validacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErroDeFormularioDTO {

    private String campo;
    private String erro;
}
