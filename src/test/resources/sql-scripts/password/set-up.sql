DROP TABLE IF EXISTS Private_Key;

CREATE TABLE Private_Key(
	Public_Key varchar(100) NOT NULL PRIMARY KEY,
	Passphrase varchar(100) NOT NULL,
)