# PostTracker
REST API отcлеживания почтовых отправлений.

**Сборка и установка**
1. Создать файл ```db/liquibase.properties``` со следующим содержанием:
```
changeLogFile=changelog.yml
driver=org.postgresql.Driver
url=jdbc:postgresql://<HOST>:<PORT>/<DB_NAME>
username=<DB_USER>
password=<DB_PASS>
```
2. Выполнить следующие команды:
```shell
cd db
liquibase update
gradle build
```
3. Запуск контейнера с помощью docker-compose:
```yaml
version: '3.3'
services:
  posttracker:
    build: .
    environment:
      POSTGRES_URL: <postgres url>
      DB_USER: <db user>
      DB_PASS: <db pass>

  db:
    image: postgres:14.2-alpine
    ports:
      - 5432:5432
    environment:
      POSTGRES_DB: <db name>
      POSTGRES_PASSWORD: <db pass>
      POSTGRES_USER: <db user>
```
```shell
docker-compose up -d
```


***Описание API функций***

**Получить список всех почтовых отделений**

*Формат запроса:*
```
GET /v1/post-office/
? page=<номер страницы>
& count=<количество элементов на странице>
```

*Формат ответа:*
```json
[
  {
    "id": 0,
    "postalCode": "<индекс почтового отделения>",
    "name": "<название почтового отделения>",
    "address": "<адрес почтового отделения>"
  }
  ...
]
```

**Добавить почтовое отделение**
*Формат запроса:*
```
POST /v1/post-office/new
```
```json
{
  "postalCode": "<индекс почтового отделения>",
  "name": "<название почтового отделения>",
  "address": "<адрес почтового отделения>"
}
```
*Формат ответа*
```
HTTP/1.1 201 Created
Content-Length: 0
```

**Удалить почтовое отделение**
*Формат запроса:*
```
DELETE /v1/post-office/<идентификатор почтового отделения>
```
*Формат ответа:*
```
HTTP/1.1 200 OK
Content-Length: 0
```
**Получить детальную информацию о почтовом отделении**
*Формат запроса*
```
GET /v1/post-office/<идентификатор почтового отделения>
```

**Зарегистрировать почтовое отправление**
*Формат запроса:*
```
POST /v1/tracking/register
```
```json
{
  "postalItemTypeId": "<идентификатор почтового отделения>",
  "recipientPostOfficeId": "<почтовое отделение получателя>",
  "recipientAddress": "<адрес получателя>",
  "recipientName": "<имя получателя>",
  "postalStatusId": "<идентификатор статуса почтового отправления>"
}
```

*Формат ответа:*
```
HTTP/1.1 200 OK
Content-Length: 0
```

**Изменить статус почтового отделения**
*Формат запроса:*
```
PUT /v1/tracking/<идентификатор почтового отправления>
/<идентификатор статуса почтового отправления>
```
*Формат ответа:*
```
HTTP/1.1 200 OK
Content-Length: 0
```

**Просмотр полной истории движения почтового отправления**
*Формат запроса:*
```
GET /v1/tracking/<идентификатор почтового отправления>
```
*Формат ответа:*
```json
[
  "recipientAddress": "<адрес получателя>",
  "recipientName": "<имя получателя>",
  "postalCode": "<индекс получателя>",
  "postalItemType": "<тип почтового отправления>"
  "history": [
    {
      "date": "<дата и вермя>",
      "status": "<статус почтового отправления>"
    },
    ...
  ]
]
```



