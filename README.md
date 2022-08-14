# vet-app

Veterinary App

## Project Heorku Url:

- https://veterinary-app-java.herokuapp.com/

## Önemli Not!!

    - Siteye giriş yapmadan önce `create-acount` sekmesinden bir tane kullanıcı tanımlanır.
    - `create-acount` sekmesindeki giriş işlemleri tamamne hayal ürünüdür.Sadece işlevsellik için öyle bir sekme eklendi.Kullanıcnın role tipi kayıt aşamasında seçilebiliyor.
    - Başarılı bir şekilde girşi veya kayıt olunduktan sonra öncelikle bir adet `Owner` eklenmelidir.Daha sonra `Animal` eklenebilir.(Tamamen göz ardı edilmiştir fonsiyonellik odaklı bir çalışmadır.)

## Önemli Not2!!

    - Eğer projeyi localden ayaga kaldırmak istiyorsanız terminalden `docker-compose up` komutuyla docker üzerinden bir postgresql database i ayaga kalakacaktır.
    - Daha sonra projenin application.properties dosyasına :
        -spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
         spring.datasource.username=root
         spring.datasource.password=toor
    Eklenmelidir.
    - Daha sonra yine terminalden `mvn clean spring-boot:run` komutu ile projeyi ayaga kaldırabilirsiniz.

## Kullanılan Teknolojiler

- Spring-boot
- JPA
- Thymeleaf
- Spring-security
- Hibernate (for connection)
- Postgresql
- Maven

## Proje Hakkında

- Projede katmanlı mimariyi derinlemesine kullanmaya çalıştım.Bunun daha okunabilir bir kod ortamı sağladıgını
  düşünüyorum.
- Model katmanımda entitylerim mevcut burada database ile iletişime geçiyorum
- Repository katmanımda Jpa ORM si ile bağlantı sağlıyorum.
- Service katmanımda repository ile iletişimde kalıyorum.
- Controller de ise rest servislerimi hazır hale getiriyorum

- Yetkilendirme için RBAC methodunu kullandım

- ER Diagram
    - ![alt text] (../resources/screenshots/er-diagram.png)