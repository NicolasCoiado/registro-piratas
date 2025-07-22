CREATE TABLE tripulacoes (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    capitao_id INT UNIQUE
);

CREATE TABLE akumanomis (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    tipo VARCHAR(10) NOT NULL,
    descricao VARCHAR(500),
    img_url VARCHAR(500),
    id_usuario INT
);

CREATE TABLE piratas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    recompensa DECIMAL(12, 2),
    img_url VARCHAR(500),
    id_tripulacao INT REFERENCES tripulacoes(id),
    id_akumanomi INT UNIQUE REFERENCES akumanomis(id)
);

ALTER TABLE tripulacoes
ADD CONSTRAINT fk_capitao
FOREIGN KEY (capitao_id)
REFERENCES piratas(id);

CREATE TABLE alcunhas (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL
);

CREATE TABLE pirata_alcunha (
    pirata_id INT NOT NULL REFERENCES piratas(id),
    alcunha_id INT NOT NULL REFERENCES alcunhas(id),
    PRIMARY KEY (pirata_id, alcunha_id)
);
