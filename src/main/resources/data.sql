INSERT INTO currencies (id, code) VALUES (1, 'EUR') ON CONFLICT (id) DO NOTHING;
INSERT INTO currencies (id, code) VALUES (2, 'SEK') ON CONFLICT (id) DO NOTHING;
INSERT INTO currencies (id, code) VALUES (3, 'GBP') ON CONFLICT (id) DO NOTHING;
INSERT INTO currencies (id, code) VALUES (4, 'USD') ON CONFLICT (id) DO NOTHING;

insert into customers(id, name) values(1, 'Joh Doe') ON CONFLICT (id) DO NOTHING;
insert into customers(id, name) values(2, 'Jonathan') ON CONFLICT (id) DO NOTHING;