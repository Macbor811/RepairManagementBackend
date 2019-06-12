INSERT INTO public.address ( city, number, post_code, street) VALUES ('Gliwice', '1', '13-213', 'Uliczna');
INSERT INTO public.address ( city, number, post_code, street) VALUES ( 'Katowice', '2', '36-045', 'Wiertnicza');
INSERT INTO public.address ( city, number, post_code, street) VALUES ( 'Warszawa', '3', '33-194', 'Wyzwolenia');
INSERT INTO public.address ( city, number, post_code, street) VALUES ( 'Gdynia', '5/2', '51-345', 'Tytusa Chałubińskiego');
INSERT INTO public.address ( city, number, post_code, street) VALUES ( 'Poznań', '13A/9', '72-346', 'Jagiellońska');
INSERT INTO public.address ( city, number, post_code, street) VALUES ( 'Gdańsk', '7', '27-754', 'Stefana Starzyńskiego');
INSERT INTO public.address ( city, number, post_code, street) VALUES ( 'Stargard', '76', '43-656', 'Pierwszej Brygady');
INSERT INTO public.address ( city, number, post_code, street) VALUES ( 'Kraków', '77/7', '46-873', 'Mogilska');
INSERT INTO public.address ( city, number, post_code, street) VALUES ( 'Kołobrzeg', '1C/4', '35-873', 'Bolesława Krzywoustego');
INSERT INTO public.address ( city, number, post_code, street) VALUES ( 'Toruń', '9', '21-65', 'Grudziądzka');

INSERT INTO public.customer ( first_name, last_name, phone_number, address_id) VALUES ( 'Adam', 'Kowalski', '111111111', 1);
INSERT INTO public.customer ( first_name, last_name, phone_number, address_id) VALUES ( 'Jan', 'Nowak', '222222222', 2);
INSERT INTO public.customer ( first_name, last_name, phone_number, address_id) VALUES ( 'Adam', 'Mickiewicz', '333333333', 3);
INSERT INTO public.customer ( first_name, last_name, phone_number, address_id) VALUES ( 'Henryk', 'Sienkiewicz', '444444444', 4);
INSERT INTO public.customer ( first_name, last_name, phone_number, address_id) VALUES ( 'Jan', 'Matejko', '555555555', 5);

INSERT INTO public.employee ( first_name, last_name, password, phone_number, role, username, address_id) VALUES ( 'Zygmunt', 'Waza', 'password4', '999999999', 'MAN', 'username4', 9);
INSERT INTO public.employee ( first_name, last_name, password, phone_number, role, username, address_id) VALUES ( 'Tadeusz', 'Soplica', 'password2', '777777777', 'WRK', 'username2', 7);
INSERT INTO public.employee ( first_name, last_name, password, phone_number, role, username, address_id) VALUES ( 'Gerwazy', 'Rębajło', 'password3', '888888888', 'MAN', 'username3', 8);
INSERT INTO public.employee ( first_name, last_name, password, phone_number, role, username, address_id) VALUES ( 'Jacek', 'Soplica', 'password1', '666666666', 'ADM', 'username1', 6);
INSERT INTO public.employee ( first_name, last_name, password, phone_number, role, username, address_id) VALUES ( 'Fryderyk', 'Chopin', 'password5', '123456789', 'WRK', 'username5', 10);

INSERT INTO public.item_type (id, type) VALUES ('Bentley', 'Bentley');
INSERT INTO public.item_type (id, type) VALUES ('Ford', 'Ford');
INSERT INTO public.item_type (id, type) VALUES ('BMW', 'BMW');
INSERT INTO public.item_type (id, type) VALUES ('Ferrari', 'Ferrari');

INSERT INTO public.item ( name, item_type_id, owner_id) VALUES ( 'Continental GT', 'Bentley', 1);
INSERT INTO public.item ( name, item_type_id, owner_id) VALUES ( 'Mustang GT 2019', 'Ford', 2);
INSERT INTO public.item ( name, item_type_id, owner_id) VALUES ( 'X5 2019', 'BMW', 3);
INSERT INTO public.item ( name, item_type_id, owner_id) VALUES ( 'F8 Tributo', 'Ferrari', 4);

INSERT INTO public.request ( description, end_date, register_date, result, status, item_id, manager_id) VALUES ( 'RequestDescription1', null, '2019-05-26 15:44:17.264000', null, 'OPN', 1, 1);
INSERT INTO public.request ( description, end_date, register_date, result, status, item_id, manager_id) VALUES ( 'RequestDescription2', null, '2019-05-26 15:44:20.504000', null, 'PRO', 2, 2);
INSERT INTO public.request ( description, end_date, register_date, result, status, item_id, manager_id) VALUES ( 'RequestDescription3', '2019-05-31 20:44:34.109000', '2019-05-26 15:44:21.304000', 'ResultCAN', 'CAN', 3, 3);
INSERT INTO public.request ( description, end_date, register_date, result, status, item_id, manager_id) VALUES ( 'RequestDescription4', '2019-05-31 19:44:25.155000', '2019-05-26 15:44:21.961000', 'ResultFIN', 'FIN', 4, 3);

INSERT INTO public.activity_type ( type) VALUES ( 'Inspekcja');
INSERT INTO public.activity_type ( type) VALUES ( 'Naprawa');
INSERT INTO public.activity_type ( type) VALUES ( 'Wymiana częsci');
INSERT INTO public.activity_type ( type) VALUES ( 'Inne');
INSERT INTO public.activity_type ( type) VALUES ( 'Zakończenie');

INSERT INTO public.activity ( description, end_date, register_date, result, sequence_num, status, type_id, request_id, worker_id) VALUES ( 'ActivityDescription1', null, '2019-05-26 16:04:05.844000', '', 1, 'OPN', 1, 1, 2);
INSERT INTO public.activity ( description, end_date, register_date, result, sequence_num, status, type_id, request_id, worker_id) VALUES ( 'ActivityDescription2', '2019-05-26 19:19:26.285000', '2019-05-26 17:19:17.292000', 'Result1', 2, 'FIN', 1, 1, 2);
INSERT INTO public.activity ( description, end_date, register_date, result, sequence_num, status, type_id, request_id, worker_id) VALUES ( 'ActivityDescription3', null, '2019-05-26 18:21:33.478000', '', 3, 'OPN', 2, 1, 2);
INSERT INTO public.activity ( description, end_date, register_date, result, sequence_num, status, type_id, request_id, worker_id) VALUES ( 'ActivityDescription4', '2019-05-26 20:22:22.249000', '2019-05-26 19:21:37.042000', 'Result2', 4, 'FIN', 2, 1, 2);
INSERT INTO public.activity ( description, end_date, register_date, result, sequence_num, status, type_id, request_id, worker_id) VALUES ( 'ActivityDescription5', null, '2019-05-26 21:21:39.354000', '', 5, 'OPN', 4, 1, 5);
INSERT INTO public.activity ( description, end_date, register_date, result, sequence_num, status, type_id, request_id, worker_id) VALUES ( 'ActivityDescription6', '2019-05-26 23:22:41.553000', '2019-05-26 22:21:49.513000', 'Result3', 6, 'FIN', 4, 1, 5);
INSERT INTO public.activity ( description, end_date, register_date, result, sequence_num, status, type_id, request_id, worker_id) VALUES ( 'ActivityDescription7', null, '2019-05-26 16:04:05.844000', '', 7, 'OPN', 1, 2, 5);
INSERT INTO public.activity ( description, end_date, register_date, result, sequence_num, status, type_id, request_id, worker_id) VALUES ( 'ActivityDescription8', '2019-05-26 19:19:26.285000', '2019-05-26 17:19:17.292000', 'Result4', 8, 'FIN', 1, 2, 5);
INSERT INTO public.activity ( description, end_date, register_date, result, sequence_num, status, type_id, request_id, worker_id) VALUES ( 'ActivityDescription9', null, '2019-05-26 18:21:33.478000', '', 9, 'OPN', 2, 2, 5);
INSERT INTO public.activity ( description, end_date, register_date, result, sequence_num, status, type_id, request_id, worker_id) VALUES ( 'ActivityDescription10', '2019-05-26 20:22:22.249000', '2019-05-26 19:21:37.042000', 'Result5', 10, 'FIN', 2, 2, 5);
INSERT INTO public.activity ( description, end_date, register_date, result, sequence_num, status, type_id, request_id, worker_id) VALUES ( 'ActivityDescription11', null, '2019-05-26 16:04:05.844000', '', 11, 'OPN', 1, 3, 5);
INSERT INTO public.activity ( description, end_date, register_date, result, sequence_num, status, type_id, request_id, worker_id) VALUES ( 'ActivityDescription12', '2019-05-26 19:19:26.285000', '2019-05-26 17:19:17.292000', 'Result6', 12, 'FIN', 1, 3, 5);
INSERT INTO public.activity ( description, end_date, register_date, result, sequence_num, status, type_id, request_id, worker_id) VALUES ( 'ActivityDescription13', null, '2019-05-26 18:21:33.478000', '', 13, 'OPN', 4, 3, 5);
INSERT INTO public.activity ( description, end_date, register_date, result, sequence_num, status, type_id, request_id, worker_id) VALUES ( 'ActivityDescription15', null, '2019-05-26 16:04:05.844000', '', 11, 'OPN', 1, 4, 2);
INSERT INTO public.activity ( description, end_date, register_date, result, sequence_num, status, type_id, request_id, worker_id) VALUES ( 'ActivityDescription14', '2019-05-26 20:22:22.249000', '2019-05-26 19:21:37.042000', 'Result7', 14, 'FIN', 4, 3, 5);
INSERT INTO public.activity ( description, end_date, register_date, result, sequence_num, status, type_id, request_id, worker_id) VALUES ( 'ActivityDescription16', '2019-05-26 19:19:26.285000', '2019-05-26 17:19:17.292000', 'Result8', 12, 'FIN', 1, 4, 2);
INSERT INTO public.activity ( description, end_date, register_date, result, sequence_num, status, type_id, request_id, worker_id) VALUES ( 'ActivityDescription17', null, '2019-05-26 18:21:33.478000', '', 13, 'OPN', 4, 4, 2);
INSERT INTO public.activity ( description, end_date, register_date, result, sequence_num, status, type_id, request_id, worker_id) VALUES ( 'ActivityDescription18', '2019-05-26 20:22:22.249000', '2019-05-26 19:21:37.042000', 'Result9', 14, 'FIN', 4, 4, 2);
