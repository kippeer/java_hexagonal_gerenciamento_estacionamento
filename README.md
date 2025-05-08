# Sistema de Gerenciamento de Estacionamento

Sistema desenvolvido em Spring Boot utilizando Arquitetura Hexagonal para gerenciar operações de estacionamento.

## Funcionalidades

- Gerenciamento de entrada e saída de veículos
- Controle de vagas de estacionamento
- Cálculo automático de tarifas
- Gestão de clientes e veículos
- Relatórios de ocupação e faturamento

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2.2
- Spring Data JPA
- H2 Database
- Maven
- Swagger/OpenAPI

## Requisitos

- Java 17 ou superior
- Maven 3.6 ou superior

## Instalação e Execução

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/parking-management.git
```

2. Entre no diretório do projeto:
```bash
cd parking-management
```

3. Execute o projeto:
```bash
mvn spring-boot:run
```

O sistema estará disponível em `http://localhost:8080`

## Documentação da API

A documentação completa da API está disponível através do Swagger UI:
- URL: `http://localhost:8080/swagger-ui.html`

## Estrutura do Projeto

O projeto segue a Arquitetura Hexagonal (Ports and Adapters), com a seguinte organização:

```
src/main/java/com/parking/
├── domain/                 # Regras de negócio e entidades
├── application/           # Casos de uso e portas
└── infrastructure/        # Adaptadores e configuração
```

Para mais detalhes sobre a arquitetura, consulte o arquivo [hexagonal.md](hexagonal.md).

## Endpoints Principais

### Clientes
- POST /api/customers - Cadastrar novo cliente
- GET /api/customers - Listar todos os clientes
- GET /api/customers/{id} - Buscar cliente por ID

### Veículos
- POST /api/vehicles - Registrar novo veículo
- GET /api/vehicles - Listar todos os veículos
- GET /api/vehicles/{id} - Buscar veículo por ID

### Estacionamento
- POST /api/parking/entry - Registrar entrada de veículo
- POST /api/parking/exit/{ticketId} - Registrar saída de veículo
- GET /api/parking/slots/available - Listar vagas disponíveis

### Vagas
- POST /api/parking-slots - Criar nova vaga
- GET /api/parking-slots - Listar todas as vagas
- GET /api/parking-slots/status/{status} - Buscar vagas por status

## Tipos de Veículos Suportados

- CAR (Carro)
- MOTORCYCLE (Moto)
- SUV (Utilitário Esportivo)
- TRUCK (Caminhão)
- ELECTRIC_VEHICLE (Veículo Elétrico)

## Tipos de Vagas

- STANDARD (Padrão)
- COMPACT (Compacta)
- HANDICAPPED (Deficiente)
- ELECTRIC (Elétrica)
- MOTORCYCLE (Moto)

## Contribuição

1. Faça o fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request