package com.erykandbogdan.eventapp.web.controller.register.email;

public interface EmailSender {
    void send(String to, String email);
}
