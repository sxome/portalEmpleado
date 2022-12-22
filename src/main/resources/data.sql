insert into USUARIO (username, nombre, email, password, estatus, fecha_registro)values('PAQUITA','PACA LOPEZ','PACALOPEZ@CORREO.ES','$2a$10$g1IEAEZOHdAcNME.f5cS4e75vM5VitDwckpsslSMMSHWF4WvnckfW','DESEMPLEADO', '2021-10-10');
insert into USUARIO (username, nombre, email, password, estatus, fecha_registro)values('CHEFA','JOSEFINA DIAZ','PEPADIAZ@CORREO.ES','$2a$10$bpTHsRlvVFxE55AKwL1WouXWSGJPLF6mq1nSn04PENZeE.qnWHZdG','DESEMPLEADO', '2020-10-10');
insert into USUARIO (username, nombre, email, password, estatus, fecha_registro)values('PEPE','JOLE GONZALEZ','JOSEGONZALEZ@CORREO.ES','$2a$10$AnJHBggKyMXMua/YMnNv3ecllXrogOnvTPRecc9J6FUgS6dLJZZFi','EMPLEADO', '2020-10-10');
insert into USUARIO (username, nombre, email, password, estatus, fecha_registro)values('KIKO','KIKO GONZALEZ','KIKOGONZALEZ@CORREO.ES','$2a$10$aVYK2hQoGue3kU9o.WTSvO/5TKFqq2vLfZP5KtfeuxE29oecjxOAS','EMPLEADO', '2020-10-10');

insert into PERFIL (perfil) values('ADMIN');
insert into PERFIL (perfil) values('USER');
insert into PERFIL (perfil) values('OTRO');

insert into USUARIO_PERFIL (id_usuario, id_perfil) values(3,1);
insert into USUARIO_PERFIL (id_usuario, id_perfil) values(3,2);
insert into USUARIO_PERFIL (id_usuario, id_perfil) values(2,2);
insert into USUARIO_PERFIL (id_usuario, id_perfil) values(1,2);
insert into USUARIO_PERFIL (id_usuario, id_perfil) values(4,2);

insert into CATEGORIA (nombre, descripcion) values('COMERCIAL','LABORES PROPIAS DE UN COMERCIAL');
insert into CATEGORIA (nombre, descripcion) values('EDUCACIÓN','FORMACION DE PERSONAS');
insert into CATEGORIA (nombre, descripcion) values('ARTES Y OFICIOS','SUS LABORES');
insert into CATEGORIA (nombre, descripcion) values('TECNOLOGIA E INFORMATICA','DESARROLLO Y GESTION DE APLICACIONES');

insert into VACANTE (nombre, descripcion, fecha, salario, destacado, estatus, id_categoria) values('COMERCIAL EN RICOH','PUESTO DE TRABAJO EN ASTURIAS', '2022-10-10', true, 30000, 'ABIERTA', 1);
insert into VACANTE (nombre, descripcion, fecha, salario, destacado, estatus, id_categoria) values('PROFESOR EN UNIOVI','PUESTO DE TRABAJO EN CANTABRIA', '2022-1-10', false, 40000, 'ABIERTA', 2);
insert into VACANTE (nombre, descripcion, fecha, salario, destacado, estatus, id_categoria) values('JARDINERO EN PARQUEPRIN','PUESTO DE TRABAJO EN GALICIA', '2022-4-10', true, 50000, 'ABIERTA', 3);
insert into VACANTE (nombre, descripcion, fecha, salario, destacado, estatus, id_categoria) values('DESARROLLADOR EN RICOH','PUESTO DE TRABAJO EN MADRID', '2022-6-10', true, 60000, 'CERRADA', 3);

insert into INSCRIPCION (id_vacante, id_usuario, fecha_inscripcion) values(1,1, '2022-12-10');
insert into INSCRIPCION (id_vacante, id_usuario, fecha_inscripcion) values(1,2, '2022-11-10');
insert into INSCRIPCION (id_vacante, id_usuario, fecha_inscripcion) values(1,3, '2022-11-10');
insert into INSCRIPCION (id_vacante, id_usuario, fecha_inscripcion) values(2,2, '2022-12-10');


