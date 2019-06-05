package tepsit;

import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import static tepsit.Server.DB_DRV;
import static tepsit.Server.resultSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author ChristianP01
 */
public class PThread extends Thread{
    
    Socket client;
    BufferedReader in;
    PrintWriter out;
    File xmlFile;
    Document doc;
    static final String DB_URL = "jdbc:mysql://localhost/magazzinosupermercato";  //collegamento a PhpMyAdmin
    static final String DB_DRV = "com.mysql.jdbc.Driver";  //driver per connettersi a PhpMyAdmin
    static final String DB_USER = "root";
    static final String DB_PASSWD = "";
    static Connection connection = null;
    static Statement statement = null;
    static ResultSet resultSet = null;    
    
    // COSTRUTTORE
    
    public PThread(Socket client, File xmlFile, Document doc){
        this.client = client;
        this.xmlFile = xmlFile;
        this.doc = doc;
    }
    
    
    public void run(){
       
        try{
            
            // RICEVO IL MESSAGGIO DALLO STREAM DEL CLIENT
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
            Node root = doc.getFirstChild();
            NodeList newUser2 = ((Element)root).getElementsByTagName("Utente");

            // RICEVO IL DATO INVIATO DAL CLIENT
            String mess = in.readLine();
            
            String risposta = "";
            int flag_default = 0;
                
                if(mess.equals("1"))
                {
                    /* Permette ad un nuovo utente di iscriversi inviando i propri dati
                    al Server che procede a memorizzarli nella propria banca dati */
                    
                    String eMail = in.readLine();  
                    String pass = in.readLine();
                    String nome = in.readLine();
                    String cognome = in.readLine();
                    String cell = in.readLine();
                    String datanascita = in.readLine();
                    String indirizzo = in.readLine();
                    String citta = in.readLine();
                    String ruolo = in.readLine();
                    
                    
                   //Inserisco i valori ottenuti dal client su XML
                    
                    
                    Element newUser = doc.createElement("Utente");
            
            
                    Element mail = doc.createElement("email");
                    mail.setTextContent(eMail);
                    newUser.appendChild(mail);
                    
                    
                    
                    
            
                    Element password = doc.createElement("password");
                    password.setTextContent(pass);
                    newUser.appendChild(password);
                    
                    

                    Element el_nome = doc.createElement("nome");
                    el_nome.setTextContent(nome);
                    newUser.appendChild(el_nome);
                    
                    
            
                    Element el_cognome = doc.createElement("cognome");
                    el_cognome.setTextContent(cognome);
                    newUser.appendChild(el_cognome);
                    
                    
                    Element el_telefono = doc.createElement("cell");
                    el_telefono.setTextContent(cell);
                    newUser.appendChild(el_telefono);
                    
                    
                    Element el_datanascita = doc.createElement("data_nascita");
                    el_datanascita.setTextContent(datanascita);
                    newUser.appendChild(el_datanascita);
                    

            
                    Element el_indirizzo = doc.createElement("indirizzo");
                    el_indirizzo.setTextContent(indirizzo);
                    newUser.appendChild(el_indirizzo);
                    
                    
                    Element el_citta = doc.createElement("citta");
                    el_citta.setTextContent(citta);
                    newUser.appendChild(el_citta);
            

                    Element el_ruolo = doc.createElement("ruolo");
                    el_ruolo.setTextContent(ruolo);
                    newUser.appendChild(el_ruolo);
            
                    
                    
                    
                    doc.getFirstChild().appendChild(newUser);  //Carichiamo l'utente sull'XML (senza salvarlo)
                    
                    salvaFile(doc, xmlFile);  //Salva l'utente sull'XML
                    
                    
                    Class.forName(DB_DRV);
                    connection=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
                    statement=connection.createStatement(); //Stabiliamo la connessione con PhpMyAdmin
                    String query = "INSERT INTO utente ( Email, Password, Nome, Cognome, Cell, Data_Nascita, Indirizzo, Citta, Ruolo)" + "VALUES ('"+eMail+"','"+pass+"','"+nome+"','"+cognome+"','"+cell+"','"+datanascita+"','"+indirizzo+"','"+citta+"','"+ruolo+"')";
                    System.out.println(query);
                    statement.executeUpdate(query);
                    out.println("Utente registrato con successo!");                                                                                                                                  
                }
                    
                    
                if(mess.equals("2"))
                {
                    /*
                    Gli utenti iscritti, dopo essersi loggati inviando la propria e-mail e password di accesso,
                    possono richiedere l’elenco delle merci memorizzate e loro disponibilità            */
                    
                    String emailric = in.readLine();
                    String passric =  in.readLine();
                   
                    
                    //Invio delle informazioni dei prodotti

                      
                    for(int i=0; i<newUser2.getLength(); i++ )
                    {
                        //Conversione ad Element per usare la funzione "item" ed ottenere gli utenti
                        Element el = (Element)newUser2.item(i);  
                        if( el.getElementsByTagName("email").item(0).getTextContent().equals(emailric) &&
                            el.getElementsByTagName("password").item(0).getTextContent().equals(passric) )
                        {
                            System.out.println("Accesso effettuato con successo!\n");
                            
                            //Usato per ottenere la radice del documento e usare la funzione "getLenght"
                            NodeList mercefor = ((Element)root).getElementsByTagName("Merce");
                            int j=0;
                            
                            
                            
                            for(j=0; j<mercefor.getLength(); j++)
                            {
                                //Conversione ad Element per usare la funzione "item" ed ottenere le merci
                                Element el2 = (Element)mercefor.item(j);
                                
                                //Stampa e invio delle risposte al client
                                out.println("ID merce num " + (j+1) + ": " + el2.getElementsByTagName("id_merce").item(0).getTextContent() );
                                out.println("Descrizione merce: " + el2.getElementsByTagName("descrizione").item(0).getTextContent() );
                                out.println("Quantita' merce: " + el2.getElementsByTagName("quantita").item(0).getTextContent() );
                            }
                            
                            out.println("#");
                            
                            

                            
                        } 
                    } 
                }
                    
                    
                
                    
                if(mess.equals("3"))
                {
                    /*
                    
                    Gli utenti iscritti di tipo “acquirenti” possono acquistare della merce indicando l’id e la quantità (ove disponibile);
                    Le merci mostrate all’utente, devono essere visualizzate secondo l’ordine alfabetico                    */
                    
                    
                    String emailric2 = in.readLine();
                    String passric2 = in.readLine();
                    String idm = in.readLine();
                    String qm = in.readLine();
                    int qme = Integer.parseInt(qm);
                    int qnt;
                    
                    root = doc.getFirstChild();
                    NodeList mercefor = ((Element)root).getElementsByTagName("Merce");
                    newUser2 = ((Element)root).getElementsByTagName("Utente");
                      
                    
                    
                    for(int i=0; i<newUser2.getLength(); i++ )
                    {
                        
                        Element el4 = (Element)newUser2.item(i);  //Utente
                        
                      if( el4.getElementsByTagName("email").item(0).getTextContent().equals(emailric2) &&
                          el4.getElementsByTagName("password").item(0).getTextContent().equals(passric2) )
                          //Controllo utente presente nel database
                        {
                            System.out.println("Accesso effettuato con successo!");
                          for(int j=0; j<mercefor.getLength(); j++ )
                            {
                               
                            Element el3 = (Element)mercefor.item(j);  //Merce
                            qnt = Integer.parseInt(el3.getElementsByTagName("quantita").item(0).getTextContent());
                        
                           
                            
                                if( idm.equals( el3.getElementsByTagName("id_merce").item(0).getTextContent() ) &&
                                    el4.getElementsByTagName("ruolo").item(0).getTextContent().equals("001")    && 
                                    qnt >= qme)
                                    //Presenza id merce e quantita' disponibile
                                {
                                    out.println("ID merce: " + el3.getElementsByTagName("id_merce").item(0).getTextContent() );
                                    out.println("Quantita' merce: " + el3.getElementsByTagName("quantita").item(0).getTextContent() );
                            
                                }
                            }
                        }

                        
                        

                       
                    }
                }
                    

                    
                    
                else
                { 
                    flag_default = 1;
                    risposta = "Mi dispiace non offriamo nessun servizio per il codice fornitoci!";
                    xmlFile.delete(); //Eliminiamo il file temporaneo, i dati sono già salvati su MyAdmin
                }
            
            
            in.close();
            out.close();
            client.close();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
       public static void salvaFile(Document doc, File file)
       {
            try
            {
                StringWriter sw = new StringWriter();
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                transformer.transform(new DOMSource(doc), new StreamResult(sw));
            
                FileWriter fw = new FileWriter(file);
                fw.write(sw.toString());
                fw.flush();
                fw.close();
            }
            
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    
}