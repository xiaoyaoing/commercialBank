drop table if exists `litigant`;
drop table if exists `punishment`;
create Table `litigant`(
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(200) ,
    `type` varchar(20) ,
    `unit` varchar(100),
    `charge_man` varchar(100),
    `province` varchar(10)

);

create Table `punishment`(
    `id` int primary key auto_increment,
    `litigant_id` numeric NOT NULL,
    `litigant_name` varchar(200),
    `case_id` numeric NOT NULL,
    `doc_name` varchar(200) ,
    `doc_num` varchar(100) ,
    `province` varchar(20),
    `city` varchar(20),
    `level` numeric NOT NULL,
    `type` varchar(20) ,
    `organ` varchar(100),
    `fine` numeric,
    `cause` varchar(3000),
    `date` datetime,
    `basis` varchar(2000)

);

ALTER TABLE `litigant` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
ALTER TABLE `punishment` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;