package com.seolgi.detector.domain.utils.notification.sms;


import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SmsSender {

    public static void send(SmsForm smsForm)  {
        Message message = new Message("NCS57A021B18D599" ,"D67A7C54C698A159F6D3C1DE37899351");
        try {
            message.send(smsForm.createMessage());
        } catch (CoolsmsException e) {
            throw new RuntimeException(e);
        }
    }
}
