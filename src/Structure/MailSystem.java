package Structure;

import FactoryPattern.MailStoreFactory;
import FactoryPattern.MailStoreFilesFactory;
import FactoryPattern.MailStoreMemoryFactory;
import FactoryPattern.RedisMailStoreFactory;
import Proxy.DynamicProxy;
import Reflection.Config;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Class MailSystem
 * @author Marc García
 * @version 1.0
 */
@Config(store="MailStoreFiles", log=true)
public class MailSystem{
    private List<User> userList = new LinkedList<>();
    private List<Mailbox> mailboxList = new LinkedList<>();
    private MailStore mailStore;



    /**
     * Method to create new user and save it.
     * @param username
     * @param name
     * @param yOfBird
     * @return mailbox
     */
    public Mailbox createNewUser(String username, String name, int yOfBird){
        Mailbox mailbox = new Mailbox(mailStore, username);
        User user = new User(username, name, yOfBird, mailbox);
        mailboxList.add(mailbox);
        userList.add(user);
        return mailbox;
    }

    /**
     * Method to get all messages
     * @return messages list
     * @throws Exception
     */
    public List<Message> getAllMessage() throws Exception{
        List<Message> total;
        List<Message> list = new LinkedList<>();
        for(User user: userList){
            total = mailStore.getMail(user.getUsername());
            list = Stream.concat(list.stream(), total.stream()).collect(Collectors.toList());
        }
        return list;
    }

    /**
     * Method to get all users
     * @return users list
     */
    public List<User> getAllUsers(){
        return userList;
    }


    /**
     * Method to get all messages filtered by one condition
     * @param predicate
     * @return messages list
     * @throws Exception
     */
    public List<Message> filterAllMessage(Predicate predicate) throws Exception {
        List<Message> list = getAllMessage();
        return (List<Message>) list.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * Method to get a number of messages
     * @throws Exception
     */
    public void countMessages() throws Exception {
        long msg = getAllMessage().stream().count();
        System.out.println("there are "+msg+" messages.");
    }

    /**
     * Method to get the average number of messages per user
     * @throws Exception
     */
    public void averageMessagesPerUser() throws Exception {
        long msg = getAllMessage().stream().count();
        double users = getAllUsers().stream().count();
        double average = msg / users;
        System.out.println("average messages by user: "+average);
    }

    /**
     * Method to get messages in groups
     * @return
     * @throws Exception
     */
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

    /**
     * Method to get a number of words of all messages from users with a particular name
     * @param name
     * @throws Exception
     */
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

    /**
     * Method to get messages to users born before a certain year
     * @param year
     * @return
     * @throws Exception
     */
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


    /**
     * Method to modify mailstore
     * @param mailStore
     */
    public void setMailStore(MailStore mailStore) {
        this.mailStore = mailStore;
    }

    /**
     * Method to get mailstore
     * @return mailstore
     */
    public MailStore getMailStore() {
        return mailStore;
    }

    /**
     * Method to get a spammers list.
     */
    public void getSpammers(){
        List<String> spammers = new LinkedList<>();
        for (Mailbox mailbox: mailboxList){
            mailbox.getSpam().stream().forEach(message ->{if (!spammers.contains(message.getSender()))spammers.add(message.getSender());});
        }
        System.out.println("----SPAMMERS------");
        spammers.forEach(System.out::println);
        System.out.println("------------------");
    }

    /**
     * Method to create or modify a mail store
     * @param factory
     */
    public void createMailStore(MailStoreFactory factory){
        mailStore = factory.createMailStore();
    }

    /**
     * Method to read annotations of Config
     */
    public void readAnnotation() {
        Class metaObject = this.getClass();

        Annotation annotation = metaObject.getAnnotation(Config.class);
        Config config = (Config) annotation;

        String store = config.store();
        switch (store){
            case "MailStoreFiles": createMailStore(new MailStoreFilesFactory());break;
            case "MailStoreMemory": createMailStore(new MailStoreMemoryFactory());break;
            case "RedisMailStore": createMailStore(new RedisMailStoreFactory());break;
            default:System.out.println("WARNING: Write a correct store");break;
        }

        if (config.log())  mailStore = (MailStore) DynamicProxy.newInstance(mailStore);

    }


}
