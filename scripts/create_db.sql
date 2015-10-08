create table usr (
	id			serial PRIMARY KEY,
	alias			varchar(60) NOT NULL,
	name			varchar(120) NOT NULL,
	email			varchar(120) NOT NULL,
	password		varchar(200) NOT NULL,
	createdby		integer NOT NULL references usr(id),
	updatedby		integer NOT NULL references usr(id),
	created			timestamp without time zone NOT NULL DEFAULT now(),
	updated			timestamp without time zone NOT NULL DEFAULT now(),
	active			boolean default true
);

insert into usr (alias, name, email, password, createdby, updatedby, created, updated, active) 
values ('admin', 'Administrador', 'test@test.com', '1234', 1, 1, now(), now(), true);