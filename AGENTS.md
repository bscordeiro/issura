# Agent Guidance

## Project essentials

- `CONTEXT.md` is the canonical stack, version, project-map, and durable-decision
  snapshot. Dependency manifests and lockfiles remain authoritative.
- `CONVENTIONS.md` contains enforced engineering rules. Read both files before
  non-trivial work.
- Workspaces: Maven Wrapper in `backend`, npm at the root for repository tooling,
  and npm in `frontend`.

## Commands

- Install tools and frontend dependencies: `npm ci && npm --prefix frontend ci`
- Full repository verification: `npm run check` (`lint` → `audit` → `sast` → `test`)
  and the same four gates individually with `npm run <gate>`.
- Apply deterministic formatting: `npm run format`
- OpenSpec: `npm run openspec -- <command>`
- Backend development: `cd backend && ./mvnw spring-boot:run`
- Frontend development: `npm --prefix frontend run dev`
- Targeted backend verification: `npm run check:backend`
- Targeted frontend verification: `npm run check:frontend`
- Not established: a frontend test command.

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
