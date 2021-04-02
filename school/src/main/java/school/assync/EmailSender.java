package school.assync;

public interface EmailSender {

    void sendMail(String to, String subject, String body);

}
