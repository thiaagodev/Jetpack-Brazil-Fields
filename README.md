# Jetpack Brazil Fields


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
