# Grundlegendes Image von Apache
FROM httpd:latest

# Apache-Konfigurationsdatei für den Reverse-Proxy erstellen
COPY ./httpd.conf /usr/local/apache2/conf/httpd.conf

# Port 80 freigeben
EXPOSE 80

# Startbefehl für den Apache-Server
CMD ["httpd-foreground"]
