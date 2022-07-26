create table `test`.`organization`(
	`單位ID` int unsigned not null auto_increment,
    `建立日期` date NOT NULL default '0000-00-00',
    `單位名稱` varchar(20) NOT NULL,
    `單位介紹` varchar(200),
    `單位連絡電話` varchar(10),
    `單位聯絡信箱` varchar(50),
    `單位地址` varchar(50),
    primary key(`單位ID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `test`.`organization_product` (
  `產品ID` int unsigned not null auto_increment,
  `產品名稱` varchar(20) NOT NULL,
  `產品說明` varchar(200),
  `產品排序` int not null default -1,
  `產品價格` int unsigned not null,
  `產品所屬單位` int unsigned not null,
  PRIMARY KEY (`產品ID`),
  foreign key(`產品所屬單位`) references `organization`(`單位ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;