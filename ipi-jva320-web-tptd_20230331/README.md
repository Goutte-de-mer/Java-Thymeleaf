# TD et TP IPI JVA320 - Application web Java (Servlet, Spring MVC, Thymeleaf)

## Pré-requis

- Avoir installé un IDE :
    - IntelliJ Ultimate, avec votre adresse IPI sur Jetbrains Student à https://www.jetbrains.com/student/
    - sinon Eclipse, à https://www.eclipse.org/downloads/packages/release/2022-09/r/eclipse-ide-java-developers
- Savoir utiliser Git et les branches (utilisez les capacités Git de votre IDE ou installez-le séparément depuis
  https://git-scm.com/download/win ). Quelques liens :
    - https://learngitbranching.js.org/
    - https://git-scm.com/book/en/v2/Git-Branching-Basic-Branching-and-Merging
- Avoir un compte Github. Voici comment y configurer l'authentification de git par clé SSH :
    - https://docs.github.com/en/authentication/connecting-to-github-with-ssh
    - https://docs.github.com/en/authentication/connecting-to-github-with-ssh/adding-a-new-ssh-key-to-your-github-account
- Connaître les bases du développement Java avec Maven (la persistence avec JPA est également utilisée ponctuellement),
  et au moins comment importer et compiler un projet Java dans l'IDE :
    - IntelliJ :
        - Installation de Git : Git > git not installed > Donwload and install
        - Cloner un projet Github : Git > Clone
        - Configuration d'un projet Maven : clic droit sur pom.xml > Add as Maven project ou bien voir IV-B-2 à https://damienrieu.developpez.com/tutoriel/java/nouveautes-intellij-12/?page=page_1
        - Installation de Java : par exemple
            - erreur ne trouve pas le symbol "java" : clic droit sur pom.xml > Build > sur Setup DSK choisir Configure > choisir Download et install
            - "Error running..." : Project JDK is not specified > Configure... > no SDK > Add SDK > Download
        - lancer un build maven complet : Run > Edit configurations > Maven > Create configuration > mettre Working directory au dossier du projet et dans Command line, écrire : clean install
        - problème de sécurisation de connexion :
          (Maven error : unable to find valid certification path to requested targetmaven unable to find valid certification path to requested target
          ou
          unable to access 'https://github.com/mdutoo/ipi-jva350-tptd.git/': SSL certificate problem: unable to get local issuer certificate)
          mvn clean package -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true
          ou dans IntelliJ Run > Edit Configurations > Java Options (sans -D) : maven.wagon.http.ssl.insecure=true maven.wagon.http.ssl.allowall=true
          comme dit à https://stackoverflow.com/questions/45612814/maven-error-pkix-path-building-failed-unable-to-find-valid-certification-path
    - sinon Eclipse : voir https://thierry-leriche-dessirier.developpez.com/tutoriels/java/importer-projet-maven-dans-eclipse-5-min/
- Avoir installé postgresql (ou mysql) : https://www.postgresql.org/download/

## Créer la base de données

### PostgreSQL

Créer l'utilisateur "ipi" :

en tant qu'administrateur (sous Windows : recherche "cmd" dans les applications et dessus clic droit > "Run as admin", sous linux : sudo su - postgres) :

    $> psql
    $postgresql> create user ipi with password 'ipi' createdb;
    $postgresql> \q

Créer la base de données "ipi_jva320_web" :

	$> psql -U ipi postgres -h localhost
	$postgresql> create database ipi_jva320_web encoding 'UTF8';
    $postgresql> \q

Vérifier que l'utilisateur créé peut bien se connecter à cette base :

	$> psql -U ipi ipi_jva320_web -h localhost

Configurer l'application pour s'en servir : dans ```main/resources/application.properties```, décommenter les lignes sous "postgresql - clean setup".


## Exécution

lancer la classe com.ipi.jva320.Jva320Application
- dans l'IDE
  - IntelliJ : l'ouvrir et cliquer sur la flèche verte sur sa gauche
  - Eclipse : clic droit > Run as application),
- avec maven (IDE ou ligne de commande) : ```mvn spring-boot:run```

Puis pointer le navigateur web à http://localhost:8080/ , ou pour afficher une page sans rendu Thymeleaf par exemple http://localhost:8080/home.html .


## Développement

Voici l'organisation du code source de l'application :
- code Java de l'application : dans le package com.ipi.jva320
- Controllers Spring MVC (à développer) : dans le sous-package web
- template Thymeleaf (à compléter) : dans main/resources/templates
- configuration : main/resources/application.properties