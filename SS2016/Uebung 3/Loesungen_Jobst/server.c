#include <sys/socket.h>
#include <sys/types.h>
#include <unistd.h>      // close()-Operation
#include <netinet/in.h>  // Umwandlung host-byte-order <--> network-byte-order
#include <stdio.h>
#include <stdlib.h>      // atoi
#include <string.h>      // memset
#include <arpa/inet.h>
#include <netdb.h>

int main(int argc, char *argv[]) {

	int socket_fd, bind_result, listen_result;
    int port = 1234;
    
    // Port aus Konsole übernehmen
    if(argc >= 2)
        port = atoi(argv[1]);
    printf("Server auf localhost für Port %d \n", port);
	
	// Socket
	socket_fd = socket(AF_INET, SOCK_STREAM, 0);
	if(socket_fd == -1)
		perror("Socket konnte nicht geöffnet werden!");

	// Bind
	struct sockaddr_in server;
	memset( &server, 0, sizeof (server));
	server.sin_family = AF_INET; // IPv4
	server.sin_addr.s_addr = htonl( INADDR_ANY );
	server.sin_port = htons( port );

	bind_result = bind( socket_fd, (struct sockaddr*)&server, sizeof(server) );
	if(bind_result != 0)
		perror("Bind nicht erfolgreich");

	// Listen (Warteschlange für eingehende Verbindungen errichten)
	listen_result = listen(socket_fd, 5); // 5 Verbindungen gleichzeitig
	if(listen_result != 0)
		perror("Listen nicht erfolgreich");
	
	// Akzeptieren
	while(1) {
		int socket_fd_neu;
		int i;
		struct sockaddr_in client;
		socklen_t length;
		char eingang[50];
		ssize_t bytes_received;
		
		printf("Warten auf Verbindung...\n");
		socket_fd_neu = accept(socket_fd, (struct sockaddr*)&client, &length);
		
		printf("Neue Verbindung akzeptiert von %lu	 \n", (unsigned long)client.sin_addr.s_addr);
		
		// Empfangen
		do {
			bytes_received = recv(socket_fd_neu, (char*)eingang, sizeof(eingang), 0);
			printf("\nEmpfangen: ");	
			for(i=0; i<(int)bytes_received; i++){
				printf("%c", eingang[i]);
			}
		} while(bytes_received == sizeof(eingang));
		printf("\n\n");
	}
	

	close(socket_fd);
	
	return 0;
}
