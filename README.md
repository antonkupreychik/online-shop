# Online-Shop Management System

## Описание
Online Shop Management System - это система управления заказами для интернет-магазина, которая позволяет пользователям управлять заказами, клиентами и товарами. Система включает в себя несколько микросервисов, каждый из которых отвечает за свою часть функциональности.

## Основные компоненты
- **Gateway Service**: Маршрутизация запросов и агрегация данных.
- **User Service**: Управление пользователями и их ролями.
  - http://localhost:9001/swagger/swagger-ui/index.html
- **Product Service**: Управление товарами.
  - http://localhost:9002/swagger/swagger-ui/index.html
- **Order Service**: Обработка заказов.
  - http://localhost:9003/swagger/swagger-ui/index.html
- **Notification Service**: Уведомления пользователей.

Чтобы я еще добавил:
- Scheduler Service - для запуска отложенных задач
- Auth Service - для авторизации/аунтефикации пользователей
- Statistics Service - для сбора статистики об товарах и покупках пользователей
- Payment Service - для оплаты заказов
- Rates Service - для возможности получения курсов валют для покупки
- Promotions Service - для работы с промокодами/скидками для товаров
- Delivery Service - для работы с доставкой товаров

## Технологии
- Spring Boot
- PostgreSQL/MongoDB
- Kafka
- Redis
- Docker, DockerCompose

### Запуск
```
docker-compose up --build
```

Начальные задачи для взаимодействия:

- User Service может взаимодействовать с Order Service для получения информации о заказах пользователя.
- Order Service может запрашивать информацию о товарах у Product Service при формировании заказа и может отправлять запросы в Notification Service для уведомления пользователя о статусе заказа.
- Product Service может быть использован Order Service для проверки наличия товара при создании заказа.


<img width="1303" alt="image" src="https://github.com/antonkupreychik/online-shop/assets/125571097/d354620f-e49f-4744-98de-a98df81c56d0">
