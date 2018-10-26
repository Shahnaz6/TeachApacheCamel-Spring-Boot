package com.learncamel.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.restlet.RestletConstants;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResponseProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

        String responseValue = (String) exchange.getIn().getBody();
        log.info("Reponse Processor : "+ responseValue);
        Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
        response.setStatus(Status.SUCCESS_OK);
        response.setEntity(responseValue, MediaType.APPLICATION_JSON);
        exchange.getOut().setBody(response);
    }
}
