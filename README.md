# Self Learning Project

* [框架]：springweb+springjpa+springcache+mapstruct
* [功能]：1.系统启动后通过实现[ApplicationRunner]接口从resource下加载[食物信息]
  csv文件到h2内存数据库；http://localhost:8081/h2-console 本地web查看内存数据库数据
  2.服务提供一个RestFul API接收一个[食物名称]参数，模糊且忽略大小写查询数据库中包含该参数的分页记录，
  3.返回结果默认按csv中ExpirationDate字段倒序排列。
  4.通过@RestControllerAdvice定义提供全局异常处理
  5.通过配置MapStruct实现Bean转换，使用一些MapStruct的高级功能
  6.自定义Repository实现类CustomBaseRepository,BaseRepository
  7.配置spring缓存实现数据库查询等缓存处理
  8.编写实现单元测试
  9.配置spring滚动文件日志

### 作者联系方式：wechat：thewarmcity，QQ邮箱：1364911321@qq.com



