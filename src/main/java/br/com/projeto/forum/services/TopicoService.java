package br.com.projeto.forum.services;

import br.com.projeto.forum.modelo.Topico;
import br.com.projeto.forum.modelo.dto.AtualizacaoTopicoForm;
import br.com.projeto.forum.modelo.dto.TopicoDTO;
import br.com.projeto.forum.modelo.dto.TopicoFormDTO;
import br.com.projeto.forum.repositories.CursoRepository;
import br.com.projeto.forum.repositories.TopicoRepository;
import br.com.projeto.forum.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;


    public List<TopicoDTO> listarTodos(String nomeCurso) {
        if (nomeCurso == null) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDTO.converter(topicos);
        } else {
            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
            return TopicoDTO.converter(topicos);
        }
    }

    @Transactional
    public TopicoDTO cadastrar(TopicoFormDTO topicoFormDTO) {
        Topico topico = topicoFormDTO.converter(cursoRepository);
        topicoRepository.save(topico);
        return new TopicoDTO(topico);
    }

    public Topico detalhar(Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        return  topico.orElseThrow(() -> new ObjectNotFoundException("Objeto nõa encontrado! Id: " + id));
    }

  public TopicoDTO atualizar(Long id, @RequestBody @Valid AtualizacaoTopicoForm form){
      Topico obj = form.atualizar(id,topicoRepository);
      Topico topico  = topicoRepository.save(obj);
      return new TopicoDTO(topico);
  }

    public void excluir(Long id) {
        topicoRepository.deleteById(id);
    }


}
