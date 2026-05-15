CCM310 – Arquitetur de Software e Programação Orientada a Objetos

Professores: Profa. Gabriela Biondi e Prof. Isaac de Jesus
Aluno: Gabriel Caldeira
RA: 22.125.034-3
Data: Maio/2026

Link do vídeo demonstrativo: https://youtu.be/WDRK8kPDAdc

1. Descrição do Projeto

O projeto FEItv consiste no desenvolvimento de uma plataforma para a visualização de videos (Filmes e Series), 
com funcionalidades de curtir e descurtir e manipulação de listas do usuario.
Utilizando:
-Java
-MVC
-Postgre
-JDBC
-Swing

2. Arquitetura

-Model

Responsável pelas regras de negócio e representação dos dados.

Principais classes:

Video (classe abstrata)
Filme
Serie
Usuario
ListaReproducao
DAOs para acesso ao banco de dados

A classe Video é abstrata e serve como base para Filme e Serie, aplicando o conceito de herança.

Os DAOs são responsáveis por realizar as operações no banco de dados utilizando JDBC.

-View

Responsável pelas interfaces gráficas desenvolvidas com Swing.

Principais telas:

Tela de Cadastro
Tela de Login
Tela Principal
Tela de Busca de Vídeos
Tela de Favoritos
Tela de Administração (caso implementado)

As interfaces foram desenvolvidas buscando facilitar a navegação do usuário.

-Controller

Responsável por intermediar a comunicação entre Model e View.

Principais controles:

Controle de Login
Controle de Usuário
Controle de Vídeos
Controle de Curtidas
Controle de Listas de Reprodução

O Controller recebe as ações da interface, executa as regras de negócio e atualiza a View.

-DAO

Responsável por guardas as classes e intens que se conectam com o banco de dados:

Principais arquivos:

Conexao.java
UsuariosDAO.java
VideosDAO.java
CurtidasDAO.java
ListaDAO.java
ListaVideoDAO.java

3. Funcionalidades do Usuário

O sistema implementa as seguintes funcionalidades:

Cadastro de novo usuário
Login de usuário
Busca de vídeos por nome
Listagem de informações dos vídeos
Curtir e descurtir vídeos
Criar lista de reprodução
Editar lista de reprodução
Excluir lista de reprodução
Adicionar vídeos à lista de favoritos
Remover vídeos da lista de favoritos

Cada operação realiza atualização direta no banco de dados via JDBC.

4.Banco de Dados

O banco de dados foi modelado no PostgreSQL contendo as seguintes tabelas principais:

usuario
video
filme
serie
lista_reproducao
curtidas
lista_videos

Assim, não perdendo nenhum tipo de informação
