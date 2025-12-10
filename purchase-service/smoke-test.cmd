@echo off
REM Simple smoke test for Purchase Service (Windows cmd)
REM Make sure the service is running at http://localhost:8080/graphql

echo Running simple books query...
curl -s -X POST -H "Content-Type: application/json" -d "{\"query\":\"{ books { id title purchase { price } } }\" }" http://localhost:8080/graphql
n
echo.
echo Running Apollo _entities example for Book id=1...
curl -s -X POST -H "Content-Type: application/json" -d "{\"query\":\"{ _entities(representations: [{ __typename: \\\"Book\\\", id: 1 }]) { ... on Book { purchase { price } } } }\" }" http://localhost:8080/graphql
echo.
echo Done.
pause

