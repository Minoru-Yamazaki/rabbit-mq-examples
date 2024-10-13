# Work Queues - Distributing tasks among workers (the competing consumers pattern)

![image](https://github.com/user-attachments/assets/057c6d12-19f3-4377-8950-581637b08fef)

"Work Queues" explora como distribuir tarefas entre vários trabalhadores (consumidores). Esse padrão é útil para balancear a carga de trabalho em vez de sobrecarregar um único consumidor com todas as tarefas.

## Conceitos principais:
1. Fila (Queue):
   As mensagens são enviadas para uma fila, e diferentes consumidores (workers) podem se conectar a essa fila para processar as mensagens. Cada mensagem é entregue a apenas um consumidor.
2. Distribuição de carga (Load Balancing):
   Quando vários consumidores estão conectados à mesma fila, o RabbitMQ distribui as mensagens entre eles. Cada consumidor recebe uma mensagem para processar, ajudando a balancear a carga entre eles.
3. Confirmações de mensagem (Acknowledgments):
   Para garantir que as mensagens não sejam perdidas se um consumidor falhar, é necessário usar confirmações. Uma mensagem é considerada processada com sucesso quando o consumidor envia um "ack" (acknowledgment). Caso contrário, se o consumidor falhar ou a conexão for interrompida, a mensagem será reenviada a outro consumidor.
4. Pre-fetch:
   Você pode configurar o pre-fetch para limitar quantas mensagens um consumidor pode receber sem enviar um acknowledgment. Isso evita sobrecarregar consumidores com muitas mensagens, garantindo que uma nova mensagem só seja enviada após o processamento da atual.
5. Modo de Round-Robin: O RabbitMQ distribui as mensagens em um esquema "round-robin", ou seja, a primeira mensagem vai para o primeiro consumidor, a segunda vai para o segundo, e assim por diante.

### Fluxo:
1. Publisher: Publica mensagens de trabalho na fila.
2. Fila: As mensagens são enfileiradas até que sejam processadas pelos consumidores.
3. Consumers (Workers): São responsáveis por pegar as mensagens da fila e processá-las. Se houver mais de um consumidor, as mensagens serão divididas entre eles.
3. Acknowledgment: Após processar a mensagem, o consumidor envia um "ack" para o RabbitMQ, indicando que a mensagem foi tratada com sucesso.
4. Negative Acknowledgements: Caso ocorra algum erro no fluxo, o consumidor envia um "nack" para o RabbitMQ, indicando que a mensagem não foi consumida.
5. Reject: Também há a opção de rejeitar a mensagem e postá-la em uma fila DLQ (Dead Letter Queue), assim como no exemplo do "Listener 1". 

### Exemplo de uso:
Esse padrão é ideal para dividir a carga de trabalho em sistemas de processamento paralelo, onde várias tarefas independentes precisam ser processadas de forma eficiente, como processamento de imagens, envio de emails em massa, ou qualquer tipo de processamento intensivo.

# Publish/Subscribe - Sending messages to many consumers at once

![image](https://github.com/user-attachments/assets/dfdfccb8-f329-46c7-9316-49198a85cecd)

"Publish/Subscribe", utiliza um Fanout Exchange para enviar mensagens a múltiplos consumidores. Esse padrão é muito útil para disseminar uma mensagem para todos os consumidores conectados, sem se preocupar com a chave de roteamento.

### Conceitos principais:
1. Fanout Exchange: O Fanout Exchange envia todas as mensagens para todas as filas que estão ligadas a ele, independentemente de qualquer chave de roteamento. Isso significa que qualquer mensagem publicada nesse exchange será copiada para todas as filas conectadas.
2. Filas: As filas que recebem as mensagens são criadas pelos consumidores, e cada consumidor pode criar sua própria fila para receber mensagens.
3. Binding: A ligação (binding) entre o Fanout Exchange e as filas garante que todas as mensagens publicadas no exchange sejam entregues a todas as filas conectadas.
4. Consumidores: Diferentes consumidores podem escutar diferentes filas. Cada um deles recebe a mesma mensagem, mas a processa de forma independente.

### Fluxo:
1. Publisher: Envia mensagens para o Fanout Exchange.
2. Fanout Exchange: O exchange recebe a mensagem e a distribui para todas as filas conectadas a ele, sem precisar de uma chave de roteamento.
3. Filas: Todas as filas conectadas ao exchange receberão uma cópia da mensagem.
4. Consumers: Os consumidores associados a essas filas receberão as mensagens e poderão processá-las.

### Exemplo de uso:
Este padrão de Publish/Subscribe é ideal para cenários onde você deseja enviar uma mensagem a vários receptores ao mesmo tempo, como em sistemas de notificações, logs em tempo real, ou atualizações de broadcast.

### Conclusão:
O Fanout Exchange oferece uma forma simples de disseminar uma mensagem para vários consumidores ao mesmo tempo, garantindo que todos eles recebam a mesma informação.

# Routing - Receiving messages selectively

![image](https://github.com/user-attachments/assets/ca6b6220-5c0c-4142-a01e-5e1e88e553b5)

"Routing" é usado para enviar mensagens para diferentes filas com base em uma chave de roteamento (routing key).
No padrão de troca "direct exchange", as mensagens são roteadas para filas específicas conforme a chave de roteamento que você define ao publicá-las.

### Conceitos principais:
1. Direct Exchange:
   Este tipo de troca (exchange) envia mensagens para as filas cuja chave de roteamento exata corresponda à chave especificada na mensagem.
2. Binding:
   As filas são associadas ao exchange por meio de "bindings", que especificam uma chave de roteamento.
   Uma fila pode estar ligada a um exchange com diferentes chaves de roteamento, e um exchange pode enviar mensagens para diferentes filas dependendo da chave.
3. Routing Key:
   É uma string usada pelo exchange para determinar qual fila deve receber a mensagem.
   A chave de roteamento é especificada tanto no envio da mensagem quanto na definição do binding.
4. Filas associadas a chaves diferentes:
   O exemplo deste projeto usa 3 tipos de mensagens: logs de "erro" de "info" e "warning".
### Fluxo:
1. Criação um direct exchange.
2. Define as filas.
3. Associa cada fila ao exchange com uma chave de roteamento específica.
4. Publica mensagens no exchange especificando uma chave de roteamento.
5. O exchange encaminha as mensagens para a(s) fila(s) correspondente(s) à chave de roteamento.

# Topics - Receiving messages based on a pattern (topics)

![image](https://github.com/user-attachments/assets/ca94cfcd-d251-446f-a28f-aa1d0ba7decd)

"Topic Exchange" é usado para roteamento de mensagens com base em padrões de chaves de roteamento. O Topic Exchange é mais flexível que o Direct Exchange porque permite o uso de caracteres especiais, como * e #, para definir padrões de roteamento.

### Conceitos principais:

1. Topic Exchange:
   Diferente do Direct Exchange, onde a chave de roteamento deve ser exata, no Topic Exchange você pode usar padrões para combinar com várias chaves de roteamento. Isso é útil quando você deseja maior flexibilidade no roteamento das mensagens.
2. Routing Key:
   É uma string que representa um caminho ou categoria para a mensagem. No Topic Exchange, a chave de roteamento é dividida em palavras separadas por pontos (.).
3. Padrões de roteamento:
   * O caractere * substitui exatamente uma palavra.
   * O caractere # substitui zero ou mais palavras.
4. Binding:
   Como no exemplo anterior, as filas são ligadas ao exchange com uma chave de roteamento, mas desta vez usamos padrões de roteamento. Assim, uma fila pode receber mensagens de várias chaves que seguem um determinado padrão.

### Fluxo:
1. Exchange: O Topic Exchange é criado.
2. Filas e padrões de chave de roteamento:
   * Uma fila recebe todas as mensagens relacionadas a "logs de erro" com a chave de roteamento "*.error".
   * Outra fila recebe mensagens de todos os logs ("#"), incluindo info, warning e error.
3. Mensagens com chaves de roteamento:
   * Mensagens são publicadas com chaves de roteamento como "kern.error", "app.info", "app.warning" etc.
   * A fila que escuta para "*.error" receberá a mensagem "kern.error", mas não "app.info".
   * A fila que escuta para "#" receberá todas as mensagens.
### Padrões de Chaves de Roteamento:
   * "kern.*": Recebe todas as mensagens com qualquer coisa após "kern", como "kern.info" ou "kern.error".
   * "*.critical": Recebe mensagens em que a última palavra seja "critical".
   * "#": Recebe todas as mensagens, independentemente da chave de roteamento.
### Exemplo de uso:
Esse tipo de roteamento é muito útil quando você quer categorizar mensagens em tópicos e precisa que diferentes consumidores tratem tipos de mensagens específicos, sem a necessidade de configurar filas para cada chave de roteamento exata.

### Conclusão:
O Topic Exchange oferece um roteamento muito mais flexível, permitindo padrões dinâmicos. Isso é útil para sistemas onde diferentes serviços ou componentes precisam escutar mensagens de várias categorias de uma forma mais geral, como logs de erro, de sistemas ou de aplicações.

# RPC - Request/reply pattern example

![image](https://github.com/user-attachments/assets/72e2dfe7-4240-4d62-bebf-5ee9139ac84f)

"Remote Procedure Call (RPC)" permite que você faça chamadas a métodos ou funções em um servidor remoto e receba uma resposta, como se estivesse chamando um método localmente.

### Conceitos principais:
1. RPC (Remote Procedure Call): A ideia principal do RPC é permitir que uma aplicação (cliente) envie uma requisição para um servidor remoto executar uma tarefa específica e, em seguida, aguarde a resposta.
2. Fila de requisição (RPC Queue): O cliente envia uma mensagem de solicitação (request) para uma fila. Essa mensagem contém as informações sobre a tarefa a ser realizada.
3. Servidor RPC (RPC Server): O servidor fica escutando a fila de requisição. Quando ele recebe uma mensagem, ele processa a solicitação e envia de volta uma resposta.
4. Fila de resposta (Callback Queue): O cliente cria uma fila temporária (fila de resposta) para receber a resposta da tarefa. Essa fila é exclusiva para o cliente, garantindo que ele receba a resposta correta.
5. Correlações (Correlation ID): Para garantir que a resposta que o cliente recebe corresponde à requisição enviada, o cliente inclui um Correlation ID em cada mensagem de requisição. Quando o servidor responde, ele copia o Correlation ID na resposta, permitindo que o cliente relacione a resposta com a requisição original.
6. Blocking: O cliente envia a requisição e fica aguardando de forma "bloqueante" até que a resposta seja recebida, simulando o comportamento de uma chamada de função normal.

### Fluxo:
1. Cliente (RPC Client):
   * Envia uma mensagem de requisição para a fila RPC, especificando a tarefa a ser realizada (por exemplo, calcular um Fibonacci).
   * Cria uma fila temporária para receber a resposta.
   * Aguarda pela resposta na fila temporária.
2. Servidor (RPC Server):
   * Escuta a fila RPC.
   * Quando uma solicitação chega, o servidor processa a tarefa (exemplo: cálculo de Fibonacci).
   * Envia a resposta de volta para a fila temporária do cliente.
3. Correlações:
   * Cada requisição possui um Correlation ID exclusivo.
   * O servidor copia esse Correlation ID na resposta, permitindo ao cliente saber qual requisição gerou a resposta.

### Exemplo de uso:
Esse padrão é útil para casos em que você precisa executar tarefas no servidor e esperar por uma resposta, como em sistemas distribuídos, microserviços ou quando uma tarefa complexa deve ser descarregada para um servidor remoto.

### Conclusão:
O RPC com RabbitMQ é uma maneira eficaz de implementar chamadas de procedimento remoto, possibilitando que um cliente envie uma solicitação de tarefa a um servidor e receba a resposta correspondente. Esse padrão permite a execução remota de funções enquanto mantém a semântica de chamadas síncronas.
