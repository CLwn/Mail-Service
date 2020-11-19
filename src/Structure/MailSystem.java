package Structure;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MailSystem{
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


    public List<Message> filterAllMessage(Predicate predicate) throws Exception {
        List<Message> list = getAllMessage();
        return (List<Message>) list.stream().filter(predicate).collect(Collectors.toList());
    }

    //Queries dintre de metodes que anirem fent
    public void countMessages() throws Exception {
        long msg = getAllMessage().stream().count();
        System.out.println("there are "+msg+" messages.");

    }

    public void averageMessagesPerUser() throws Exception {
        long msg = getAllMessage().stream().count();
        double users = getAllUsers().stream().count();
        double average = msg / users;
        System.out.println("average messages by user: "+average);
    }

    public Map<String, List<Message>> groupMessagePerSubject() throws Exception {
        List<Message> messages = getAllMessage();
        List<Message> list;
        Map<String, List<Message>> map = new HashMap<>();
        for (Message message: messages){
            if(map.containsKey(message.getSubject())) list= map.get(message.getSubject());
            else list= new LinkedList<>();
            list.add(message);
            map.put(message.getSubject(), list);
        }
        return map;

    }
    public void countWords(String name) throws Exception {
        List<User> list = userList.stream().filter(user-> user.getName().contains(name)).collect(Collectors.toList());
        List<Message> allMessages = getAllMessage();
        int count = 0;
        long words;
        for (User user: list){
            for (Message message: allMessages){
                if (message.getSender().equalsIgnoreCase(user.getUsername())){
                    words = Arrays.stream(message.getBody().split(" ")).count();
                    count += words;
                }
            }
        }
        System.out.println("this messages has "+count+" words");
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

    public List<Message> getMessageByYO(Predicate predicate) throws Exception {
        List<Message> result = new LinkedList<>();
        List<Message> messages = getAllMessage();
        List<User> users = (List<User>) userList.stream().filter(predicate).collect(Collectors.toList());
        for(User user: users){
            for(Message message: messages){
                if (message.getSender().equalsIgnoreCase(user.getUsername()))
                    result.add(message);
            }
        }
        return result;
    }


    public List<Mailbox> getMailboxList() {
        return mailboxList;
    }

    public void setMailStore(MailStore mailStore) {
        this.mailStore = mailStore;
    }

    public MailStore getMailStore() {
        return mailStore;
    }

    public void getSpammers(){
        List<String> spammers = new LinkedList<>();
        for (Mailbox mailbox: mailboxList){
            mailbox.getSpam().stream().forEach(message ->{if (!spammers.contains(message.getSender()))spammers.add(message.getSender());});
        }
        System.out.println("----SPAMMERS------");
        spammers.forEach(System.out::println);
        System.out.println("------------------");
    }

}
