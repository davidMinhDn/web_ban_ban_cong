package com.example.web_ban_cong.OTP;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.Random;

public class MailSending {

    public String generateOtp() {
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            result += random.nextInt(10) + "";
        }

        return result;
    }

    public void send(String fromEmail, String password, String toEmail, String subject, String content) {
        String host = "smtp.gmail.com";
        String port = "587";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port); // for TLS
        props.put("mail.smtp.starttls.enable", "true"); // for TLS
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void resetPasswordMail(String toEmail, String token) {
        String fromEmail = "datnguyentien.20602@gmail.com";
        String password = "lygzmpkipxtylicx";
        String host = "smtp.gmail.com";
        String port = "587";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port); // for TLS
        props.put("mail.smtp.starttls.enable", "true"); // for TLS

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            String content = "Bạn đang thực hiện việc reset mật khẩu. Đây là mã xác thực: " + token +
                    "\n Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi!";

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("[Thông báo] Mã OPT reset mật khẩu");
            message.setText(content);
            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void authenEmail(String fromEmail, String password, String toEmail, String token) {
        String host = "smtp.gmail.com";
        String port = "587";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port); // for TLS
        props.put("mail.smtp.starttls.enable", "true"); // for TLS

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            String content = "Cảm ơn bạn đã đăng ký tài khoản. Dưới đây là chuỗi OTP xác thực:\nOTP: " + token +
                    "\nNote: Hân hạnh được phục vụ !";

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Authentication email");
            message.setText(content);
            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void authenEmailStaff(String fromEmail, String password, String toEmail,String staffPassword, String token) {
        String host = "smtp.gmail.com";
        String port = "587";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port); // for TLS
        props.put("mail.smtp.starttls.enable", "true"); // for TLS

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            String content = "Email của bạn đã được đăng kí tài khoản nhân viên, dưới đấy là mật khẩu và mã OTP kích hoạt khi đăng nhập" +
                    ":\nMật khẩu: " + staffPassword  +
                    "\nMã OTP "+ token +
                    "\nNote: Sau khi đăng nhập bạn sẽ được chuyển đến trang kích hoạt tài khoản!";

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Authentication email Staff");
            message.setText(content);
            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void sendEmailPassStaff(String fromEmail, String password, String toEmail,String staffPassword) {
        String host = "smtp.gmail.com";
        String port = "587";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port); // for TLS
        props.put("mail.smtp.starttls.enable", "true"); // for TLS

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            String content = "Email của bạn đã được đăng kí tài khoản nhân viên, dưới đấy là mật khẩu và mã OTP kích hoạt khi đăng nhập" +

                    ":\nEmail:" +toEmail+
                    ":\nMật khẩu: " + staffPassword  +

                    "\nNote: Sau khi đăng nhập bạn sẽ được chuyển đến trang kích hoạt tài khoản!";

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Send Email Password to Staff");
            message.setText(content);
            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}