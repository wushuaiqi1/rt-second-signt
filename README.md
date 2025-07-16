# 简介
千里眼，英译second sight，远程监控服务状态

# 功能
- 一键上报系统部署后的服务健康状态

# 项目架构分析

本项目是一个基于 Spring Boot 的 Java 应用，主要关注于“部署健康报告”与“通知服务”相关的功能。下面是对整个项目架构的详细分析：

## 1. 项目结构总览

```
rt-second-sight/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── tal/
    │   │           ├── annotation/
    │   │           ├── config/
    │   │           ├── consts/
    │   │           ├── service/
    │   │           │   ├── handler/
    │   │           │   └── notice/
    │   │           │       └── dto/
    │   │           └── utils/
    │   └── resources/
    │       ├── application.yml
    │       ├── bootstrap.yml
    │       └── META-INF/
    │           └── spring.factories
    └── test/
```

## 2. 主要模块说明

### 2.1. annotation
- **EnableDeployHealthReport.java**  
  自定义注解，配合 Spring 的 `@Import`，用于插拔式地开启部署健康报告功能。导入了 `DeployHealthReporter` 和 `DeployHealthReportAutoConfiguration` 两个核心类。

### 2.2. config
- **BeanConfig.java**  
  Spring Bean 配置类，负责项目中 Bean 的初始化和依赖注入。
- **DeployHealthReportAutoConfiguration.java**  
  自动配置类，配合 Spring Boot 的自动装配机制，自动注册部署健康报告相关的 Bean。

### 2.3. consts
- **MsgTypeConst.java**  
  消息类型常量定义，便于统一管理和复用。

### 2.4. service
- **handler/DeployHealthReporter.java**  
  部署健康报告的核心处理器，负责具体的上报逻辑。
- **notice/**  
  - **IYachNoticeService.java**  
    通知服务接口，定义通知相关的操作。
  - **YachNoticeServiceImpl.java**  
    通知服务实现类，具体实现通知的发送逻辑。
  - **dto/**  
    - **At.java, MarkDown.java, MarkDownMessageRequest.java, Text.java, TextMessageRequest.java**  
      通知消息的数据传输对象（DTO），用于封装不同类型的消息内容和请求参数。

### 2.5. utils
- **TimeUtils.java**  
  时间工具类，提供时间相关的辅助方法。

### 2.6. resources
- **application.yml, bootstrap.yml**  
  Spring Boot 配置文件，管理应用的环境配置。
- **META-INF/spring.factories**  
  Spring Boot 自动装配入口，声明自动配置类。

## 3. 关键架构特性

- **Spring Boot 自动装配**  
  通过 `@Import` 和 `spring.factories`，实现了模块的自动装配和插拔式功能开启。
- **模块解耦**  
  采用注解和自动配置，业务逻辑与框架解耦，便于扩展和维护。
- **通知服务抽象**  
  通知服务通过接口和实现分离，支持不同类型的消息格式（如文本、Markdown、@人等）。
- **DTO 设计**  
  使用 DTO 封装消息内容，便于消息格式的扩展和维护。

## 4. 典型使用场景

- 通过在 Spring Boot 项目中添加 `@EnableDeployHealthReport` 注解，即可自动启用部署健康报告功能，无需手动配置。
- 部署健康报告功能会自动注入相关的处理器和通知服务，实现部署结果的自动上报和通知。

## 5. 总结

本项目采用了 Spring Boot 的自动装配和注解驱动开发模式，结构清晰、模块解耦，易于扩展。核心关注点在于“部署健康报告”的自动化和通知服务的灵活实现，适合在企业级微服务或自动化运维场景下使用。

