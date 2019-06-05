package tepsit;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Cioffi Andrea, Preti Christian, Minucci Luca
 */

public class Server {
    
    private static int port = 1234;
    static final String DB_URL = "jdbc:mysql://localhost/magazzinosupermercato";  //collegamento a PhpMyAdmin
    static final String DB_DRV = "com.mysql.jdbc.Driver";
    static final String DB_USER = "root";
    static final String DB_PASSWD = "";
    static Connection connection = null;
    static Statement statement = null;
    static ResultSet resultSet = null;
    
    public static void main(String[] args){
        
            
        try{
            
           // CREO LA CONNESSIONE AL DATABASE
           Class.forName(DB_DRV);
           connection=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
           statement=connection.createStatement();
           
           File xsdFile = new File("SCHEMA.xsd");  //Creo i file XML e XML Schema temporanei, trasmessi poi al database 
           //CREA FILE TREMPORANEO XML
           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
           Document doc = dBuilder.newDocument();
           TransformerFactory transformerFactory = TransformerFactory.newInstance();
           Transformer transformer = transformerFactory.newTransformer();
           DOMSource domSource = new DOMSource(doc);
           StreamResult streamResult = new StreamResult(new File("Inserire percorso salvataggio file"));
           File xmlFile = new File("Magazzino.xml");
           
           salvaFile(doc, xmlFile);
           //RIEMPI XML: prendo dati dal database
           riempi(doc);
           salvaFile(doc, xmlFile);
         
           
           
           
           ServerSocket socket = new ServerSocket(port);  //Creazione server
            System.out.println("Server pronto!");
            while(true){
                Socket client = socket.accept();
                PThread newConnect = new PThread(client, xmlFile, doc);
                newConnect.start();
            }
           
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
           try {
              resultSet.close();
              statement.close();
              connection.close();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
        }
     }

        public static void riempi(Document xml){
            try{
                Element root = xml.createElement("Magazzino");  //Creazione documento XML, con root Magazzino
                xml.appendChild(root);
                PreparedStatement sel = connection.prepareStatement("SELECT * FROM utente");
                resultSet=sel.executeQuery();
                while(resultSet.next()){
                    /************************
                    *****TABELLA UTENTE******
                    ************************/
                    Element nuovo = xml.createElement("Utente");
                    Element newName = xml.createElement("email");
                    newName.setTextContent(resultSet.getString("Email"));
                    nuovo.appendChild(newName);
                    newName = xml.createElement("password");
                    newName.setTextContent(resultSet.getString("Password"));
                    nuovo.appendChild(newName);
                    newName = xml.createElement("nome");
                    newName.setTextContent(resultSet.getString("Nome"));
                    nuovo.appendChild(newName);
                    newName = xml.createElement("cognome");
                    newName.setTextContent(resultSet.getString("Cognome"));
                    nuovo.appendChild(newName);
                    newName = xml.createElement("cell");
                    newName.setTextContent(resultSet.getString("Cell"));
                    nuovo.appendChild(newName);
                    newName = xml.createElement("data_nascita");
                    newName.setTextContent(resultSet.getString("Data_Nascita"));
                    nuovo.appendChild(newName);
                    newName = xml.createElement("indirizzo");
                    newName.setTextContent(resultSet.getString("Indirizzo"));
                    nuovo.appendChild(newName);
                    newName = xml.createElement("citta");
                    newName.setTextContent(resultSet.getString("Citta"));
                    nuovo.appendChild(newName);
                    newName = xml.createElement("ruolo");
                    newName.setTextContent(resultSet.getString("Ruolo"));
                    nuovo.appendChild(newName);
                    root.appendChild(nuovo);
                }
                sel = connection.prepareStatement("SELECT * FROM merce");
                resultSet=sel.executeQuery();
                while(resultSet.next()){
                    /************************
                    ******TABELLA MERCE******
                    ************************/
                    Element nuovo = xml.createElement("Merce");
                    Element newName = xml.createElement("id_merce");
                    newName.setTextContent(resultSet.getString("ID_Merce"));
                    nuovo.appendChild(newName);
                    newName = xml.createElement("tipologia");
                    newName.setTextContent(resultSet.getString("Tipologia"));
                    nuovo.appendChild(newName);
                    newName = xml.createElement("prezzo");
                    newName.setTextContent(resultSet.getString("Prezzo"));
                    nuovo.appendChild(newName);
                    newName = xml.createElement("descrizione");
                    newName.setTextContent(resultSet.getString("Descrizione"));
                    nuovo.appendChild(newName);
                    newName = xml.createElement("quantita");
                    newName.setTextContent(resultSet.getString("Quantita"));
                    nuovo.appendChild(newName);
                    root.appendChild(nuovo);
                }
                sel = connection.prepareStatement("SELECT * FROM ruolo");
                resultSet=sel.executeQuery();
                while(resultSet.next()){
                    /************************
                    ******TABELLA RUOLO******
                    ************************/
                    Element nuovo = xml.createElement("Ruolo");
                    Element newName = xml.createElement("id_ruolo");
                    newName.setTextContent(resultSet.getString("ID_Ruolo"));
                    nuovo.appendChild(newName);
                    newName = xml.createElement("desc_ruolo");
                    newName.setTextContent(resultSet.getString("Desc_ruolo"));
                    nuovo.appendChild(newName);
                    root.appendChild(nuovo);
                }
                sel = connection.prepareStatement("SELECT * FROM tipologia");
                resultSet=sel.executeQuery();
                while(resultSet.next()){
                    /************************
                    ****TABELLA TIPOLOGIA****
                    ************************/
                    Element nuovo = xml.createElement("Tipologia");
                    Element newName = xml.createElement("id_tipo");
                    newName.setTextContent(resultSet.getString("ID_Tipo"));
                    nuovo.appendChild(newName);
                    newName = xml.createElement("titolo_tipo");
                    newName.setTextContent(resultSet.getString("Titolo_tipo"));
                    nuovo.appendChild(newName);
                    root.appendChild(nuovo);
                }
                sel = connection.prepareStatement("SELECT * FROM operazione");
                resultSet=sel.executeQuery();
                while(resultSet.next()){
                    /************************
                    ****TABELLA OPERAZIONE***
                    ************************/
                    Element nuovo = xml.createElement("Operazione");
                    Element newName = xml.createElement("id_merce");
                    newName.setTextContent(resultSet.getString("ID_Merce"));
                    nuovo.appendChild(newName);
                    newName = xml.createElement("email");
                    newName.setTextContent(resultSet.getString("Email"));
                    nuovo.appendChild(newName);
                    root.appendChild(nuovo);
                }
            }
            catch(SQLException sql_ex){
            sql_ex.printStackTrace();
        }
        }
        public static void salvaFile(Document doc, File file){
            try{
                StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            
            FileWriter fw = new FileWriter(file);
            fw.write(sw.toString());
            fw.flush();
            fw.close();
            }
            catch(Exception ex){ex.printStackTrace();}
        }
}
