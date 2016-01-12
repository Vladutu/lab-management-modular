package com.iquestint.jms.email;

import javax.xml.bind.annotation.*;

/**
 * @author vld
 */
@XmlRootElement(name = "REQUEST")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(factoryClass = ObjectFactory.class, factoryMethod = "createEmailRequest")
public class EmailRequest {

    /**
     * The channel which is going to handle the request.
     */
    @XmlElement(name = "EMAIL", required = true)
    private String email;
    /**
     * The identifier of the function.
     */
    @XmlElement(name = "SENDER_EMAIL", required = true)
    private String senderEmail;
    /**
     * The version of the message.
     */
    @XmlElement(name = "SUBJECT", required = true)
    private String subject;
    /**
     * The identifier of the function.
     */
    @XmlElement(name = "BODY", required = true)
    private String body;

    public EmailRequest(String email, String senderEmail, String subject, String body) {
        this.email = email;
        this.senderEmail = senderEmail;
        this.subject = subject;
        this.body = body;
    }

    public EmailRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String toString() {
        return "<REQUEST>" + "<EMAIL>" + email + "</EMAIL>" + "<SENDER_EMAIL>" + senderEmail + "</SENDER_EMAIL>"
            + "<SUBJECT>" + subject + "</SUBJECT>" + "<BODY>" + body + "</BODY>" + "</REQUEST>";

    }
}
