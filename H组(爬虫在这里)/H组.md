后端启动：

- 在src/main/resources/application.properies中，将datasource的配置改为自己本地的配置；使用sql文件夹中的caseBase.sql在本地构建数据库
- 运行src/main/java/com.example.caseBase/CaseBaseApplication即可启动后端
  - 该项目数据库由MongoDB迁移至MySQL。如有MongoDB相关报错，不影响运行的情况下可以直接忽略
  - 该项目搜索引擎相关部分位于caseBase包下的util/ESUtil，如需研究可以自行查看
- src以外的文件夹为webService遗留代码，不影响后端启动



前端启动：

- 使用npm install安装依赖
- 使用npm run serve启动前端
- 前端进入的默认界面为search界面，查看工作台请在url后添加/workbench
  - 具体的路由设置可以在src/router/index.js下查看