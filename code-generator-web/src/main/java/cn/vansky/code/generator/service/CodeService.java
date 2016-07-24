/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.service;

import cn.vansky.code.generator.vo.CodeVo;
import cn.vansky.code.generator.vo.TableName;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/3/27.
 */
public interface CodeService {

    /**
     * Select table name.
     *
     * @param codeVo codeVo
     * @return the map of tableName
     */
    List<TableName> selectTableName(CodeVo codeVo);
}
