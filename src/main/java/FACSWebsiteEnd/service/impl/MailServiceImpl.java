package FACSWebsiteEnd.service.impl;

import FACSWebsiteEnd.Entity.MailDTO;
import FACSWebsiteEnd.service.MailService;
import FACSWebsiteEnd.utils.EffectiveCheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.util.Date;

/**
 * @Author: HiramHe
 * @Date: 2019/12/2 15:29
 * QQ:776748935
 */

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    MailProperties mailProperties;

    @Override
    public Boolean sendMail(MailDTO mailDTO) {
        return null;
    }

    @Override
    public Boolean sendSimpleMailMessage(MailDTO mailDTO) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        return null;
    }

    @Override
    public Boolean sendMimeMessage(MailDTO mailDTO) {

        try {
            // true 表示支持复杂类型
            MimeMessageHelper messageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(),true);
            mailDTO.setTo(mailProperties.getUsername());
            messageHelper.setFrom(mailDTO.getFrom());
            messageHelper.setTo(mailDTO.getTo());
            messageHelper.setSubject(mailDTO.getSubject());
            messageHelper.setText(mailDTO.getText());
            // 抄送
            if (EffectiveCheckUtils.strEffectiveCheck(mailDTO.getCc())){
                messageHelper.setCc(mailDTO.getCc().split(","));
            }
            // 密送
            if (EffectiveCheckUtils.strEffectiveCheck(mailDTO.getBcc())){
                messageHelper.setBcc(mailDTO.getBcc().split(","));
            }
            // 附件
            if (mailDTO.getMultipartFiles()!=null){
                for (MultipartFile multipartFile:mailDTO.getMultipartFiles()){
                    messageHelper.addAttachment(multipartFile.getOriginalFilename(),multipartFile);
                }
            }
            // 发送时间
            if (StringUtils.isEmpty(mailDTO.getSentDate())){
                mailDTO.setSentDate(new Date());
                messageHelper.setSentDate(mailDTO.getSentDate());
            }

            // 发送邮件
            javaMailSender.send(messageHelper.getMimeMessage());
            mailDTO.setStatus("ok");
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean saveMail(MailDTO mailDTO) {
        // 将邮件保存到数据库
        return null;
    }
}
