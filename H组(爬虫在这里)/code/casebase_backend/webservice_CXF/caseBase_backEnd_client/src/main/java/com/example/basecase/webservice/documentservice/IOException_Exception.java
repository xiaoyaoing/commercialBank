
package com.example.basecase.webservice.documentservice;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.4.1
 * 2021-11-11T22:24:13.128+08:00
 * Generated source version: 3.4.1
 */

@WebFault(name = "IOException", targetNamespace = "http://basecase.example.com/webservice/DocumentService")
public class IOException_Exception extends Exception {

    private IOException faultInfo;

    public IOException_Exception() {
        super();
    }

    public IOException_Exception(String message) {
        super(message);
    }

    public IOException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public IOException_Exception(String message, IOException ioException) {
        super(message);
        this.faultInfo = ioException;
    }

    public IOException_Exception(String message, IOException ioException, Throwable cause) {
        super(message, cause);
        this.faultInfo = ioException;
    }

    public IOException getFaultInfo() {
        return this.faultInfo;
    }
}
