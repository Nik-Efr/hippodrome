1. Проект: Тестирование и Логирование
   Симулятор скачек лошадей с полным покрытием unit-тестами и системой логирования.

2. Собрать и запустить
   mvn clean package
   java -jar target/hippodrome-1.0-SNAPSHOT-jar-with-dependencies.jar

3. Технические особенности
   Покрыл тестами весь функционал с использованием JUnit 5 и Mockito
   Настроил логирование с помощью SLF4J и Logback
   Написал pom.xml с настройкой Maven Shade Plugin