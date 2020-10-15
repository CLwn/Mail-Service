package utilities;

import java.io.*;
import java.util.List;

public class MailStoreFiles implements MailStore {
    private File file = new File("/home/clwn1/IdeaProjects/Mail-Service/mailStore.txt");

    @Override
    public void sendMail(Message message) throws IOException {
        System.out.println(message.getBody());
        FileWriter writer = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(writer);
        bw.write(message.getSender()+";");
        bw.write(message.getReceiver()+";");
        bw.write(message.getSubject()+";");
        bw.write(message.getBody());
    }

    @Override
    public Message getMail(){
        try{
            FileReader reader = new FileReader(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
