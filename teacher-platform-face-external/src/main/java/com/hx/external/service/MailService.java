package com.hx.external.service;

import com.hx.external.domain.TrialUsers;

/**
 * Created by summer on 2017/5/4.
 */
public interface MailService {

    public void sendSimpleMail(String to, String subject, String content);

    public void sendHtmlMail(String to, String subject, String content);

    public void sendAttachmentsMail(String to, String subject, String content, String filePath);

    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

    String testMail(TrialUsers trialUsers);
}
