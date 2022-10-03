
use test;
DROP TABLE IF EXISTS `Penalties`;
CREATE TABLE Penalties(
id INT(20) not null PRIMARY KEY auto_increment,
decisionNumber TEXT DEFAULT NULL,
punishedPartyName TEXT DEFAULT NULL,
agencyName TEXT DEFAULT NULL,
agencyType VARCHAR(256) DEFAULT NULL,
province VARCHAR(256) DEFAULT NULL,
penaltyCause TEXT DEFAULT NULL,
penaltyLaw TEXT DEFAULT NULL,
penaltyBasis TEXT DEFAULT NULL,
penaltyDecision TEXT DEFAULT NULL,
penaltyDate Date DEFAULT NULL,
penaltyAmount INT(20) DEFAULT NULL,
penaltyName TEXT DEFAULT NULL,
penaltyType VARCHAR(256) DEFAULT NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8;


# INSERT INTO Penalties (id,decisionNumber,punishedPartyName,penaltyDate,province,penaltyType,penaltyAmount,penaltyLaw) VALUES
# (1,1,1,'2020-01-02','四川','银保监局',20,12),
# (2,2,2,'2020-03-04','江苏','银保监分局',30,34),
# (3,3,2,'2020-02-01','四川','银保监分局',40,56),
# (4,3,2,'2020-02-01','四川','银保监局',20,55),
# (5,4,6,'2020-03-11','四川','银保监分局',105,90),
# (6,4,6,'2020-04-20','四川','银保监局',25,5),
# (7,4,6,'2020-04-28','四川','银保监分局',15,66),
# (8,4,6,'2020-05-20','四川','银保监局',39,77)

