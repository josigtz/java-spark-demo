package com.josigtz.cryptoModule.model;

import com.josigtz.cryptoModule.model.command.FielCommand;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="fiel", schema = "public")
@Data
public class Fiel {

    @Id
    private String serie;
    private String password;
    private String rsaContent;
    private String keyContent;
    private String cveContent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean active;

    public Fiel() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public void merge(Fiel otherFiel) {
        this.keyContent = otherFiel.getKeyContent() != null ? otherFiel.getKeyContent() : this.getKeyContent();
        this.cveContent = otherFiel.getCveContent() != null ? otherFiel.getCveContent() : this.getCveContent();
    }

    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
