# springbootdemo
## 前言 
这个东西连了好久，可能我比较笨吧！
数据来源是我之前爬取美团的。
## 过程
先放数据库的设计表
![数据库表结构](https://img-blog.csdnimg.cn/20190124090020169.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2hhc19zb24=,size_16,color_FFFFFF,t_70)
**第一步添加配置** 
这里配置有两处，一是application.properties中的配置
```
mybatis.config-locations=classpath:mybatis/mybatis-config.xml   #mybatis的配置文件
mybatis.mapper-locations=classpath:mybatis/mapper/*.xml # mabatis mapper文件扫描处
mybatis.type-aliases-package=com.neo.entity  #实体所在包处

spring.datasource.driverClassName = com.mysql.cj.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/meituan?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC # 数据库名（meituan）
spring.datasource.username = root
spring.datasource.password = password #账号密码
```
另外一个是mybatis-config的配置,主要配置等价关系，供mapper使用
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <typeAliases>
        <typeAlias alias="Integer" type="java.lang.Integer" />
        <typeAlias alias="Long" type="java.lang.Long" />
        <typeAlias alias="Double" type="java.lang.Double"/>
        <typeAlias alias="Boolean" type="java.lang.Boolean"/>
        <typeAlias alias="HashMap" type="java.util.HashMap" />
        <typeAlias alias="LinkedHashMap" type="java.util.LinkedHashMap" />
        <typeAlias alias="ArrayList" type="java.util.ArrayList" />
        <typeAlias alias="LinkedList" type="java.util.LinkedList" />
    </typeAliases>
</configuration>
```

**第二步创建实体类**
这里最好还是跟数据库的属性名保持一致
```java
public class Shop implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long uid;
    private String name;
    private double score;
    private double price;
    private String address;
    private String phone;
    private boolean wifi;
    private String parking;
    private double lat;
    private double lon;
	##省略getter、setter,toString()
```
**第三步编写mapper**
此处mapper 有两个，一个是提供接口的
```java
public interface ShopMapper {
    Shop getOne(Long uid);
}
```
另外一个是在resources的ShopMapper.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.example.demo.mapper.ShopMapper">
<!-- 对应关系，column表示在表中的属性名，property对应entity的属性名，jdbcType代表着在数据库的数据类型 -->
    <resultMap id="shopResultMap" type="com.example.demo.entity.Shop">
        <result column="name" property="name" jdbcType="VARCHAR"/>
        <result column="score" property="score" jdbcType="DOUBLE"/>
        <result column="price" property="price" jdbcType="DOUBLE"/>
        <result column="address" property="address" jdbcType="VARCHAR"/>
        <result column="phone" property="phone" jdbcType="VARCHAR"/>
        <result column="wifi" property="wifi" jdbcType="TINYINT"/>
        <result column="parking" property="parking" jdbcType="VARCHAR"/>
        <result column="lat" property="lat" jdbcType="DOUBLE"/>
        <result column="lon" property="lon" jdbcType="DOUBLE"/>
    </resultMap>
    <!-- 返回结果的关系，也可不写 -->
    <sql id="shop_column_list">
        name,score,price,address,phone,wifi,parking,lat,lon
    </sql>
        <!-- 编写sql语句 -->
    <select id="getOne" parameterType="java.lang.Long" resultMap="shopResultMap" >
        SELECT
        <include refid="shop_column_list" />
        FROM yule
        WHERE uid = #{uid}
    </select>
</mapper>
```
**第四步编写controller**
```java
@RestController
public class ShopController {

    @Autowired
    ShopMapper shopMapper; #自动装配

    @GetMapping("/getShop/" ) #Get方法接口
    public Shop getOne(@RequestParam(value = "uid", required = true) Long uid){
        Shop shop = shopMapper.getOne(uid);
        return shop;
    }
}
```
接下来在主文件里添加
```java
@MapperScan("com.example.demo.mapper")
```
这个注解，告诉mapper所在包处。
**得出结果**
最后启动程序
![结果](https://img-blog.csdnimg.cn/20190124092410739.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2hhc19zb24=,size_16,color_FFFFFF,t_70)
## 小坑
- 特别要注意表属性和实体类属性名是否一致，是否与关键词冲突，语句书写是否正确。

