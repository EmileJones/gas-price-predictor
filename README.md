# ⛽ 加油站销量预测系统

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.8-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Vue](https://img.shields.io/badge/Vue-2.6.12-blue.svg)](https://vuejs.org/)
[![Element UI](https://img.shields.io/badge/Element%20UI-2.15.6-blue.svg)](https://element.eleme.cn/)
[![RuoYi](https://img.shields.io/badge/RuoYi-3.8.1-red.svg)](http://ruoyi.vip)
[![License](https://img.shields.io/badge/License-Internal-orange.svg)](https://www.sinopec.com/)

## 📖 项目简介
本项目是受委托开发的内部自用系统。其核心目标是将自研的**销量预测算法**进行软件化落地，通过多维数据分析与地理信息服务，为加油站的运营决策提供科学、精准的数据支持。

## 🚀 核心功能

### 1. 加油站与地理信息管理
- **站场信息维护**：管理自有加油站的基础资料、坐标位置及运营状态。
- **竞对加油站管理**：记录周边竞争对手的分布情况，支持修改竞对站名称与监控状态。
- **周边检索**：支持按经纬度坐标和距离半径，查询指定范围内的加油站地理分布。

### 2. 销售数据与价格周期管理
- **数据导入导出**：支持通过 Excel 模板批量导入各油品（92#、95#、98#、0#柴油）的历史销售流水。
- **销量数据查询**：按站场、油品类型展示历史销量，支持分页查看。
- **调价周期定义**：支持自定义变价时间节点，管理系统预设或用户自定的价格变动周期。

### 3. 销量模拟计算
- **预测模拟**：用户输入本站当前价、目标价以及周边竞对价格，系统模拟计算调价后的预计平均销量。
- **分类计算**：支持针对 92#、95#、98# 汽油和 0# 柴油进行分类的价格敏感度分析与销量测算。


## 🛠️ 技术栈

### 后端 (Backend)
- **核心框架**：Spring Boot 2.5.8
- **安全认证**：Spring Security, JWT 0.9.1
- **持久层**：MyBatis, Druid 1.2.8
- **任务调度**：Quartz
- **权限管理**：基于 RuoYi-Vue 3.8.1 构建

### 前端 (Frontend)
- **框架**：Vue.js 2.6.12
- **UI 组件库**：Element UI 2.15.6
- **可视化**：Echarts 4.9.0
- **数据交互**：Axios 0.24.0

---

## 📐 开发约定
> [!IMPORTANT]
> **地理坐标存储规范**：为了保证预测算法的计算精度与统一性，系统中所有的经纬度坐标必须严格遵循 `[纬度],[经度]` (例如：`39.9042,116.4074`) 的格式进行存储和运算。

---

## 📦 环境部署 & 运行启动

### 🛠️ 开发环境准备
运行本项目，您需要安装以下环境：
- **JDK 1.8**
- **MySQL 5.7+**
- **Redis 3.0+**
- **Maven 3.0+**
- **Node.js 12+**

### 🗄️ 数据库准备
1. 创建数据库 `gas_station` (字符集推荐使用 `utf8mb4`)。
2. 按顺序执行 `sql/` 目录下的所有 SQL 脚本。

### ☕ 后端启动 (Spring Boot)
1. **修改配置文件**：
   - 打开 `ruoyi-admin/src/main/resources/application-druid.yml`，修改数据库连接地址、用户名和密码。
   - 打开 `ruoyi-admin/src/main/resources/application.yml`，修改 Redis 的连接配置。
2. **运行启动类**：
   - 在 IDE 中找到 `ruoyi-admin` 模块下的 `com.ruoyi.RuoYiApplication` 类并运行。
   - 看到控制台输出 `(♥◠‿◠)ﾉﾞ  若依启动成功  ლ(´ڡ`ლ)ﾞ` 即表示启动完成。

### 💻 前端启动 (Vue CLI)
1. **安装依赖**：
   ```bash
   cd ruoyi-ui
   npm install
   ```
2. **运行启动**：
   ```bash
   npm run dev
   ```
3. **访问系统**：
   - 打开浏览器访问 `http://localhost:80`
   - 默认账号：`admin` / 密码：`admin123`
