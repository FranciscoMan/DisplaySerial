#include <Time.h>
#include <TimeLib.h>
#include <LiquidCrystal.h>

time_t t;
LiquidCrystal lcd(12, 11, 5, 4, 3, 2);

String saludo[3];
String reloj="S/H";

int cambio;
boolean m=false;
int index=0;

float centigrados=0.0;

void setup(){  
lcd.begin(16, 2);
Serial.begin(9600);
  saludo[0] = "Saludo 1";
  saludo[1] = "saludo 2";
  saludo[2] = "saludo 3";

  
  
}


void loop(){

actualizar();

escribeHora();

escribeM();


delay(500);
cambio = cambio +500;
if(cambio>=4000){
  cambio=0;
 if(m){
  m = false;
 }else{
  m = true;
 }
}

}

void actualizar(){
String Mensaje="";
int indice=0;
int mensajes=0;
String aux="";
int h,m,s;
while (Serial.available()>0){
Mensaje= Mensaje+Decimal_to_ASCII(Serial.read());

}

if(Mensaje!=""){
  //capturamos la hora del serial
    reloj ="";
    while(Mensaje.charAt(indice)!='*'){
      while(Mensaje.charAt(indice)!=' '){
      reloj = reloj+Mensaje.charAt(indice);
      indice++;
      }
      h = reloj.toInt();
      
      reloj="";
      indice++;
      while(Mensaje.charAt(indice)!=' '){
      reloj = reloj+Mensaje.charAt(indice);
      indice++;
      }
      m = reloj.toInt();
    
      reloj="";
      indice++;
      while(Mensaje.charAt(indice)!='*'){
      reloj = reloj+Mensaje.charAt(indice);
      indice++;
      }
      s = reloj.toInt();
      reloj="";
      
      setTime(h,m,s,12,04,2018);
      
    }
    
      indice++;
  while(Mensaje.charAt(indice)!='*'){
    if(Mensaje.charAt(indice)!=','){
     aux = aux+Mensaje.charAt(indice);
     }
     else{
      
      saludo[mensajes] = aux;
      mensajes++;
      aux="";
     }
    indice++;
  }
  
  indice++;
  
switch(Mensaje.charAt(indice)){
  case '0' : index =0;
  break;
  case '1' : index =1;
  break;
  case '2': index = 2;
  break;
}
  
}
//capturamos temperatura

centigrados = (analogRead(A0)*500)/1024;

}
//fin de metodo

void escribeHora(){
  lcd.setCursor(0,1);
  lcd.print("                ");
  lcd.setCursor(0,1);
  t = now();
  reloj = hour(t);
  reloj = reloj+":";
  reloj = reloj+minute(t);
  reloj = reloj+":";
  reloj = reloj+second(t);
  
  lcd.print(reloj);
  
}

void escribeM(){
  lcd.setCursor(0,0);
  lcd.print("                ");
  lcd.setCursor(0,0);
  if(m){
    lcd.print(saludo[index]);
  }else{
    lcd.print("temp: ");
    lcd.print(centigrados);
  }
}



char Decimal_to_ASCII(int entrada){
  char salida=' ';
  switch(entrada){
case 32: 
salida=' '; 
break; 
case 33: 
salida='!'; 
break; 
case 34: 
salida='"'; 
break; 
case 35: 
salida='#'; 
break; 
case 36: 
salida='$'; 
break; 
case 37: 
salida='%'; 
break; 
case 38: 
salida='&'; 
break; 
case 39: 
salida=' '; 
break; 
case 40: 
salida='('; 
break; 
case 41: 
salida=')'; 
break; 
case 42: 
salida='*'; 
break; 
case 43: 
salida='+'; 
break; 
case 44: 
salida=','; 
break; 
case 45: 
salida='-'; 
break; 
case 46: 
salida='.'; 
break; 
case 47: 
salida='/'; 
break; 
case 48: 
salida='0'; 
break; 
case 49: 
salida='1'; 
break; 
case 50: 
salida='2'; 
break; 
case 51: 
salida='3'; 
break; 
case 52: 
salida='4'; 
break; 
case 53: 
salida='5'; 
break; 
case 54: 
salida='6'; 
break; 
case 55: 
salida='7'; 
break; 
case 56: 
salida='8'; 
break; 
case 57: 
salida='9'; 
break; 
case 58: 
salida=':'; 
break; 
case 59: 
salida=';'; 
break; 
case 60: 
salida='<'; 
break; 
case 61: 
salida='='; 
break; 
case 62: 
salida='>'; 
break; 
case 63: 
salida='?'; 
break; 
case 64: 
salida='@'; 
break; 
case 65: 
salida='A'; 
break; 
case 66: 
salida='B'; 
break; 
case 67: 
salida='C'; 
break; 
case 68: 
salida='D'; 
break; 
case 69: 
salida='E'; 
break; 
case 70: 
salida='F'; 
break; 
case 71: 
salida='G'; 
break; 
case 72: 
salida='H'; 
break; 
case 73: 
salida='I'; 
break; 
case 74: 
salida='J'; 
break; 
case 75: 
salida='K'; 
break; 
case 76: 
salida='L'; 
break; 
case 77: 
salida='M'; 
break; 
case 78: 
salida='N'; 
break; 
case 79: 
salida='O'; 
break; 
case 80: 
salida='P'; 
break; 
case 81: 
salida='Q'; 
break; 
case 82: 
salida='R'; 
break; 
case 83: 
salida='S'; 
break; 
case 84: 
salida='T'; 
break; 
case 85: 
salida='U'; 
break; 
case 86: 
salida='V'; 
break; 
case 87: 
salida='W'; 
break; 
case 88: 
salida='X'; 
break; 
case 89: 
salida='Y'; 
break; 
case 90: 
salida='Z'; 
break; 
case 91: 
salida='['; 
break; 
case 92: 
salida=' '; 
break; 
case 93: 
salida=']'; 
break; 
case 94: 
salida='^'; 
break; 
case 95: 
salida='_'; 
break; 
case 96: 
salida='`'; 
break; 
case 97: 
salida='a'; 
break; 
case 98: 
salida='b'; 
break; 
case 99: 
salida='c'; 
break; 
case 100: 
salida='d'; 
break; 
case 101: 
salida='e'; 
break; 
case 102: 
salida='f'; 
break; 
case 103: 
salida='g'; 
break; 
case 104: 
salida='h'; 
break; 
case 105: 
salida='i'; 
break; 
case 106: 
salida='j'; 
break; 
case 107: 
salida='k'; 
break; 
case 108: 
salida='l'; 
break; 
case 109: 
salida='m'; 
break; 
case 110: 
salida='n'; 
break; 
case 111: 
salida='o'; 
break; 
case 112: 
salida='p'; 
break; 
case 113: 
salida='q'; 
break; 
case 114: 
salida='r'; 
break; 
case 115: 
salida='s'; 
break; 
case 116: 
salida='t'; 
break; 
case 117: 
salida='u'; 
break; 
case 118: 
salida='v'; 
break; 
case 119: 
salida='w'; 
break; 
case 120: 
salida='x'; 
break; 
case 121: 
salida='y'; 
break; 
case 122: 
salida='z'; 
break; 
case 123: 
salida='{'; 
break; 
case 124: 
salida='|'; 
break; 
case 125: 
salida='}'; 
break; 
case 126: 
salida='~'; 
break; 




  }
  return salida;
}

