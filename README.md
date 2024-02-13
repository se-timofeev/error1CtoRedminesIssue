Сервис позволяет обрабатывать возникающие ошибки в работе программы 1С и создавать таски в Redmine.
Для его использования, необходимо в разделе "Функции для технического специалиста" -> "Управление настройками обработки ошибок" указать адрес публикуемого сервиса для обработки ошибок.
![image](https://github.com/se-timofeev/stableWork/assets/100536336/19802bf6-eb4a-4f46-842f-3e66f148ced7)
Приложение содержит 2 REST контроллера 
getInfo в который из 1C отправляется  json с информацией. В свою очередь, 1С ожидает ответ с указанием типа дампа, текстом информации для пользователя.
pushReport - принимает zip файл с ошибкой.
