#include <sys/socket.h>
#include <sys/types.h>
#include <unistd.h>      // close()-Operation
#include <netinet/in.h>  // Umwandlung host-byte-order <--> network-byte-order
#include <stdio.h>
#include <stdlib.h>      // atoi
#include <string.h>      // memset
#include <netdb.h>       // gethostbyname
#include <arpa/inet.h>   // inet_addr

int main(int argc, char *argv[]) {
    
    int socket_fd, connect_result;
    int port = 1234;
    char default_host[] = "localhost";
    char* host = default_host;
    struct hostent *ziel;
    struct sockaddr_in server;
    unsigned long ip_adresse;
    
    // Host und Port aus Konsole übernehmen
    if(argc >= 3) {
        host = argv[1];
        port = atoi(argv[2]);
    }
    printf("Client für Verbindung zu %s:%d \n", host, port);

    
    if ((ip_adresse = inet_addr( host )) != INADDR_NONE) {
        memcpy( (char *)&server.sin_addr, &ip_adresse, sizeof(ip_adresse));
    }
    else {
        // hostname
        ziel = gethostbyname(host);
        if (ziel == NULL)
            perror("Hostname unbekannt");
        memcpy( (char *)&server.sin_addr, ziel->h_addr, ziel->h_length );
    }
    server.sin_family = AF_INET; // IPv4
    server.sin_port = htons( port );

    
    socket_fd = socket(AF_INET, SOCK_STREAM, 0);
    connect_result = connect(socket_fd, (struct sockaddr*)&server, sizeof(server));
    if(connect_result < 0)
        perror("Keine Connection möglich");
    
    send(socket_fd, "Hallo Welt", 11, 0);
    
    close(socket_fd);
    
    
    return 0;
}