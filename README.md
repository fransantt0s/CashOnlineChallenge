# CashOnlineChallenge
Cash Online Challenge
----------------------------------------------
Pasos para levantar la Api:
1) Crear un SCHEMA en MySQL WorkBench que se llame "cashonline"
2) Dentro del SCHEMA creado ejecutar el Dump de MYSQL (incluido en este repositorio)(con sus respectivas tablas y datos), una vez ejecutado se van a crear las tablas y se van a insertar los datos con los cuales está configurada la api para persistir.
3) Ejecutar la api con los paths indicados en el controller con el port 9090 (uso ese puerto porque tengo el 8080 ocupado),
     Por ejemplo: http://localhost:9090/loans o http://localhost:9090/users
    
----------------------------------------------
Test Incluidos de ambos controllers en el Package "test".

Una vez levantada la API se puede ver su Documentación BY SWAGGER en esta url: http://localhost:9090/swagger-ui.html

     
     
     
     
