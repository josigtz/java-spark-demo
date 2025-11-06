package com.josigtz.cryptoModule.service;

import com.josigtz.cryptoModule.model.Fiel;
import com.josigtz.cryptoModule.model.command.FielCommand;
import com.josigtz.cryptoModule.model.dto.FielDto;
import com.josigtz.cryptoModule.repository.FielRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class FielService {
    private static final Logger log = LoggerFactory.getLogger(FielService.class);
    private FielRepository fielRepository;

    public FielService(FielRepository fielRepository) {
        this.fielRepository = fielRepository;
    }

    public FielDto findBySerie(String serie) {
        Fiel fiel = fielRepository.findBySerie(serie);
        return fiel != null ? new FielDto(fiel.getSerie(), fiel.getCreatedAt(), fiel.getUpdatedAt(), fiel.getActive()) : null;
    }

    public List<FielDto> findAll() {
        List<Fiel> fielList = fielRepository.findAll();
        return fielList.stream()
                .map(fiel -> new FielDto(fiel.getSerie(), fiel.getCreatedAt(), fiel.getUpdatedAt(), fiel.getActive()))
                .collect(Collectors.toList());
    }

    public String insert(FielCommand fiel) throws Exception {
        Fiel nuevaFiel = new Fiel();
        nuevaFiel.setSerie(fiel.getSerie());
        nuevaFiel.setPassword(fiel.getPassword());
        switch (fiel.getKeyType()){
            case "KEY":
                nuevaFiel.setKeyContent(fiel.getKeyContent());
                break;
            case "CVE":
                nuevaFiel.setCveContent(fiel.getKeyContent());
                break;
            default:
                throw new Exception("Invalid key format");
        }

        return fielRepository.save(nuevaFiel).getSerie();
    }

    public Fiel put(Fiel task, String serie) {
        Fiel foundTask = fielRepository.findBySerie(serie);
        if (foundTask != null) {
            task.setSerie(serie);
        }
        return fielRepository.save(task);
    }

    public void patch(Fiel fiel, String serie) {
        Fiel existingFiel = fielRepository.findBySerie(serie);
        existingFiel.merge(fiel);
        existingFiel.setUpdatedAt();
        fielRepository.save(existingFiel);
    }

    public void delete(String serie) {
        fielRepository.deleteBySerie(serie);
    }
}
