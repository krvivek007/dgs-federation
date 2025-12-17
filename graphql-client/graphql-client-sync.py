from gql import gql, Client
from gql.transport.requests import RequestsHTTPTransport

transport = RequestsHTTPTransport(
    url="http://localhost:8080/graphql",
    #headers={
    #    "Authorization": "Bearer YOUR_TOKEN"
    #},
    verify=True,
    retries=3,
)

client = Client(transport=transport, fetch_schema_from_transport=True)

query = gql("""
  query{
	book(id:1){
		author{
			id
			email
			name
		}
		purchase{
			buyer{
				id
				email
				name
			}
		}
	}
}
""")

response_json = client.execute(query)
#breakpoint()
authors = response_json["book"] ["author"]["name"]  
print(authors)
