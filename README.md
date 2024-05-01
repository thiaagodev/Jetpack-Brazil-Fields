# Jetpack Brazil Fields

## Validação e formatação de CPF e CNPJ

A lib possui validadores de CPF e CNPJ, que podem ser utilizados chamando suas classes validadoras:

```
val cpf = "11395212090" // Pode ser passado tanto com máscara quanto sem
val cpfIsValid = CPFValidator.isValid(cpf)

val cnpj = "20334958000126" // Pode ser passado tanto com máscara quanto sem
val cnpjIsValid = CNPJValidator.isValid(cnpj)
```

Ou também você pode utilizar as extensions para String da seguinte forma:

```
val cpf = "11395212090" 
val cpfIsValid = cpf.isCPF()

val cnpj = "20334958000126" 
val cnpjIsValid = cnpj.isCNPJ()
```

Além da validação, a biblioteca conta com métodos para aplicar e remover as máscaras de CPF e CNPJ:

```
val cpf = "11395212090".applyCPFMask() // retorno "113.952.120-90"
cpf.unmaskCPF() // retorno "11395212090"

val cnpj = "20334958000126".applyCNPJMask() // retorno "20.334.958/0001-26"
cnpj.unmaskCNPJ() // retorno "20334958000126"
```

## Máscaras de input

Para aplicar máscaras nos seus inputs, basta utlizar as classes de VisualTransformation disponíveis:

```
// No seu TextField passe o parâmetro visualTransformation 

visualTransformation = CPFVisualTransformation() // Máscara de CPF
visualTransformation = CNPJVisualTransformation() // Máscara de CNPJ

visualTransformation = MoneyVisualTransformation() // Máscara para valores monetários EX: (Ao digitar 1200 o input irá mostrar: 12,00)

// O MoneyVisualTransformation pode receber um parâmetro currencySymbol para adicionar o símbolo de moeda
// EX: visualTransformation = MoneyVisualTransformation(currencySymbol="R$") teremos R$12,00
```

## Como instalar

Primeiramente, é preciso configurar a autenticação do Github Packages, para isso, gere um token pessoal com todas as permissões, 
em seguida crie um arquivo `~/.m2/settings.xml` e insira as seguintes informações, ou caso já possua o arquivo, adicione somente o repository:

```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">

  <activeProfiles>
    <activeProfile>github</activeProfile>
  </activeProfiles>

  <profiles>
    <profile>
      <id>github</id>
      <repositories>
        <repository>
          <id>central</id>
          <url>https://repo1.maven.org/maven2</url>
        </repository>
        <repository>
          <id>github</id>
          <url>https://maven.pkg.github.com/thiaagodev/Jetpack-Brazil-Fields</url>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
        </repository>
      </repositories>
    </profile>
  </profiles>

  <servers>
    <server>
      <id>github</id>
      <username>USER</username>
      <password>TOKEN</password>
    </server>
  </servers>
</settings>
```
Na tag `servers` substitua `USER` e `TOKEN` por suas credenciais.

Feito isso, basta adicionar a dependência `dev.thiaago:jetpackbrazilfields:<Version>`
