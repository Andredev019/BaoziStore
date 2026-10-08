# Estrutura do projeto

```text
BaoziStore/
├── pom.xml
├── README.md
├── docker-compose.yml
├── .gitignore
├── postman/
│   └── BaoziStore.postman_collection.json
├── docs/
│   ├── ESTRUTURA.md
│   └── diagramas/
│       ├── diagrama-caso-de-uso.png
│       ├── diagrama-caso-de-uso.mmd
│       └── diagrama-caso-de-uso.dot
└── src/
    └── main/
        ├── java/br/com/baozi/store/
        │   ├── BaoziStoreApplication.java
        │   ├── controller/
        │   │   ├── ClienteController.java
        │   │   ├── PedidoController.java
        │   │   └── ProdutoController.java
        │   ├── model/
        │   │   ├── Cliente.java
        │   │   ├── Pedido.java
        │   │   └── Produto.java
        │   └── repository/
        │       ├── ClienteRepository.java
        │       ├── PedidoRepository.java
        │       └── ProdutoRepository.java
        └── resources/
            └── application.properties
```

## Tecnologias

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- JSON / REST
