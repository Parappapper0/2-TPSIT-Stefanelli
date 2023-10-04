package com.itismeucci.stefanelli;

public class AppServer 
{
    public static void main( String[] args )
    {
        Server server = new Server();
        String inputString;

        server.start(6789, System.out);

        while(true) {

            server.send("Connessione con il server riuscita, sei il client #" + Server.clientNumber + "\n");
            System.out.println("\n" + server.receive());

            while(true) {

                inputString = server.receive();
        
                System.out.println("Ricevuto: " + inputString);
    
                if (inputString.equals("BYE"))
                    break;
        
                server.send(inputString.toUpperCase() + "\n");
            }

            server.send("Connessione terminata\n");
            System.out.println("Connessione con il Client #" + Server.clientNumber + " terminata");
            server.closeClient();
            server.accept();
        }
    }
}
