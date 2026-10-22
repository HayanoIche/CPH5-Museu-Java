DROP TABLE tb_esculturas_jv;

CREATE TABLE tb_esculturas_jv (
    id              INTEGER GENERATED ALWAYS AS IDENTITY,
    nome            VARCHAR(100)   NOT NULL,
    artista         VARCHAR(100)   NOT NULL,
    descricao       VARCHAR(500)   NOT NULL,
    numero_de_votos INTEGER        DEFAULT 0,
    data_criacao    DATE,
    material        VARCHAR(50),
    peso_kg         NUMERIC(10, 2),

    CONSTRAINT pk_esculturas_jv PRIMARY KEY (id)
);

CREATE TABLE tb_fotos_jv (
    id              INTEGER GENERATED ALWAYS AS IDENTITY,
    nome            VARCHAR(100)    NOT NULL,
    artista         VARCHAR(100)    NOT NULL,
    descricao       VARCHAR(500)    NOT NULL,
    numero_de_votos INTEGER         DEFAULT 0,
    data_criacao    DATE,
    camera          VARCHAR(100),
    resolucao_dpi   NUMERIC(10, 2),
    colorida        NUMERIC(1)

    CONSTRAINT pk_fotos_jv PRIMARY KEY (id)
);

INSERT INTO tb_esculturas_jv (nome, artista, descricao, numero_de_votos, data_criacao, material, peso_kg)
VALUES ('Escultura Ronaldinho Gaucho', 'José Cardoso', 'Escultura do bruxo em ouro', 5, TO_DATE('1999-01-01', 'YYYY-MM-DD'), 'Ouro', 50.5);

COMMIT;