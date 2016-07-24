# mybatis自动生成工具
## pom.xml集成tomcat插件,直接使用插件启动
## 访问主页 http://localhost:8081/code

### 表命名规范：
表名以tb_或td_开头(留个好习惯),如:菜单表:tb_menu

### Framework生成的文件
#### JAVA文件
* Bo
    基本对象,对应数据库相应的表信息。
	如:菜单表,MenuBase,Menu两个类Base对应的是数据的各个字段信息,非Base类是对基础类的扩展.
* Dao
    持久层。
	如:MenuMapper,对应mybatis文件中相应的SQL方法.MenuDao,MenuDaoImpl.
* Service
    service层,事务配置在此.
	如:MenuService，MenuServiceImpl.
目前只能使用XML方式来配置事务.基本方法中增删改查为,save,update,del,find.

#### XML文件
* xml文件mapper。
	如：BaseMenuMapper.xml，基础XML文件，包括增删改查方法。MenuMapper是XML的扩展，自定义方法放在里面。
	
### PPms生成的文件
#### JAVA文件
* Po 基本对象. 如:Menu,对应表的各个字段信息.
* Dao dao层. 如:MenuDao.对应mybatis文件中相应的SQL方法.
* Service service层. 如:MenuService,MenuServiceImpl.
* Action action层. 如:Action.

#### XML文件
* xml文件mapper. 如:MenuMapper.xml. 基本方法中包括增(insert),批量增(insertBatch),改(update),删(delete),查(get),查列表(列表),分页(findPage).

#### spring文件
* spring对应的action, servlet, tx.
	
### 注: 底层依赖framework,本地运行时,需maven仓库包含framework包,具体看framework源码
### 感谢开源项目
![MyBatis](https://camo.githubusercontent.com/196d30052623ff7b233765c5f641dbc8ae2f287d/687474703a2f2f6d7962617469732e6769746875622e696f2f696d616765732f6d7962617469732d6c6f676f2e706e67)
---
[MyBatis Generator](https://github.com/mybatis/generator.git)