package com.seolgi.detector.domain.utils.notification.sms;

enum MessageType {
    SMS("SMS"), LMS("LMS"), MMS("MMS"), ATA("ATA");

    private String type;

    MessageType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}