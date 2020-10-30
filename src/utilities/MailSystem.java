package utilities;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MailSystem {
    private List<User> userList = new LinkedList<>();
    private List<Mailbox> mailboxList = new LinkedList<>();
    private MailStore mailStore;

    public MailSystem(MailStore mailStore) {
        this.mailStore = mailStore;
    }

    public Mailbox createNewUser(String username, String name, int yOfBird){
        Mailbox mailbox = new Mailbox(mailStore, username);
        User user = new User(username, name, yOfBird, mailbox);
        mailboxList.add(mailbox);
        userList.add(user);
        return mailbox;
    }

    public List<Message> getAllMessage() throws Exception{
        List<Message> total;
        List<Message> list = new LinkedList<>();
        for(User user: userList){
            total = mailStore.getMail(user.getUsername());
            list = Stream.concat(list.stream(), total.stream()).collect(Collectors.toList());
        }
        return list;
    }

    public List<User> getAllUsers(){
        return userList;
    }

    //TODO
    public void filterAllMessage(String value){}

    //Queries dintre de metodes que anirem fent
    public void countMessages() throws Exception {
        long msg = getAllMessage().stream().count();
        System.out.println(msg);

    }

    public void averageMessagesPerUser() throws Exception {
        long msg = getAllMessage().stream().count();
        double users = getAllUsers().stream().count();
        double average = msg / users;
        System.out.println("average messages by user: "+average);
    }

    public void groupMessagePerSubject(String name){
        List<User> list = userList.stream().filter(user-> user.getUsername().contains(name)).collect(Collectors.toList());
        User user = list.get(0);
        user.getBox();
        //TODO
    }
    public void countWords(String name){
        List<User> list = userList.stream().filter(user-> user.getUsername().contains(name)).collect(Collectors.toList());
        User user = list.get(0);
        int count = 0;
        for (Message mesg: user.getBox().listMail()){
            long words = Arrays.stream(mesg.getBody().split(" ")).count();
            count += words;
        }
        System.out.println(count);
    }
    public List<Message> getMessageByYO(int year) throws Exception {
        List<User> users = userList.stream().filter(user-> user.getYearOfBirth()<year).collect(Collectors.toList());
        List<Message> list = new LinkedList<>();
        for(User user: users){
            List<Message> total = mailStore.getMail(user.getUsername());
            list = Stream.concat(list.stream(), total.stream()).collect(Collectors.toList());
        }
        return list;
    }

    public List<Mailbox> getMailboxList() {
        return mailboxList;
    }
}
