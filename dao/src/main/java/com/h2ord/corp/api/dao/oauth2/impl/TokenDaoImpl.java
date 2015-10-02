package com.h2ord.corp.api.dao.oauth2.impl;

import com.h2ord.corp.api.dao.impl.AbstractDaoImpl;
import com.h2ord.corp.api.dao.oauth2.TokenDao;
import com.h2ord.corp.api.model.oauth2.Token;
import org.springframework.stereotype.Repository;

/**
 * Created by chy on 14-10-16.
 */
@Repository
public class TokenDaoImpl extends AbstractDaoImpl<Token,Long> implements TokenDao{

}
