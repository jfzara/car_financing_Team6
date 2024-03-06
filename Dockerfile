# Utilise l'image officielle PostgreSQL comme image de base
FROM postgres

# Définit la variable d'environnement pour le nom de la base de données
ENV POSTGRES_DB=postgres

# Définit la variable d'environnement pour l'utilisateur de la base de données
ENV POSTGRES_USER=postgres

# Définit la variable d'environnement pour le mot de passe de l'utilisateur de la base de données
ENV POSTGRES_PASSWORD=postgres

# Indique que le conteneur écoute sur le port 5432
EXPOSE 5432