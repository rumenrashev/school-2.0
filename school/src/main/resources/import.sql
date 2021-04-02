USE school_db;
INSERT INTO authorities(authority) VALUE ('ADMIN');
INSERT INTO authorities(authority) VALUE ('TEACHER');
INSERT INTO authorities(authority) VALUE ('STUDENT');
INSERT INTO authorities(authority) VALUE ('USER');

INSERT INTO  users(email,password) VALUE ('admin@gmail.com','admin');
INSERT INTO  users(email,password) VALUE ('teacher@gmail.com','teacher');
INSERT INTO  users(email,password) VALUE ('student@gmail.com','student');
INSERT INTO  users(email,password) VALUE ('user@gmail.com','user');

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
INSERT INTO subjects(subject, classroom_id, teacher_id) VALUE (2,1,1);

INSERT INTO students(first_name, last_name, middle_name, classroom_id,user_id) VALUE ('Асен','Иванов','Петров',1,3);

INSERT INTO marks(value, student_id, subject_id) VALUE (6,1,1);
INSERT INTO marks(value, student_id, subject_id) VALUE (2,1,1);
INSERT INTO marks(value, student_id, subject_id) VALUE (5,1,1);

INSERT INTO marks(value, student_id, subject_id) VALUE (6,1,2);
INSERT INTO marks(value, student_id, subject_id) VALUE (6,1,2);
INSERT INTO marks(value, student_id, subject_id) VALUE (6,1,2);
INSERT INTO marks(value, student_id, subject_id) VALUE (6,1,2);
