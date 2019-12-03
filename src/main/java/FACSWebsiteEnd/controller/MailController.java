package FACSWebsiteEnd.controller;

import FACSWebsiteEnd.Entity.MailDTO;
import FACSWebsiteEnd.common.ResultObject;
import FACSWebsiteEnd.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: HiramHe
 * @Date: 2019/12/2 15:27
 * QQ:776748935
 */

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    MailService mailService;

    @PostMapping("/sendMimeMessage")
    public ResultObject sendMimeMessage(MailDTO mailDTO, MultipartFile file){
        mailService.sendMimeMessage(mailDTO);
        return null;
    }
}
