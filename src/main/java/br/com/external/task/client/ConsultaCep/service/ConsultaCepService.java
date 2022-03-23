package br.com.external.task.client.ConsultaCep.service;

import br.com.external.task.client.ConsultaCep.dto.RequestCepDto;
import br.com.external.task.client.ConsultaCep.dto.ResponseCepDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaCepService {

    @Autowired
    private ConsultaCepClient consultaCepClient;

    public ResponseCepDto consultaCep(RequestCepDto dados) {
        System.out.println(dados.getCep());
        ResponseCepDto response = consultaCepClient.consultaCep(dados.getCep()).getBody();
        return response;

    }

}
