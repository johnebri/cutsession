CREATE TABLE IF NOT EXISTS `studio_sessions` (
  `id` varchar(100) NOT NULL,
  `merchant_id` varchar(55) NOT NULL,
  `starts_at` varchar(50) NOT NULL,
  `ends_at` varchar(50) NOT NULL,
  `type` varchar(255) NOT NULL,
   PRIMARY KEY  (`id`)
);