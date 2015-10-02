package com.h2ord.corp.api.service.menu.category.impl;

import com.h2ord.corp.api.dao.AbstractDao;
import com.h2ord.corp.api.dao.menu.category.CategoryDao;
import com.h2ord.corp.api.model.menu.Category;
import com.h2ord.corp.api.service.impl.AbstractServiceImpl;
import com.h2ord.corp.api.service.menu.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chy on 15/8/1.
 */
@Service
@Transactional
public class CategoryServiceImpl extends AbstractServiceImpl<Category,Integer> implements CategoryService {

    @Autowired
    public CategoryServiceImpl(CategoryDao dao) {
        super(dao);
    }


}
