package com.algaworks.example.bulkhead.producer.domain;

import com.algaworks.example.bulkhead.producer.infra.NotificadorEncomenda;
import com.algaworks.example.bulkhead.producer.api.RecursoNaoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncomendaService {

    private final EncomendaRepository encomendaRepository;
    private final List<NotificadorEncomenda> notificadores;
    private final Logger log = LoggerFactory.getLogger(EncomendaService.class);

    public EncomendaService(EncomendaRepository encomendaRepository,
                            List<NotificadorEncomenda> notificadores) {
        this.encomendaRepository = encomendaRepository;
        this.notificadores = notificadores;
    }

    public Encomenda criarNova(Encomenda encomenda) {
        encomenda = encomendaRepository.save(encomenda);
        return encomenda;
    }

    public void marcarComoEmAndamento(Long id) {
        log.info("Marcando encomenda como em andamento");
        Encomenda encomenda = encomendaRepository.findById(id).orElseThrow(RecursoNaoEncontradoException::new);
        encomenda.marcarComoEmAndamento();
        encomendaRepository.save(encomenda);
        notificarAlteracao(encomenda);
    }

    public void marcarComoEntregue(Long id) {
        log.info("Marcando encomenda como entregue");
        Encomenda encomenda = encomendaRepository.findById(id).orElseThrow(RecursoNaoEncontradoException::new);
        encomenda.marcarComoEntregue();
        encomendaRepository.save(encomenda);
        notificarAlteracao(encomenda);
    }

    private void notificarAlteracao(Encomenda encomenda) {
        notificadores.forEach(notificador -> {
            try {
                notificador.notificar(encomenda);
            } catch (Exception e) {
                log.error("Erro ao enviar notificação.", e);
            }
        });
    }

}
