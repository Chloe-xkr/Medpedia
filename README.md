# 泰健医药百科系统

## 前端

前端推荐使用 Intellij IDEA，或者使用 VS Code，并安装 Eslint 和 Prettier 插件。

### 运行

```bash
$ npm install
$ npm run serve
```

运行在 `http://localhost:8080`。已设置 CORS，前端代码中直接向 `http://localhost:8080` 发送 API 请求。

## 后端

后端直接在 Intellij IDEA 中运行 `TaijianApplication`。联机数据库已经在 `\server\src\main\resources\application.yml` 中配置好。

运行在 `http://localhost:5000`。
