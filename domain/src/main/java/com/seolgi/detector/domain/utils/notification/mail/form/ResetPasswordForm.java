package com.seolgi.detector.domain.utils.notification.mail.form;

import lombok.Builder;

public class ResetPasswordForm extends MailForm {
    private String token;

    @Builder
    public ResetPasswordForm(String token) {
        this.token = token;
    }
}
