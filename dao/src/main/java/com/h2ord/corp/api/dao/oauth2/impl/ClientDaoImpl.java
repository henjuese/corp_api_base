package com.h2ord.corp.api.dao.oauth2.impl;

import com.h2ord.corp.api.dao.impl.AbstractDaoImpl;
import com.h2ord.corp.api.dao.oauth2.ClientDao;
import com.h2ord.corp.api.model.oauth2.Client;
import org.springframework.stereotype.Repository;

/**
 * Created by chy on 14-10-17.
 */
@Repository
public class ClientDaoImpl extends AbstractDaoImpl<Client, Long> implements ClientDao {

}
