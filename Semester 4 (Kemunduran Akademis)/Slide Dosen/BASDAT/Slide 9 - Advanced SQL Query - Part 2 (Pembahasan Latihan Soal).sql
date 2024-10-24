/*
PEMBAHASAN LATIHAN BASIS DATA SLIDE 9 (PART 2): ADVANCED SQL

Kontributor:
- Lintang Matahari H.

Versi: 2.00/26-04-2021
*/

/*1. Tampilkan rata-rata salary pada pegawai perempuan */

-- Ide: Gunakan AVG() dan condition Sex = 'F'

-- SOLUSI:

SELECT AVG(Salary)
FROM EMPLOYEE
WHERE Sex = 'F';

--------------------------------------------------------------------------------------------------------

/*2. Tampilkan jumlah project yang dikerjakan oleh setiap pegawai. */

-- Asumsi: Untuk setiap EMPLOYEE ditampilkan Fname, Lname, Ssn, dan Jumlah Proyeknya serta diurutkan
-- dari terbesar ke terkecil

-- Ide: Gunakan COUNT() untuk menghitung PROJECT (melalui menghitung Pno), OUTER JOIN untuk meretrieve EMPLOYEE
-- yang tidak terlibat pada satupun proyek dan GROUP BY Ssn (hitung per pegawai). Mengurutkan luaran query menggunakan ORDER BY dan DESC.

-- SOLUSI:

SELECT Fname, Lname, Ssn, COUNT(Pno) AS Number_of_projects
FROM EMPLOYEE LEFT OUTER JOIN WORKS_ON ON Essn = Ssn
GROUP BY Ssn
ORDER BY Number_of_projects DESC;

--------------------------------------------------------------------------------------------------------

/*3. Tampilkan nama departemen dan jumlah project yang ditangani department tersebut. */

-- Asumsi: Tuples luaran query diurutkan dari jumlah proyek terbesar ke terkecil

-- Ide: Gunakan COUNT() untuk menghitung PROJECT (melalui menghitung Pnumber), OUTER JOIN untuk meretrieve DEPARTMENT
-- yang tidak menangani satupun proyek dan GROUP BY Dname (hitung per DEPARTMENT). Mengurutkan luaran query menggunakan ORDER BY dan DESC.

-- SOLUSI:

SELECT Dname, Count(Pnumber) AS Number_of_projects
FROM DEPARTMENT LEFT OUTER JOIN PROJECT ON Dnumber = Dnum
GROUP BY Dname
ORDER BY Number_of_projects DESC;

--------------------------------------------------------------------------------------------------------

/*4. Tampilkan nama depan semua pegawai yang bekerja di sebuah department yang memiliki
pegawai dengan salary tertinggi. */

-- STEP 1: Atur strategi, pecah problem ke yang lebih sederhana.
-- Idenya kita bisa memeriksa apakah Dno seorang EMPLOYEE ada pada list Dnumber DEPARTMENT yang memiliki
-- Employee dengan Salary tertinggi (dicek dengan MAX). Dalam hal ini kita bisa mencoba memecah problem tersebut menjadi:
-- a.) Menseleksi Fname semua EMPLOYEE
-- b.) Menseleksi Dno dari DEPARTMENT dengan EMPLOYEE yang memiliki Salary tertinggi

-- Query untuk problem a:
SELECT Fname
FROM EMPLOYEE;

-- Query untuk problem b:
SELECT Dno
FROM DEPARTMENT, EMPLOYEE
WHERE Dno = Dnumber AND Salary = (
	SELECT MAX(Salary)
	FROM EMPLOYEE
);

-- STEP 2: Gunakan clause IN untuk mengecek pakah Dno seorang EMPLOYEE ada pada list Dnumber DEPARTMENT yang memiliki
-- Employee dengan Salary tertinggi. Tentukan outer dan nested query-nya.

-- SOLUSI:

SELECT Fname
FROM EMPLOYEE
WHERE Dno IN (
	SELECT Dno
	FROM DEPARTMENT, EMPLOYEE
	WHERE Dno = Dnumber AND Salary = (
		SELECT MAX(Salary)
		FROM EMPLOYEE
	)
);

--------------------------------------------------------------------------------------------------------

/*5. Untuk setiap department, tampilkan nama departemen dan rata-rata salary pegawai yang
bekerja pada department tersebut. */

-- Idenya kita dapat menggunakan AVG(Salary) dan GROUP BY Dnumber untuk menghitung rerata salary pegawai yang bekerja
-- di departemen tersebut

-- SOLUSI:

SELECT Dname, AVG(Salary)
FROM EMPLOYEE, DEPARTMENT
WHERE Dno = Dnumber 
GROUP BY Dnumber;

--------------------------------------------------------------------------------------------------------

/*6. Untuk setiap department yang rata-rata salary pegawainya kurang dari 50000, tampilkan
nama department beserta jumlah pegawainya.*/

-- Idenya kita bisa menggunakan HAVING clause untuk mengecek rerata gaji pegawai di setiap departemen (gunakan AVG dan GROUP BY)
-- Jumlah pegawai dapat ditampilkan dengan Count(Ssn)

-- SOLUSI:

SELECT Dname, Count(Ssn)
FROM EMPLOYEE, DEPARTMENT
WHERE Dno = Dnumber 
GROUP BY Dnumber HAVING AVG(Salary) < 50000;

--------------------------------------------------------------------------------------------------------

/*7. Untuk setiap supervisor, tampilkan nama depan supervisor dan akumulasi jumlah jam kerja
employee yang disupervisinya. Tampilkan hanya yang akumulasi jumlah jam kerjanya
employee lebih dari 50 jam. */

-- Ide: Gunakan SUM() untuk menghitung akumulasi jam kerja (melalui menghitung Hours), JOIN untuk meretrieve Hours setiap EMPLOYEE
-- yang disupervisi seorang supervisor. 

-- SOLUSI:

SELECT S.Fname, SUM(Hours)
FROM EMPLOYEE E, EMPLOYEE S, WORKS_ON
WHERE S.Ssn = E.Super_ssn AND E.Ssn = Essn
GROUP BY S.Fname HAVING Hours > 50;

--------------------------------------------------------------------------------------------------------

/*8. Buatlah sebuah view bernama EMPLOYEE_DEPENDENT yang berisi daftar nama depan
pegawai yang memiliki dependent dan jumlah dependant yang dimilikinya. */

CREATE VIEW EMPLOYEE_DEPENDENT AS 
SELECT Fname, Count(Dependent_name)
FROM EMPLOYEE, DEPENDENT
WHERE Ssn = Essn
GROUP BY Fname;
