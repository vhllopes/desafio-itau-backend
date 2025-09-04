package br.com.vitorlopes.desafioitaubackend.service;

import br.com.vitorlopes.desafioitaubackend.model.dto.EstatisticaResponse;
import br.com.vitorlopes.desafioitaubackend.model.entity.Transacao;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TransacaoService {
    /**
     * Aqui foi escolhido a estrutura 'ConcurrentLinkedQueue' pelo seu auto gerenciamento 'thread safe'
     * Importante ressaltar que o desafio pede que os dados sejam trabalhados na própria memória da aplicacao
     */
    private final Queue<Transacao> transacoes = new ConcurrentLinkedQueue<>();

    public void addTransacao(Transacao transacao){
        transacoes.add(transacao);
    }

    public void clearTransacoes(){
        transacoes.clear();
    }

    public EstatisticaResponse getEstatistica(){
        OffsetDateTime limite = OffsetDateTime.now().minusSeconds(60); //pega a data atual no momento de execucao e subtrai 60sec

        double soma = 0;
        long contagem = 0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        double media = 0;

        //Loop para cada transacao na fila
        for(Transacao transacao : transacoes){

            //filtrando apenas as transacoes de até 60seg atras
            if(transacao.getDataHora().isAfter(limite)){

                double valor = transacao.getValor();

                soma += valor;
                contagem++;

                if(valor < min){
                    min = valor;
                }

                if(valor > max){
                    max = valor;
                }
            }
        }

        //Condicional que evita a divisão por 0 em casos de contagem == 0
        if(contagem > 0){
            media = soma/contagem;
        }
        //Caso tenhamos 0 transacoes ajustamos os valores de min e max para 0
        else{
            min = 0;
            max = 0;
        }

        return new EstatisticaResponse(contagem, soma, media, min, max);

    }

}
