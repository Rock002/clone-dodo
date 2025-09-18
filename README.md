## Clone Dodo - Сервис заказа еды

Приложение для заказа продуктов с аутентификацией пользователей, созданное на Spring Boot. Позволяет добавлять, удалять, заказывать продукты.

### Особенности

- Регистрация и аутентификация пользователей
- базовые операции с заказами
- Защита данных с помощью Spring Security
- Хранение задач в PostgreSQL
- Просмотр детальной информации о заказе и продуктах

### Технологический стек

- Язык: Java 24
- Фреймворк: Spring Boot 3.5.3
- База данных: PostgreSQL
- Безопасность: Spring Security, BCrypt
- Шаблонизация: Freemarker
- Сборка: Maven
- Другие технологии: Hibernate, JPA

### Функциональные возможности
#### Любой пользователь
- Регистрация пользователя
- Вход в систему
#### Аутентифицированный пользователь
- Просмотр всех продуктов
- Добавление продукта в корзину
- Удаление продукта из корзины
- Просмотр деталей продукта
- Возможность заказать продукты

### Запуск проекта

#### Предварительные требования

#### 1. Установите:
    - Java 24
    - PostgreSQL
    - Maven
    - Apache Kafka

#### 2. Создайте базу данных в PostgreSQL:
    CREATE DATABASE dodo;

#### 3.	Настройте подключение к БД в application.properties:
    spring.application.name=Dodo
    spring.datasource.url=jdbc:postgresql://localhost:5432/calculator
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true

    server.port=8080
    spring.kafka.bootstrap-servers=localhost:9092
    spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
    spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer

### Сборка и запуск
#### 1.	Клонируйте репозиторий:
      git clone https://github.com/Rock002/clone-dodo.git
      cd clone-dodo
#### 2.	Соберите проект с помощью Maven:
      mvn clean package
#### 3.	Запустите приложение:
      java -jar target/Dodo-0.0.1-SNAPSHOT.jar
#### 4.	Приложение будет доступно по адресу (если он свободен):
      http://localhost:8080

### Использование
#### 1.	Регистрация
      Регистрация пользователей — http://localhost:8080/registration
      Регистрация администраторов — http://localhost:8080/adminregistration
#### 2.	Вход в систему
      Используйте свои учетные данные для входа:
      http://localhost:8080/login
#### 3.	Работа приложения
      o	Главная страница: 
                Просмотр продуктов, 
                Просмотр отдельных продуктов,
                Возможноть добавить продукты в корзину
      o	Корзина: 
                Удаление отдельных продуктов,
                Возможность заказать всю корзину,
                Возможность вернуться на главную
      o	Профиль: 
                Просмотр данных о профиле,
                Возможность удалить профиль,
                Возможность сменить пароль,
                Возможность вернуться на главную
