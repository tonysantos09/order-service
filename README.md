### GET
curl -X GET \
  http://localhost:8080/order/1 \
  -H 'Postman-Token: a17dd368-90fc-41f9-8b2c-abfed0eebe9d' \
  -H 'cache-control: no-cache'
  
### POST
curl -X POST \
  http://localhost:8080/order \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: dcd37e8f-97ee-4023-bdfa-1f81653c1983' \
  -H 'cache-control: no-cache' \
  -d '{
    "id": 501,
    "email": "xxxx@xxxx.com",
    "nome": "Tony Santos",
    "endereco": "Av XXXXX, 12 - SP/SP",
    "itemPedido": [
        {
            "descricao": "Item descrição 0",
            "qtd": 1,
            "valor": 20
        },
        {
            "descricao": "Item descrição 1",
            "qtd": 1,
            "valor": 40
        },
        {
            "descricao": "Item descrição 2",
            "qtd": 1,
            "valor": 60
        },
        {
            "descricao": "Item descrição 3",
            "qtd": 1,
            "valor": 80
        },
        {
            "descricao": "Item descrição 4",
            "qtd": 1,
            "valor": 100
        }
    ],
    "formaPagamento": "Débito",
    "data": "23/03/2019",
    "status": "Pagamento Aprovado",
    "idOrder": "1",
    "nroCartao": "1234 5678 9012 3456",
    "valCartao": "12/22",
    "cartaoBandeira": "MasterCard"
}'

###PUT
curl -X PUT \
  http://localhost:8080/order/501 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: e5476f85-f144-4bf2-b07b-de43628ddea0' \
  -H 'cache-control: no-cache' \
  -d '{
    "id": 501,
    "email": "xxxx@xxxx.com",
    "nome": "Tony Santos XXX",
    "endereco": "Av XXXXX, 12 - SP/SP",
    "itemPedido": [
        {
            "descricao": "Item descrição 0",
            "qtd": 1,
            "valor": 20
        },
        {
            "descricao": "Item descrição 1",
            "qtd": 1,
            "valor": 40
        },
        {
            "descricao": "Item descrição 2",
            "qtd": 1,
            "valor": 60
        },
        {
            "descricao": "Item descrição 3",
            "qtd": 1,
            "valor": 80
        },
        {
            "descricao": "Item descrição 4",
            "qtd": 1,
            "valor": 100
        }
    ],
    "formaPagamento": "Débito",
    "data": "23/03/2019",
    "status": "Pagamento Aprovado",
    "idOrder": "1",
    "nroCartao": "1234 5678 9012 3456",
    "valCartao": "12/22",
    "cartaoBandeira": "MasterCard"
}'

### DELETE
curl -X DELETE \
  http://localhost:8080/order/501 \
  -H 'Postman-Token: 2d3fbbe2-debb-476b-b47e-e39b0eed632e' \
  -H 'cache-control: no-cache'