CREATE TABLE IF NOT EXISTS `users` (
  `id` varchar(100) NOT NULL,
  `name` varchar(55) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `dob` varchar(50),
  `city` varchar(20),
  `phone_number` varchar(100),
  `type` varchar(20) NOT NULL,
   PRIMARY KEY  (`id`)
);