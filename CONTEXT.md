# Project Context

## Snapshot

- Root: `.`
- Stack: Java 21, Spring Boot 4.1.0, Spring Modulith 2.1.0,
  springdoc-openapi 3.0.3, React 19, TypeScript 6, Vite 8, and MariaDB
  12.3 via Docker Compose.
- Toolchain: Node 22.23.1 and npm 10.9.8, pinned at the repository root.
- Package manager: Maven Wrapper 3.9.16 (`backend`) and npm with lockfile
  v3 at the root (OpenSpec) and in `frontend`.
- Application paths: `backend/src/main`, `frontend/src`.
- Test paths: `backend/src/test`.
- Commands: recorded in `AGENTS.md`.
- Not detected: unified workspace configuration, frontend test path,
  or frontend test command.

## Project Map

- Root `package.json`: pinned OpenSpec CLI and Node/npm contract.
- `backend`: Spring Boot Web MVC, Security, and JDBC application.
- `compose.yaml`: MariaDB service for local development.
- `frontend`: React and TypeScript client built with Vite.
- `openspec`: Spec-driven change and specification workspace.

## Excluded Paths

- Normal probe skips: `.git`, `.pi-subagents`, `.serena`, `.vscode`,
  `node_modules`, `backend/target`, and `frontend/node_modules`.
- Inspect these paths only when the task concerns generated output,
  dependencies, or local tooling.

## Durable Decisions

- Backend foundation uses Spring Modulith for module-boundary verification,
  ArchUnit for package constraints, and springdoc-openapi for authenticated,
  code-first JSON/YAML HTTP contract generation without Swagger UI.
- Root `.env` is the local configuration source for Docker Compose, the
  backend datasource, and Vite; only variables prefixed with `VITE_` may be
  exposed to browser code.
- OpenSpec 1.6.0 is a root development dependency; global installation is not
  part of the project workflow.
