package com.h2ord.corp.api.service.oauth2;

import com.h2ord.corp.api.model.oauth2.Client;
import com.h2ord.corp.api.service.AbstractService;

/**
 * Created by chy on 14-10-17.
 */
public interface ClientService extends AbstractService<Client,Long> {

    String getClientCode(String clientName);

    Client getClientByCode(String clientCode);

    boolean checkClient(String clientId,String clientSecret);
}
