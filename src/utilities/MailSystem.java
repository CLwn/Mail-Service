package utilities;

import java.util.LinkedList;
import java.util.List;

public class MailSystem {
    private List<User> userList = new LinkedList<>();
    private MailStore mailStore;
    List<Message> list = new LinkedList<>();

    public MailSystem(MailStore mailStore) {
        this.mailStore = mailStore;
    }

    public Mailbox createNewUser(String username, String name, int yOfBird){
        Mailbox mailbox = new Mailbox(mailStore);
        User user = new User(username, name, yOfBird, mailbox);
        userList.add(user);
        return mailbox;
    }

    public List<Message> getAllMessage() throws Exception{
        List<Message> total = new LinkedList<>();
        for(User user: userList){
            System.out.println("hola");
            total = mailStore.getMail(user.getUsername());
        }
        return list;
    }

    public List<User> getAllUsers(){
        return userList;
    }

    public void filterAllMessage(String value){

    }

    //Queries dintre de metodes que anirem fent
    public void countMessages() throws Exception {
        if (list == null) list = getAllMessage();
        long value = list.stream().count();
        System.out.println(value);

    }
    public void averageMessagesPerUser(){}
    public void groupMessagePerSubject(){} //not for user
    public void countWords(){}
    public void getMessageByYO(){} //get messages to users born before a certain year

}
