
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

DROP SEQUENCE grupo_seq;
DROP SEQUENCE matricula_seq;

create table carrera(
codigo varchar2(15) not null,
nombre varchar2(50) not null,
titulo varchar2(50)
)tablespace MATRICULA_DATOS;

create table curso(
codigo varchar2(15) not null,
codCarrera varchar2(15) not null,
cicloNumero number(1,0) not null,
cicloAnio date not null,
nombre varchar2(50) not null,
creditos number(2,0) not null,
horas number(2,1) not null
)tablespace MATRICULA_DATOS;

create table ciclo(
annio date not null,
numero number(1,0) not null,
inicioD date ,
finalD date
)tablespace MATRICULA_DATOS;

create table grupo(
codigo number not null,
numGrupo number not null,
codCurso varchar2(15) not null,
numCiclo number(1,0) not null,
annioCiclo date not null,
idProfesor varchar2(15) not null,
horario varchar2(30) not null
)tablespace MATRICULA_DATOS;

create table usuario(
cedula varchar2(15) not null,
clave varchar2(50) not null,
email varchar2(30) not null
)tablespace MATRICULA_DATOS;

create table profesor(
cedula varchar2(15) not null,
nombre varchar2(50) not null,
telefono varchar2(30)
)tablespace MATRICULA_DATOS;

create table alumno(
cedula varchar2(15) not null,
nombre varchar2(50) not null,
telefono varchar2(30),
nacimiento date not null,
codCarrera varchar2(15) not null
)tablespace MATRICULA_DATOS;


create table matriculaGrupo(
codigo number not null,
idAlumno varchar2(15) not null,
codGrupo number not null
)tablespace MATRICULA_DATOS;


alter table carrera add constraint carrera_pk primary key (codigo) using index tablespace MATRICULA_INDICES;

alter table ciclo add constraint ciclo_pk primary key (annio,numero) using index tablespace MATRICULA_INDICES;


alter table usuario add constraint usuario_pk primary key (cedula) using index tablespace MATRICULA_INDICES;
alter table usuario add constraint UK_usuario unique (email); 

alter table curso add constraint curso_pk primary key (codigo) using index tablespace MATRICULA_INDICES;
alter table curso add foreign key (codCarrera) references carrera(codigo);
--alter table curso add foreign key (cicloNumero) references ciclo(numero);
alter table curso add foreign key (cicloAnio,cicloNumero) references ciclo(annio,numero);


alter table profesor add foreign key (cedula) references usuario(cedula);
alter table profesor add constraint profesor_pk primary key (cedula) using index tablespace MATRICULA_INDICES;

alter table alumno add foreign key (cedula) references usuario(cedula);
alter table alumno add constraint alumno_pk primary key (cedula) using index tablespace MATRICULA_INDICES;

alter table grupo add constraint grupo_pk primary key (codigo) using index tablespace MATRICULA_INDICES;
alter table grupo add foreign key (codCurso) references curso(codigo);
alter table grupo add foreign key (idProfesor) references profesor(cedula);
--alter table grupo add foreign key (numCiclo) references ciclo(numero);
alter table grupo add foreign key (annioCiclo,numCiclo) references ciclo(annio,numero);
alter table grupo add constraint UK_grupo unique (numGrupo,codCurso,numCiclo,annioCiclo);

alter table matriculaGrupo add constraint matricula_pk primary key (codigo) using index tablespace MATRICULA_INDICES;
alter table matriculaGrupo add foreign key (idAlumno) references alumno(cedula);
alter table matriculaGrupo add foreign key (codGrupo) references grupo(codigo);

CREATE SEQUENCE grupo_seq START WITH 1 INCREMENT BY  1;
CREATE SEQUENCE matricula_seq START WITH 1 INCREMENT BY  1;



create or replace procedure inserta_carrera(PCod in varchar2,Pnombre in varchar2,Ptitulo in varchar2) is
begin
    insert into carrera(codigo,nombre,titulo) values (PCod,Pnombre,Ptitulo);
	commit;
end inserta_carrera;
/

create or replace procedure inserta_ciclo(Pannio in date,Pnumero in number,PinicioD in date,Pfinal in date) is
begin
    insert into ciclo(annio,numero,inicioD,finalD) values (Pannio,Pnumero,PinicioD,Pfinal);
	commit;
end inserta_ciclo;
/

create or replace procedure inserta_curso(PCod in varchar2,PcodCarrera in varchar2,PcicloNum in number,PcicloAnn in varchar2,Pnombre in varchar2,Pcreditos in number,Phoras in number) is
begin
    insert into curso(codigo,codCarrera,cicloNumero,cicloAnio,nombre,creditos,horas) values (PCod,PcodCarrera,PcicloNum,TO_DATE(PcicloAnn, 'YEAR'),Pnombre,Pcreditos,Phoras);
	commit;
end inserta_curso;
/



create or replace procedure inserta_usuario(PCed in varchar2,Pclave in varchar2,Pemail in varchar2) is
begin
    insert into usuario(cedula,clave,email) values (PCed,Pclave,Pemail);
	commit;
end inserta_usuario;
/





create or replace procedure inserta_profesor(PCed in varchar2,Pnombre in varchar2,Ptel in varchar2) is
begin
    insert into profesor(cedula,nombre,telefono) values (PCed,Pnombre,Ptel);
	commit;
end inserta_profesor;
/



create or replace procedure inserta_alumno(PCed in varchar2,Pnombre in varchar2,Ptel in varchar2,Pnac in date,PcodCarr in varchar2) is
begin
    insert into alumno(cedula,nombre,telefono,nacimiento,codCarrera) values (PCed,Pnombre,Ptel,Pnac,PcodCarr);
	commit;
end inserta_alumno;
/



create or replace procedure inserta_grupo(Pnum in number,PCodCurso in varchar2,PnumCiclo in number,PannioCiclo in date,PidProf in varchar2,Phorario in varchar2) is
begin
    insert into grupo(codigo,numGrupo,codCurso,numCiclo,annioCiclo,idProfesor,horario) values (grupo_seq.NEXTVAL ,Pnum,PCodCurso,PnumCiclo,PannioCiclo,PidProf,Phorario);
	commit;
end inserta_grupo;
/


create or replace procedure inserta_matricula_grupo(PIdAlum in varchar2,PIDGrup in number) is
begin
    insert into matriculaGrupo(codigo,idAlumno,codGrupo) values (matricula_seq.NEXTVAL,PIdAlum,PIDGrup);
	commit;
end inserta_matricula_grupo;
/



