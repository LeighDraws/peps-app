# ----------- Stage build (utilisé par prod) ----------- #

# 1 Image de Maven Eclipse Temurin pour le build -- peut compiler et exécuter des applications Java
FROM maven:eclipse-temurin AS build

WORKDIR /app

# Copie du pom.xml et téléchargement des dépendances (optimise le cache)
COPY pom.xml .

# Dependency go-offline télécharge toutes les dépendances nécessaires pour le projet -- pas obligatoire mais optimise le build -B (passe les questions interactives)
RUN mvn dependency:go-offline -B

# Copie du code source
COPY src ./src

# Compilation du projet (compile les tests, mais ne les exécute pas)
RUN mvn clean package -DskipTests

# ----------- Stage dev (hot reload) -----------
FROM maven:eclipse-temurin AS dev
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

# on copie le code pour initialiser (mais volume montera par-dessus)
COPY src ./src

# devtools: auto-reload
CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.profiles=dev", "-Dspring.devtools.restart.enabled=true", "-Dspring.devtools.livereload.enabled=true"]


# Étape 2 : runtime avec un JRE léger au lieu du JDK complet
FROM eclipse-temurin:21-jre-alpine AS prod

WORKDIR /app

# Copie du jar depuis l'étape build, il est dans le dossier target (from build psq nommé build plus haut sinon nommé 0)
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]





