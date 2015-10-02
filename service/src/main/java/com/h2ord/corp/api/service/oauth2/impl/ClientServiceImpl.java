package com.h2ord.corp.api.service.oauth2.impl;


import com.h2ord.corp.api.dao.oauth2.ClientDao;
import com.h2ord.corp.api.model.oauth2.Client;
import com.h2ord.corp.api.service.impl.AbstractServiceImpl;
import com.h2ord.corp.api.service.oauth2.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chy on 14-10-17.
 */
@Service("clientService")
@Transactional(readOnly = true)
public class ClientServiceImpl extends AbstractServiceImpl<Client, Long> implements ClientService {

    @Autowired
    public ClientServiceImpl(ClientDao dao) {
        super(dao);
    }

    @Override
    @Transactional
    public String getClientCode(String clientName) {
        String hql = "from " + Client.class.getName() + " as c where c.clientName=:clientName";
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("clientName", clientName);
        List<Client> result = findByHql(hql, conditionMap);
        String clientCode = null;
        if (result != null && !result.isEmpty()) {
            clientCode = result.get(0).getClientId();
        }

        return clientCode;
    }

    // TODO 需要修改增加密码验证
    @Override
    @Transactional
    public Client getClientByCode(String clientCode) {
        String hql = "from " + Client.class.getName() + " as c where c.clientId=:clientCode";
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("clientCode", clientCode);
        List<Client> result = findByHql(hql, conditionMap);
        Client client = null;
        if (result != null && !result.isEmpty()) {
            client = result.get(0);
        }
        return client;
    }

    public boolean checkClient(String clientId, String clientSecret) {
        String hql = "from " + Client.class.getName() + " as c where c.clientId=:clientId and c.clientSecret = :clientSecret";
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("clientId", clientId);
        conditionMap.put("clientSecret", clientSecret);
        List<Client> result = findByHql(hql, conditionMap);
        if (result != null && !result.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
