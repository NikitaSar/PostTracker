INSERT INTO post_office(postal_code, name, address) VALUES
('123456', 'name 1', 'address 1'),
('123457', 'name 2', 'address 2'),
('123458', 'name 3', 'address 3');

INSERT INTO postal_item_status (name) VALUES
('Status 1'),
('Status 2'),
('Status 3');

INSERT INTO postal_item_type (name) VALUES
('Type 1'),
('Type 2'),
('Type 3');

INSERT INTO postal_item (postal_item_type_id, post_office_id, address, name) VALUES
(
    (SELECT id FROM postal_item_type WHERE name LIKE 'Type 2'),
    (SELECT id FROM post_office WHERE postal_code LIKE '123456'),
    'Recipient address',
    'Recipient name'
);

INSERT INTO tracking (date, postal_item_id, postal_item_status_id) VALUES
(
    (TIMESTAMP '2022-02-21 10:00:00'),
    (SELECT MAX(id) FROM postal_item),
    (SELECT id FROM postal_item_status WHERE name LIKE 'Status 1')
);

