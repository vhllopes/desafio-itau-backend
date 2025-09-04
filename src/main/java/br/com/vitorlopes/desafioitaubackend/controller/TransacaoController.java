package br.com.vitorlopes.desafioitaubackend.controller;

import br.com.vitorlopes.desafioitaubackend.model.dto.TransacaoRequest;
import br.com.vitorlopes.desafioitaubackend.model.entity.Transacao;
import br.com.vitorlopes.desafioitaubackend.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Void> createTransacao(@Valid @RequestBody TransacaoRequest request){
        if(request.getValor() < 0 || request.getDataHora().isAfter(OffsetDateTime.now())){

            System.out.println("Valor de 'Transacao' invalido");
            return  ResponseEntity.unprocessableEntity().build();

        }
        transacaoService.addTransacao(new Transacao(request.getValor(), request.getDataHora()));
        System.out.println("Transacao criada com sucesso");
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransacao(){
        transacaoService.clearTransacoes();
        return ResponseEntity.ok().build();
    }
}
