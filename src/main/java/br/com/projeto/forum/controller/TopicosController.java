package br.com.projeto.forum.controller;

import br.com.projeto.forum.modelo.Topico;
import br.com.projeto.forum.modelo.dto.AtualizacaoTopicoForm;
import br.com.projeto.forum.modelo.dto.TopicoDTO;
import br.com.projeto.forum.modelo.dto.TopicoFormDTO;
import br.com.projeto.forum.repositories.CursoRepository;
import br.com.projeto.forum.services.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private CursoRepository cursoRepository;



    @GetMapping
    public List<TopicoDTO> listarTodos(String nomeCurso) {
        return topicoService.listarTodos(nomeCurso);
    }


    @Transactional
    @PostMapping
    public ResponseEntity<TopicoDTO> cadastrar(@Valid @RequestBody TopicoFormDTO topicoFormDTO) {
        TopicoDTO topicoDTO = topicoService.cadastrar(topicoFormDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(topicoDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(topicoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> detalhar(@PathVariable Long id) {
        Topico topicoDTO = topicoService.detalhar(id);
        return ResponseEntity.ok().body(topicoDTO);
    }


    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){
        TopicoDTO topico = topicoService.atualizar(id,form);
        return ResponseEntity.ok().body(topico);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<TopicoDTO> excluir(@PathVariable Long id){
        topicoService.excluir(id);
        return ResponseEntity.ok().build();
    }



}
