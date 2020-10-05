INSERT INTO `image` (`image_id`, `image1`, `image2`, `image3`, `image4`, `image5`, `image6`, `notes`) VALUES 
('1', '/business/img/iphone11.jpg', NULL, NULL, NULL, NULL, NULL, NULL),
('2', '/business/img/iphone11den.jpg', NULL, NULL, NULL, NULL, NULL, NULL),
('3', '/business/img/iphone8den.jpg', NULL, NULL, NULL, NULL, NULL, NULL),
('4', '/business/img/samsungnote10.jpg', NULL, NULL, NULL, NULL, NULL, NULL),
('5', '/business/img/lgv50den.jpg', NULL, NULL, NULL, NULL, NULL, NULL),
('6', '/business/img/lgg8do.jpg', NULL, NULL, NULL, NULL, NULL, NULL);



INSERT INTO `trademark` (`trademark_id`, `name`, `notes`) VALUES 
('1', 'iphone', NULL),
('2', 'samsung', NULL),
('3', 'lg', NULL),
('4', 'sony', NULL),
('5', 'nokia', NULL);


INSERT INTO `promotion` (`promotion_id`, `discountvalue`, `end`, `name`, `notes`, `start`) 
VALUES ('1', '10', NULL, 'giảm 10%', NULL, NULL);


INSERT INTO `product` (`product_id`, `name`, `notes`, `promotion_id`, `trademark_id`) VALUES 
('1', 'iphone 11', NULL, '1', '1'),
('2', 'iphone 8', NULL, '1', '1'),
('3', 'iphone 7', NULL, '1', '1'),
('4', 'lg g8', NULL, '1', '3'),
('5', 'samsung s9', NULL, '1', '2'),
('6', 'samsung note 10', NULL, '1', '2');
 
 INSERT INTO `configurator` (`configurator_id`, `camera_front`, `camera_rear`, `chipset`, `cpu`, `notes`, `pin`, `ram`, `rom`, `screen`, `systems`) VALUES 
 ('1', NULL, NULL, NULL, NULL, NULL, NULL, '4 gb', '64 gb', NULL, NULL),
 ('2', NULL, NULL, NULL, NULL, NULL, NULL, '4 gb', '128 gb', NULL, NULL),
 ('3', NULL, NULL, NULL, NULL, NULL, NULL, '4 gb', '256 gb', NULL, NULL);
 
 
 INSERT INTO `color` (`color_id`, `name`, `notes`) VALUES 
('1', 'xanh', NULL),
('2', 'đỏ', NULL),
('3', 'đen', NULL);
 
 
 
 
  INSERT INTO `product_detail` (`product_detail_id`, `amount`, `price`, `color_id`, `configurator_id`, `image_id`, `product_id`) VALUES 
('1', '12', '22000000', '2', '3', '1', '1'),
('2', '12', '12000000', '2', '1', '3', '2'),
('3', '15', '13000000', '3', '1', '1', '3'),
('4', '17', '25000000', '3', '2', '5', '4'),
('5', '12', '22000000', '2', '3', '4', '5'),
('6', '10', '28000000', '3', '1', '4', '5'),
('7', '12', '22000000', '1', '2', '6', '4'),
('8', '12', '22000000', '1', '3', '1', '6'),
('9', '12', '22000000', '1', '1', '1', '1');


