CREATE TABLE pessoas (
	id_pessoa INT AUTO_INCREMENT NOT NULL,
	nome VARCHAR(80) NOT NULL DEFAULT "",
	data_nascimento DATE NOT NULL,
	email VARCHAR(80) NOT NULL DEFAULT "",
	sexo CHAR(1) NOT NULL DEFAULT "",
	UNIQUE INDEX idx_nome_pessoas (nome),
	UNIQUE INDEX idx_email_pessoas (email),
	PRIMARY KEY (id_pessoa)
)ENGINE=InnoDB;