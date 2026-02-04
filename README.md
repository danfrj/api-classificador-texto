#  API Classificador de Texto

API desenvolvida com **Spring Boot 3** que funciona como um filtro de conteúdo inteligente. Ela analisa textos recebidos via requisições REST e identifica automaticamente padrões de **SPAM** ou **conteúdos ofensivos**.

##  Tecnologias Utilizadas
* **Java 17**
* **Spring Boot 3**
* **Maven** (Gerenciamento de dependências)
* **Lombok** (Produtividade no código)
* **Spring Web** (Criação de endpoints REST)

##  Como Funciona?
A API expõe um endpoint `POST` que recebe um JSON contendo o texto. O sistema processa a string, converte para caracteres minúsculos (para evitar burlas com letras maiúsculas) e verifica a presença de termos bloqueados em uma lista predefinida (Blacklist).

### Exemplo de Termos Filtrados:
* "ganhe dinheiro"
* "clique aqui"
* "oferta imperdivel"
* "spam"

##  Como Testar
Com a aplicação rodando, você pode testar via Terminal (PowerShell) usando o seguinte comando:

```powershell
$meuTexto = @{conteudo='Olá, clique aqui para ganhar prêmios!'} | ConvertTo-Json
$buffer = [System.Text.Encoding]::UTF8.GetBytes($meuTexto)
Invoke-RestMethod -Uri http://localhost:8080/api/classificar -Method Post -Body $buffer -ContentType "application/json; charset=utf-8"
