package com.josigtz.cryptoModule.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FielCommand {
    private String serie;
    private String password;
    private String keyType;
    private String keyContent;
}
