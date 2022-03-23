package br.com.external.task.client.ConsultaCep.service;

import br.com.external.task.client.ConsultaCep.dto.ResponseCepDto;
import feign.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(url = "${url.consulta.cep}", name = "ConsultaCEP")
public interface ConsultaCepClient {

    @RequestMapping(value = "${path.consulta.cep}", headers = {"Content-Type: application/json"})
    ResponseEntity<ResponseCepDto> consultaCep(@PathVariable String cep);
}
