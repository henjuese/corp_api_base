package com.h2ord.corp.api.service.corp.impl;

import com.h2ord.corp.api.dao.corp.CorpDao;
import com.h2ord.corp.api.model.corp.Corp;
import com.h2ord.corp.api.service.corp.CorpService;
import com.h2ord.corp.api.service.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chy on 15/7/31.
 *
 */
@Service
@Transactional(readOnly = true)
public class CorpServiceImpl extends AbstractServiceImpl<Corp,Integer> implements CorpService{

    @Autowired
    public CorpServiceImpl(CorpDao dao) {
        super(dao);
    }
}
