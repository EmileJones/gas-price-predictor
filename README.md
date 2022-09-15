# 加油站销量预测系统

## 约定

1. 地图API使用过程中，遇到的经纬度必须按照 `[纬度],[经度]` 的格式来存储运算

## 运行服务器信息

建议开发环境和运行环境数据进行隔离，可以在后台开两个java进程，一个生产环境，一个是开发环境

### MySQL

数据库是机器 `114.116.5.228` 目前有两个数据库，这两个数据数据隔离，开发时请使用开发环境数据，线上环境数据谨慎操作

- `gas_station` : 生产环境数据
- `gas_station_dev` ：开发环境数据

### Docker

只有一个Nginx容器，对外映射两个接口：80、8001

80端口用于生产环境：

- 前端文件在 `~/docker/nginx/html/gas-station-prod` 
- 配置文件为 `~/docker/nging/conf/conf.d/gas-station.conf`

8001端口用于开发环境：

- 前端文件在 `~/docker/nginx/html/gas-station-dev` 
- 配置文件为 `~/docker/nging/conf/conf.d/gas-station-dev.conf`