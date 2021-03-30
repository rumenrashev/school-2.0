USE school_db;
INSERT INTO authorities(authority) VALUE ('ADMIN');
INSERT INTO authorities(authority) VALUE ('TEACHER');
INSERT INTO authorities(authority) VALUE ('STUDENT');
INSERT INTO authorities(authority) VALUE ('USER');

INSERT INTO  users(username,password) VALUE ('admin','admin');
INSERT INTO  users(username,password) VALUE ('teacher','teacher');
INSERT INTO  users(username,password) VALUE ('student','student');
INSERT INTO  users(username,password) VALUE ('user','user');

INSERT INTO users_authorities(user_id, authority_id) VALUE (1,1);
INSERT INTO users_authorities(user_id, authority_id) VALUE (1,4);
INSERT INTO users_authorities(user_id, authority_id) VALUE (2,2);
INSERT INTO users_authorities(user_id, authority_id) VALUE (2,4);
INSERT INTO users_authorities(user_id, authority_id) VALUE (3,3);
INSERT INTO users_authorities(user_id, authority_id) VALUE (3,4);
INSERT INTO users_authorities(user_id, authority_id) VALUE (4,4);

INSERT INTO  teachers(first_name, last_name, middle_name,user_id) VALUE('Иван','Петров','Даскалов',2);

INSERT INTO classrooms(letter, number) VALUE (0,0);
INSERT INTO classrooms(letter, number) VALUE (0,1);

INSERT INTO subjects(subject, classroom_id, teacher_id) VALUE (1,1,1);
INSERT INTO subjects(subject, classroom_id, teacher_id) VALUE (1,2,1);

INSERT INTO students(first_name, last_name, middle_name, classroom_id,user_id) VALUE ('Асен','Иванов','Петров',1,3);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Петър','Георгиев','Атанасов',1);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Траян','Василев','Иванов',1);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Георги','Траявов','Петков',1);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Васил','Петров','Стоянов',1);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Стефан','Иванов','Тончев',1);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Ясен','Георгиев','Петров',1);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Иван','Петров','Стоянов',1);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Васил','Петков','Велев',1);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Тодор','Петров','Стоянов',1);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Георги','Стоянов','Петков',1);
#
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Асен','Иванов','Петров',2);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Петър','Георгиев','Атанасов',2);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Траян','Василев','Иванов',2);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Георги','Траявов','Петков',2);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Васил','Петров','Стоянов',2);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Стефан','Иванов','Тончев',2);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Ясен','Георгиев','Петров',2);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Иван','Петров','Стоянов',2);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Васил','Петков','Велев',2);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Тодор','Петров','Стоянов',2);
# INSERT INTO students(first_name, last_name, middle_name, classroom_id) VALUE ('Георги','Стоянов','Петков',2);


INSERT INTO marks(value, student_id, subject_id) VALUE (6,1,1);
INSERT INTO marks(value, student_id, subject_id) VALUE (2,1,1);
INSERT INTO marks(value, student_id, subject_id) VALUE (5,1,1);
