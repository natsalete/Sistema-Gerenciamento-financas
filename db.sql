-- Tabela Categorias
CREATE TABLE Categorias (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) UNIQUE NOT NULL
);

-- Tabela Orçamentos
CREATE TABLE Orcamentos (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    valor_total NUMERIC(10, 2) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    status VARCHAR(20) CHECK (status IN ('Em Andamento', 'Concluído', 'Cancelado')) DEFAULT 'Em Andamento'
);

-- Tabela Despesas
CREATE TABLE Despesas (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    valor NUMERIC(10, 2) NOT NULL,
    data DATE NOT NULL,
    categoria_id INT NOT NULL REFERENCES Categorias(id) ON DELETE CASCADE ON UPDATE CASCADE,
    orcamento_id INT NOT NULL REFERENCES Orcamentos(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabela Transacoes
CREATE TABLE Transacoes (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(10) CHECK (tipo IN ('entrada', 'saída')) NOT NULL,
    valor NUMERIC(10, 2) NOT NULL,
    data DATE NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    orcamento_id INT NOT NULL REFERENCES Orcamentos(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabela Fornecedores
CREATE TABLE Fornecedores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(15),
    email VARCHAR(100)
);

-- Tabela Pagamentos
CREATE TABLE Pagamentos (
    id SERIAL PRIMARY KEY,
    valor NUMERIC(10, 2) NOT NULL,
    data DATE NOT NULL,
    descricao VARCHAR(255),
    fornecedor_id INT REFERENCES Fornecedores(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Tabela Itens de Orçamento
CREATE TABLE Itens_Orcamento (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    quantidade INT NOT NULL,
    valor_unitario NUMERIC(10, 2) NOT NULL,
    valor_total NUMERIC(10, 2),
    orcamento_id INT NOT NULL REFERENCES Orcamentos(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabela Usuários
CREATE TABLE Usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    papel VARCHAR(20) CHECK (papel IN ('Administrador', 'Usuário')) DEFAULT 'Usuário'
);