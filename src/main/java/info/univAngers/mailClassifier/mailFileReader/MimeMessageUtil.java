/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.mailFileReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.AddressException;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import org.apache.commons.io.FilenameUtils;

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
            System.out.println(">> Multipart/*");
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws IOException, MessagingException {

        int count = mimeMultipart.getCount();
        if (count == 0) {
            throw new MessagingException("Multipart with no body parts not supported.");
        }
        boolean multipartAlt = new ContentType(mimeMultipart.getContentType()).match("multipart/alternative");
        if (multipartAlt) // alternatives appear in an order of increasing 
        // faithfulness to the original content. Customize as req'd.
        {
            System.out.println(">>> Multipart/alternative");
            return getTextFromBodyPart(mimeMultipart.getBodyPart(count - 1));
        }
        String result = "";
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            result += getTextFromBodyPart(bodyPart);
        }
        return result;
    }

    private static String getTextFromBodyPart(BodyPart bodyPart) throws IOException, MessagingException {
        String result = "";
        if (bodyPart.isMimeType("text/plain")) {
            System.out.println("\t>> text/plain");
            result = (String) bodyPart.getContent();
        } else if (bodyPart.isMimeType("text/html")) {
            System.out.println("\t>> text/html");
            String html = (String) bodyPart.getContent();
            result = org.jsoup.Jsoup.parse(html).text();
        } else if (bodyPart.getContent() instanceof MimeMultipart) {
            System.out.println("\t>> MimeMultipart");
            result = getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
        }
        return result;
    }

    private static Attach getAttachFromBodyPart(MimeMultipart content) throws IOException, MessagingException {
        Attach attach = null;
        try {
            for (int i = 0; i < content.getCount(); i++) {
                BodyPart bodyPart = content.getBodyPart(i);
                Object o;
                o = bodyPart.getContent();
                if (o instanceof String) {
                    System.out.println("Text = " + o);
                } else if (null != bodyPart.getDisposition() && bodyPart.getDisposition().equalsIgnoreCase(Part.ATTACHMENT)) {
                    String fileName = bodyPart.getFileName();
                    
                    if(fileName != null && (!fileName.isEmpty())){
                        System.setProperty("mail.mime.decodetext.strict", "false");
                        fileName = MimeUtility.decodeText(fileName);

                        System.out.println("fileName = " + fileName);
                        attach = new Attach();
                        attach.setFileName(fileName);
                        FileOutputStream outStream;
                        try (InputStream inStream = bodyPart.getInputStream()) {
                            File outFile = new File("/home/barro/NetBeansProjects/MailClassifier/attach"+File.separator+fileName);
                            outStream = new FileOutputStream(outFile);
                            byte[] tempBuffer = new byte[4096]; // 4 KB
                            int numRead;
                            while ((numRead = inStream.read(tempBuffer)) != -1) {
                                outStream.write(tempBuffer);
                            }
                            String attPath = outFile.getAbsolutePath();
                            attach.setAttPath(attPath);

                            String ext = FilenameUtils.getExtension(fileName);
                            attach.setExtension(ext);
                        }
                        outStream.close();
                    }
                    
                } // else?
            }
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
        return attach;
    }

    public static List<Attach> getMessageAttach(MimeMessage message) throws MessagingException, IOException {
        List<Attach> attachList = new ArrayList<>();
        try {
            Object content = message.getContent();
            // check for string 
            // then check for multipart 
            if (content instanceof String) {
                System.out.println(content);
            } else if (content instanceof Multipart) {
                MimeMultipart multiPart = (MimeMultipart) content;
                Attach attach = getAttachFromBodyPart(multiPart);
                if (attach != null) {
                    attachList.add(attach);
                }
            } else if (content instanceof InputStream) {
                InputStream inStream = (InputStream) content;
                int ch;
                while ((ch = inStream.read()) != -1) {
                    System.out.write(ch);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return attachList;
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

    public static InternetAddress[] removeDuplicateAddress(InternetAddress[] addressArray) {
        List<InternetAddress> addressWithoutDup = null;
        if (addressArray != null && (addressArray.length > 0)) {
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
            InternetAddress[] finalAddrArray = new InternetAddress[addressWithoutDup.size()];
            addressWithoutDup.toArray(finalAddrArray);
            return finalAddrArray;
        } else {
            InternetAddress[] finalAddrArray = {};
            return finalAddrArray;
        }
    }

    //Pull all links from the body for easy retrieval
    public static List<String> getMailLinks(MimeMessage message) {
        
        String text;
        List<String> links = new ArrayList<>();
        
        try {
            text = getBodyTextFromMessage(message);
            String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(text);
            while (m.find()) {
                String urlStr = m.group();
                if (urlStr.startsWith("(") && urlStr.endsWith(")")){
                    urlStr = urlStr.substring(1, urlStr.length() - 1);
                }
                links.add(urlStr);
            }
        } catch (MessagingException | IOException ex) {
            System.out.println("Erreur: LINKS");
            Logger.getLogger(MimeMessageUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return links;
        
    }
}
