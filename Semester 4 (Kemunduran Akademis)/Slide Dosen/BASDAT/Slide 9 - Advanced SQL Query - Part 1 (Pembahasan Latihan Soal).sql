/*
PEMBAHASAN LATIHAN BASIS DATA SLIDE 9 (PART 1): ADVANCED SQL

Kontributor:
- Lintang Matahari H.

Versi: 2.00/27-04-2021
*/

-- Saran: Create DB baru dan import company DDL pada DB baru, lalu tambahkan dummy data berikut
-- Dummy Data & sedikit DDL untuk mengecek luaran query solusi
ALTER TABLE WORKS_ON ALTER COLUMN Hours DROP NOT NULL;
INSERT INTO EMPLOYEE VALUES ('Vladimir','V', 'Putin', '111112222', NULL, NULL, 'M',999999,NULL,5);
INSERT INTO EMPLOYEE VALUES ('Joe','J', 'Biden', '111113333', NULL, NULL, 'M',999999,NULL,5);
INSERT INTO WORKS_ON VALUES ('111112222', 1, NULL);

------------------------------------------------------------------------

/* 1. Tampilkan nama depan dan gaji employee yang terlibat pada
project namun memiliki jam kerja null. */

-- Asumsi: Identitas pegawai cukup muncul sekali saja pada luaran query

-- Alternatif Solusi 1: Dengan INNER JOIN tanpa explicit JOIN clause pada WHERE clause
SELECT DISTINCT Fname, Salary
FROM EMPLOYEE, WORKS_ON
WHERE Ssn = Essn AND Hours IS NULL;

-- Alternatif Solusi 2: Dengan INNER JOIN yang di-state secara explicit pada WHERE clause
SELECT DISTINCT Fname, Salary
FROM EMPLOYEE INNER JOIN WORKS_ON ON Ssn = Essn
WHERE Hours IS NULL;

-- Alternatif Solusi 3: Dengan (INNER) JOIN clause yang di-state secara explicit pada WHERE clause
SELECT DISTINCT Fname, Salary
FROM EMPLOYEE JOIN WORKS_ON ON Ssn = Essn
WHERE Hours IS NULL;
-- Perhatikan bahwa clause INNER tidak wajib di-state secara explicit untuk melakukan INNER JOIN
-- INNER JOIN secara default akan dilakukan jika tipe JOIN tidak dinyatakan dalam query

------------------------------------------------------------------------

/* 2. Tampilkan nama depan manager dan nama department manager tersebut bekerja
dimana project pada departemen tersebut dikerjakan terdapat karyawan yang memiliki jam kerja null.  */

-- STEP 1: Buat strategi memecah query, tentukan simpler queries untuk menyeleksi 
-- a.) nama depan manager dan nama department manager tersebut bekerja, dan
-- b.) departemen yang terdapat karyawan yang memiliki jam kerja null

-- Idenya adalah kita ingin mengecek apakah Dno departemen tempat si manager bekerja ada pada 
-- kumpulan Dno departemen yang terdapat karyawan yang memiliki jam kerja null (diretrieve dengan query b)

-- Query a:
SELECT Fname, Dname
FROM EMPLOYEE, DEPARTMENT
WHERE Dno = Dnumber AND Mgr_ssn = Ssn 

-- Query b: 
SELECT Dnumber
FROM EMPLOYEE, DEPARTMENT, PROJECT, WORKS_ON
WHERE Dno = Dnumber AND Dnum = Dnumber AND Pno = Pnumber AND Hours IS NULL;

-- STEP 2: Tentukan outer query dan nested query-nya. 

-- SOLUSI:
SELECT Fname, Dname
FROM EMPLOYEE, DEPARTMENT
WHERE Dno = Dnumber AND Mgr_ssn = Ssn 
	AND Dno IN (SELECT Dnumber
	FROM EMPLOYEE, DEPARTMENT, PROJECT, WORKS_ON
	WHERE Dno = Dnumber AND Dnum = Dnumber AND Pno = Pnumber AND Hours IS NULL);

/* Pada query ini, nested query mengembalikan sebuah list Dnumber departemen yang
mempekerjakan karyawan yang memiliki jam kerja null pada suatu proyek. Clause IN digunakan untuk 
mengecek apakah Dnumber departemen tempat si manager bekerja ada pada list Dnumber yang dikembalikan
nested query. */ 

-- Alternatif solusi
SELECT Fname, Dname
FROM EMPLOYEE, DEPARTMENT, PROJECT, WORKS_ON
WHERE Dno = Dnumber AND Mgr_ssn = Ssn AND Dnum = Dnumber AND Pno = Pnumber AND Hours IS NULL;

------------------------------------------------------------------------

/*3. Tampilkan nama depan dan ssn employee yang mempunyai
departemen dan jenis kelamin yang sama dengan Franklin Wong */

-- STEP 1: Atur strategi, pecah problem menjadi lebih sederhana. 
-- Perhatikan bahwa kita perlu mengecek atribut yang berasal dari relasi yang sama (jenis kelamin).
-- Idenya adalah kita ingin mengecek apakah Dno departemen tempat seorang EMPLOYEE bekerja ada pada 
-- sama dengan Dno departemen yang terdapat EMPLOYEE yang bernama Franklin Wong bekerja dan memiliki jenis kelamin yang sama
-- dengan EMPLOYEE bernama Franklin Wong. Untuk ini kita mengandaikan akan mengecek dari dua tabel EMPLOYEE dengan state yang sama.
-- Tabel EMPLOYEE 'E' digunakan untuk mengambil Fname dan Ssn semua pegawai, sedangkan tabel EMPLOYEE 'W' digunakan untuk mengecek dan mengambil
-- EMPLOYEE bernama Franklin Wong beserta data jenis kelaminnya. Namun, kita juga ingin agar query tidak mengikutsertakan tuple EMPLOYEE
-- bernama 'Franklin Wong'

-- Dalam hal ini kita bisa
-- mencoba memecah problem tersebut menjadi:
-- a.) menampilkan nama depan dan ssn EMPLOYEE dengan Fname 'Franklin' dan Lname 'Wong'
-- b.) menampilkan departemen dan jenis kelamin EMPLOYEE dengan Fname 'Franklin' dan Lname 'Wong'
-- c.) menseleksi nama depan dan ssn semua EMPLOYEE


-- Query untuk problem a:
SELECT Fname, Ssn
FROM EMPLOYEE
WHERE Fname = 'Franklin' AND Lname = 'Wong';

-- Query untuk problem b:
SELECT Dname, Sex
FROM EMPLOYEE, DEPARTMENT
WHERE Fname = 'Franklin' AND Lname = 'Wong' AND Dno = Dnumber; 

-- Query untuk problem c:
SELECT Fname, Ssn
FROM EMPLOYEE;

-- STEP 2: Kita dapat meretrieve data sesuai yang diminta soal dengan melakukan set operation minus (dengan clause EXCEPT)
-- terhadap luaran query yang me-retrieve 'nama depan dan ssn employee yang mempunyai
-- departemen dan jenis kelamin yang sama dengan Franklin Wong' dengan query yang mengembalikan Fname dan ssn EMPLOYEE bernama Franklin Wong

-- SOLUSI:
(SELECT E.Fname, E.Ssn
FROM EMPLOYEE E, EMPLOYEE W, DEPARTMENT
WHERE W.Fname = 'Franklin' AND W.Lname = 'Wong' AND E.Dno = Dnumber AND W.Dno = Dnumber AND E.Sex = W.Sex) 
EXCEPT
(SELECT Fname, Ssn
FROM EMPLOYEE
WHERE Fname = 'Franklin' AND Lname = 'Wong');

/*4. Tampilkan nama employee dan nama departmentnya dimana
employee tersebut minimal terlibat pada satu project.*/

-- STEP 1: Atur strategi, pecah problem menjadi lebih sederhana. Dalam hal ini kita bisa
-- mencoba memecah problem tersebut menjadi:
-- a.) menseleksi semua nama Employee (Fname & Lname) beserta departemenya
-- b.) menseleksi semua Ssn pegawai yang mengerjakan proyek
-- Idenya adalah kita dapat mengecek apakah Ssn seorang employee muncul (IN) dalam kumpulan ssn employee 
-- yang mengerjakan proyek

-- Query untuk problem a:
SELECT DISTINCT Fname, Lname, Dname
FROM EMPLOYEE, DEPARTMENT
WHERE Dno = Dnumber;

-- Query untuk problem b:
SELECT DISTINCT Essn
FROM WORKS_ON;

-- STEP 2: Tentukan outer query dan nested query-nya. 

-- SOLUSI:
SELECT DISTINCT Fname, Dname
FROM EMPLOYEE, DEPARTMENT
WHERE Dno = Dnumber AND Ssn IN (
	SELECT Essn
	FROM WORKS_ON
	WHERE Essn = Ssn
);

-- Alternatif solusi:
SELECT DISTINCT Fname, Dname
FROM EMPLOYEE, DEPARTMENT, WORKS_ON
WHERE Dno = Dnumber AND Essn = Ssn;

/*5. Tampilkan nama belakang dan alamat employee yang tidak
memiliki tanggungan anak (Son atau Daughter)*/

-- STEP 1: Atur strategi, pecah problem menjadi lebih sederhana. 
-- Idenya kita dapat mengecek kondisi apakah Ssn seorang EMPLOYEE tidak ada pada 
-- kumpulan semua Ssn EMPLOYEE yang memiliki DEPENDENT dengan relationship 'Son' ATAU 'Daughter'
-- dan menampilkan Lname serta Address dari EMPLOYEE yang memenuhi kondisi tersebut.
-- Dalam hal ini kita bisa mencoba memecah problem tersebut menjadi:
-- a.) Menseleksi Lname dan Address semua EMPLOYEE
-- b.) Menseleksi Ssn EMPLOYEE yang memiliki DEPENDENT dengan relationship 'Son' ATAU 'Daughter'

-- Query untuk problem a:
SELECT DISTINCT Lname, Address
FROM EMPLOYEE;

-- Query untuk problem b:
SELECT DISTINCT Ssn
FROM EMPLOYEE, DEPENDENT
WHERE Essn = Ssn AND (Relationship = 'Son' OR Relationship = 'Daughter')

-- STEP 2: Tentukan outer query dan nested query-nya. 

-- SOLUSI:
SELECT DISTINCT E.Lname, E.Address
FROM EMPLOYEE E
WHERE E.Ssn NOT IN (
	SELECT DISTINCT Ssn
	FROM EMPLOYEE E_DEP, DEPENDENT
	WHERE Essn = E_DEP.Ssn AND (Relationship = 'Son' OR Relationship = 'Daughter')
);

-- Alternatif solusi:
SELECT DISTINCT Lname, Address
FROM EMPLOYEE 
WHERE NOT EXISTS (
	SELECT DISTINCT Ssn
	FROM DEPENDENT
	WHERE Essn = Ssn AND (Relationship = 'Son' OR Relationship = 'Daughter')
);

/* 6. Tampilkan nama belakang department manager yang tidak
mempunyai tanggungan */

-- Mirip Dengan No. 5

SELECT DISTINCT Fname
FROM EMPLOYEE, DEPARTMENT
WHERE Mgr_ssn = Ssn AND Ssn NOT IN (
	SELECT DISTINCT Ssn
	FROM EMPLOYEE, DEPENDENT
	WHERE Essn = Ssn AND (Relationship = 'Son' OR Relationship = 'Daughter')
);

/* 7. Tampilkan nama depan dan ssn employee dimana project yang
employee tersebut kerjakan selalu sama dengan yang dikerjakan
oleh James Borg. */

-- STEP 1: Atur strategi, pecah problem menjadi lebih sederhana.
-- Idenya kita bisa me-retrieve data Fname dan Ssn  EMPLOYEE yang bekerja di suatu proyek, lalu 
-- mengecek apakah Pno PROJECT yang dikerjakannya ada pada list Pno PROJECT yang dikerjakan EMPLOYEE bernama James Wong.
-- Kemudian pastikan tuple EMPLOYEE bernama James Wonmg tidak diikutkan pada luaran query utama (outer query).
-- Dalam hal ini kita bisa mencoba memecah problem tersebut menjadi:
-- a.) Menseleksi semua Pno PROJECT yang dikerjakan James Wong
-- b.) Menseleksi semua Pno PROJECT yang dikerjakan masing-masing EMPLOYEE
-- c.) Menseleksi tuple EMPLOYEE bernama James Wong

-- Query untuk problem a:
SELECT Pno
FROM EMPLOYEE, WORKS_ON
WHERE Essn = Ssn AND Fname = 'James' AND Lname = 'Borg'; 

-- Query untuk problem b:
SELECT DISTINCT E.Fname, E.Ssn
FROM EMPLOYEE E, WORKS_ON PE
WHERE PE.Essn = E.Ssn;

-- Query untuk problem c:
SELECT Fname, Ssn
FROM EMPLOYEE
WHERE Fname = 'James' AND Lname = 'Borg'

-- STEP 2: Tentukan outer query dan nested query-nya. Gunakan EXCEPT untuk mengeliminasi tuple yang tidak diinginkan

-- SOLUSI:

SELECT DISTINCT E.Fname, E.Ssn
FROM EMPLOYEE E, WORKS_ON PE
WHERE PE.Essn = E.Ssn AND PE.Pno IN (
	SELECT Pno
	FROM EMPLOYEE B, WORKS_ON PB
	WHERE PB.Essn = B.Ssn AND B.Fname = 'James' AND Lname = 'Borg')
EXCEPT
(SELECT Fname, Ssn
FROM EMPLOYEE
WHERE Fname = 'James' AND Lname = 'Borg');



-- Points to ponder:
-- Dalam dunia per-SQL-an, banyak alternatif solusi = banyak cara untuk satu tujuan (suatu luaran query yang spesifik)
-- seperti kata pepatah Latin: Mīlle viae dūcunt hominēs per saecula Rōmam (meaning.: "all roads lead to rome")
-- Diantara sekian alternatif, apakah ada alternatif query yang terbaik untuk meretrieve suatu data?