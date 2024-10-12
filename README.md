# Routing - Receiving messages selectively

![image](https://github.com/user-attachments/assets/4c66262e-2945-4b1d-99e9-83ddac8ff253)

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
* Criação um direct exchange.
* Define as filas.
* Associa cada fila ao exchange com uma chave de roteamento específica.
* Publica mensagens no exchange especificando uma chave de roteamento.
* O exchange encaminha as mensagens para a(s) fila(s) correspondente(s) à chave de roteamento.
