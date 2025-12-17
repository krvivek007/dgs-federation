import asyncio
from http import client
from gql import gql, Client
from gql.transport.aiohttp import AIOHTTPTransport

async def main():
    transport = AIOHTTPTransport(
        url="http://localhost:8080/graphql",
        #headers={"Authorization": "Bearer YOUR_TOKEN"},
    )

    async with Client(
        transport=transport,
        fetch_schema_from_transport=True,
    ) as session:
        query = gql("""
          query{
	    books{
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
        response_json = await session.execute(query)
        #breakpoint()
        authors = [
            book["author"]["name"] for book in response_json["books"] 
        ]
        print(authors)

asyncio.run(main())
