package br.com.vitorlopes.desafioitaubackend.controller;

import br.com.vitorlopes.desafioitaubackend.model.dto.EstatisticaResponse;
import br.com.vitorlopes.desafioitaubackend.model.entity.Transacao;
import br.com.vitorlopes.desafioitaubackend.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {
    @Autowired
    private TransacaoService transacaoService;

    @GetMapping
    public ResponseEntity<EstatisticaResponse> getEstatistica(){
        EstatisticaResponse stats = transacaoService.getEstatistica();
        return ResponseEntity.ok(stats);
    }
}
