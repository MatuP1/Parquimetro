#---------------------------------------------------------#
#                     Carga de datos
#---------------------------------------------------------#

INSERT INTO conductores VALUES(35001457,"Javier","Guzman","Caronti 397","4887132",35001457);
INSERT INTO conductores VALUES(36041458,"Carolina","Perez","Colon 80","4887742",36041458);
INSERT INTO conductores VALUES(33025651,"Valentino","Gomez","Estomba 1923","4927331",33025651);
INSERT INTO conductores VALUES(40741457,"Juan","Carofalo","Colon 80","4887742",40741457);
INSERT INTO conductores VALUES(41256126,"Fiorella","Dellapittima","Donado 503","4895138",41256126);
INSERT INTO conductores VALUES(42002567,"Maximiliano","Torres","Pueyrredon 2948","4886001",42002567);
INSERT INTO conductores VALUES(36631474,"Matias","Enriquez","Guemes 3926","4882941",36631474);
INSERT INTO conductores VALUES(37267891,"Belen","Giacco","Castelli 351","4983948",37267891);

INSERT INTO automoviles VALUES("AAS364","Fiat","Uno","Blanco",35001457);
INSERT INTO automoviles VALUES("JBK291","Renault","Logan","Gris",36041458);
INSERT INTO automoviles VALUES("AHS123","Citroen","C4 Lounge","Negro",33025651);
INSERT INTO automoviles VALUES("JBK292","Renault","Logan","Gris",40741457);
INSERT INTO automoviles VALUES("ABC987","Honda","Civic","Negro Perlado",41256126);
INSERT INTO automoviles VALUES("HCK212","Fiat","Spazio","Rojo",42002567);
INSERT INTO automoviles VALUES("BBC456","Chevrolet","Camaro","Amarillo",36631474);
INSERT INTO automoviles VALUES("ZXY147","Ford","Mustang GT","Naranja",37267891);
INSERT INTO automoviles VALUES("ABC147","Renault","Logan","Naranja",37267891);


INSERT INTO tipos_tarjeta(tipo,descuento) VALUES("Normal",0.25);
INSERT INTO tipos_tarjeta(tipo,descuento) VALUES("Vip",0.50);
INSERT INTO tipos_tarjeta(tipo,descuento) VALUES("Exclusivo",0.75);

INSERT INTO tarjetas(saldo,tipo,patente) VALUES(150.00,"Normal","AAS364");
INSERT INTO tarjetas(saldo,tipo,patente) VALUES(76.50,"Normal","JBK291"); #Repetida, es decir que un auto tiene 2 tarjetas
INSERT INTO tarjetas(saldo,tipo,patente) VALUES(350.10,"Vip","AHS123");
INSERT INTO tarjetas(saldo,tipo,patente) VALUES(210.23,"Vip","JBK291"); #Repetida, es decir que un auto tiene 2 tarjetas
INSERT INTO tarjetas(saldo,tipo,patente) VALUES(0.00,"Vip","ABC987");
INSERT INTO tarjetas(saldo,tipo,patente) VALUES(20.52,"Normal","HCK212");
INSERT INTO tarjetas(saldo,tipo,patente) VALUES(500.21,"Exclusivo","BBC456");
INSERT INTO tarjetas(saldo,tipo,patente) VALUES(-15.02,"Exclusivo","ZXY147");
INSERT INTO tarjetas(saldo,tipo,patente) VALUES(5.02,"Exclusivo","ABC147");
INSERT INTO tarjetas(saldo,tipo,patente) VALUES(63.82,"Normal","JBK292");



INSERT INTO inspectores VALUES(123456,40392812,"Alejo","Sada",md5("123456"));
INSERT INTO inspectores VALUES(234567,36728923,"Franco","Alarcon",md5("234567"));
INSERT INTO inspectores VALUES(345678,37281937,"Joaquin","Camus",md5("345678"));
INSERT INTO inspectores VALUES(456789,35120379,"Romina","Perez",md5("456789"));


INSERT INTO ubicaciones VALUES("Donado",300,20.00);
INSERT INTO ubicaciones VALUES("Donado",200,40.00);
INSERT INTO ubicaciones VALUES("Donado",100,60.00);
INSERT INTO ubicaciones VALUES("Brown",300,20.00);
INSERT INTO ubicaciones VALUES("Brown",200,40.00);
INSERT INTO ubicaciones VALUES("Brown",100,60.00);
INSERT INTO ubicaciones VALUES("Alsina",300,20.00);
INSERT INTO ubicaciones VALUES("Alsina",200,40.00);
INSERT INTO ubicaciones VALUES("Alsina",100,60.00);
INSERT INTO ubicaciones VALUES("San Martin",300,20.00);
INSERT INTO ubicaciones VALUES("San Martin",200,40.00);
INSERT INTO ubicaciones VALUES("San Martin",100,60.00);
INSERT INTO ubicaciones VALUES("Chiclana",300,20.00);
INSERT INTO ubicaciones VALUES("Chiclana",200,40.00);
INSERT INTO ubicaciones VALUES("Chiclana",100,60.00);


INSERT INTO parquimetros VALUES(001,001,"Donado",300);
INSERT INTO parquimetros VALUES(002,001,"Donado",200);
INSERT INTO parquimetros VALUES(003,001,"Donado",100);
INSERT INTO parquimetros VALUES(503,002,"Donado",100);
INSERT INTO parquimetros VALUES(004,001,"Brown",300);
INSERT INTO parquimetros VALUES(005,001,"Brown",200);
INSERT INTO parquimetros VALUES(006,001,"Brown",100);
INSERT INTO parquimetros VALUES(506,002,"Brown",100);
INSERT INTO parquimetros VALUES(007,001,"Alsina",300);
INSERT INTO parquimetros VALUES(008,001,"Alsina",200);
INSERT INTO parquimetros VALUES(009,001,"Alsina",100);
INSERT INTO parquimetros VALUES(509,002,"Alsina",100);
INSERT INTO parquimetros VALUES(010,001,"San Martin",300);
INSERT INTO parquimetros VALUES(011,001,"San Martin",200);
INSERT INTO parquimetros VALUES(012,001,"San Martin",100);
INSERT INTO parquimetros VALUES(512,002,"San Martin",100);
INSERT INTO parquimetros VALUES(013,001,"Chiclana",300);
INSERT INTO parquimetros VALUES(014,001,"Chiclana",200);
INSERT INTO parquimetros VALUES(015,001,"Chiclana",100);
INSERT INTO parquimetros VALUES(515,002,"Chiclana",100);


INSERT INTO estacionamientos VALUES(1,001,"2020-10-07","12:30:20","2020-10-07","20:33:27");
INSERT INTO estacionamientos VALUES(2,004,"2020-10-06","07:30:17","2020-10-06","15:03:08");
INSERT INTO estacionamientos VALUES(3,512,"2020-10-05","12:30:20","2020-10-06","07:33:50");
INSERT INTO estacionamientos VALUES(4,007,"2020-10-05","16:24:10","2020-10-05","18:37:29");
INSERT INTO estacionamientos VALUES(5,003,"2020-10-07","13:50:13","2020-10-08","02:12:12");
INSERT INTO estacionamientos VALUES(6,515,"2020-10-06","16:58:21","2020-10-06","21:28:59");
#-----------------------------------------------------------------------------------------
#Tienen fecha sal null
INSERT INTO estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent) VALUES(7,001,"2020-10-05","12:26:22");
INSERT INTO estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent) VALUES(8,001,"2020-10-05","14:37:13");
INSERT INTO estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent) VALUES(2,001,"2020-10-05","16:48:54");
#--------------------------------------------------------------------------------------------
#Cerraron su estacionamiento antenriore y abrieron uno nuevo
INSERT INTO estacionamientos VALUES(3,011,"2020-10-07","12:02:18","2020-10-08","12:1:52");
INSERT INTO estacionamientos VALUES(6,509,"2020-10-07","16:59:15","2020-10-07","21:30:02");

#----------------------------------------------------------------------------------------------
#Inspector Donado turno mañana
INSERT INTO accede VALUES(123456,001,"2020-10-05","08:24:58");
INSERT INTO accede VALUES(123456,002,"2020-10-05","08:34:20");
INSERT INTO accede VALUES(123456,003,"2020-10-05","08:46:48");
INSERT INTO accede VALUES(123456,503,"2020-10-05","08:53:27");
INSERT INTO accede VALUES(123456,001,"2020-10-06","08:24:58");
INSERT INTO accede VALUES(123456,002,"2020-10-06","08:34:20");
INSERT INTO accede VALUES(123456,003,"2020-10-06","08:46:48");
INSERT INTO accede VALUES(123456,503,"2020-10-06","08:53:27");
INSERT INTO accede VALUES(123456,001,"2020-10-07","08:24:58");
INSERT INTO accede VALUES(123456,002,"2020-10-07","08:34:20");
INSERT INTO accede VALUES(123456,003,"2020-10-07","08:46:48");
INSERT INTO accede VALUES(123456,503,"2020-10-07","08:53:27");
INSERT INTO accede VALUES(123456,001,"2020-10-08","08:24:58");
INSERT INTO accede VALUES(123456,002,"2020-10-08","08:34:20");
INSERT INTO accede VALUES(123456,003,"2020-10-08","08:46:48");
INSERT INTO accede VALUES(123456,503,"2020-10-08","08:53:27");
#---------------------------------------------------------------------------------------------
#Inspector Brown turno mañana
INSERT INTO accede VALUES(234567,004,"2020-10-05","08:24:58");
INSERT INTO accede VALUES(234567,005,"2020-10-05","08:34:20");
INSERT INTO accede VALUES(234567,006,"2020-10-05","08:46:48");
INSERT INTO accede VALUES(234567,506,"2020-10-05","08:53:27");
INSERT INTO accede VALUES(234567,004,"2020-10-06","08:24:58");
INSERT INTO accede VALUES(234567,005,"2020-10-06","08:34:20");
INSERT INTO accede VALUES(234567,006,"2020-10-06","08:46:48");
INSERT INTO accede VALUES(234567,506,"2020-10-06","08:53:27");
INSERT INTO accede VALUES(234567,004,"2020-10-07","08:24:58");
INSERT INTO accede VALUES(234567,005,"2020-10-07","08:34:20");
INSERT INTO accede VALUES(234567,006,"2020-10-07","08:46:48");
INSERT INTO accede VALUES(234567,506,"2020-10-07","08:53:27");
INSERT INTO accede VALUES(234567,004,"2020-10-08","08:24:58");
INSERT INTO accede VALUES(234567,005,"2020-10-08","08:34:20");
INSERT INTO accede VALUES(234567,006,"2020-10-08","08:46:48");
INSERT INTO accede VALUES(234567,506,"2020-10-08","08:53:27");

#---------------------------------------------------------------------------------------------
#Inspector Alsina turno mañana
INSERT INTO accede VALUES(345678,007,"2020-10-05","08:24:58");
INSERT INTO accede VALUES(345678,008,"2020-10-05","08:34:20");
INSERT INTO accede VALUES(345678,009,"2020-10-05","08:46:48");
INSERT INTO accede VALUES(345678,509,"2020-10-05","08:53:27");
INSERT INTO accede VALUES(345678,007,"2020-10-06","08:24:58");
INSERT INTO accede VALUES(345678,008,"2020-10-06","08:34:20");
INSERT INTO accede VALUES(345678,009,"2020-10-06","08:46:48");
INSERT INTO accede VALUES(345678,509,"2020-10-06","08:53:27");
INSERT INTO accede VALUES(345678,007,"2020-10-07","08:24:58");
INSERT INTO accede VALUES(345678,008,"2020-10-07","08:34:20");
INSERT INTO accede VALUES(345678,009,"2020-10-07","08:46:48");
INSERT INTO accede VALUES(345678,509,"2020-10-07","08:53:27");
INSERT INTO accede VALUES(345678,007,"2020-10-08","08:24:58");
INSERT INTO accede VALUES(345678,008,"2020-10-08","08:34:20");
INSERT INTO accede VALUES(345678,009,"2020-10-08","08:46:48");
INSERT INTO accede VALUES(345678,509,"2020-10-08","08:53:27");
#---------------------------------------------------------------------------------------------
#Inspector San Martin turno mañana
INSERT INTO accede VALUES(456789,010,"2020-10-05","08:24:58");
INSERT INTO accede VALUES(456789,011,"2020-10-05","08:34:20");
INSERT INTO accede VALUES(456789,012,"2020-10-05","08:46:48");
INSERT INTO accede VALUES(456789,512,"2020-10-05","08:53:27");
INSERT INTO accede VALUES(456789,010,"2020-10-06","08:24:58");
INSERT INTO accede VALUES(456789,011,"2020-10-06","08:34:20");
INSERT INTO accede VALUES(456789,012,"2020-10-06","08:46:48");
INSERT INTO accede VALUES(456789,512,"2020-10-06","08:53:27");
INSERT INTO accede VALUES(456789,010,"2020-10-07","08:24:58");
INSERT INTO accede VALUES(456789,011,"2020-10-07","08:34:20");
INSERT INTO accede VALUES(456789,012,"2020-10-07","08:46:48");
INSERT INTO accede VALUES(456789,512,"2020-10-07","08:53:27");
INSERT INTO accede VALUES(456789,010,"2020-10-08","08:24:58");
INSERT INTO accede VALUES(456789,011,"2020-10-08","08:34:20");
INSERT INTO accede VALUES(456789,012,"2020-10-08","08:46:48");
INSERT INTO accede VALUES(456789,512,"2020-10-08","08:53:27");
#---------------------------------------------------------------------------------------------
#Inspector Donado turno tarde
INSERT INTO accede VALUES(456789,001,"2020-10-05","15:24:58");
INSERT INTO accede VALUES(456789,002,"2020-10-05","15:34:20");
INSERT INTO accede VALUES(456789,003,"2020-10-05","15:46:48");
INSERT INTO accede VALUES(456789,503,"2020-10-05","15:53:27");
INSERT INTO accede VALUES(456789,001,"2020-10-06","15:24:58");
INSERT INTO accede VALUES(456789,002,"2020-10-06","15:34:20");
INSERT INTO accede VALUES(456789,003,"2020-10-06","15:46:48");
INSERT INTO accede VALUES(456789,503,"2020-10-06","15:53:27");
INSERT INTO accede VALUES(456789,001,"2020-10-07","15:24:58");
INSERT INTO accede VALUES(456789,002,"2020-10-07","15:34:20");
INSERT INTO accede VALUES(456789,003,"2020-10-07","15:46:48");
INSERT INTO accede VALUES(456789,503,"2020-10-07","15:53:27");
INSERT INTO accede VALUES(456789,001,"2020-10-08","15:24:58");
INSERT INTO accede VALUES(456789,002,"2020-10-08","15:34:20");
INSERT INTO accede VALUES(456789,003,"2020-10-08","15:46:48");
INSERT INTO accede VALUES(456789,503,"2020-10-08","15:53:27");
#---------------------------------------------------------------------------------------------
#Inspector Brown turno tarde
INSERT INTO accede VALUES(345678,004,"2020-10-05","15:24:58");
INSERT INTO accede VALUES(345678,005,"2020-10-05","15:34:20");
INSERT INTO accede VALUES(345678,006,"2020-10-05","15:46:48");
INSERT INTO accede VALUES(345678,506,"2020-10-05","15:53:27");
INSERT INTO accede VALUES(345678,004,"2020-10-06","15:24:58");
INSERT INTO accede VALUES(345678,005,"2020-10-06","15:34:20");
INSERT INTO accede VALUES(345678,006,"2020-10-06","15:46:48");
INSERT INTO accede VALUES(345678,506,"2020-10-06","15:53:27");
INSERT INTO accede VALUES(345678,004,"2020-10-07","15:24:58");
INSERT INTO accede VALUES(345678,005,"2020-10-07","15:34:20");
INSERT INTO accede VALUES(345678,006,"2020-10-07","15:46:48");
INSERT INTO accede VALUES(345678,506,"2020-10-07","15:53:27");
INSERT INTO accede VALUES(345678,004,"2020-10-08","15:24:58");
INSERT INTO accede VALUES(345678,005,"2020-10-08","15:34:20");
INSERT INTO accede VALUES(345678,006,"2020-10-08","15:46:48");
INSERT INTO accede VALUES(345678,506,"2020-10-08","15:53:27");
#---------------------------------------------------------------------------------------------
#Inspector Alsina turno tarde
INSERT INTO accede VALUES(234567,007,"2020-10-05","15:24:58");
INSERT INTO accede VALUES(234567,008,"2020-10-05","15:34:20");
INSERT INTO accede VALUES(234567,009,"2020-10-05","15:46:48");
INSERT INTO accede VALUES(234567,509,"2020-10-05","15:53:27");
INSERT INTO accede VALUES(234567,007,"2020-10-06","15:24:58");
INSERT INTO accede VALUES(234567,008,"2020-10-06","15:34:20");
INSERT INTO accede VALUES(234567,009,"2020-10-06","15:46:48");
INSERT INTO accede VALUES(234567,509,"2020-10-06","15:53:27");
INSERT INTO accede VALUES(234567,007,"2020-10-07","15:24:58");
INSERT INTO accede VALUES(234567,008,"2020-10-07","15:34:20");
INSERT INTO accede VALUES(234567,009,"2020-10-07","15:46:48");
INSERT INTO accede VALUES(234567,509,"2020-10-07","15:53:27");
INSERT INTO accede VALUES(234567,007,"2020-10-08","15:24:58");
INSERT INTO accede VALUES(234567,008,"2020-10-08","15:34:20");
INSERT INTO accede VALUES(234567,009,"2020-10-08","15:46:48");
INSERT INTO accede VALUES(234567,509,"2020-10-08","15:53:27");

#---------------------------------------------------------------------------------------------
#Inspector San Martin turno tarde
INSERT INTO accede VALUES(123456,010,"2020-10-05","15:24:58");
INSERT INTO accede VALUES(123456,011,"2020-10-05","15:34:20");
INSERT INTO accede VALUES(123456,012,"2020-10-05","15:46:48");
INSERT INTO accede VALUES(123456,512,"2020-10-05","15:53:27");
INSERT INTO accede VALUES(123456,010,"2020-10-06","15:24:58");
INSERT INTO accede VALUES(123456,011,"2020-10-06","15:34:20");
INSERT INTO accede VALUES(123456,012,"2020-10-06","15:46:48");
INSERT INTO accede VALUES(123456,512,"2020-10-06","15:53:27");
INSERT INTO accede VALUES(123456,010,"2020-10-07","15:24:58");
INSERT INTO accede VALUES(123456,011,"2020-10-07","15:34:20");
INSERT INTO accede VALUES(123456,012,"2020-10-07","15:46:48");
INSERT INTO accede VALUES(123456,512,"2020-10-07","15:53:27");
INSERT INTO accede VALUES(123456,010,"2020-10-08","15:24:58");
INSERT INTO accede VALUES(123456,011,"2020-10-08","15:34:20");
INSERT INTO accede VALUES(123456,012,"2020-10-08","15:46:48");
INSERT INTO accede VALUES(123456,512,"2020-10-08","15:53:27");

INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",300,"do","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",200,"do","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",100,"do","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",300,"lu","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",200,"lu","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",100,"lu","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",300,"ma","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",200,"ma","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",100,"ma","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",300,"mi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",200,"mi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",100,"mi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",300,"ju","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",200,"ju","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",100,"ju","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",300,"vi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",200,"vi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",100,"vi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",300,"sa","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",200,"sa","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"Donado",100,"sa","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",300,"do","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",200,"do","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",100,"do","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",300,"lu","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",200,"lu","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",100,"lu","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",300,"ma","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",200,"ma","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",100,"ma","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",300,"mi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",200,"mi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",100,"mi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",300,"ju","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",200,"ju","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",100,"ju","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",300,"vi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",200,"vi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",100,"vi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",300,"sa","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",200,"sa","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(123456,"San Martin",100,"sa","t");

INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",300,"do","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",200,"do","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",100,"do","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",300,"lu","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",200,"lu","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",100,"lu","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",300,"ma","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",200,"ma","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",100,"ma","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",300,"mi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",200,"mi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",100,"mi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",300,"ju","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",200,"ju","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",100,"ju","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",300,"vi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",200,"vi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",100,"vi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",300,"sa","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",200,"sa","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Brown",100,"sa","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",300,"do","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",200,"do","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",100,"do","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",300,"lu","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",200,"lu","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",100,"lu","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",300,"ma","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",200,"ma","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",100,"ma","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",300,"mi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",200,"mi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",100,"mi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",300,"ju","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",200,"ju","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",100,"ju","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",300,"vi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",200,"vi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",100,"vi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",300,"sa","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",200,"sa","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(234567,"Alsina",100,"sa","t");

INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",300,"do","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",200,"do","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",100,"do","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",300,"lu","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",200,"lu","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",100,"lu","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",300,"ma","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",200,"ma","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",100,"ma","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",300,"mi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",200,"mi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",100,"mi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",300,"ju","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",200,"ju","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",100,"ju","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",300,"vi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",200,"vi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",100,"vi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",300,"sa","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",200,"sa","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Brown",100,"sa","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",300,"do","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",200,"do","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",100,"do","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",300,"lu","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",200,"lu","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",100,"lu","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",300,"ma","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",200,"ma","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",100,"ma","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",300,"mi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",200,"mi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",100,"mi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",300,"ju","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",200,"ju","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",100,"ju","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",300,"vi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",200,"vi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",100,"vi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",300,"sa","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",200,"sa","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(345678,"Alsina",100,"sa","m");


INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",300,"do","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",200,"do","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",100,"do","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",300,"lu","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",200,"lu","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",100,"lu","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",300,"ma","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",200,"ma","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",100,"ma","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",300,"mi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",200,"mi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",100,"mi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",300,"ju","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",200,"ju","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",100,"ju","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",300,"vi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",200,"vi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",100,"vi","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",300,"sa","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",200,"sa","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"Donado",100,"sa","t");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",300,"do","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",200,"do","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",100,"do","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",300,"lu","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",200,"lu","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",100,"lu","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",300,"ma","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",200,"ma","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",100,"ma","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",300,"mi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",200,"mi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",100,"mi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",300,"ju","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",200,"ju","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",100,"ju","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",300,"vi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",200,"vi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",100,"vi","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",300,"sa","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",200,"sa","m");
INSERT INTO asociado_con(legajo,calle,altura,dia,turno) VALUES(456789,"San Martin",100,"sa","m");

INSERT INTO multa(fecha,hora,patente,id_asociado_con) VALUES("2020-10-07","08:34:20","ABC147",158); 
INSERT INTO multa(fecha,hora,patente,id_asociado_con) VALUES("2020-10-08","15:24:58","JBK292",99);
INSERT INTO multa(fecha,hora,patente,id_asociado_con) VALUES("2020-10-06","08:46:48","JBK292",7);
