
CREATE TABLE public."usuarios" (
	id_user serial4 NOT NULL,
	id_funcionario int not null,
	usuario varchar(50) NOT NULL,
	email varchar(255) NOT NULL,
	password_hash varchar(255) NOT NULL,
	activo bool NULL,
	verificado bool NULL,
	intento_login_fallido int4 DEFAULT 0 NULL,
	refresh_token varchar(255) NULL,
	refresh_token_expires_at timestamptz NULL,
	fecha_creacion timestamptz NULL,
	fecha_actualizacion timestamptz NULL,
	ultimo_login timestamptz NULL,
	ultimo_ip_ingreso inet NULL,
	
	CONSTRAINT email_check CHECK (((email)::text ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'::text)),
	CONSTRAINT user_email_key UNIQUE (email),
	CONSTRAINT user_pk PRIMARY KEY (id_user),
	CONSTRAINT user_usuario_key UNIQUE (usuario),
	CONSTRAINT usuario_check CHECK ((char_length((usuario)::text) >= 3))
);
CREATE INDEX idx_users_email ON public."usuarios" USING btree (email);
CREATE INDEX idx_users_refresh_token ON public."usuarios" USING btree (refresh_token) WHERE (refresh_token IS NOT NULL);
CREATE INDEX idx_users_usuario ON public."usuarios" USING btree (usuario);

INSERT INTO public.usuarios  (
    usuario, 
    id_funcionario,
    email, 
    password_hash, 
    activo,
    refresh_token
) VALUES (
    'testuser',
    1,
    'test@example.com',
    '$2a$10$N.zmdr9k7uOCQb376NoMeuTnXN5m8YvZJZs8K5K5K5K5K5K5K5K5K',  -- BCrypt de "password123"
    true,
    'rt_abc123xyz'  -- Refresh token de ejemplo
);

select * from public.usuarios
---PARA TALBA FUNCIONARIO
CREATE TABLE public."funcionarios" (
	id_funcionario serial4 NOT NULL,
	id_rol int not null,
	nombres varchar(80) NOT NULL,
	apellido_paterno varchar(50) NOT NULL,
	apellido_materno varchar(50) NULL,
	numero_ci varchar(20) not null,
	direccion varchar (250) null,
	numero_celular varchar(30) null,
	fecha_nacimiento date null,
	estado char(1) NULL,
	fecha_creacion timestamptz NULL,
	fecha_actualizacion timestamptz NULL
	
);
CREATE INDEX idx_funcionarios_id_funcionario ON public."funcionarios" USING btree (id_funcionario);
CREATE INDEX idx_funcionarios_numero_ci ON public."funcionarios" USING btree (numero_ci);
CREATE INDEX idx_funcionarios_nombres ON public."funcionarios" USING btree (nombres);

insert into public.funcionarios (id_rol,nombres,apellido_paterno,numero_ci,estado)
values(1,'CARLOS', 'MEDRANO',8310267,'A')
select * from public.funcionarios
----PARA ROLES
CREATE TABLE public."roles" (
	id_rol serial4 NOT NULL,
	area_rol varchar(50) not null,
	descripcion_rol varchar(80) NOT NULL,
	estado char(1) NULL,
	fecha_creacion timestamptz NULL,
	fecha_actualizacion timestamptz NULL
	
);
CREATE INDEX idx_roles_id_rol ON public."roles" USING btree (id_rol);
insert into public.roles(area_rol,descripcion_rol,estado)
values('ADMINISTRACION','CAJA', 'A')
select * from public.roles
----
select * from public.funcionarios;
select * from public.usuarios;
select * from public.roles;
