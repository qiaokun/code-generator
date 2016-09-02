package cn.vansky.code.generator.service.general;

import cn.vansky.code.generator.api.TableInfoWrapperEnum;
import cn.vansky.framework.core.kv.handler.DefaultKvHandler;
import com.google.common.base.Function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/9/2
 */
public class GeneratorTypeHandler extends DefaultKvHandler {

    public GeneratorTypeHandler(Integer name, String tableName, String keyField, String valueField,
                                Map<String, Object> extraFields, int sqFlag, boolean daoExecuteFlag) {
        super(name, tableName, keyField, valueField, extraFields, sqFlag, daoExecuteFlag, 2);
    }

    protected void setupTemplates() {
        this.fn = new Function<Map<String, Object>, List<Map<String, Object>>>() {
            @Override
            public List<Map<String, Object>> apply(Map<String, Object> input) {
                List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
                TableInfoWrapperEnum [] tableInfoWrapperEnums = TableInfoWrapperEnum.values();
                for (TableInfoWrapperEnum tableInfoWrapperEnum : tableInfoWrapperEnums) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name", tableInfoWrapperEnum.getName());
                    map.put("desc", tableInfoWrapperEnum.getName());
                    result.add(map);
                }
                return result;
            }
        };
    }
}
