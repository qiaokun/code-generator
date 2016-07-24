package cn.vansky.code.generator.db.framework;

import cn.vansky.code.generator.db.AbstractAttributes;
import cn.vansky.code.generator.db.ColumnInfo;
import cn.vansky.code.generator.java.Field;
import cn.vansky.code.generator.java.JavaTypeInfo;
import cn.vansky.code.generator.util.JavaBeansUtil;
import cn.vansky.framework.core.dao.ConfigurableBaseSqlMapDao;
import cn.vansky.framework.core.dao.DaoMapper;
import cn.vansky.framework.core.dao.SqlMapDao;
import cn.vansky.framework.core.service.GenericService;
import cn.vansky.framework.core.service.GenericSqlMapServiceImpl;

import java.util.List;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/20
 */
public class FrameworkAttributes extends AbstractAttributes {

    private JavaTypeInfo baseBo;

    private JavaTypeInfo bo;

    private JavaTypeInfo daoMapper;

    private JavaTypeInfo mapper;

    private JavaTypeInfo dao;

    private JavaTypeInfo sqlMapDao;

    private JavaTypeInfo daoImpl;

    private JavaTypeInfo configurableBaseSqlMapDao;

    private JavaTypeInfo service;

    private JavaTypeInfo genericService;

    private JavaTypeInfo serviceImpl;

    private JavaTypeInfo genericSqlMapServiceImpl;

    private JavaTypeInfo controller;

    private String baseRecord;

    private String basebBanPackage;

    private String beanPackage;

    private String XMLMapperPackage;

    private String mapperPackage;

    private String daoPackage;

    private String daoImplPackage;

    private String servicePackage;

    private String serviceImplPackage;

    private String actionPackage;

    private String XmlMapperFileName;

    private String XmlMapperBaseFileName;

    private String recordWithBLOBsType;

    private String exampleType;

    private String pk;

    public void calculateModelAttributes() {
        String pakkage = calculateSqlMapPackage();

        StringBuilder sb = new StringBuilder();
        sb.append(pakkage);
        setBaseRecord(sb.toString());

        setXMLMapperPackage();

        setBaseBeanPackage();
        setBeanPackage();
        // 计算cn.vansky.code.user.dao.UserMapper命名空间
        setMapperPackage();
        setDaoPackage();
        setDaoImplPackage();
        setServicePackage();
        setServiceImplPackage();
        setControllerPackage();

        setBaseBo();
        setBo();
        setPk();
        setDaoMapper();
        setMapper();
        setSqlMapDao();
        setDao();
        setConfigurableBaseSqlMapDao();
        setDaoImpl();
        setGenericService();
        setService();
        setGenericSqlMapServiceImpl();
        setServiceImpl();
        setController();

        sb.setLength(0);
        sb.append(pakkage);
        sb.append('.');
        sb.append(tableInfo.getDomainObjectName());
        sb.append("WithBLOBs");
        setRecordWithBLOBsType(sb.toString());

        sb.setLength(0);
        sb.append(pakkage);
        sb.append('.');
        sb.append(tableInfo.getDomainObjectName());
        sb.append("Example");
        setExampleType(sb.toString());
    }

    /**
     * 计算XML属性
     */
    public void calculateXmlAttributes() {
        // 计算生成UserMapper.xml文件名
        String fileXMLMapperName = calculateMyBatis3XmlMapperFileName();
        // 计算生成BaseUserMapper.xml文件名
        setMyBatis3XmlMapperBaseFileName("Base" + fileXMLMapperName);
        setMyBatis3XmlMapperFileName(fileXMLMapperName);
    }

    public void setRecordWithBLOBsType(String recordWithBLOBsType) {
        this.recordWithBLOBsType = recordWithBLOBsType;
    }

    public String getRecordWithBLOBsType() {
        return recordWithBLOBsType;
    }

    public void setExampleType(String exampleType) {
        this.exampleType = exampleType;
    }

    public String getExampleType() {
        return exampleType;
    }

    public void setMyBatis3XmlMapperBaseFileName(String baseMybatis3XmlMapperFileName) {
        this.XmlMapperBaseFileName = baseMybatis3XmlMapperFileName;
    }

    public String getMyBatis3XmlMapperBaseFileName() {
        return this.XmlMapperBaseFileName;
    }

    public void setMyBatis3XmlMapperFileName(String mybatis3XmlMapperFileName) {
        this.XmlMapperFileName = mybatis3XmlMapperFileName;
    }

    public String getMyBatis3XmlMapperFileName() {
        return XmlMapperFileName;
    }

    public void setXMLMapperPackage() {
        this.XMLMapperPackage = baseRecord + ".dao";
    }

    public String getXMLMapperPackage() {
        return this.XMLMapperPackage;
    }

    public void setBaseBeanPackage() {
        this.basebBanPackage = baseRecord + ".bo." + tableInfo.getDomainObjectName() + "Base";
    }

    public void setBeanPackage() {
        this.beanPackage = baseRecord + ".bo." + tableInfo.getDomainObjectName();
    }

    public void setMapperPackage() {
        this.mapperPackage = this.XMLMapperPackage + "." + tableInfo.getDomainObjectName() + "Mapper";
    }

    public String getNamespace() {
        return this.mapperPackage;
    }

    public void setDaoPackage() {
        this.daoPackage = baseRecord + ".dao." + tableInfo.getDomainObjectName() + "Dao";
    }

    public void setDaoImplPackage() {
        this.daoImplPackage = baseRecord + ".dao.impl." + tableInfo.getDomainObjectName() + "DaoImpl";
    }

    public void setServicePackage() {
        this.servicePackage = baseRecord + ".service." + tableInfo.getDomainObjectName() + "Service";
    }

    public void setServiceImplPackage() {
        this.serviceImplPackage = baseRecord + ".service.impl." + tableInfo.getDomainObjectName() + "ServiceImpl";
    }

    public void setControllerPackage() {
        this.actionPackage = baseRecord + ".action." + tableInfo.getDomainObjectName() + "Controller";
    }

    public void setBaseRecord(String baseRecord) {
        this.baseRecord = baseRecord;
    }

    public String getBaseRecord() {
        return baseRecord;
    }

    public void setBaseBo() {
        this.baseBo = new JavaTypeInfo(basebBanPackage);
    }

    public void setBo() {
        this.bo = new JavaTypeInfo(beanPackage);
    }

    public void setDaoMapper() {
        this.daoMapper = new JavaTypeInfo(DaoMapper.class.getName() + getPk());
    }

    public void setMapper() {
        this.mapper = new JavaTypeInfo(mapperPackage);
    }

    public void setDao() {
        this.dao = new JavaTypeInfo(daoPackage);
    }

    public void setSqlMapDao() {
        this.sqlMapDao = new JavaTypeInfo(SqlMapDao.class.getName() + getPk());
    }

    public void setDaoImpl() {
        this.daoImpl = new JavaTypeInfo(daoImplPackage);
    }

    public void setConfigurableBaseSqlMapDao() {
        this.configurableBaseSqlMapDao = new JavaTypeInfo(ConfigurableBaseSqlMapDao.class.getName() + getPk());
    }

    public void setGenericService() {
        this.genericService = new JavaTypeInfo(GenericService.class.getName() + getPk());
    }

    public void setService() {
        this.service = new JavaTypeInfo(servicePackage);
    }

    public void setGenericSqlMapServiceImpl() {
        this.genericSqlMapServiceImpl = new JavaTypeInfo(GenericSqlMapServiceImpl.class.getName() + getPk());
    }

    public void setServiceImpl() {
        this.serviceImpl = new JavaTypeInfo(serviceImplPackage);
    }

    public void setController() {
        this.controller = new JavaTypeInfo(actionPackage);
    }

    public JavaTypeInfo getBaseBo() {
        return baseBo;
    }

    public JavaTypeInfo getBo() {
        return bo;
    }

    public JavaTypeInfo getDaoMapper() {
        return daoMapper;
    }

    public JavaTypeInfo getMapper() {
        return mapper;
    }

    public JavaTypeInfo getDao() {
        return dao;
    }

    public JavaTypeInfo getSqlMapDao() {
        return sqlMapDao;
    }

    public JavaTypeInfo getDaoImpl() {
        return daoImpl;
    }

    public JavaTypeInfo getConfigurableBaseSqlMapDao() {
        return configurableBaseSqlMapDao;
    }

    public JavaTypeInfo getService() {
        return service;
    }

    public JavaTypeInfo getGenericService() {
        return genericService;
    }

    public JavaTypeInfo getGenericSqlMapServiceImpl() {
        return genericSqlMapServiceImpl;
    }

    public JavaTypeInfo getServiceImpl() {
        return serviceImpl;
    }

    public JavaTypeInfo getController() {
        return controller;
    }

    public void setPk() {
        List<ColumnInfo> pkList = tableInfoWrapper.getPrimaryKeyColumns();
        StringBuilder sb = new StringBuilder();
        sb.append('<');
        sb.append(this.bo.getShortName());
        sb.append(", ");
        boolean pk = pkList.size() > 1;
        if (pk) {
            sb.append("HashMap");
        } else {
            for (ColumnInfo pkColumnInfo : pkList) {
                Field field = JavaBeansUtil.getJavaBeansField(pkColumnInfo, tableInfoWrapper);
                sb.append(field.getType().getShortName());
            }
        }
        sb.append('>');
        this.pk = sb.toString();
    }

    public String getPk() {
        return pk;
    }
}
