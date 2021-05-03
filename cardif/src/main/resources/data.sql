/*DROP TABLE IF EXISTS Funcionario;
DROP TABLE IF EXISTS Cargo;

CREATE TABLE Funcionario(
	id int NOT NULL AUTO_INCREMENT PRIMARY_KEY,
	name varchar(50),
	birthday date,
	document varchar(50),
	cargo_id int FOREIGN KEY REFERENCES Cargo(id)
	
);

CREATE TABLE Cargo(
	id int NOT NULL AUTO_INCREMENT PRIMARY_KEY,
	name varchar(50)
);
*/
INSERT INTO DEPARTAMENTO(name) VALUES ('Administrativo');
INSERT INTO DEPARTAMENTO(name) VALUES ('Recursos Humanos');
INSERT INTO DEPARTAMENTO(name) VALUES ('Comercial');
INSERT INTO DEPARTAMENTO(name) VALUES ('Financeiro');
INSERT INTO DEPARTAMENTO(name) VALUES ('Operacional');

INSERT INTO CARGO(name) VALUES ('Chefe');
INSERT INTO CARGO(name) VALUES ('Gerente');
INSERT INTO CARGO(name) VALUES ('Supervisor');
INSERT INTO CARGO(name) VALUES ('Coordenador');
INSERT INTO CARGO(name) VALUES ('Consultor');
INSERT INTO CARGO(name) VALUES ('Analista Sênior');
INSERT INTO CARGO(name) VALUES ('Analista Pleno');
INSERT INTO CARGO(name) VALUES ('Analista Júnior');
INSERT INTO CARGO(name) VALUES ('Estagiário');

INSERT INTO Funcionario(age,name,birthday, document,cargo_id,departamento_id) VALUES (27,'Jeferson A.','1994-04-12','42.400.683-2',1,2);
INSERT INTO Funcionario(age,name,birthday, document,cargo_id,departamento_id) VALUES (28,'Robson Cabral','1993-04-12','41.051.725-2',2,1);
INSERT INTO Funcionario(age,name,birthday, document,cargo_id,departamento_id) VALUES (26,'Matheus Magalhães','1994-04-12','47879456-8',3,3);
INSERT INTO Funcionario(age,name,birthday, document,cargo_id,departamento_id) VALUES (32,'Thiago Santana','1989-04-12','29.962.292-7',4,4);
INSERT INTO Funcionario(age,name,birthday, document,cargo_id,departamento_id) VALUES (27,'Daniel Santana','1993-04-12','30.585.335-1',5,5);
INSERT INTO Funcionario(age,name,birthday, document,cargo_id,departamento_id,historico_departamentos) VALUES (27,'Daniel Santana','1993-04-12','30.585.335-1',5,5,'Recursos Humanos,Administrativo');


