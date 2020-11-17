import Structure.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import static utilities.Comparators.*;
import static utilities.Predicates.*;

public class CLIMain {
    public static void main(String[] args) throws Exception, java.lang.Exception {
        boolean noExit = true;
        Scanner reader = new Scanner(System.in);
        MailSystem mailSystem = new MailSystem(new MailStoreFiles());

        ArrayList<String> dataEntry = new ArrayList();
        Mailbox mailbox = null;

        System.out.println("-----WELCOME TO CLI-------");
        for (String arg: args) dataEntry.add(arg);
        do {
            if (mailbox==null){
                switch (dataEntry.get(0)){
                    case "createUser": createUser(dataEntry, mailSystem);break;
                    case "logas": mailbox = logAs(dataEntry, mailSystem);break;
                    case "filter": filterAllMessages(dataEntry, mailSystem);break;
                    default:
                        System.out.println("commands: \n -createUser \n -logas \n -filter");;break;
                }
            }else{
                switch (dataEntry.get(0)){
                    case "send": sendMessage(dataEntry,mailbox);break;
                    case "update":
                        System.out.println("update done!");
                        mailbox.updateMail(mailbox.getUsername());break;
                    case "list":
                        System.out.println("list done!");
                        mailbox.getMail(orderByTimestamp());break;
                    case "sort": sortMessages(dataEntry, mailbox);break;
                    case "filter": filterMessages(dataEntry, mailbox, mailSystem);break;
                    default:
                        System.out.println("commands:\n -send \n -update \n -list \n -sort \n " +
                                "-filter");break;
                }
            }
            dataEntry.clear();
            String line = reader.nextLine();
            for (String word: line.split("-")){
                dataEntry.add(word);
            }
            if (dataEntry.get(0).equals("logout") && dataEntry.size()==1) {
                mailbox=null;
                System.out.println("LOGOUT");
            }
            if (dataEntry.get(0).equals("exit") && dataEntry.size()==1) noExit= false;

        }while(noExit);
    }

    static void createUser(ArrayList<String> dataEntry, MailSystem mailSystem){
        if (dataEntry.size() == 4) {
            try {
                int age = Integer.parseInt(dataEntry.get(3));
                mailSystem.createNewUser(dataEntry.get(1), dataEntry.get(2), age);
                System.out.println("user created");
            }catch (NumberFormatException numberFormatException){
                numberFormatException.getMessage();
            }


        } else System.out.println("ERROR: YOU NEED 4 PARAMETERS:\n   -createUser-username-name-age");

    }

    static Mailbox logAs(ArrayList<String> dataEntry, MailSystem mailSystem){
        for (User user: mailSystem.getAllUsers()){
            if (user.getUsername().equals(dataEntry.get(1))){
                System.out.println("access");
                return user.getBox();
            }
        }
        System.out.println("ERROR: THIS USER DOESN'T EXIST");
        return null;
    }

    static void sendMessage(ArrayList<String> dataEntry, Mailbox mailbox) throws IOException {
        mailbox.sendMail(new Message(mailbox.getUsername(), dataEntry.get(1), dataEntry.get(2)
                ,dataEntry.get(3), new Timestamp(System.currentTimeMillis())));
    }

    static void sortMessages(ArrayList<String> dataEntry, Mailbox mailbox){
        switch (dataEntry.get(1)){
            case "sender":  mailbox.getMail(orderBySender()); break;
            case "subject": mailbox.getMail(orderBySubject()); break;
            case "time":  mailbox.getMail(orderByTimestamp()); break;
            default: System.out.println("Use sender, subject or time to order messages"); break;
        }


        //
    }
    //TODO en el caso de que tuvieran esa palabra en el subject y el body el mensaje se duplicaria pero lo miraré más adelante
    static void filterMessages(ArrayList<String> dataEntry, Mailbox mailbox, MailSystem mailSystem){

        switch (dataEntry.get(1)) {
            case "contains":
                List<Message> subjList = mailbox.filterMail(filterBySubject(dataEntry.get(2)));
                List<Message> bodyList = mailbox.filterMail(filterWordBody(dataEntry.get(2)));
                subjList.addAll(bodyList);
                for (Message message : subjList) System.out.println(message.toString());
                break;
            case "lessthan":
                try {
                    int value = Integer.parseInt(dataEntry.get(2));
                    for (Message message: mailbox.listMail()){
                        if(Arrays.stream(message.getBody().split(" ")).count()<value) System.out.println(message.toString());
                    }

                }catch (NumberFormatException numberFormatException){
                    numberFormatException.getMessage();
                }
                break;
            default:
                System.out.println("PARAMETERS: filter-contains/lessthan-word/value");
                break;
        }
    }

    static void filterAllMessages(ArrayList<String> dataEntry, MailSystem mailSystem) throws java.lang.Exception {


            switch (dataEntry.get(1)) {
                case "contains":
                    List<Message> subjList= mailSystem.filterAllMessage(filterBySubject(dataEntry.get(2)));
                    List<Message> bodyList= mailSystem.filterAllMessage(filterWordBody(dataEntry.get(2)));
                    subjList.addAll(bodyList);
                    for (Message message : subjList) System.out.println(message.toString());
                    break;
                case "lessthan":
                    try {
                        int value = Integer.parseInt(dataEntry.get(2));
                        for (Message message: mailSystem.getAllMessage()){
                            if(Arrays.stream(message.getBody().split(" ")).count()<value) System.out.println(message.toString());
                        }
                    }catch(NumberFormatException numberFormatException){
                        numberFormatException.getMessage();
                    }
                    break;
                default:
                    System.out.println("PARAMETERS: filter-contains/lessthan-word/value");
                    break;
            }


    }

}

