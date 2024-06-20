Online-shop 

 
Users-service:
	Swagger: http://localhost:9001/swagger/swagger-ui/index.html 
	Default:  http://localhost:9001 

Product-service:
	Swagger: http://localhost:9002/swagger/swagger-ui/index.html
	Default:  http://localhost:9002 

Order-service:
	Swagger: http://localhost:9003/swagger/swagger-ui/index.html 
	Default:  http://localhost:9003 


Задача для взаимодействия:

- User Service может взаимодействовать с Order Service для получения информации о заказах пользователя.

- Order Service может запрашивать информацию о товарах у Product Service при формировании заказа и может отправлять запросы в Notification Service для уведомления пользователя о статусе заказа.

- Product Service может быть использован Order Service для проверки наличия товара при создании заказа.


<img width="1303" alt="image" src="https://github.com/antonkupreychik/online-shop/assets/125571097/d354620f-e49f-4744-98de-a98df81c56d0">
