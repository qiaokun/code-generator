/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.controller;

import cn.vansky.code.generator.api.MyBatisGenerator;
import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.factory.GeneratorFactory;
import cn.vansky.code.generator.service.CodeService;
import cn.vansky.code.generator.vo.CodeVo;
import cn.vansky.code.generator.vo.TableName;
import cn.vansky.compress.CommonCompress;
import cn.vansky.compress.zip.ZIP;
import cn.vansky.framework.core.util.JsonResp;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/6/1.
 */
@Controller
@RequestMapping(value = "/code")
public class CodeGenController extends AbstractController {

    @Resource
    CodeService codeService;

    @ResponseBody
    @RequestMapping(value = "/selectTableName")
    public String selectTableName(CodeVo vo) {
        if (StringUtils.isBlank(vo.getUrl())) {
            vo.setRows(new ArrayList());
            return JsonResp.asData(vo).toJson();
        }
        List<TableName> list = codeService.selectTableName(vo);
        vo.setRows(list);
        return JsonResp.asData(vo).toJson();
    }

    @RequestMapping(value = "/download")
    public void download(CodeVo codeVo, HttpServletResponse response) {
        CodeGenContext context = new CodeGenContext();
        BeanUtils.copyProperties(codeVo, context);

        context.setDatabaseByUrl(codeVo.getUrl());
        String [] tableNames = codeVo.getTableNames().split(",");
        List<String> list = new ArrayList<String>();
        for (String tableName : tableNames) {
            list.add(tableName);
        }
        context.setTableNameList(list);
        MyBatisGenerator myBatisGenerator = GeneratorFactory.getInstance().getTableInfoWrapper(context);
        myBatisGenerator.generate();

        CommonCompress compress = new ZIP();
        compress.response(response);
        compress.source(context.getOut());
        compress.compress();
    }
}
