create schema if not exists db_schema;

create table db_schema.t_function(
    id serial primary key,
    c_name_function varchar(255),
    c_count integer check (c_count>=2),
    c_x_from double precision,
    c_x_to double precision

);
create table db_schema.t_point(
    id serial primary key,
    function_id integer references db_schema.t_function(id),
     c_x_value double precision,
     c_y_value double precision,
);