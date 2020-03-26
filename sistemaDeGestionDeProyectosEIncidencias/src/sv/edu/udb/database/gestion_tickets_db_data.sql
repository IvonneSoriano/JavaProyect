/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Rick
 * Created: Mar 11, 2020
 */
ALTER TABLE `gestion_tickets`.`roles` AUTO_INCREMENT = 0;
INSERT INTO `gestion_tickets`.`roles` (`ROLNAME`) VALUES ('JEFE_AREA_FUNCIONAL');
INSERT INTO `gestion_tickets`.`roles` (`ROLNAME`) VALUES ('EMPLEADO_AREA_FUNCIONAL');
INSERT INTO `gestion_tickets`.`roles` (`ROLNAME`) VALUES ('JEFE_DE_DESARROLLO');
INSERT INTO `gestion_tickets`.`roles` (`ROLNAME`) VALUES ('PROGRAMADOR');
INSERT INTO `gestion_tickets`.`roles` (`ROLNAME`) VALUES ('ADMINISTRADOR');

ALTER TABLE `gestion_tickets`.`departments` AUTO_INCREMENT = 0;
INSERT INTO `gestion_tickets`.`departments` (`DEPARMENTNAME`) VALUES ('Departamento de Finanzas');
INSERT INTO `gestion_tickets`.`departments` (`DEPARMENTNAME`) VALUES ('Departamento de Ventas');
INSERT INTO `gestion_tickets`.`departments` (`DEPARMENTNAME`) VALUES ('Departamento de Facturacion Fija');
INSERT INTO `gestion_tickets`.`departments` (`DEPARMENTNAME`) VALUES ('Departamento de Facturacion Movil');

ALTER TABLE `gestion_tickets`.`employees` AUTO_INCREMENT = 0;
INSERT INTO `gestion_tickets`.`employees` (`ROLID`, `DEPARMENTID`, `EMPLOYEENAME`, `EMPLOYEELASTNAME`, `USERNAME`, `PASSWORD`) 
VALUES ('1', '1', 'Administrador', 'I', 'admin1', 'admin1');

INSERT INTO `projects` (`PROJECTID`, `DEPARMENTID`, `PROJECTNAME`, `PROJECTDESCRIPTION`, `CREATIONDATE`)
VALUES
(1, 4, 'Proyecto', 'Un Proyecto', '2020-03-19 05:46:30'),
(2, 2, 'Otro proyecto', 'Bien bonito el proyecto.', '2020-03-19 19:07:18'),
(4, 3, 'Otro proyecto Facturas', 'Bien bonito el proyecto 2.', '2020-03-19 19:09:02'),
(5, 2, 'Otro proyecto Facturas 3', 'Bien bonito el proyecto 2.', '2020-03-19 19:09:40');

INSERT INTO `gestion_tickets`.`requesttypes` (`REQUESTTYPEID`,`REQUESTTYPENAME`) VALUES (1,'SISTEMA_NUEVO');
INSERT INTO `gestion_tickets`.`requesttypes` (`REQUESTTYPEID`,`REQUESTTYPENAME`) VALUES (2,'NUEVA_FUNCIONALIDAD');
INSERT INTO `gestion_tickets`.`requesttypes` (`REQUESTTYPEID`,`REQUESTTYPENAME`) VALUES (3,'CORRECCION');

INSERT INTO  `gestion_tickets`.`requests` (`REQUESTID`,`REQUESTTYPEID`,`REQUESTDATE`,`REQUESTDESCRIPTION`,`REQUESTSTATUS`) VALUES (1,1,'2020-01-01 10:10:10','description test','EN_ESPERA');
INSERT INTO  `gestion_tickets`.`requests` (`REQUESTID`,`REQUESTTYPEID`,`REQUESTDATE`,`REQUESTDESCRIPTION`,`REQUESTSTATUS`) VALUES (2,2,'2020-03-25 01:31:25','test','EN_ESPERA');
INSERT INTO  `gestion_tickets`.`requests` (`REQUESTID`,`REQUESTTYPEID`,`REQUESTDATE`,`REQUESTDESCRIPTION`,`REQUESTSTATUS`) VALUES (3,2,'2020-03-25 01:43:06','esta es una nueva funcionalidad 3D para este proyecto existente en el\ndepartamento','EN_ESPERA');
INSERT INTO  `gestion_tickets`.`requests` (`REQUESTID`,`REQUESTTYPEID`,`REQUESTDATE`,`REQUESTDESCRIPTION`,`REQUESTSTATUS`) VALUES (4,1,'2020-03-25 02:06:48','nuevo sistema para el manejo de aula virtual','EN_ESPERA');
INSERT INTO  `gestion_tickets`.`requests` (`REQUESTID`,`REQUESTTYPEID`,`REQUESTDATE`,`REQUESTDESCRIPTION`,`REQUESTSTATUS`) VALUES (5,3,'2020-03-25 02:13:01','nuevo proyecto para votaciones estudiantiles udb','EN_ESPERA');
INSERT INTO  `gestion_tickets`.`requests` (`REQUESTID`,`REQUESTTYPEID`,`REQUESTDATE`,`REQUESTDESCRIPTION`,`REQUESTSTATUS`) VALUES (6,3,'2020-03-25 02:14:05','nuevo sistema contable tin marin','EN_ESPERA');
INSERT INTO  `gestion_tickets`.`requests` (`REQUESTID`,`REQUESTTYPEID`,`REQUESTDATE`,`REQUESTDESCRIPTION`,`REQUESTSTATUS`) VALUES (7,2,'2020-03-25 02:21:25','testing requerimiento con proyecto','EN_ESPERA');
INSERT INTO  `gestion_tickets`.`requests` (`REQUESTID`,`REQUESTTYPEID`,`REQUESTDATE`,`REQUESTDESCRIPTION`,`REQUESTSTATUS`) VALUES (8,1,'2020-03-25 02:36:11','nuevo sistema para ing.biomedica inventarios','EN_ESPERA');
INSERT INTO  `gestion_tickets`.`requests` (`REQUESTID`,`REQUESTTYPEID`,`REQUESTDATE`,`REQUESTDESCRIPTION`,`REQUESTSTATUS`) VALUES (9,3,'2020-03-25 02:36:52','Correcion en la UI del sistema actual\nMenu de ventas','EN_ESPERA');

UPDATE `employees` SET `PASSWORD`= SHA2('admin1', 256) WHERE `EMPLOYEENAME` = 'ADMINISTRADOR'


