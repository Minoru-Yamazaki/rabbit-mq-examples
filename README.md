# Work Queues - Distributing tasks among workers (the competing consumers pattern)

![image](https://github.com/user-attachments/assets/057c6d12-19f3-4377-8950-581637b08fef)

# Publish/Subscribe - Sending messages to many consumers at once

![image](https://github.com/user-attachments/assets/dfdfccb8-f329-46c7-9316-49198a85cecd)

# Routing - Receiving messages selectively

![image](https://github.com/user-attachments/assets/ca6b6220-5c0c-4142-a01e-5e1e88e553b5)

Routing aborda como enviar mensagens para diferentes filas com base em uma chave de roteamento (routing key).
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

Topic Exchange é usado para roteamento de mensagens com base em padrões de chaves de roteamento. O Topic Exchange é mais flexível que o Direct Exchange porque permite o uso de caracteres especiais, como * e #, para definir padrões de roteamento.

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





