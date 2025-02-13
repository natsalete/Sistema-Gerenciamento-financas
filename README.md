# Sistema de Gerenciamento de Finanças

## Visão Geral

O **Sistema de Gerenciamento de Finanças** é uma aplicação voltada para a gestão de despesas, orçamentos e transações financeiras. Ele permite que empresas ou indivíduos organizem e monitorem seus gastos de forma eficiente.

## Padrões de Projeto Utilizados

O sistema foi desenvolvido seguindo padrões de projeto para garantir escalabilidade e manutenção fácil:

- **Criacionais:**

  - **Padrão Singleton**: Usado para a conexão com o banco de dados.
  - **Padrão Fábrica**: Implementado para a criação dos DAOs (Data Access Objects).

- **Estrutural:**

  - **Padrão Adaptador**: Pode ser implementado para compatibilizar diferentes tipos de bancos de dados.

- **Comportamental:**

  - **Padrão Observador**: Usado para atualização de orçamentos em tempo real.

## Arquitetura do Sistema

A aplicação segue o padrão **MVC (Model-View-Controller)**:

- **Modelo (Model):** Classes de entidade e DAOs.
- **Visão (View):** Interface gráfica implementada em **Swing/JavaFX**.
- **Controlador (Controller):** Classes responsáveis pela lógica de negócios e interação entre Model e View.

## Funcionalidades

### 1. Gerenciamento de Categorias

- Criar e listar categorias de despesas (exemplo: "Aluguel", "Marketing", "Equipamentos").
- Facilita a organização e o filtro de despesas.

### 2. Controle de Orçamentos

- Criar orçamentos com um valor total definido e um período específico.
- Monitoramento de despesas associadas a cada orçamento.
- Validação para evitar estouro do orçamento.

### 3. Registro de Despesas

- Associar despesas a categorias e orçamentos.
- Exemplo:
  - **Categoria:** Marketing
  - **Despesa:** Criação de site
  - **Valor:** R\$ 1.500,00
  - **Orçamento:** Janeiro de 2025

### 4. Gestão de Transações

- Registro de **entradas** (receitas) e **saídas** (pagamentos).
- Atualiza automaticamente os valores do orçamento.
- Exemplo:
  - **Entrada:** Receita de R\$ 5.000,00
  - **Saída:** Pagamento de fornecedor de R\$ 2.000,00

### 5. Controle de Pagamentos

- Registro de pagamentos realizados para despesas.
- Exemplo:
  - **Despesa:** Compra de equipamentos
  - **Pagamento:** R\$ 1.000,00 realizado em 10/01/2025
- Benefício: Facilita o rastreamento de pagamentos realizados e pendentes.

### 6. Cadastro de Fornecedores

- Registro de fornecedores vinculados às despesas.
- Exemplo:
  - **Nome:** ABC Equipamentos
  - **Telefone:** (11) 99999-8888
  - **Email:** [contato@abcequipamentos.com](mailto\:contato@abcequipamentos.com)
- Benefício: Organização e facilidade de contato com fornecedores.

### 7. Itens de Orçamento

- Permite detalhar os itens de um orçamento.
- Exemplo:
  - **Orçamento:** Janeiro de 2025
  - **Item:** Serviços de limpeza
  - **Quantidade:** 2
  - **Valor unitário:** R\$ 300,00
  - **Valor total:** R\$ 600,00

### 8. Controle de Usuários

- Permite o cadastro e autenticação de usuários.
- Exemplo:
  - **Nome:** Natália
  - **Email:** [natalia@email.com](mailto\:natalia@email.com)
  - **Senha:** (armazenada de forma segura com hashing)

## Fluxo Geral do Sistema

1. **Cadastro de Dados Básicos:**

   - O administrador cadastra categorias e fornecedores.
   - Usuários são criados para acessar o sistema.

2. **Criação de Orçamentos:**

   - O usuário define o valor total e o período do orçamento.

3. **Registro de Despesas:**

   - As despesas são registradas, associadas a categorias e orçamentos.

4. **Gestão de Transações:**

   - Entradas e saídas financeiras são registradas e vinculadas aos orçamentos.

5. **Registro de Pagamentos:**

   - Pagamentos são associados às despesas.

## Tecnologias Utilizadas

- **Linguagem:** Java
- **Banco de Dados:** MySQL / PostgreSQL (compatível com outros bancos via Adaptador)
- **Interface Gráfica:** Swing / JavaFX
- **Padrões de Projeto:** Singleton, Fábrica, Adaptador, Observador
- **Arquitetura:** MVC (Model-View-Controller)

## Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/natsalete/Sistema-Gerenciamento-financas.git
   ```
2. Configure o banco de dados e ajuste as credenciais no arquivo de conexão.
3. Compile e execute a aplicação Java:
   ```bash
   javac Main.java
   java Main
   ```

## Contribuição

Ficamos felizes com contribuições! Para contribuir:

1. Fork o repositório
2. Crie uma branch para sua funcionalidade (`git checkout -b minha-feature`)
3. Commit suas alterações (`git commit -m "Adicionando nova funcionalidade"`)
4. Envie para o repositório (`git push origin minha-feature`)
5. Abra um Pull Request

## Licença

Este projeto está sob a licença MIT - consulte o arquivo LICENSE para mais detalhes.

---
