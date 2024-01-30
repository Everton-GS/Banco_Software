# Banco Software

<img src="https://grupovilario.com.br/images/segmentos/bancos2.jpg" width="900px" height="300px">

## 📚Informação👨‍🏫👩‍🎓
O projeto foi concebido com intuinto de similar as operações dentro de um sistema de Banco. Atráves desse sistema, foi desenvolvido funcionalidades como transferência,deposito,estorno,extrato de conta, bloqueio de conta.

<h3>Funcionalidade do Usuário<br> </h3>   
 
+ Transferência de conta<br>
+ extrato da conta<br>

<h3>Funcionalidade do Gerente<br></h3>

+ Criação de conta funcionário<br>
+ Criação de conta cliente<br>
+ Deposito em conta<br>
+ Bloquear cartão do cliente<br>
+ Estorno de pagamento realizado<br>

<h3>Funcionalidade do Atendente<br></h3>

+ Deposito em conta<br>

## Arquitetura Software💻
O projeto foi desenvolvido no padrão MVC (Model-View-Controller) é uma arquitetura de software que divide uma aplicação em três componentes principais para melhorar a organização e a manutenção do código

## Segurança🔐
O sistema utiliza a configuração do Spring Security, onde podemos realizar a autenticação com rotas específicas para usuários de determinado 'role' de acesso. Utilizamos a classe BCryptPasswordEncoder, que faz parte do Spring Security, para criptografar a senha de acesso no banco de dados. Além disso, a implementação da validação do usuário é realizada por meio do JSON Web Token (JWT).

## Credencias de acesso 📧
O sistema utiliza uma API chamada JavaMailsend, desenvolvida pela Oracle, para enviar e-mails. Esta API oferece recursos robustos para enviar e-mails em aplicações Java. O sistema de envio de e-mail foi implementado para comunicar as credenciais de acesso da aplicação.

```
Obs: É necessário configurar o arquivo application.properties para conseguir enviar e-mails

Configuração de envio de e-mail:spring.mail.username=
                                spring.mail.password=
```
## Contenização da aplicação 🐳
O sistema foi containerizado utilizando o Docker com o objetivo de tornar mais fácil para outros desenvolvedores utilizarem e evitarem conflitos de ambiente. Os programas containerizados incluem um banco de dados MySQL para armazenamento de dados e o phpMyAdmin para gerenciamento do banco de dados.

## Inicialização aplicação
```
 docker-compose up --build -d
```
```
O sistema starta Usuário e Funcionário automaticamente.

Obs:A senha dentro do código é encryptada para a seguranaça da aplicação
```
```
Acesso Gerente:

login:692.812.590-01
senha:Japão
```
```
Acesso Cliente:

login:0001-000001
senha:Espanha
```

## Acesso ao SGBD
```
Rota:http://localhost:8000/

login:root
senha:bp1234
```

## Tecnologia🛠️
- Java 17
- Springboot
- Mysql
- JWT.io
- Docker
- phpmyadmin
