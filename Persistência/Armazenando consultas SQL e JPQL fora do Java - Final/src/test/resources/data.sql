INSERT INTO "customer" (id, name) VALUES (1, 'Alex');
INSERT INTO "customer" (id, name) VALUES (2, 'Thiago');
INSERT INTO "customer" (id, name) VALUES (3, 'Maria');

INSERT INTO "order" (id, created_at, status, total, customer_id) VALUES (1, CURRENT_TIMESTAMP(), 'PAID', 100, 1);
INSERT INTO "order" (id, created_at, status, total, customer_id) VALUES (2, CURRENT_TIMESTAMP(), 'PAID', 55, 1);
INSERT INTO "order" (id, created_at, status, total, customer_id) VALUES (3, CURRENT_TIMESTAMP(), 'PAID', 31, 1);
INSERT INTO "order" (id, created_at, status, total, customer_id) VALUES (4, CURRENT_TIMESTAMP(), 'CANCELLED', 64, 1);

INSERT INTO "order" (id, created_at, status, total, customer_id) VALUES (5, CURRENT_TIMESTAMP(), 'PAID', 14, 2);
INSERT INTO "order" (id, created_at, status, total, customer_id) VALUES (6, CURRENT_TIMESTAMP(), 'CANCELLED', 55, 2);
INSERT INTO "order" (id, created_at, status, total, customer_id) VALUES (7, CURRENT_TIMESTAMP(), 'CANCELLED', 11, 2);

INSERT INTO "order" (id, created_at, status, total, customer_id) VALUES (8, CURRENT_TIMESTAMP(), 'PENDING', 12, 3);
INSERT INTO "order" (id, created_at, status, total, customer_id) VALUES (9, CURRENT_TIMESTAMP(), 'PENDING', 12, 3);