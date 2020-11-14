
import Structure.MailStoreFiles;
import Structure.MailSystem;
import Structure.Mailbox;
import Structure.User;
import java.util.*;

public class CLIMain {
    public static void main(String[] args){
        boolean noExit = true;
        Scanner reader = new Scanner(System.in);
        MailSystem mailSystem = new MailSystem(new MailStoreFiles());

        ArrayList<String> dataEntry = new ArrayList();
        Mailbox mailbox = null;
        String user = "";

        System.out.println("-----WELCOME TO CLI-------");
        for (String arg: args) dataEntry.add(arg);
        do {

            if (dataEntry.get(0).equals("createUser"))  createUser(dataEntry, mailSystem);
            try {//faltaria pillar bien el throw
                if (dataEntry.get(0).equals("logas")) mailbox = logAs(dataEntry, mailSystem, user);
                //cuando ya estas logged
                if (mailbox.getUsername().equals(user)){
                    if (dataEntry.get(0).equals("send")) {}
                    if (dataEntry.get(0).equals("update")){}
                    if (dataEntry.get(0).equals("list")){}
                    if (dataEntry.get(0).equals("sort")){}
                    if (dataEntry.get(0).equals("filter")){}
                }
                }catch (Exception e){
                e.getMessage();
            }
            dataEntry.clear();
            String line = reader.nextLine();
            for (String word: line.split(" ")) dataEntry.add(word);
            //for (String word: list) System.out.println("comprobando "+word);

            if (dataEntry.get(0).equals("exit") && dataEntry.size()==1) noExit= false;
            /**
             * pruebas
             */
            for (User use: mailSystem.getAllUsers()) System.out.println("Username: "+use.getUsername());

        }while(noExit);
    }

    public void help(String value){}

    static void createUser(ArrayList<String> dataEntry, MailSystem mailSystem){
        if (dataEntry.size() == 4) {
            int age = Integer.parseInt(dataEntry.get(3));
            mailSystem.createNewUser(dataEntry.get(1), dataEntry.get(2), age);
            System.out.println("usuario creado con éxito");
        } else System.out.println("Error faltan parametros");

    }

    static Mailbox logAs(ArrayList<String> dataEntry, MailSystem mailSystem, String username) throws Exception {
        for (User user: mailSystem.getAllUsers()){
            if (user.getUsername().equals(dataEntry.get(1))){
                System.out.println("Acces");
                username = user.getUsername();
                return user.getBox();
            }
        }
        throw new Exception("doesn't exist");
    }

}
