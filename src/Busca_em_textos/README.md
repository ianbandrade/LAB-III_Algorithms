# Busca em textos

## O que é

Busca por alguma palavra ou elemento dentro de uma sequência de caracteres. A ideia central é encontrar um padrão entre caracteres.

### Padrão

Padrão é diferente de String. String é uma série de caracteres. Mas um padrão pode ser uma String ou uma caractere. 

## Problema

### Força bruta clássica

Percorrer o texto e ir comparando a primeira caractere com o padrão indicado. Caso a primeira caractere for idêntica, segue a comparação entre a caractere do texto e o padrão, até achar o padrão. Se alguma caractere do texto não coincidir com alguma caractere do padrão, irá para a próxima palavra, no caso de texto, por exemplo.

### Boyer Moore

Boyer e Moore (1977) propuseram uma nova abordagem para o problema: por que não começar de no sentido da esquerda para a direita? 

Faz a comparação entre o primeiro caractere do elemento do texto com o último caractere do padrão. Dessa forma, se a comparação for inválida, o padrão seria deslocado para a próxima caractere do texto, até que, para ser encontrado, o padrão tem de estar emparelhado com a palavra do texto e ser idêntica a ela. 

### Horspool

Tem como base o algoritmo de Boyer Moore.

Horspool (1980) notou que o alinhamento poderia ser com qualquer posição. Por quê não a última? Sunday (1990) perguntou: por quê não depois da última?

Continua fazendo a comparação entre o primeiro caractere do elemento do texto com o último caractere do padrão. No entanto, quando constata-se que caractere do texto que está sendo comparada é diferente do padrão, será deslocada a quantidade de bits equivalente ao padrão, no  texto, e a próxima caractere do padrão será comparada com a próxima caractere do texto (já contando o deslocamento).

Trabalha com o sufixo da palavra. É feita apenas uma comparação, se esta comparação não for exata, será descartada tal possibilidade.

O alinhamento é feito da esquerda para a direita.

### Algoritmos

1. searchBF
2. searchBMHS
