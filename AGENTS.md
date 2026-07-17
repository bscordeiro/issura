# Agent Guidance

## Project essentials

- Stack: Java 21, Spring Boot 4.1.0, Spring Modulith 2.1.0,
  springdoc-openapi 3.0.3, React 19, TypeScript 6, Vite 8, and MariaDB
  12.3 via Docker Compose.
- Toolchain: Node 22.23.1 and npm 10.9.8, pinned at the repository root.
- Package manager: Maven Wrapper 3.9.16 (`backend`) and npm with lockfile
  v3 at the root (OpenSpec) and in `frontend`.
- Detail files: `CONTEXT.md` (snapshot, project map, durable decisions),
  `CONVENTIONS.md` (engineering rules). Read `CONTEXT.md` before
  non-trivial work.

## Commands

- Root tool install: `npm ci`
- OpenSpec: `npm run openspec -- <command>`
- Backend tests: `cd backend && ./mvnw test`
- Backend development: `cd backend && ./mvnw spring-boot:run`
- Backend build: `cd backend && ./mvnw verify`
- Frontend install: `cd frontend && npm ci`
- Frontend development: `cd frontend && npm run dev`
- Frontend build and type check: `cd frontend && npm run build`
- Frontend lint: `cd frontend && npm run lint`
- Frontend preview: `cd frontend && npm run preview`
- Not detected: root aggregate command, backend lint command,
  or frontend test command.

## Working rules

- Start from one explicit goal; search before broad reading; keep work within
  requested scope and `CONVENTIONS.md`.
- Verify with checks proportional to change risk, using the commands above.
- Stop for material ambiguity involving requirements, public contracts,
  data loss, security, or irreversible actions.
- Use repository evidence for APIs, commands, endpoints, files, and behavior.
- Keep secrets, tokens, PII, and private paths out of logs and examples.

## Documentation impact

After verified code or configuration changes, report whether public behavior,
setup, contracts, architecture, security, or developer-workflow documentation
needs updating.
