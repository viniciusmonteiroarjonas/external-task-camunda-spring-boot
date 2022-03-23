package br.com.external.task.client.ConsultaCep;

import br.com.external.task.client.ConsultaCep.dto.RequestCepDto;
import br.com.external.task.client.ConsultaCep.dto.ResponseCepDto;
import br.com.external.task.client.ConsultaCep.service.ConsultaCepService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.converter.Converter;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Component
@ExternalTaskSubscription("consulta-cep")
public class ExternalTaskConsultaCepHandler implements ExternalTaskHandler {

    @Autowired
    private Converter<ExternalTask, RequestCepDto> conversor;

    @Autowired
    private ConsultaCepService consultaCepService;

    private Log logger = LogFactory.getLog(getClass());

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        logger.info("Iniciando a execução da tarefa da service task");

        try {
            RequestCepDto dados = conversor.convert(externalTask);

            if(dados != null) {
                Map<String, Object> variables = consultaCep(dados);
                logger.info("Complementado task Service");
                externalTaskService.complete(externalTask, variables);
            } else {
                logger.error("CEP não informado");
                throw new Exception("Cep não foi informado");
            }

        } catch (Exception e) {
            logger.error("Enviando erro para o cockpit engine", e);
            externalTaskService.handleFailure(externalTask, e.getMessage(), Arrays.toString(e.getStackTrace()), 0, 0);
        } finally {
            logger.info("Encerrando a execução da atividade");
        }

    }

    private Map<String, Object> consultaCep(RequestCepDto dados) {
        Map<String, Object> variables = new HashMap<>();

        logger.info("Chamando o serviço do Via CEP");
        ResponseCepDto dadosResponse = consultaCepService.consultaCep(dados);

        logger.info("Inserindo variavéis no Cockpit");
        variables.put("logradouro", dadosResponse.getLogradouro());

        return variables;
    }

}
