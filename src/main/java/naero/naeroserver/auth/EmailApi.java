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
        return "<html>" +
                "<body>" +
                "<h1>테스트 메일</h1>" +
                "<p>이것은 테스트 메일입니다.</p>" +
                "<div><strong>인증 코드</strong> <p>" + code + "</p> </div>" +
                "</body>" +
                "</html>";
    }
    }

