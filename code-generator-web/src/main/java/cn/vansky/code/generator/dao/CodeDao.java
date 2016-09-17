/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.dao;

import cn.vansky.code.generator.vo.CodeVo;
import cn.vansky.code.generator.vo.TableName;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/3/27.
 */
public interface CodeDao {

    /**
     * 查询表名
     * @param codeVo codeVo
     * @return 表名称MAP列表
     */
    List<TableName> selectTableName(CodeVo codeVo);
}
