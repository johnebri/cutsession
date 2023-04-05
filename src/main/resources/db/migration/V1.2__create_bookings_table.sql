CREATE TABLE IF NOT EXISTS `bookings` (
  `booking_id` varchar(100) NOT NULL,
  `booking_ref` varchar(10) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `session_id` varchar(100) NOT NULL,
  `date` varchar(20) NOT NULL,
  `starts_at` varchar(20) NOT NULL,
  `ends_at` varchar(20) NOT NULL,
  `notes` varchar(500),
  `title` varchar(75),
   PRIMARY KEY  (`booking_id`)
);