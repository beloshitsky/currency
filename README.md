# currency
Тестовое задание
Rest API на Spring Boot + Gradle

# Описание
Сервис, который обращается к сервису курсов валют, и отображает gif:
- если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich
- если ниже - отсюда https://giphy.com/search/broke

# Endpoints
    http://localhost:8080/rates
показывает курсы валют на сегодня  

    localhost:8080/gif?currency={code}  
возвращает ссылку на гифку с сервиса GIPHY по соответствующему тегу, где *code* - трёхзначный код валюты

# Запуск
    gradle bootRun
