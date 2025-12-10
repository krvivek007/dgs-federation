# Purchase Service (GraphQL)

A lightweight Spring Boot GraphQL service (using Netflix DGS) that exposes purchase information for books.

This repository contains a small demo service that resolves purchases for books via GraphQL (including an example of the Apollo Federation-style _entities query).

## Quick overview

- Artifact: `purchase-service`
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

This produces a runnable jar under `target/purchase-service-0.0.1-SNAPSHOT.jar`.

## Run

Option 1 — Run with the Maven wrapper (development):

```cmd
.\\mvnw.cmd spring-boot:run
```

Option 2 — Run the packaged jar:

```cmd
java -jar target\purchase-service-0.0.1-SNAPSHOT.jar
```

After startup the service listens on port 8080 by default.

## GraphQL endpoint and sample queries

The service exposes a GraphQL endpoint at:

http://localhost:8080/graphql

You can use any GraphQL client (GraphiQL, GraphQL Playground, Altair, Postman) or curl to query the API.

Sample GraphQL queries:

Fetch all books with their purchases (example):

```graphql
{
  books {
    id
    title
    purchase {
      id
      price
      currency
      paymentMode
      buyer {
        id
        name
        email
      }
      date
    }
  }
}
```

Apollo Federation-style _entities request (the sample you provided):

```graphql
{
  _entities(representations: [
    { __typename: "Book", id: 1 }
  ]) {
    ... on Book {
      purchase {
        price
      }
    }
  }
}
```

Windows curl example for the same `_entities` request (note the escaping required in cmd.exe):

```cmd
curl -X POST -H "Content-Type: application/json" -d "{\"query\":\"{ _entities(representations: [{ __typename: \\\"Book\\\", id: 1 }]) { ... on Book { purchase { price } } } }\" }" http://localhost:8080/graphql
```

Replace the id and fields as needed.

Example using a simple single-query curl (books list):

```cmd
curl -X POST -H "Content-Type: application/json" -d "{\"query\":\"{ books { id title purchase { price } } }\" }" http://localhost:8080/graphql
```

## Project structure (important files)

- `pom.xml` - Maven configuration (Spring Boot + DGS dependency)
- `src/main/java/com/vivek/dgs/purchase/PurchaseServiceApplication.java` - Spring Boot main class
- `src/main/resources/schema/purchase_schema.graphqls` - GraphQL schema (types and queries)
- `src/main/java/com/vivek/dgs/purchase/model/` - Domain models (`Book`, `Purchase`, `Buyer`, etc.)
- `src/main/java/com/vivek/dgs/purchase/datafetcher/` - Data fetchers / resolvers for GraphQL
- `src/main/resources/application.properties` - Spring Boot properties

## Testing the GraphQL API quickly

1. Start the application (see Run section).
2. Open a GraphQL client and point it to `http://localhost:8080/graphql`.
3. Paste the `_entities` sample query above and run it. You should receive the purchase.price for the book with id 1 (if the demo purchase exists in the resolver).

You can also use the curl examples above as a simple smoke test.

## Extending the API

- Add types/fields to `src/main/resources/schema/purchase_schema.graphqls`.
- Implement data fetchers (resolvers) under `src/main/java/com/vivek/dgs/purchase/datafetcher/` to provide the data.
- Rebuild and restart the app to pick up schema and code changes.

## Troubleshooting

- If the server doesn't start, ensure Java 17 is installed and `JAVA_HOME` points to the JDK.
- If the port 8080 is in use, set `server.port` in `src/main/resources/application.properties` or pass `--server.port=XXXX` on the command line.

## Notes

- This project uses the Netflix DGS GraphQL starter and includes an entity resolver example.
- The GraphQL schema is located at `src/main/resources/schema/purchase_schema.graphqls`.

## License

This project does not include a license file. Add a LICENSE if you want to make the project open source.

---

If you'd like, I can also:
- Add a small `smoke-test.cmd` script with the curl example.
- Add a short section showing the relevant portion of `purchase_schema.graphqls` (entities) in the README.
- Add a LICENSE file or CI badges.
