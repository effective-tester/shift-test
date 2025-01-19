# FileFilterApp

**FileFilterApp** — приложение на базе Spring Boot, предоставляющее функциональность фильтрации файлов, согласно ТЗ.

## Особенности реализации

Парсинг происходит последовательно:
   - Выбирается **последняя** опция в случае, если опции **противоречат** друг другу по смыслу (для `-s` и `-f`)
   - Выбирается **последний** параметр в случае, если опции **повторяются** (для `-o` и `-p`)

Стилистика:
- Используется **AOP** для создания точки отлова ошибок
- Используется **Lombok** для уменьшения количества кода

## Требования

### Система сборки
- Для сборки проекта используется **Gradle**.

### Сторонние библиотеки

Проект использует следующие зависимости:

1. **Spring Boot Starter**
    - Версия: 3.1.2
    - Зависимость:
   ```groovy
   implementation 'org.springframework.boot:spring-boot-starter'
   ```

2. **Spring Boot Starter AOP**
    - Версия: 3.1.2
    - Зависимость:
   ```groovy
   implementation 'org.springframework.boot:spring-boot-starter-aop'
   ```

3. **Lombok**
    - Версия: 1.18.20
    - Зависимость:
   ```groovy
   implementation 'org.projectlombok:lombok:1.18.20'
   annotationProcessor 'org.projectlombok:lombok:1.18.20'
   ```

## Установка и запуск

1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/effective-tester/shift-test.git
   ```
   или скачайте архив и распакуйте его


2. Откройте папку с проектом и выполните сборку:
   ```bash
   gradle build
   ```

3. После сборки запустите приложение с помощью:
   ```bash
   java -jar build/libs/filefilterapp-1.0.0.jar
   ```

   Можно воспользоваться уже готовым jar-файлом:
   ```bash
   java -jar filefilterapp-1.0.0.jar
   ```


## Примеры
1)
   ```bash
   java -jar filefilterapp-1.0.0.jar -s -a -f -o .\some\dir -p test- in1.txt C:\Users\kochachi/shift/test/in4.txt
   ```
2)
   ```bash
   gradle build 
   java -jar filefilterapp/build\libs\filefilterapp-1.0.0.jar -s -a -f -o .\some\dir -p test- in1.txt C:\Users\kochachi/shift/test/in4.txt
      ```