package naero.naeroserver.auth;
import java.util.Properties;
import java.util.Random;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import naero.naeroserver.auth.DTO.AuthDTO;
import naero.naeroserver.exception.LoginFailedException;

public class EmailApi {
    private EmailApi() {
    }

    public static AuthDTO sendAuthEmail(String user_email, String password, String toSend){

        final String smtp_host = "smtp.gmail.com";
        final int smtp_port = 465;  // TLS : 587, SSL : 465

        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtp_host);
        props.put("mail.smtp.port", smtp_port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", smtp_host);

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user_email, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user_email));
            // 받는 이메일
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toSend)
            );
            // 제목
            message.setSubject("Naero에서 이메일 인증번호를 보냅니다.");
            // 내용
            String code = generateRandomCode(6);
            message.setContent(generateHtmlContent(code), "text/html; charset=utf-8");
            // 발송
            AuthDTO data = new AuthDTO(null, code, toSend, null, 'N');
            System.out.println("발송 준비 끝"+ data);
            Transport.send(message);

            return data;
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new LoginFailedException("인증 정보 생성 실패");
        }

    }
    public static String generateRandomCode(int length) {
        StringBuilder randomCode = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            // 'A'(65)부터 'Z'(90)까지의 아스키 코드 값에서 랜덤한 값 선택
            char randomChar = (char) (random.nextInt(26) + 65); // 65는 'A'의 아스키 코드 값
            randomCode.append(randomChar);
        }

        return randomCode.toString();
    }
    private static String generateHtmlContent(String code) {
        StringBuilder codeHtml = new StringBuilder();
        for (char c : code.toCharArray()) {
            codeHtml.append("<div style='width: 40px; height: 40px; display: inline-block; margin: 5px; border: 1px solid #27ae60; border-radius: 5px; font-size: 20px; font-weight: bold; line-height: 40px; text-align: center; color: #34495e;'>")
                    .append(c)
                    .append("</div>");
        }

        return "<html>" +
                "<head>" +
                "<link href='https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap' rel='stylesheet'>" +
                "</head>" +
                "<body style='margin:0; padding:0;  font-family: \"Montserrat\", Arial, sans-serif; background:#f4f4f4; display: flex; justify-content: center; align-items: center; width:auto; height: auto;'>" +
                "<div style='width: 470px; height: 300px; background: #ffffff; border: 1px solid rgba(123, 144, 100, 0.3); border-radius: 10px; box-sizing: border-box; text-align: center; text-align: center; padding: 20px;'>" +
                "<h1 style='color: #2c3e50; margin-bottom: 20px;'>인증코드를 보내드립니다.</h1>" +
                "<strong style='font-size: 20px; color: #27ae60; margin-bottom: 10px;'>Naero 인증 코드</strong>" +
                "<div style='display: flex; justify-content: center; align-items: center;'>" +
                codeHtml.toString() +
                "</div>"  +
                "</div>" +
                "</body>" +
                "</html>";
    }

}