# Project Context

## Snapshot

- Root: `.`
- Stack: Java 21, Spring Boot 4.1.0, Spring Modulith 2.1.0,
  springdoc-openapi 3.0.3, React 19, TypeScript 6, Vite 8, and MariaDB
  12.3 via Docker Compose.
- Toolchain: Maven Wrapper 3.9.16, Node 22.23.1, and npm 10.9.8.
- Dependency manifests and lockfiles are authoritative for exact versions.
- Application paths: `backend/src/main`, `frontend/src`.
- Test paths: `backend/src/test`.
- Full verification: `npm run check` (`lint` → `audit` → `sast` → `test`);
  deterministic formatting: `npm run format`.
- Not detected: npm workspaces, a frontend test path, or a frontend test command.

## Project Map

- Root `package.json`: repository checks, formatting, OpenSpec, and the Node/npm contract.
- `backend`: Spring Boot Web MVC, Security, and JDBC application.
- `compose.yaml`: MariaDB service for local development.
- `frontend`: React and TypeScript client built with Vite.
- `openspec`: Spec-driven change and specification workspace.

## Excluded Paths

- Normal probe skips: `.git`, `.pi-subagents`, `.serena`, `.vscode`,
  `node_modules`, `backend/target`, `frontend/node_modules`, and generated
  `frontend/src/design-theme.css`.
- Inspect these paths only when the task concerns generated output,
  dependencies, or local tooling.

## Durable Decisions

- Backend foundation uses Spring Modulith for module-boundary verification,
  ArchUnit for package constraints, and springdoc-openapi for authenticated,
  code-first JSON/YAML HTTP contract generation without Swagger UI.
- Root `.env` is the local configuration source for Docker Compose, the
  backend datasource, and Vite; only variables prefixed with `VITE_` may be
  exposed to browser code.
- `npm run check` is the shared local verification contract; CI invokes its
  four component gates as named steps in the same order.
- Spring Java Format and Prettier enforce deterministic source formatting.
- npm audits the Node lockfiles, CycloneDX plus OSV-Scanner audit the resolved
  Maven graph, and Semgrep scans Java and TypeScript. Scanner containers are
  pinned by version and digest in `package.json`.
- Bootstrap backend tests exclude datasource auto-configuration and run
  without MariaDB; database-backed test behavior is not established.
- `frontend/DESIGN.md` is canonical; `design-theme.css` is generated and ignored.
- OpenSpec is a root-local development dependency; global installation is not
  part of the project workflow.
