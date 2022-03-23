package br.com.external.task.client.ConsultaCep.exception;

public class ErroInternoException extends Exception {

    private Integer statusCode;
    private String reasonError;

    public ErroInternoException(String message, String reasonError, Integer statusCode) {
        super(message);
        this.reasonError = reasonError;
        this.statusCode = statusCode;
    }



}
