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



-- Inserir dados na tabela Cliente
INSERT INTO Cliente (Nome, Morada) VALUES 
('Carlos Silva', 'Rua A, 123'),
('Ana Santos', 'Avenida B, 456'),
('João Pereira', 'Rua C, 789'),
('Rita Almeida', 'Avenida D, 101'),
('Pedro Costa', 'Rua E, 202'),
('Marta Neves', 'Rua F, 303'),
('Tiago Lopes', 'Rua G, 404'),
('Sara Ferreira', 'Avenida H, 505'),
('Ricardo Nunes', 'Rua I, 606'),
('Filipa Mendes', 'Avenida J, 707');

-- Inserir dados na tabela Produto
INSERT INTO Produto (Designacao, Preco, QtdStock, QtdEncomendado) VALUES
('Produto A', 15.50, 100, 10),
('Produto B', 25.00, 200, 20),
('Produto C', 9.99, 150, 15),
('Produto D', 12.75, 80, 8),
('Produto E', 45.00, 60, 6),
('Produto F', 7.90, 300, 30),
('Produto G', 99.99, 40, 4),
('Produto H', 55.55, 70, 7),
('Produto I', 23.00, 90, 9),
('Produto J', 5.00, 120, 12);

-- Inserir dados na tabela Encomenda
INSERT INTO Encomenda (ClienteId, Data, Total) VALUES
(1, '2024-01-01', 100.00),
(2, '2024-01-02', 200.00),
(3, '2024-01-03', 150.00),
(4, '2024-01-04', 180.00),
(5, '2024-01-05', 75.00),
(6, '2024-01-06', 300.00),
(7, '2024-01-07', 220.00),
(8, '2024-01-08', 95.00),
(9, '2024-01-09', 60.00),
(10, '2024-01-10', 175.00);

-- Inserir dados na tabela LinhaEncomenda
INSERT INTO LinhaEncomenda (EncId, ProdutoId, Qtd) VALUES
(1, 1, 2),
(1, 2, 1),
(2, 3, 5),
(2, 4, 3),
(3, 5, 4),
(3, 6, 2),
(4, 7, 1),
(4, 8, 2),
(5, 9, 3),
(5, 10, 2),
(6, 1, 4),
(6, 3, 1),
(7, 5, 3),
(7, 7, 2),
(8, 9, 1),
(8, 2, 3),
(9, 4, 2),
(9, 6, 1),
(10, 8, 3),
(10, 10, 2);


-- Trigger para atualizar o Total na tabela Encomenda
CREATE OR ALTER TRIGGER AtualizarTotalEncomenda
ON LinhaEncomenda
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    -- Atualizar o Total para cada EncId afetado
    UPDATE Encomenda
    SET Total = (
        SELECT SUM(L.Qtd * P.Preco)
        FROM LinhaEncomenda L
        JOIN Produto P ON L.ProdutoId = P.ProdutoId
        WHERE L.EncId = Encomenda.EncId
    )
    WHERE Encomenda.EncId IN (
        SELECT DISTINCT EncId FROM inserted
        UNION
        SELECT DISTINCT EncId FROM deleted
    );
END;


-- Trigger para atualizar a QtdEncomendada na tabela Produto
CREATE OR ALTER TRIGGER AtualizarQtdEncomendada
ON LinhaEncomenda
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    -- Atualizar a QtdEncomendada para cada ProdutoId afetado
    UPDATE Produto
    SET QtdEncomendado = (
        SELECT COALESCE(SUM(Qtd), 0)
        FROM LinhaEncomenda
        WHERE ProdutoId = Produto.ProdutoId
    )
    WHERE Produto.ProdutoId IN (
        SELECT DISTINCT ProdutoId FROM inserted
        UNION
        SELECT DISTINCT ProdutoId FROM deleted
    );
END;
