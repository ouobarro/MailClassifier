/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.mailFileReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import org.apache.commons.mail.util.MimeMessageParser;

/**
 *
 * @author barro
 */
public class MailFileReader {

    private static InputStream mailFileInputStream;
    private final Properties props;
    private static Session session;
    private static MimeMessage message; //new MimeMessage(session, mailFileInputStream);

    public MailFileReader() {
        this.props = new Properties();
        session = Session.getDefaultInstance(props, null);
    }

    public static MimeMessageParser readMailFile(String mailFileName) {
        try {
            mailFileInputStream = new FileInputStream(mailFileName);
            message = new MimeMessage(session, mailFileInputStream);
            MimeMessageParser parser = new MimeMessageParser(message);
            return parser.parse();

        } catch (FileNotFoundException | MessagingException ex) {
            Logger.getLogger(MailFileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MailFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
