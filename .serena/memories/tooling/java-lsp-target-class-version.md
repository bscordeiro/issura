# Java LSP target class version mismatch

After editing `backend/pom.xml`, Java dispatch/LSP may recompile `backend/target/classes` and `backend/target/test-classes` as classfile 69 (Java 25), while Maven Wrapper runs Java 21 and Surefire accepts through classfile 65. Maven incremental may then skip compilation and fail with `class file version 69.0 ... up to 65.0`.

Diagnose with `file backend/target/**/**/*.class` or `javap -verbose ... | grep 'major version'`. Run final Maven verification with `cd backend && ./mvnw clean ...` after last Java/POM edit so Maven rebuilds classfiles with Java 21.