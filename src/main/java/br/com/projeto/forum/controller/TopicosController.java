package br.com.projeto.forum.controller;

import br.com.projeto.forum.modelo.Curso;
import br.com.projeto.forum.modelo.Topico;
import br.com.projeto.forum.modelo.dto.TopicoDTO;
import br.com.projeto.forum.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository repository;

    @GetMapping
    public List<TopicoDTO> findAll(String nomeCurso) {
        if (nomeCurso == null) {
            List<Topico> topicos = repository.findAll();
            return TopicoDTO.converter(topicos);
        } else {
            List<Topico> topicos = repository.findByCursoNome(nomeCurso);
            return TopicoDTO.converter(topicos);
        }
    }
}
