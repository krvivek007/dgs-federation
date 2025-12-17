# dgs-federation — multi-service GraphQL example 🚀

**Overview**

This repository demonstrates a small federated GraphQL setup built with Netflix DGS (Java/Spring Boot) services and a Node gateway. It includes simple example services, a Python GraphQL client, and scripts to run and test components locally.

---

## Services (quick map) 📁

- **book-service/** — A DGS (GraphQL) service that exposes book and author data.
  - Java (Maven/Spring Boot)
  - Schema: `src/main/resources/schema/schema.graphqls`

- **purchase-service/** — A DGS (GraphQL) service that models purchases and buyers.
  - Java (Maven/Spring Boot)
  - Schema: `src/main/resources/schema/purchase_schema.graphqls`
  - Includes `smoke-test.cmd` to run a simple local smoke check on Windows.

- **gateway/** — A Node-based GraphQL gateway / aggregator (entry point for federated schema)
  - Node/npm
  - Main file: `index.js`

- **graphql-client/** — Example Python clients (sync and async) that call the gateway/services
  - Python scripts: `graphql-client-sync.py`, `graphql-client-async.py`
  - `requirements.txt` lists required packages

---

## Getting started (local development) 🔧

1. Prerequisites
   - Java 11+ and Maven (or use included `mvnw`/`mvnw.cmd`)
   - Node.js + npm
   - Python 3.8+ (for the client)

2. Start services (order matters)

   - Start **book-service**

     ```bash
     # Linux/macOS
     ./mvnw -f book-service spring-boot:run

     # Windows
     mvnw.cmd -f book-service spring-boot:run
     ```

   - Start **purchase-service**

     ```bash
     # Linux/macOS
     ./mvnw -f purchase-service spring-boot:run

     # Windows
     mvnw.cmd -f purchase-service spring-boot:run
     ```

   - Start **gateway**

     ```bash
     cd gateway
     npm install
     npm start
     ```

3. Run the GraphQL client (examples)

   ```bash
   cd graphql-client
   python -m venv .venv
   # Activate the venv and install deps
   pip install -r requirements.txt
   python graphql-client-sync.py
   # or
   python graphql-client-async.py
   ```

> Tip: Start the backend services first, then the gateway, then run the client scripts.

---

## Build & packaging 📦

- Build a service:

```bash
# From repo root
./mvnw -f book-service clean package
./mvnw -f purchase-service clean package
# JARs will be in each service's target/ folder
```

- Run a packaged JAR:

```bash
java -jar book-service/target/*.jar
```

---

## Project notes & tips ⚠️

- Schemas are located under each service's `src/main/resources/schema/` directory.
- On Windows, use `mvnw.cmd` instead of `./mvnw`.
- If ports conflict, check `src/main/resources/application.properties` of each service.

---

## Contributing & development 💡

- Use feature branches and open a PR for changes.
- Keep GraphQL schema additions backward-compatible when possible.

---

## License

No license specified (add one if you plan to open-source this project).

---

If you want, I can also:
- Add run scripts or Dockerfiles for easier local setup ✅
- Add a single `dev` script to start all services with one command ✅

