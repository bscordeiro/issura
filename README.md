# Issura

Monorepo com backend Java/Spring Boot, frontend React/TypeScript e MariaDB para desenvolvimento local.

## Stack e versões

- Java 21
- Maven Wrapper 3.9.16
- Node.js 22.23.1 (`.nvmrc`)
- npm 10.9.8
- OpenSpec 1.6.0, instalado localmente na raiz
- Spring Boot 4.1.0, Spring MVC, Spring Security e Spring JDBC
- React 19, TypeScript 6, Vite 8, Tailwind CSS v4 e shadcn/ui
- MariaDB 12.3 via Docker Compose

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
cd frontend
npm ci
cd ..
```

## Arquivo `.env` raiz

O mesmo `.env` atende todo o ambiente local:

- Docker Compose carrega o arquivo automaticamente.
- Backend importa `../.env` ao ser iniciado dentro de `backend`.
- Vite usa a raiz como `envDir`.
- Somente variáveis com prefixo `VITE_` podem ser expostas ao navegador; credenciais `MARIADB_*` permanecem no ambiente local.

Variáveis disponíveis:

| Variável | Uso |
| --- | --- |
| `MARIADB_DATABASE` | Nome do banco da aplicação |
| `MARIADB_USER` | Usuário da aplicação |
| `MARIADB_PASSWORD` | Senha do usuário da aplicação |
| `MARIADB_ROOT_PASSWORD` | Senha administrativa usada pelo container |
| `MARIADB_PORT` | Porta local publicada pelo MariaDB |

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

Execute checks a partir da raiz:

```bash
docker compose config --quiet

cd backend
./mvnw clean verify
cd ..

cd frontend
npm run lint
npm run build
cd ..

npm run openspec -- doctor
```

## OpenSpec

OpenSpec é dependência de desenvolvimento da raiz, fixada em `1.6.0`. Instalação global não é necessária.

Confirme a versão:

```bash
npm run openspec -- --version
```

Configure integrações locais para Pi e Claude Code quando necessário:

```bash
npm run openspec -- init --tools pi,claude
```

Após atualizar deliberadamente a dependência no `package.json`, regenere instruções das ferramentas:

```bash
npm run openspec -- update
```

Mudanças seguem o fluxo definido em `openspec/config.yaml`. Para iniciar uma proposta no assistente:

```text
/opsx:propose "change description"
```

## Design frontend

`frontend/DESIGN.md` é a fonte visual. `npm run dev` e `npm run build` validam e exportam tokens para `frontend/src/design-theme.css`; não edite esse arquivo gerado manualmente.

Validação manual:

```bash
cd frontend
npm run design:lint
npm run design:build
```
