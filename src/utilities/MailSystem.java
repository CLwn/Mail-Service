package utilities;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<Message> total;
        for(User user: userList){
            total = mailStore.getMail(user.getUsername());
            list = Stream.concat(list.stream(), total.stream()).collect(Collectors.toList());
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
        list = getAllMessage();
        long value = list.stream().count();
        System.out.println(value);

    }
    public void averageMessagesPerUser(){}
    public void groupMessagePerSubject(){} //not for user
    public void countWords(){}
    public void getMessageByYO(){} //get messages to users born before a certain year

}
