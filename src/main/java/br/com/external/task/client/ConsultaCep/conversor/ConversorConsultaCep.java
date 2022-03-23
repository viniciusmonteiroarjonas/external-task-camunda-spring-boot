package br.com.external.task.client.ConsultaCep.conversor;

import br.com.external.task.client.ConsultaCep.dto.RequestCepDto;
import br.com.external.task.client.ConsultaCep.exception.ErroInternoException;
import org.apache.commons.logging.LogFactory;
import org.camunda.bpm.client.task.ExternalTask;
import org.apache.commons.logging.Log;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

@Component
public class ConversorConsultaCep implements Converter<ExternalTask, RequestCepDto> {

    private Log logger = LogFactory.getLog(ConversorConsultaCep.class);

    @Override
    public RequestCepDto convert(ExternalTask externalTask) {

        logger.info("Iniciando a conversão da classe Consulta CEP");
        RequestCepDto dados = new RequestCepDto();

        try {
            Map<String, Object> variables = externalTask.getAllVariables();
            dados.setCep(variables.get("cep").toString());

        } catch (Exception e) {
            try {
                logger.error("Erro ao Converter dados ${externalTask.getId()}", e);
                throw new ErroInternoException("Erro ao converter CEP", Arrays.toString(e.getStackTrace()), 500);
            } catch (ErroInternoException ex) {
                logger.error(e.getMessage());
                ex.printStackTrace();
            }
        }

        return dados;
    }


}
