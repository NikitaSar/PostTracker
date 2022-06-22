INSERT INTO post_office(postal_code, name, address) VALUES
('987654', 'name 1', 'address 1');

INSERT INTO postal_item_status (name) VALUES
('Status 4'),
('Status 5'),
('Status 6');

INSERT INTO postal_item_type (name) VALUES
('Type 7');

INSERT INTO postal_item (postal_item_type_id, post_office_id, address, name) VALUES
(
    (SELECT id FROM postal_item_type WHERE name LIKE 'Type 7'),
    (SELECT id FROM post_office WHERE postal_code LIKE '987654'),
    'Recipient address',
    'Recipient name'
);

INSERT INTO tracking (date, postal_item_id, postal_item_status_id) VALUES
(
    (TIMESTAMP '2022-02-21 10:00:00'),
    (SELECT MAX(id) FROM postal_item),
    (SELECT id FROM postal_item_status WHERE name LIKE 'Status 4')
),
(
    (TIMESTAMP '2022-03-01 14:00:00'),
    (SELECT MAX(id) FROM postal_item),
    (SELECT id FROM postal_item_status WHERE name LIKE 'Status 5')
),
(
    (TIMESTAMP '2022-03-02 08:45:00'),
    (SELECT MAX(id) FROM postal_item),
    (SELECT id FROM postal_item_status WHERE name LIKE 'Status 6')
);