const { ApolloServer, gql } = require('apollo-server');
const {ApolloGateway, IntrospectAndCompose} = require('@apollo/gateway')

const gateway = new ApolloGateway({
    supergraphSdl: new IntrospectAndCompose({
        subgraphs: [
            { name: 'book', url: 'http://localhost:8081/graphql' },
            { name: 'purchase', url: 'http://localhost:8082/graphql' },
        ]
    })
});

const server = new ApolloServer({ gateway, subscriptions:false, tracing:true });
const PORT = 8080; 
server.listen(PORT, () => {
  console.log(`🚀 Server running on http://localhost:${PORT}/graphql`);
});