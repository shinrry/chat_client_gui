/* Copyright (C) 
 * 2010 - He Anda
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 * 
 */

#include <sys/types.h>
#include <sys/stat.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <netdb.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>

#include "client.h"

/**
 * @brief receiving data from server
 *
 * @param active_socket current socket
 */
void clientd(int active_socket, char buf[])
{    
    memset(buf, 0, sizeof(buf));
    if (recv(active_socket, buf, sizeof(buf), 0) > 0 && buf[strlen(buf)] == '*') {
        printf("in clientd: %s\n", buf);
        return;
    }
}

int connect_init()
{
    char hostname[] = "localhost";
    struct hostent *hp;
    struct sockaddr_in name;
    int ns, len;

    /*get host name.*/
    if (gethostname(hostname, sizeof(hostname)) < 0) {
        perror("gethostname");
        exit(1);
    }

    /*look up our host's network address.*/
    if ((hp = gethostbyname(hostname)) == NULL) {
        fprintf(stderr, "unknown host: %s.\n", hostname);
        exit(1);
    }

    /*create a socket in the INET domain.*/
    if ((ns = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
        perror("socket");
        exit(1);
    }

    /*create the address of the server.*/
    memset(&name, 0, sizeof(struct sockaddr_in));

    name.sin_family = AF_INET;
    name.sin_port = htons(PORT);
    memcpy(&name.sin_addr, hp->h_addr_list[0], hp->h_length);
    len = sizeof(struct sockaddr_in);

    /*connect to the server.*/
    if (connect(ns, (struct sockaddr *) &name, len) < 0) {
        return -1;
    }
    return ns;
}

char login(int socket, const char username[], const char password[])
{
    char message[MSG];

    encap_msg(message, LOGIN, username, password);
    send(socket, message, strlen(message), 0);
    if (recv(socket, message, sizeof(message), 0) > 0) {        
        return message[0];
    }
}

void talk(int self_socket, const char target[], const char sender[], const char content[])
{
    char message[MSG];
    encap_content(message, sender, target, content);
    send(self_socket, message, strlen(message), 0);
}

char reg(int socket, const char username[], const char password[])
{
    char message[MSG];

    encap_msg(message, REGISTER, username, password);
    send(socket, message, strlen(message), 0);
    if (recv(socket, message, sizeof(message), 0) > 0) {
        return message[0];
    }
}

void list(int socket)
{
    char message[2];
    message[0] = LIST;
    message[1] = '\0';
    send(socket, message, strlen(message), 0);
}
