# Desafio-ECommerce

O projeto consiste em um carrinho de compras feito utilizando JAVA e alguma ferramenta de banco de dados (MySQL ou MongoDB);

Nesse projeto, tudo acontecerá atráves do terminal da IDE, de modo que, para interagir, o usuário precisa seguir os passos especificados no prompt.

##Como rodar o programa

O programa possui na sua main toda a logica para criar e popular o banco de dados quando iniciado, o usuário precisa apenas iniciar o MySQL e executar o programa a partir da main.

Em caso de erro ao popular o banco, é necessario que o usuário acesse o arquivo "db.properties" e altera o nome do banco de dados para que este nao entre em conflito com algum banco ja existente.
O arquivo "db.properties" possui o seguinte formato:
"user = default
password = 12345678
dburl=jdbc:mysql://localhost:3306/products
createDatabaseIfNotExist=true
useSSL=false "

Se necessário, o usuário deverá alterar o campo: "dburl=jdbc:mysql://localhost:3306/products"; Onde products refere-se ao nome do banco de dados.
Desse modo, o novo nome ficará: "dburl=jdbc:mysql://localhost:3306/(nome escolhido)";
Além disso, deve-se criar uma conexão ao MySQL e, se necessário, alterar os campos "user" e "password" de modo que estes alinhem ao do seu próprio MySQL.
