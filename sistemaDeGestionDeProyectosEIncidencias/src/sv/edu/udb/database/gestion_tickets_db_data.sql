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



