# Book Service (GraphQL)

A lightweight Spring Boot GraphQL service (using Netflix DGS) that exposes a Book API.

This repository contains a simple demo service that serves books and authors via a GraphQL endpoint.

## Quick overview

- Artifact: `book-service`
- Java: 17
- Build: Maven (maven wrapper included)
- Default HTTP port: 8080
- GraphQL endpoint: `http://localhost:8080/graphql`

## Requirements

- Java 17 (JDK 17)
- Git (optional)
- On Windows: use the provided Maven wrapper `mvnw.cmd` to build and run without installing Maven globally.

## Build

From the repository root (Windows cmd):

```cmd
.\\mvnw.cmd clean package
```

This produces a runnable jar under `target/book-service-0.0.1-SNAPSHOT.jar`.

## Run

Option 1 — Run with the Maven wrapper (development):

```cmd
.\\mvnw.cmd spring-boot:run
```

Option 2 — Run the packaged jar:

```cmd
java -jar target\book-service-0.0.1-SNAPSHOT.jar
```

After startup the service listens on port 8080 by default.

## GraphQL endpoint and sample queries

The service exposes a GraphQL endpoint at:

http://localhost:8080/graphql

You can use any GraphQL client (GraphiQL, GraphQL Playground, Altair, Postman) or curl to query the API.

Sample GraphQL queries:

Fetch all books with their authors:

```graphql
{
  books {
    id
    title
    author {
      id
      name
      email
    }
  }
}
```

Fetch a single book by id:

```graphql
{
  book(id: 1) {
    id
    title
    author {
      id
      name
      email
    }
  }
}
```

Example using curl (Windows cmd). Note the escaping of quotes in the JSON payload:

```cmd
curl -X POST -H "Content-Type: application/json" -d "{\"query\":\"{ books { id title author { id name email } } }\"}" http://localhost:8080/graphql
```

## Project structure (important files)

- `pom.xml` - Maven configuration (Spring Boot + DGS dependency)
- `src/main/java/com/vivek/dgs/book/BookServiceApplication.java` - Spring Boot main class
- `src/main/resources/schema/schema.graphqls` - GraphQL schema (types and queries)
- `src/main/java/com/vivek/dgs/book/model/` - Domain models (`Book`, `Author`)
- `src/main/java/com/vivek/dgs/book/datafetcher/` - Data fetchers / resolvers for GraphQL
- `src/main/resources/application.properties` - Spring Boot properties

## Extending the API

- Add types/fields to `schema.graphqls`.
- Implement data fetchers (resolvers) under `datafetcher` to provide the data.
- Rebuild and restart the app to pick up schema and code changes.

## Troubleshooting

- If the server doesn't start, ensure Java 17 is installed and `JAVA_HOME` points to the JDK.
- If the port 8080 is in use, set `server.port` in `src/main/resources/application.properties` or pass `--server.port=XXXX` on the command line.

## Notes

- This project uses the Netflix DGS GraphQL starter. The GraphQL endpoint path is the default `/graphql`.
- The included GraphQL schema can be found at `src/main/resources/schema/schema.graphqls`.

## License

This project does not include a license file. Add a LICENSE if you want to make the project open source.

---

If you want, I can also:
- Add a `README` badge for build status or Java version.
- Include a small curl-based smoke test script in the repo.
- Add instructions for running on Linux/macOS as well.

