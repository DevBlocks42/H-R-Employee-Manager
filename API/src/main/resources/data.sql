
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

DROP TABLE IF EXISTS employeeDepartment;

CREATE TABLE employeeDepartment (
   employee_id INT,
   department_id INT,
   isLeading BOOLEAN,
   PRIMARY KEY(employee_id, department_id),
   FOREIGN KEY(employee_id) REFERENCES employees(id),
   FOREIGN KEY(department_id) REFERENCES departments(id)
);

INSERT INTO admins (username, password_hash) VALUES
	('admin', '$2a$10$dpBn4dpJCCluHV6I0k4Yb.PT4zmV1irgvCyjpPhDtWPf/yknwaMju');
 
INSERT INTO employees (first_name, last_name, mail, password) VALUES
  ('Laurent', 'GINA', 'laurentgina@mail.com', 'laurent'),
  ('Sophie', 'FONCEK', 'sophiefoncek@mail.com', 'sophie'),
  ('Agathe', 'FEELING', 'agathefeeling@mail.com', 'agathe');
  
 INSERT INTO departments (name, description) VALUES 
 	('Ressources Humaines', 'Service en charge de la gestion des ressources humaines de l''entreprise.');