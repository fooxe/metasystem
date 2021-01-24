package com.dashow.connector;

import java.sql.Timestamp;

/**
 * MetaSystemPro
 *
 * @author: Create DBLogger  by Fuqifeng on 2021 2021/1/23;
 * Function:
 */
public class DBLogger {
    private static final long serialVersionUID = 2552429215424205489L;
    private String requestId;
    private String keyWord;
    private String code;
    private String method;
    private String inParam;
    private String outParam;
    private Timestamp inTime;
    private Timestamp outTime;

    public DBLogger() {
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getKeyWord() {
        return this.keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getInParam() {
        return this.inParam;
    }

    public void setInParam(String inParam) {
        this.inParam = inParam;
    }

    public String getOutParam() {
        return this.outParam;
    }

    public void setOutParam(String outParam) {
        this.outParam = outParam;
    }

    public Timestamp getInTime() {
        return this.inTime;
    }

    public void setInTime(Timestamp inTime) {
        this.inTime = inTime;
    }

    public Timestamp getOutTime() {
        return this.outTime;
    }

    public void setOutTime(Timestamp outTime) {
        this.outTime = outTime;
    }

    public String toString() {
        return "DBLogger{requestId='" + this.requestId + '\'' + ", keyWord='" + this.keyWord + '\'' + ", code='" + this.code + '\'' + ", method='" + this.method + '\'' + ", inParam='" + this.inParam + '\'' + ", outParam='" + this.outParam + '\'' + ", inTime=" + this.inTime + ", outTime=" + this.outTime + '}';
    }
}
