/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.mailFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.jsoup.Jsoup;

/**
 *
 * @author barro
 */
public class MimeMessageUtil {

    public static String getBodyTextFromMessage(MimeMessage message) throws MessagingException, IOException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + Jsoup.parse(html).text();
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
            }
        }
        return result;
    }

    /**
     * Vérifie la validité d'une adresse email (adresse bien formée)
     *
     * @param email
     * @return
     */
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    private static String removeDoubleBacSlash(String content) {
        String mailContent;
        Matcher m = Pattern.compile("(?i)\\\\u([\\da-f]{4})").matcher(content);
        if (m.find()) {
            mailContent = String.valueOf((char) Integer.parseInt(m.group(1), 16));
        } else {
            mailContent = content;
        }
        return mailContent;
    }

    public static InternetAddress[] removeDuplicateAddress(InternetAddress[] addressArray) {
        List<InternetAddress> addressWithoutDup = null;
        if (addressArray.length > 0) {
            addressWithoutDup = new ArrayList<>();
            for (int i = 0; i < addressArray.length; i++) {
                InternetAddress address = addressArray[i];
                Boolean addrExist = false;
                for (InternetAddress adr : addressWithoutDup) {
                    if (address.getAddress().equalsIgnoreCase(adr.getAddress())) {
                        addrExist = true;
                    }
                }
                if (!addrExist) {
                    addressWithoutDup.add(address);
                }
            }
        }
        
        InternetAddress[] finalAddrArray = new InternetAddress[addressWithoutDup.size()];
        addressWithoutDup.toArray(finalAddrArray);

        return finalAddrArray;
    }
}
