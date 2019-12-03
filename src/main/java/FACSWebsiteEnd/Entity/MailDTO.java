package FACSWebsiteEnd.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;

/**
 * @Author: HiramHe
 * @Date: 2019/12/2 15:30
 * QQ:776748935
 */
public class MailDTO {

    // 邮件id
    private String id;
    // 邮件发送人
    private String from;
    // 邮件接收人
    private String to;
    // 邮件主题
    private String subject;
    // 邮件内容
    private String text;
    // 发送时间
    private Date sentDate;
    // 抄送（多个邮箱则用逗号","隔开）
    private String cc;
    // 密送（多个邮箱则用逗号","隔开）
    private String bcc;
    // 状态
    private String status;
    // 邮件附件，不自动封装
    @JsonIgnore
    private MultipartFile[] multipartFiles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MultipartFile[] getMultipartFiles() {
        return multipartFiles;
    }

    public void setMultipartFiles(MultipartFile[] multipartFiles) {
        this.multipartFiles = multipartFiles;
    }

    @Override
    public String toString() {
        return "MailDTO{" +
                "id='" + id + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", sentDate=" + sentDate +
                ", cc='" + cc + '\'' +
                ", bcc='" + bcc + '\'' +
                ", status='" + status + '\'' +
                ", multipartFiles=" + Arrays.toString(multipartFiles) +
                '}';
    }
}
