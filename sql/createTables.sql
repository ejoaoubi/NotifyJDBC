-- Conectar-se à base de dados SGBD_PL1_10
USE SGBD_PL1_10;

-- Criar a tabela Cliente
CREATE TABLE Cliente (
    ClienteId INT PRIMARY KEY IDENTITY(1,1),
    Nome VARCHAR(100) NOT NULL,
    Morada VARCHAR(255)
);

-- Criar a tabela Produto
CREATE TABLE Produto (
    ProdutoId INT PRIMARY KEY IDENTITY(1,1),
    Designacao VARCHAR(100) NOT NULL,
    Preco DECIMAL(10, 2) NOT NULL,
    QtdStock INT NOT NULL,
    QtdEncomendado INT NOT NULL
);

-- Criar a tabela Encomenda
CREATE TABLE Encomenda (
    EncId INT PRIMARY KEY IDENTITY(1,1),
    ClienteId INT NOT NULL,
    Data DATE NOT NULL,
    Total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (ClienteId) REFERENCES Cliente(ClienteId)
);

-- Criar a tabela LinhaEncomenda
CREATE TABLE LinhaEncomenda (
    EncId INT,
    ProdutoId INT,
    Qtd INT NOT NULL,
    PRIMARY KEY (EncId, ProdutoId),
    FOREIGN KEY (EncId) REFERENCES Encomenda(EncId),
    FOREIGN KEY (ProdutoId) REFERENCES Produto(ProdutoId)
);
