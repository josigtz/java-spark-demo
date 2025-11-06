package com.josigtz.cryptoModule.repository;

import com.josigtz.cryptoModule.model.Fiel;

import java.util.List;

public interface FielRepository {
    Fiel findBySerie(String serie);
    List<Fiel> findAll();
    Fiel save(Fiel task);
    void deleteBySerie(String serie);
}
