# Issura

Monorepo com backend Java/Spring Boot, frontend React/TypeScript e MariaDB para desenvolvimento local.

## Stack

- Backend: Java 21, Spring Boot, Spring Modulith, Spring MVC, Spring Security,
  Spring JDBC e springdoc-openapi.
- Frontend: React, TypeScript, Vite, Tailwind CSS e shadcn/ui.
- Data: MariaDB via Docker Compose.
- Workflow: Maven Wrapper, npm, OpenSpec, Spring Java Format e Prettier.

As versões exatas das dependências estão fixadas em `backend/pom.xml`,
`package.json`, `frontend/package.json`, seus lockfiles, `.nvmrc` e `compose.yaml`.

## Pré-requisitos

- Java 21 disponível em `PATH`
- Docker com Docker Compose
- Node Version Manager (`nvm`) ou ferramenta equivalente

Prepare as versões fixadas do Node e npm:

```bash
nvm install
nvm use
npm install --global npm@10.9.8
```

O arquivo `.npmrc` rejeita instalações executadas com versões diferentes das declaradas nos manifests.

## Configuração inicial

Crie o arquivo local de ambiente na raiz:

```bash
cp .env.example .env
```

Preencha `MARIADB_PASSWORD` e `MARIADB_ROOT_PASSWORD` antes de iniciar o banco. Nunca versione `.env`.

Instale as ferramentas da raiz e as dependências do frontend:

```bash
npm ci
npm --prefix frontend ci
```

## Arquivo `.env` raiz

O mesmo `.env` atende todo o ambiente local:

- Docker Compose carrega o arquivo automaticamente.
- Backend importa `../.env` ao ser iniciado dentro de `backend`.
- Vite usa a raiz como `envDir`.
- Somente variáveis com prefixo `VITE_` podem ser expostas ao navegador; credenciais `MARIADB_*` permanecem no ambiente local.

Variáveis disponíveis:

| Variável                | Uso                                       |
| ----------------------- | ----------------------------------------- |
| `MARIADB_DATABASE`      | Nome do banco da aplicação                |
| `MARIADB_USER`          | Usuário da aplicação                      |
| `MARIADB_PASSWORD`      | Senha do usuário da aplicação             |
| `MARIADB_ROOT_PASSWORD` | Senha administrativa usada pelo container |
| `MARIADB_PORT`          | Porta local publicada pelo MariaDB        |

Em ambientes implantados, forneça variáveis pelo mecanismo de segredos da plataforma em vez de distribuir arquivos `.env`.

## Executar ambiente local

### 1. Banco

Na raiz:

```bash
docker compose up -d --wait database
```

MariaDB fica disponível somente em `127.0.0.1:${MARIADB_PORT}` e persiste dados no volume `mariadb_data`.

### 2. Backend

Em outro terminal:

```bash
cd backend
./mvnw spring-boot:run
```

Backend inicia em `http://localhost:8080`. Executar dentro de `backend` garante importação do `.env` da raiz.

### 3. Frontend

Em outro terminal:

```bash
cd frontend
npm run dev
```

Frontend inicia em `http://localhost:5173`.

Estado atual: frontend e backend executam como processos separados; ainda não existe chamada HTTP implementada entre eles. Banco já está configurado como datasource do backend.

Para encerrar o banco sem apagar dados:

```bash
docker compose down
```

## Verificação

Execute todas as verificações usadas pela CI a partir da raiz:

```bash
npm run check
```

O comando executa quatro gates, na mesma ordem usada pela CI:

```text
lint → audit → sast → test
```

- `lint`: Spring Java Format, Prettier e ESLint.
- `audit`: `npm audit` para os lockfiles Node e OSV-Scanner sobre um SBOM
  CycloneDX do grafo Maven.
- `sast`: Semgrep sobre o código Java e TypeScript.
- `test`: configuração do Compose, testes e pacote backend, build frontend e
  OpenSpec Doctor.

OSV-Scanner e Semgrep executam em imagens Docker fixadas por versão e digest em
`package.json`; a primeira verificação baixa essas imagens. Os testes bootstrap
do backend não dependem de MariaDB: a autoconfiguração do datasource é excluída
somente no ambiente de teste.

Para aplicar a formatação determinística:

```bash
npm run format
```

## OpenSpec

OpenSpec é uma dependência de desenvolvimento local da raiz, fixada em
`package.json`; instalação global não faz parte do fluxo.

Use OpenSpec quando a mudança envolver comportamento observável intencional,
contrato HTTP ou entre módulos, persistência ou migração de dados, segurança,
integração externa, decisão arquitetural material ou trabalho em várias sessões.

Uma mudança direta sem OpenSpec é permitida somente quando comportamento e
contratos públicos permanecem inalterados, não há migração nem decisão material
de segurança ou arquitetura, e a alteração cabe em uma revisão pequena. Uma
correção que restaura uma especificação existente pode seguir diretamente com
teste de regressão; use OpenSpec quando o comportamento esperado precisar ser
decidido ou alterado.

Para iniciar uma mudança material no assistente:

```text
/opsx:propose "change description"
```

Comandos de manutenção:

```bash
npm run openspec -- --version
npm run openspec -- init --tools pi,claude
npm run openspec -- update
```

O fluxo estável permanece `spec-driven`, configurado em
`openspec/config.yaml`; esquemas experimentais não fazem parte do bootstrap.

## Design frontend

`frontend/DESIGN.md` é a fonte visual. `npm run dev` e `npm run build` validam
os tokens e geram `frontend/src/design-theme.css`, que é ignorado pelo Git; não
edite nem versione esse arquivo.

Validação manual:

```bash
cd frontend
npm run design:lint
npm run design:build
```
