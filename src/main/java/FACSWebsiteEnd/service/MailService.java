package FACSWebsiteEnd.service;

import FACSWebsiteEnd.Entity.MailDTO;

/**
 * @Author: HiramHe
 * @Date: 2019/12/2 15:28
 * QQ:776748935
 */
public interface MailService {

    Boolean sendMail(MailDTO mailDTO);
    Boolean sendSimpleMailMessage(MailDTO mailDTO);
    Boolean sendMimeMessage(MailDTO mailDTO);
    Boolean saveMail(MailDTO mailDTO);
}
