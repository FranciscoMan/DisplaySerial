# DisplaySerial
El en siguiente proyecto crearemos una conexion por comunicacion serial hacia un arduino con display desde una interfaz de java.
Para lo que necesitaremos:
* Arduino UNO
* Cables para protoboard
* Un protoboard
* Sensor de temperatura lm35
* Display LCD de 16X2 con su placa controladora
* Potenciometro de 10K Ohm's

Adicionalmente, necesitaremos tener a nuestra disposicion del Java Developer Kit para lograr la programacion en Java.
Ya sea en un IDE (como netbeans, Oracle, etc) o sólo el compilador por consola.

## IMPORTANTE
Hay que tener en consideracion ciertas librerias que se utilizan para tanto java como el desarrollo en arduino

* JAVA: RXTX
Esta libreria nos ayudara en Java con la comunicacion serial.

* Arduino: Timer.h
Esta libreria nos permitira utilizar metodos de tiempo horario y de calendario con nuestro arduino, es una libreria relativamente nueva
por lo que no es muy facil de conseguir (una version adecuada) por lo que dejo una liga a otro repositorio de GitHub donde pueden descargarla

https://github.com/PaulStoffregen/Time

## Imagen del prototipo
![Una imagen cualquiera](https://github.com/FranciscoMan/DisplaySerial/blob/master/img1.jpg "Prototipo")
![Una imagen cualquiera](https://github.com/FranciscoMan/DisplaySerial/blob/master/img2.jpg "Prototipo")

## Funcionalidad
El LCD mostrara en todo momento una cierta hora predeterminada como 00:00:00 en un formato de 24hrs
En la parte superior del LCD se mostraran mensajes cambiantes, siendo uno la lectura del sensor de temperatura y el otro 1 de 3 mensajes
varios que, mediante la interfaz se podrán personalizar (como se muestra en las imagenes anteriores).
