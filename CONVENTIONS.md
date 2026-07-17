# Engineering Conventions

Every rule below traces to an explicit source; patterns merely observed in
source code are never promoted to rules.

## Formatting

- Preserve LF for `mvnw` and CRLF for `*.cmd`; enforced by Git attributes
  — source: `.gitattributes`.
- Use `frontend/DESIGN.md` as frontend visual source, including its
  accessibility and interaction rules — source: `README.md`.

## Linting and Types

- Enforced by ESLint for TypeScript and TSX files
  — source: `frontend/eslint.config.js`.
- Enforced by TypeScript compiler for application and Vite configuration
  — sources: `frontend/tsconfig.app.json`, `frontend/tsconfig.node.json`.

## Testing

- Run Spring Modulith and ArchUnit architecture checks with the backend test
  suite — source: `backend/src/test/java/com/github/bscordeiro/issura/ArchitectureTests.java`.
- Verify generated OpenAPI JSON and YAML contracts through MVC integration
  tests — source: `backend/src/test/java/com/github/bscordeiro/issura/OpenApiContractTests.java`.

## Commits and Workflow

- Use OpenSpec to organize specifications, changes, and development tasks
  — source: `README.md`.

## Not established

- Commit-message conventions.
