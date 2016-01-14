package com.iquestint.jms.email;

import javax.xml.bind.annotation.*;

/**
 * @author vladu
 */
@XmlRootElement(name = "RESPONSE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(factoryClass = ObjectFactory.class, factoryMethod = "createEmailResponse")
public class EmailResponse {

    /**
     * Params given for the ePortal information retrieval.
     */
    @XmlElement(name = "STATUS", required = true)
    private String status;

    @XmlElement(name = "ERROR_CODE", required = true)
    private String errorCode;

    @XmlElement(name = "ERROR_MESSAGE", required = true)
    private String errorMessage;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        return "<RESPONSE>" + "<STATUS>" + status + "</STATUS>" + "<ERROR_CODE>" + errorCode + "</ERROR_CODE>"
            + "<ERROR_MESSAGE>" + errorMessage + "</ERROR_MESSAGE" + "</RESPONSE>";
    }

}