# 🚗 Sistema de Parquímetro 🚗

## 🧪 Como Testar a Aplicação

Para testar a aplicação, siga os passos abaixo:

1. Clone o repositório para sua máquina local:
    ```bash
    git clone https://github.com/aricomputacao/parquimetro-api.git
    ```

2. Navegue até o diretório do projeto:
    ```bash
    cd parquimetro-api
    ```

3. Certifique-se de ter o Docker instalado. Se não tiver, [instale o Docker](https://docs.docker.com/get-docker/).

4. Suba os containers do Docker utilizando o docker-compose:
    ```bash
    docker-compose -f docker-compose.yml up -d
    ```

5. A aplicação estará disponível e pronta para uso. Verifique a documentação específica para acessar os serviços e endpoints.

## 💡 Motivação
A cidade com 300.000 habitantes, recebe um grande fluxo de turistas. Este aumento populacional gera uma demanda significativa por vagas de estacionamento, expondo as limitações do sistema atual, que se mostra ineficiente e incapaz de acompanhar o crescimento da cidade. A implementação de um novo sistema se torna crucial para otimizar a gestão de vagas, melhorar a experiência do usuário e gerar novas fontes de receita para o município.

## 🎯 Objetivos

### Objetivo Geral:
Desenvolver um sistema de parquímetro moderno, eficiente e escalável para gerenciar o estacionamento na cidade, proporcionando aos usuários uma experiência intuitiva e segura, além de fornecer à administração pública ferramentas de controle e acompanhamento em tempo real.

### Objetivos Específicos:
- Permitir o cadastro de condutores e veículos, incluindo informações relevantes para o controle de estacionamento.
- Oferecer diferentes métodos de pagamento (cartão de crédito, débito e PIX), com atenção à restrição do PIX para períodos fixos.
- Implementar um sistema de controle de tempo preciso e confiável, oferecendo opções flexíveis de tempo de estacionamento (fixo e variável).
- Fornecer alertas de tempo de estacionamento para os usuários, incluindo notificações de expiração e renovação automática para períodos variáveis.
- Gerar recibos detalhados das transações, informando o tempo de permanência e o método de pagamento utilizado.
- Fornecer dashboards e relatórios gerenciais para a administração do sistema, possibilitando a análise de dados e a tomada de decisão.

## 🛠 Problemas a serem Resolvidos
O sistema atual apresenta as seguintes deficiências:
- Lentidão e falta de confiabilidade.
- Baixa escalabilidade, tornando-o inadequado para atender à crescente demanda.
- Interfaces de usuário desatualizadas e pouco intuitivas.
- Falta de flexibilidade nas opções de pagamento.
- Dificuldade na geração de relatórios e acompanhamento de receitas.
- Ausência de funcionalidades como alertas de tempo de estacionamento e controle de tempo preciso.

## 💡 Solução Proposta
A solução proposta consiste no desenvolvimento de um novo sistema de parquímetro utilizando tecnologias modernas e eficientes, como Java Spring, para garantir segurança, escalabilidade e flexibilidade. O Sistema será acessível através de diferentes plataformas, incluindo:
- Aplicação móvel: Permitirá aos condutores gerenciar seus veículos, iniciar e finalizar o tempo de estacionamento, realizar pagamentos, configurar alertas e consultar histórico de uso.
- Terminais de autoatendimento: Posicionados estrategicamente nas áreas de estacionamento, permitirão aos usuários efetuar o pagamento, consultar informações e imprimir recibos.

## 🌟 Benefícios Esperados
A implementação do novo sistema trará os seguintes benefícios:
- Melhoria na experiência do usuário: Interface amigável, opções flexíveis de pagamento, alertas de tempo de estacionamento e controle total sobre o tempo de uso.
- Aumento da receita: Controle preciso do tempo de estacionamento e redução de perdas por fraudes ou falhas no sistema.
- Otimização da gestão de vagas: Monitoramento em tempo real da ocupação das vagas e melhor distribuição da demanda.
- Geração de dados para tomada de decisão: Relatórios detalhados sobre o uso do estacionamento para embasar políticas públicas.
- Modernização da infraestrutura tecnológica da cidade.

## 🗂 Domínios e Subdomínios
O Sistema abrange os seguintes domínios e subdomínios:
### Gerenciamento de Cadastrados:
- Cadastro e autenticação de condutores.
- Gerenciamento de veículos.
- Registro de formas de pagamento (cartão ou PIX)
- Histórico de uso e pagamentos.
### Controle de Estacionamento:
- Definição de áreas de estacionamento e horários de funcionamento.
- Gerenciamento de vagas.
- Controle de tempo de estacionamento (início, término, tipos de período).
### Alertas:
- Configuração de alertas de tempo limite.
- Notificações de expiração (fixo) e renovação automática (variável).

## 🗣 Linguagem Ubíqua
- **Condutor**: Usuário do sistema que utiliza o estacionamento.
- **Veículo**: Veículo registrado no sistema e associado a um condutor.
- **Área de estacionamento**: Área delimitada que contém diversas vagas.
- **Tempo de estacionamento**: Período em que um veículo ocupa uma vaga, podendo ser fixo ou variável.
- **Tarifa**: Valor cobrado por hora ou fração de hora de estacionamento.
- **Pagamento**: Transação financeira realizada pelo condutor para quitar o valor do estacionamento.
- **Forma Pagamento**: Tipo do pagamento escolhido pelo condutor “P” = Pix | “C” = Cartão

## 🔄 Fluxo de Funcionamento do Sistema
1. O condutor se registra no sistema, fornecendo informações pessoais.
2. O condutor registra sua forma de pagamento preferida (cartão de crédito, débito ou PIX).
3. O condutor inicia o registro de tempo no sistema, escolhendo entre tempo fixo (indicando a duração desejada) ou por hora.
4. O sistema monitora o tempo de estacionamento e cobra o valor adequado com base nas opções de pagamento selecionadas.
5. Para horários fixos, o sistema emite um alerta quando o tempo está prestes a expirar.
6. Para períodos variáveis, o sistema emite um alerta informando que estenderá automaticamente o estacionamento por mais uma hora, a menos que o condutor desligue o registro.
7. Quando o tempo de estacionamento é encerrado, o sistema emite um recibo para o condutor.

## 🛠 Especificações Técnicas
Para o desenvolvimento do sistema de adoção de pets, propomos as seguintes especificações técnicas:

### Tecnologias:
- Spring Boot como framework principal para o desenvolvimento da aplicação.
- Java
- Spring Data
- Lombok
- Maven
- Docker

### Banco de Dados:
- PostgreSQL

## 👥 Contribuintes

Agradecemos às seguintes pessoas que contribuíram para este projeto:

<table>
  <tr>
   <td align="center"><a href="https://github.com/aricomputacao" target="blank"><img src="https://avatars.githubusercontent.com/aricomputacao" alt="aricomputacao" width="50" /></a><br>@aricomputacao</td>
   <td align="center"<a href="https://github.com/edipojoseoliveira" target="blank"><img src="https://avatars.githubusercontent.com/edipojoseoliveira" alt="edipojoseoliveira" width="50" /></a><br>@edipojoseoliveira</td>
   <td align="center"><a href="https://github.com/Gabrielzc88" target="blank"><img src="https://avatars.githubusercontent.com/Gabrielzc88" alt="Gabrielzc88" width="50" /></a><br>@Gabrielzc88</td>
   <td align="center"><a href="https://github.com/nicolasrds" target="blank"><img src="https://avatars.githubusercontent.com/nicolasrds" alt="nicolasrds" width="50" /></a><br>@nicolasrds</td>
   <td align="center"><a href="https://github.com/yurialves23" target="blank"><img src="https://avatars.githubusercontent.com/yurialves23" alt="yurialves23" width="50" /></a><br>@yurialves23</td>
  </tr>
</table>
