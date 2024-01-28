# Banco Software

## 📚Informação👨‍🏫👩‍🎓
O projeto foi concebido com intuinto de similar as operações dentro de um sistema de Banco. Atráves desse sistema, foi desenvolvido funcionalidades  como transferência,deposito,estorno..

## Arquitetura Software💻
O projeto foi desenvolvido no padrão MVC (Model-View-Controller) é uma arquitetura de software que divide uma aplicação em três componentes principais para melhorar a organização e a manutenção do código

## Segurança🔐
O sistema utiliza a configuração do Spring Security, onde podemos realizar a autenticação com rotas específicas para usuários de determinado 'role' de acesso. Utilizamos a biblioteca BCryptPasswordEncoder, que faz parte do Spring Security, para criptografar a senha de acesso no banco de dados.



## Credencias de acesso 📧
O sistema utiliza uma API chamada JavaMailsend, desenvolvida pela Oracle, para enviar e-mails. Esta API oferece recursos robustos para enviar e-mails em aplicações Java. O sistema de envio de e-mail foi implementado para comunicar as credenciais de acesso da aplicação.

## Contenização da aplicação 🐳
O sistema foi conteinerizado no Docker, visando facilitar a usabilidade para outros desenvolvedores e evitar conflitos de ambiente.

## Tecnologia🛠️
- Java 17
- Springboot
- Mysql
- JWT.io
- Docker
- phpmyadmin