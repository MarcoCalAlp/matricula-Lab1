
conn system/root

drop user matricula cascade;


DROP TABLESPACE MATRICULA_DATOS INCLUDING CONTENTS AND DATAFILES;
DROP TABLESPACE MATRICULA_INDICES INCLUDING CONTENTS AND DATAFILES;


PROMPT ====>Creamos el tablespace para tablas
CREATE TABLESPACE MATRICULA_DATOS datafile 'C:\oraclexe\app\oracle\oradata\XE\MATRICULA_DATOS.DBF' SIZE 10M REUSE AUTOEXTEND ON;
PROMPT ====>Creamos el tablespace para Indices
CREATE TABLESPACE MATRICULA_INDICES datafile 'C:\oraclexe\app\oracle\oradata\XE\MATRICULA_INDICES.DBF' SIZE 10M REUSE AUTOEXTEND ON;

PROMPT ====>Creamos usuario matricula
create user matricula identified by matricula123;


PROMPT ====>Damos permisos a matricula
grant create session to matricula;
grant create table to matricula;
grant create procedure to matricula;
grant create view to matricula;
GRANT UNLIMITED TABLESPACE TO matricula;
grant create sequence to matricula;
PROMPT ====>Conectamos con matricula
conn matricula/matricula123

--====================================================

create table carrera(
codigo varchar2(15) not null,
nombre varchar2(50) not null,
titulo varchar2(50)
)tablespace MATRICULA_DATOS;

CREATE OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/


PROMPT Restricciones Tabla CARRERA
alter table carrera add constraint carrera_pk primary key (codigo) using index tablespace MATRICULA_INDICES;

create or replace procedure inserta_carrera(PCod in carrera.codigo%TYPE,Pnombre in carrera.nombre%TYPE,Ptitulo in carrera.titulo%TYPE) is
begin
    insert into carrera(codigo,nombre,titulo) values (PCod,Pnombre,Ptitulo);
	commit;
end inserta_carrera;
/

create or replace procedure actualiza_carrera(PCod in carrera.codigo%TYPE,Pnombre in carrera.nombre%TYPE,Ptitulo in carrera.titulo%TYPE) is
begin
    update carrera set nombre = Pnombre ,titulo = Ptitulo where codigo = PCod;
	commit;
end actualiza_carrera;
/

CREATE OR REPLACE FUNCTION carrerasXnom(Pnombre IN carrera.nombre%TYPE)
RETURN Types.ref_cursor 
AS 
        carrera_cursor types.ref_cursor; 
BEGIN 
  OPEN carrera_cursor FOR 
       SELECT * FROM MATRICULA.carrera WHERE nombre=Pnombre; 
RETURN carrera_cursor; 
END;
/

CREATE OR REPLACE FUNCTION carrerasXcod(Pcodigo IN carrera.codigo%TYPE)
RETURN Types.ref_cursor 
AS 
        carrera_cursor types.ref_cursor; 
BEGIN 
  OPEN carrera_cursor FOR 
       SELECT * FROM MATRICULA.carrera WHERE codigo=Pcodigo; 
RETURN carrera_cursor; 
END;
/


--====================================================
create table ciclo(
annio number(4,0) not null,
numero number(1,0) not null,
inicioD date ,
finalD date
)tablespace MATRICULA_DATOS;


CREATE OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/


PROMPT Restricciones Tabla CICLO
alter table ciclo add constraint ciclo_pk primary key (annio,numero) using index tablespace MATRICULA_INDICES;

create or replace procedure inserta_ciclo(Pannio in  ciclo.annio%TYPE,Pnumero in ciclo.numero%TYPE,PinicioD in varchar2,Pfinal in varchar2) is
begin
    insert into ciclo(annio,numero,inicioD,finalD) values (Pannio,Pnumero,TO_DATE(PinicioD, 'dd/mm/yyyy'),TO_DATE(Pfinal, 'dd/mm/yyyy'));
	commit;
end inserta_ciclo;
/

create or replace procedure actualiza_ciclo(Pannio in ciclo.annio%TYPE,Pnumero in ciclo.numero%TYPE,PinicioD in varchar2,Pfinal in varchar2) is
begin
    update ciclo set inicioD = TO_DATE(PinicioD, 'dd/mm/yyyy') ,finalD = TO_DATE(Pfinal, 'dd/mm/yyyy') where annio = Pannio  and numero = Pnumero ;
	commit;
end actualiza_ciclo;
/


--==============================================

create table usuario(
cedula varchar2(15) not null,
clave varchar2(50) not null,
email varchar2(30) not null
)tablespace MATRICULA_DATOS;

CREATE OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/


PROMPT Restricciones Tabla USUARIO
alter table usuario add constraint usuario_pk primary key (cedula) using index tablespace MATRICULA_INDICES;
alter table usuario add constraint UK_usuario unique (email); 

create or replace procedure inserta_usuario(PCed in usuario.cedula%TYPE,Pclave in usuario.clave%TYPE,Pemail in usuario.email%TYPE) is
begin
    insert into usuario(cedula,clave,email) values (PCed,Pclave,Pemail);
	commit;
end inserta_usuario;
/

create or replace procedure actualiza_usuario(PCed in usuario.cedula%TYPE,Pclave in usuario.clave%TYPE,Pemail in usuario.email%TYPE) is
begin
    update usuario set clave = Pclave ,email = Pemail where cedula = PCed ;
	commit;
end actualiza_usuario;
/

--==============================================

create table curso(
codigo varchar2(15) not null,
codCarrera varchar2(15) not null,
cicloNumero number(1,0) not null,
cicloAnio number(4,0)  not null,
nombre varchar2(50) not null,
creditos number(2,0) not null,
horas number(2,1) not null
)tablespace MATRICULA_DATOS;

CREATE OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/

PROMPT Restricciones Tabla CURSO
alter table curso add constraint curso_pk primary key (codigo) using index tablespace MATRICULA_INDICES;
alter table curso add foreign key (codCarrera) references carrera(codigo);
--alter table curso add foreign key (cicloNumero) references ciclo(numero);
alter table curso add foreign key (cicloAnio,cicloNumero) references ciclo(annio,numero);


create or replace procedure inserta_curso(PCod in curso.codigo%TYPE,PcodCarrera in curso.codCarrera%TYPE,PcicloNum in curso.cicloNumero%TYPE,
PcicloAnn in curso.cicloAnio%TYPE,Pnombre in curso.nombre%TYPE,Pcreditos in curso.creditos%TYPE,Phoras in curso.horas%TYPE) is
begin
    insert into curso(codigo,codCarrera,cicloNumero,cicloAnio,nombre,creditos,horas) values (PCod,PcodCarrera,PcicloNum,PcicloAnn,Pnombre,Pcreditos,Phoras);
	commit;
end inserta_curso;
/

create or replace procedure actualiza_curso(PCod in curso.codigo%TYPE,PcodCarrera in curso.codCarrera%TYPE,PcicloNum in curso.cicloNumero%TYPE,
PcicloAnn in curso.cicloAnio%TYPE,Pnombre in curso.nombre%TYPE,Pcreditos in curso.creditos%TYPE,Phoras in curso.horas%TYPE) is
begin
    update curso set codCarrera = PcodCarrera ,cicloNumero = PcicloNum,
    cicloAnio = PcicloAnn, nombre=Pnombre,creditos=Pcreditos,
    horas =Phoras where codigo = PCod;
	commit;
end actualiza_curso;
/

CREATE OR REPLACE FUNCTION cursosXnom(Pnombre IN curso.nombre%TYPE)
RETURN Types.ref_cursor 
AS 
        curso_cursor types.ref_cursor; 
BEGIN 
  OPEN curso_cursor FOR 
       SELECT * FROM MATRICULA.curso l,MATRICULA.carrera d  WHERE l.nombre=Pnombre and l.codCarrera = d.codigo;
RETURN curso_cursor; 
END;
/


CREATE OR REPLACE FUNCTION cursosXcodigo(Pcod IN curso.codigo%TYPE)
RETURN Types.ref_cursor 
AS 
        curso_cursor types.ref_cursor; 
BEGIN 
  OPEN curso_cursor FOR 
       SELECT * FROM MATRICULA.curso l,MATRICULA.carrera d  WHERE l.codigo=Pcod and l.codCarrera = d.codigo;
RETURN curso_cursor; 
END;
/

CREATE OR REPLACE FUNCTION cursosXcarrera(PcodCarr IN curso.codCarrera%TYPE)
RETURN Types.ref_cursor 
AS 
        curso_cursor types.ref_cursor; 
BEGIN 
  OPEN curso_cursor FOR 
       SELECT * FROM MATRICULA.curso l,MATRICULA.carrera d  WHERE l.codCarrera='INGSYS' and l.codCarrera = d.codigo; 
RETURN curso_cursor; 
END;
/




--=========================================================================

create table profesor(
cedula varchar2(15) not null,
nombre varchar2(50) not null,
telefono varchar2(30)
)tablespace MATRICULA_DATOS;

CREATE OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/


PROMPT Restricciones Tabla PROFESOR
alter table profesor add foreign key (cedula) references usuario(cedula);
alter table profesor add constraint profesor_pk primary key (cedula) using index tablespace MATRICULA_INDICES;

create or replace procedure inserta_profesor(PCed in profesor.cedula%TYPE,Pnombre in profesor.nombre%TYPE,Ptel in profesor.telefono%TYPE) is
begin
    insert into profesor(cedula,nombre,telefono) values (PCed,Pnombre,Ptel);
	commit;
end inserta_profesor;
/

create or replace procedure actualiza_profesor(PCed in profesor.cedula%TYPE,Pnombre in profesor.nombre%TYPE,Ptel in profesor.telefono%TYPE) is
begin
    update profesor set nombre = Pnombre ,telefono = Ptel where cedula = PCed ;
	commit;
end actualiza_profesor;
/

CREATE OR REPLACE FUNCTION profesorXnombre(Pnombre IN profesor.nombre%TYPE)
RETURN Types.ref_cursor 
AS 
        profesor_cursor types.ref_cursor; 
BEGIN 
  OPEN profesor_cursor FOR 
       SELECT * FROM MATRICULA.profesor l,MATRICULA.usuario d  WHERE l.nombre=Pnombre and l.cedula = d.cedula; 
RETURN profesor_cursor; 
END;
/

CREATE OR REPLACE FUNCTION profesorXcedula(Pcedula IN profesor.cedula%TYPE)
RETURN Types.ref_cursor 
AS 
        profesor_cursor types.ref_cursor; 
BEGIN 
  OPEN profesor_cursor FOR 
       SELECT * FROM MATRICULA.profesor l,MATRICULA.usuario d  WHERE l.cedula=Pcedula and l.cedula = d.cedula; 
RETURN profesor_cursor; 
END;
/


--=========================================================================
create table alumno(
cedula varchar2(15) not null,
nombre varchar2(50) not null,
telefono varchar2(30),
nacimiento date not null
)tablespace MATRICULA_DATOS;

CREATE OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/


PROMPT Restricciones Tabla ALUMNO
alter table alumno add foreign key (cedula) references usuario(cedula);
alter table alumno add constraint alumno_pk primary key (cedula) using index tablespace MATRICULA_INDICES;

create or replace procedure inserta_alumno(PCed in alumno.cedula%TYPE,Pnombre in alumno.nombre%TYPE,
Ptel in alumno.telefono%TYPE,Pnac in varchar2) is
begin
    insert into alumno(cedula,nombre,telefono,nacimiento) values (PCed,Pnombre,Ptel,TO_DATE(Pnac,'dd/mm/yyyy'));
	commit;
end inserta_alumno;
/

create or replace procedure actualiza_alumno(PCed in alumno.cedula%TYPE,Pnombre in alumno.nombre%TYPE,
Ptel in alumno.telefono%TYPE,Pnac in varchar2) is
begin
    update  alumno set nombre = Pnombre,telefono = Ptel,nacimiento = TO_DATE(Pnac,'dd/mm/yyyy') where cedula = Pced;
	commit;
end actualiza_alumno;
/



--búsqueda por nombre, cédula y carrera
--=========================================================================

create table grupo(
codigo number not null,
numGrupo number not null,
codCurso varchar2(15) not null,
numCiclo number(1,0) not null,
annioCiclo number(4,0) not null,
idProfesor varchar2(15) not null,
horario varchar2(30) not null
)tablespace MATRICULA_DATOS;

CREATE OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/


PROMPT Restricciones Tabla GRUPO
alter table grupo add constraint grupo_pk primary key (codigo) using index tablespace MATRICULA_INDICES;
alter table grupo add foreign key (codCurso) references curso(codigo);
alter table grupo add foreign key (idProfesor) references profesor(cedula);

alter table grupo add foreign key (annioCiclo,numCiclo) references ciclo(annio,numero);
alter table grupo add constraint UK_grupo unique (numGrupo,codCurso,numCiclo,annioCiclo);

PROMPT Sequence para grupo
CREATE SEQUENCE grupo_seq START WITH 1 INCREMENT BY  1;

create or replace procedure inserta_grupo(Pnum in grupo.numGrupo%TYPE,PCodCurso in grupo.codCurso%TYPE,
PnumCiclo in grupo.numCiclo%TYPE,PannioCiclo grupo.annioCiclo%TYPE,PidProf in grupo.idProfesor%TYPE,Phorario in grupo.horario%TYPE) is
begin
    insert into grupo(codigo,numGrupo,codCurso,numCiclo,annioCiclo,idProfesor,horario) 
    values (grupo_seq.NEXTVAL ,Pnum,PCodCurso,PnumCiclo,PannioCiclo,PidProf,Phorario);
	commit;
end inserta_grupo;
/

create or replace procedure actualiza_grupo(PCod in grupo.codigo%TYPE,Pnum in grupo.numGrupo%TYPE,PCodCurso in grupo.codCurso%TYPE,
PnumCiclo in grupo.numCiclo%TYPE,PannioCiclo in grupo.annioCiclo%TYPE,PidProf in grupo.idProfesor%TYPE,Phorario in grupo.horario%TYPE) is
begin
    update  grupo set numGrupo=Pnum,codCurso = PCodCurso,numCiclo = PnumCiclo ,annioCiclo = PannioCiclo,idProfesor = PidProf,horario = Phorario where codigo = PCod;
	commit;
end actualiza_grupo;
/

--================================================================

create table matriculaCarrera(
idAlu varchar2(15) not null,
idCarr varchar2(15) not null,
fechaInscripcion date
)tablespace MATRICULA_DATOS;

CREATE OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/


PROMPT Restricciones Tabla MATRICULACARRERA
alter table matriculaCarrera add foreign key (idAlu) references alumno(cedula);
alter table matriculaCarrera add foreign key (idCarr) references carrera(codigo);
alter table matriculaCarrera add constraint matricula_carrera_pk primary key (idAlu,idCarr) using index tablespace MATRICULA_INDICES;

create or replace procedure inserta_matricula_carrera(PIdAlum in matriculaCarrera.idAlu%TYPE,
PIdCarr in matriculaCarrera.idCarr%TYPE,PFech in  varchar2) is
begin
    insert into matriculaCarrera(idAlu,idCarr,fechaInscripcion) values (PIdAlum,PIdCarr,TO_DATE(PFech,'dd/mm/yyyy'));
	commit;
end inserta_matricula_carrera;
/

create or replace procedure actualiza_matricula_carrera(PIdAlum in matriculaCarrera.idAlu%TYPE,
PIdCarr in matriculaCarrera.idCarr%TYPE,PFech in  varchar2) is
begin
    update  matriculaCarrera set fechaInscripcion =TO_DATE(PFech,'dd/mm/yyyy') where idAlu=PIdAlum and idCarr = PIdCarr;
	commit;
end actualiza_matricula_carrera;
/

CREATE OR REPLACE FUNCTION alumnoXnombre(Pnombre IN alumno.nombre%TYPE)
RETURN Types.ref_cursor 
AS 
        alumno_cursor types.ref_cursor; 
BEGIN 
  OPEN alumno_cursor FOR 
       SELECT * FROM MATRICULA.matriculaCarrera t,MATRICULA.carrera p, MATRICULA.alumno l,MATRICULA.usuario d 
       WHERE t.idCarr = p.codigo and t.idAlu = l.cedula and l.nombre=Pnombre and l.cedula = d.cedula; 
RETURN alumno_cursor; 
END;
/

CREATE OR REPLACE FUNCTION alumnoXcedula(Pcedula IN alumno.cedula%TYPE)
RETURN Types.ref_cursor 
AS 
        alumno_cursor types.ref_cursor; 
BEGIN 
  OPEN alumno_cursor FOR 
       SELECT * FROM MATRICULA.matriculaCarrera t,MATRICULA.carrera p, MATRICULA.alumno l,MATRICULA.usuario d 
       WHERE t.idCarr = p.codigo and t.idAlu = l.cedula and t.idAlu=Pcedula and l.cedula = d.cedula; 
RETURN alumno_cursor; 
END;
/

CREATE OR REPLACE FUNCTION alumnoXcarr(PcodCarr IN carrera.codigo%TYPE)
RETURN Types.ref_cursor 
AS 
        alumno_cursor types.ref_cursor; 
BEGIN 
  OPEN alumno_cursor FOR 
       SELECT * FROM MATRICULA.matriculaCarrera t,MATRICULA.carrera p, MATRICULA.alumno l,MATRICULA.usuario d 
       WHERE t.idCarr = p.codigo and t.idAlu = l.cedula and t.idCarr=PcodCarr and l.cedula = d.cedula; 
RETURN alumno_cursor; 
END;
/

--Palumno
--========================================================

create table matriculaGrupo(
idAlumno varchar2(15) not null,
codGrupo number not null,
nota number(4,0),
idCarrera varchar2(15) not null
)tablespace MATRICULA_DATOS;

CREATE OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/



PROMPT Restricciones Tabla MATRICULAGRUPO
alter table matriculaGrupo add foreign key (idAlumno) references alumno(cedula);
alter table matriculaGrupo add foreign key (idCarrera) references carrera(codigo);
alter table matriculaGrupo add foreign key (codGrupo) references grupo(codigo);
alter table matriculaGrupo add constraint matricula_grupo_pk primary key (idAlumno,codGrupo) using index tablespace MATRICULA_INDICES;


create or replace procedure inserta_matricula_grupo(PIdAlum in matriculaGrupo.idAlumno%TYPE,
PIDGrup in matriculaGrupo.codGrupo%TYPE,Pnota in  matriculaGrupo.nota%TYPE,PIdCarr in matriculaGrupo.idCarrera%TYPE ) is
begin
    insert into matriculaGrupo(idAlumno,codGrupo,nota,idCarrera) values (PIdAlum,PIDGrup,Pnota,PIdCarr);
	commit;
end inserta_matricula_grupo;
/

create or replace procedure actualiza_matricula_grupo(PIdAlum in matriculaGrupo.idAlumno%TYPE,
PIDGrup in matriculaGrupo.codGrupo%TYPE,Pnota in  matriculaGrupo.nota%TYPE ) is
begin
    update  matriculaGrupo set nota = Pnota where idAlumno=PIdAlum and codGrupo = PIDGrup;
	commit;
end actualiza_matricula_grupo;
/

--================================================================

exec inserta_carrera('INGSYS','Ingieneria en Sistemas','Bachillerato y Diplomado');
exec inserta_carrera('ENSING','Enseñanza del Ingles','Bachillerato y Licenciatura');

exec inserta_ciclo(2018,1,'03/02/2018','06/06/2018');
exec inserta_ciclo(2018,2,'20/06/2018','30/10/2018');

exec inserta_curso('EIF01','INGSYS',1,2018,'FUNDAMENTOS DE INFORMATICA',4,6);

exec inserta_usuario('111111','CLAVE123','marcos@gmail.com');
exec inserta_usuario('222222','CLAVE321','hillary@gmail.com');
exec inserta_usuario('333333','PASS123','vinico@gmail.com'); 
exec inserta_usuario('444444','PASS321','tatiana@gmail.com');


exec inserta_profesor('111111','Marco','88884444');
exec inserta_profesor('222222','Hillary','88889999');

exec inserta_alumno('333333','Vinicio','88887777','30/04/1996');
exec inserta_alumno('444444','Tatiana','88883333','13/04/1998');

exec inserta_grupo(1,'EIF01',1,2018,'111111','L,M:9:30am a 11:40am');
exec inserta_grupo(2,'EIF01',1,2018,'222222','L,M:9:30am a 11:40am');

exec inserta_matricula_carrera('333333','INGSYS','11/02/2016');
exec inserta_matricula_carrera('444444','INGSYS','10/02/2016');


exec inserta_matricula_grupo('333333',1,100,'INGSYS');
exec inserta_matricula_grupo('444444',1,100,'INGSYS');





--select * from matricula.ciclo;





