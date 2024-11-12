CREATE TABLE `usertable` (
  `id` varchar(20) NOT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `birthyear` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `mydb`.`usertable` 
CHANGE COLUMN `pwd` `pwd` VARCHAR(20) NOT NULL ;
