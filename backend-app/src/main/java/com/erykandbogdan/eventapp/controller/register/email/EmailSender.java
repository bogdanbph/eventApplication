package com.erykandbogdan.eventapp.controller.register.email;

public interface EmailSender {
    void send(String to, String email);
}
