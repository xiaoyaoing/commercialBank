后端启动：

- 在src/main/resources/application.properies中，将datasource的配置改为自己本地的配置；使用schema.sql在本地构建数据库
- 运行src/main/java/com.example.demo/CircpenaltiesApplication即可启动后端
- data_s.json为使用爬虫爬下来的部分数据，经过转换抽取以后可以直接存入数据库进行测试



前端启动：

- 使用npm install安装依赖
- 使用npm run serve启动前端
- 具体的路由设置可以在src/router/index.js下查看