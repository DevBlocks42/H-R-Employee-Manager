
DROP TABLE IF EXISTS employees;
 
CREATE TABLE employees (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  mail VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS admins;

CREATE TABLE admins (
	id INT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(20) NOT NULL,
	password_hash VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS departments;

CREATE TABLE departments(
   id INT AUTO_INCREMENT,
   name VARCHAR(50),
   description VARCHAR(255),
   PRIMARY KEY(id)
);

CREATE TABLE employee_department (
	employee_id INT,
   	department_id INT,
   	isLeading BOOLEAN,
   	PRIMARY KEY(employee_id, department_id),
   	FOREIGN KEY(employee_id) REFERENCES employees(id) ON DELETE CASCADE,
   	FOREIGN KEY(department_id) REFERENCES departments(id)
);

CREATE TABLE admin_logs (
	id INT AUTO_INCREMENT,
	admin_id INT,
   	admin_action VARCHAR(64),
   	dateTime TIMESTAMP,
   	ip VARCHAR(255),
   	userAgent VARCHAR(255),
   	data VARCHAR(255),
   	PRIMARY KEY(id, admin_id),
   	FOREIGN KEY(admin_id) REFERENCES admins(id)
);


CREATE TABLE db_operations (
	id INT PRIMARY KEY AUTO_INCREMENT,
	operation VARCHAR(64) NOT NULL,
	table_name VARCHAR(64) NOT NULL, 
	datetime TIMESTAMP NOT NULL
);

/* employees triggers */

CREATE TRIGGER db_update_employees_trigger AFTER UPDATE ON employees
for each row call 'com.hr.api.dbms.TriggerNotifier';

CREATE TRIGGER db_insert_employees_trigger AFTER INSERT ON employees
for each row call 'com.hr.api.dbms.TriggerNotifier';

CREATE TRIGGER db_delete_employees_trigger AFTER DELETE ON employees
for each row call 'com.hr.api.dbms.TriggerNotifier';

/* departments trigger */

CREATE TRIGGER db_update_dpt_trigger AFTER UPDATE ON departments
for each row call 'com.hr.api.dbms.TriggerNotifier';

CREATE TRIGGER db_insert_dpt_trigger AFTER INSERT ON departments
for each row call 'com.hr.api.dbms.TriggerNotifier';

CREATE TRIGGER db_delete_dpt_trigger AFTER DELETE ON departments
for each row call 'com.hr.api.dbms.TriggerNotifier';


INSERT INTO admins (username, password_hash) VALUES
	('admin', '$2a$10$dpBn4dpJCCluHV6I0k4Yb.PT4zmV1irgvCyjpPhDtWPf/yknwaMju');
 
INSERT INTO employees (first_name, last_name, mail, password) VALUES
  ('Laurent', 'GINA', 'laurentgina@mail.com', 'laurent'),
  ('Sophie', 'FONCEK', 'sophiefoncek@mail.com', 'sophie'),
  ('Agathe', 'FEELING', 'agathefeeling@mail.com', 'agathe');
  
 INSERT INTO departments (name, description) VALUES 
 	('Ressources Humaines', 'Service en charge de la gestion des ressources humaines de l''entreprise.');
 	
INSERT INTO employee_department (employee_id, department_id, isLeading) VALUES 
	(1, 1, true),
	(2, 1, false),
	(3, 1, false);