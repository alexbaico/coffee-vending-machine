INSERT INTO product (id, name, description, price, type) VALUES
  (1, 'BLACK', 'Black', 5.5, 'COFFEE'),
  (2, 'IRISH', 'Irish', 15, 'COFFEE'),
  (3, 'CAPPUCCINO', 'Capuccino', 10, 'COFFEE'),
  (4, 'MOCHA', 'Mocha', 4, 'COFFEE'),
  (5, 'ESPRESSO', 'Espresso', 3, 'COFFEE'),

  (6, 'MILK', 'Leche', 3, 'EXTRA_OPTION'),
  (7, 'CACAO', 'Cacao', 4, 'EXTRA_OPTION'),
  (8, 'CHOCOLATE', 'Chocolate', 5.5, 'EXTRA_OPTION'),
  (9, 'RON', 'Ron', 10, 'EXTRA_OPTION'),
  (10, 'CINNAMON', 'Canela', 6, 'EXTRA_OPTION')
;

INSERT INTO inventory (id, product_id, stock) VALUES
  (1, 1, 6),
  (2, 2, 2),
  (3, 3, 4),
  (4, 4, 5),
  (5, 5, 2),
  (6, 6, 1),
  (7, 7, 1),
  (8, 8, 0),
  (9, 9, 2),
  (10, 10, 7);
