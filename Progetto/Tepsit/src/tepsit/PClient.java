package tepsit;

import java.net.*;
import java.io.*;
import java.util.*;

public class PClient {

    public PClient(String address, int port, int service){
        
        try{
            // CREO IL SOCKET
            Socket client = new Socket(address, port);
            
            // CREO LO STREAM SUL SOCKET
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
            
            // SCRIVO SULLO STREAM DEL SOCKET
            out.println(service);
            
            // RICEVO IL MESSAGGIO DALLO STREAM DEL CLIENT
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            switch(service){
            case 1:
                
                System.out.println("Inserire email");
                Scanner sc_email = new Scanner(System.in);
                String email = sc_email.nextLine();
                
                System.out.println("Inserire password");
                Scanner sc_password=new Scanner(System.in);
                String password=sc_password.nextLine();
                
                System.out.println("Inserire nome");
                Scanner sc_nome=new Scanner(System.in);
                String nome=sc_nome.nextLine();
                
                System.out.println("Inserire cognome");
                Scanner sc_cognome=new Scanner(System.in);
                String cognome=sc_cognome.nextLine();
                
                System.out.println("Inserire cellulare");
                Scanner sc_cellulare=new Scanner(System.in);
                String cellulare=sc_cellulare.nextLine();
                
                System.out.println("Inserire data di nascita (es aaaa-mm-gg) ");
                Scanner sc_data=new Scanner(System.in);
                String datanascita=sc_data.nextLine();
                
                System.out.println("Inserire indirizzo");
                Scanner sc_indirizzo=new Scanner(System.in);
                String indirizzo=sc_indirizzo.nextLine();
                
                System.out.println("Inserire città");
                Scanner sc_citta=new Scanner(System.in);
                String citta=sc_citta.nextLine();
                
                System.out.println("Inserire ruolo ( acquirente[001] / magazziniere[002] )");
                Scanner sc_ruolo=new Scanner(System.in);
                String ruolo=sc_ruolo.nextLine();
                
                out.println(email);
                out.println(password);
                out.println(nome);
                out.println(cognome);
                out.println(cellulare);
                out.println(datanascita);
                out.println(indirizzo);
                out.println(citta);
                out.println(ruolo);
                System.out.println("Risposta dal server: "+in.readLine());
                break;  
                
                
                
            case 2:System.out.println("Inserire email");
                Scanner sc_email2=new Scanner(System.in);
                String email2=sc_email2.nextLine();              
                out.println(email2);
                System.out.println("Inserire password");
                Scanner sc_password2=new Scanner(System.in);
                String password2=sc_password2.nextLine();
                out.println(password2);
                System.out.println("Risposta dal server");
                String str;
                str = in.readLine();
                    while(!str.equals("#")){
                        System.out.println(str);
                        str = in.readLine(); 
                    }
                
                break;
                
                
                
                
            case 3:
                
                System.out.println("Inserire email");
                Scanner sc_email3=new Scanner(System.in);
                String email3=sc_email3.nextLine();              
                out.println(email3);
                System.out.println("Inserire password");
                Scanner sc_password3=new Scanner(System.in);
                String password3=sc_password3.nextLine();
                out.println(password3);
                System.out.println("Inserire id merce");
                Scanner sc_merce3=new Scanner(System.in);
                String merce3=sc_merce3.nextLine();
                System.out.println("Inserire quantità");
                Scanner sc_quantita3=new Scanner(System.in);
                String quantita3=sc_quantita3.nextLine();
                out.println(merce3);
                out.println(quantita3);
                System.out.println("Risposta dal server: ");
                System.out.println(in.readLine());
                System.out.println(in.readLine());
                
                break;
                
                
            default:
                
                client.close();
                break;    
        }
        
 
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String args[]){
        
        System.out.println("Qual è l'indirizzo del server?");
        Scanner sc_address = new Scanner(System.in);
        String address = sc_address.nextLine();
        
        System.out.println("Qual è la porta su cui il server offre il servizio?");
        Scanner sc_port = new Scanner(System.in);
        String str_port = sc_port.nextLine();
        int service = 0;
        while(service != 4 )
        {
        System.out.println("Qual è il servizio che richiedi?");
        System.out.println("1:Inserire utente");
        System.out.println("2:Visualizzare merce");
        System.out.println("3:Acquistare merce");
        System.out.println("4:Chiudi sessione");
        Scanner sc_service = new Scanner(System.in);
        service = sc_service.nextInt();
        
        int port = Integer.parseInt(str_port);
        new PClient(address, port, service);
        }

        
    }
    
}