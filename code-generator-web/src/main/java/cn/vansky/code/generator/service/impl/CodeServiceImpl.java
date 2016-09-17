/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.service.impl;

import cn.vansky.code.generator.dao.CodeDao;
import cn.vansky.code.generator.service.CodeService;
import cn.vansky.code.generator.vo.CodeVo;
import cn.vansky.code.generator.vo.TableName;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/3/27.
 */
@Service("codeService")
public class CodeServiceImpl implements CodeService {

    @Resource(name = "codeDao")
    private CodeDao codeDao;

    public List<TableName> selectTableName(CodeVo codeVo) {
        return codeDao.selectTableName(codeVo);
    }
}
