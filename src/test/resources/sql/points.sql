insert into db_schema.t_function(id, c_name_function, c_count, c_x_from, c_x_to)
values (1, 'example_function', 10, 0, 10);

insert into db_schema.t_point(id, function_id, c_x_value, c_y_value)
values  (1, 1, 0, 0),
        (2, 1, 1, 2),
        (3, 1, 4, 8),
        (4, 1, 8, 16);