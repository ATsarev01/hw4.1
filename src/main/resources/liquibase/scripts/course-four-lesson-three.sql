--liquibase formatted sql

--changeset atsarev:1
--precondition-sql-check expectedResult:1 select case when count(i.tablename) = 1 then 0 else 1 end from pg_tables t inner join
--onFail=MARK_RAN
create index idx_students_name on student (name);

--changeset atsarev:2
--precondition-sql-check expectedResult:1 select case when count(i.tablename) = 1 then 0 else 1 end from pg_tables t inner join
--onFail=MARK_RAN
create unique index idx_faculties_name_color on faculty (name, color);

