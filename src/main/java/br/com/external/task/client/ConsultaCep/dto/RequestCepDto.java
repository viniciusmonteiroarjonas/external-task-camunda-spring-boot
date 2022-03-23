package br.com.external.task.client.ConsultaCep.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestCepDto {

    @JsonProperty("cep")
    private String cep;

    public RequestCepDto(String cep) {
        this.cep = cep;
    }

    public RequestCepDto() {}

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
