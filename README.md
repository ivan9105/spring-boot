# Задание #

## Условие ##

Веб приложение, цель которого - операции со счетами пользователей.
RESTful api:
* Перевод денег с одного счёта на другой
* Положить деньги на счёт
* Снять деньги со счёта

## Выбранный стек технологий ##
Spring Boot, H2 database, Hibernate, Spring MVC, Spring Data JPA, Bootstrap, Thymeleaf

## Выполнено ##
* Добавлены initial sql скрипты
* Админка, с основными операциями и валидацией
* Добавлена поддержка Swagger UI для тестирования
* Добавлены интеграционные тесты на основную логику с операциями со счетами пользователя, а также тесты для проверки правильности настройки JPA
* Основное RESTful api + дополнительно api для получения метаинформации по данным
* Для сборки использовать gradle build, исполняемый jar файл будет в projectDir\build\libs, для запуска используем java -jar youFile.jar

## Инструкция для разработчиков ##
Для создания проекта выполняем из projectDir gradle idea, открываем файл проекта через IDE Idea

## Elastic ##
Добавлена поддержка elastic search, необходимо проставить:
* elasticsearch.home - путь до elastic, местонахождение bin папки
* elasticsearch.cluster.name - имя кластера, по умолчанию elasticsearch
* elasticsearch.host - host(см. настройки)
* elasticsearch.port - port(см. настройки)

## Kubernetes ##
В планах добавить deployment в kubernetes, получил кучу проблем с установкой на WINDOWS, так как docker требует включенного Hyper-V, minikube поднимал так же на нем, тестировал на Windows 10
* Первое - заходим в "Пуск" -> "Установка и удаление программ" -> "Программы и компоненты" -> "Включение и отключение компонентов Windows" -> Убеждаемся что checkbox "Hyper-V" проставлен в true
* Второе - заходим в bios и убеждаемся что виртуализация доступна, у всех по разному советую загуглить
* Если не включена перезагружаем компьютер, после включение. После включения может быть недоступен интернет. Решение этой проблемы, заходим в доступные подключения и убираем с физического подкдлючения по кабелю в настройках галку "Hyper-V Extensible Virtual Switch", ок, ждем немного и проверяем интернет.
* Идем в заходим в "Пуск" -> "Диспетчер Hyper-V" -> "Диспетчер виртуальных коммутаторов" и создаем там новый "Виртуальный коммутатор" к внешней сети и выбираем нашу сетевую карточку с именем "Primary Virtual Switch".
* Далее устанавливаем через Power Shell(запусти через администратора) менеджер пакетов choco(гугли)
* choco install minikube
* `minikube start --cpus=4 --memory=4096 --vm-driver hyperv --hyperv-virtual-switch "Primary Virtual Switch"`, ждем
* `minikube addons enable ingress` установка ingress
* `minikube dashboard` если открылся dashboard в броузере по умолчания прыгаем от счастья

## Решение проблем с Kubernetes ##
Иногда его проще переустановить через заставить работать после гибернизации к примеру, решение проблем
* `minikube stop` остановка, не помогло? - `minikube ssh \ sudo poweroff`
* `minikube delete`, не удаляет ругается на config.json, нужно полностью все удалить, отключаем Hyper-V, так же как и включаем(смотри выше), перезапускаемся заходим в $UserHome удаляем .minikube, удаляем C:\ProgramData\Microsoft\Windows\Hyper-V - папку Virtual Machines, включаем Hyper-V и все настраиваем снова как выше

## Volumes ##
Для docker и kubernetes volumes paths прописываются в unix style, для того чтобы перевести в WINDOWS можно установить git bash и открыть к примеру в target folder, например E:\storage\psql == /e/storage/psql

## Exec ##
* `kubectl exec -it podId(kubectl get pods | grep name)` -- /bin/bash(depends from docker image)

