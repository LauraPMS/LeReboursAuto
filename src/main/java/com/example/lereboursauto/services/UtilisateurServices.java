package com.example.lereboursauto.services;

import com.example.lereboursauto.models.Utilisateur;
import com.example.lereboursauto.repository.UtilisateurRepository;

import java.sql.SQLException;

public class UtilisateurServices {
    UtilisateurRepository utilisateurRepository;

    public UtilisateurServices(){
        utilisateurRepository = new UtilisateurRepository();
    }

    public Utilisateur findByCode(int id) throws SQLException {
        return utilisateurRepository.findByCode(id);
    }

    // CRUD
    public void create(Utilisateur utilisateuru) throws SQLException {
        utilisateurRepository.create(utilisateuru);
    }

    public void update(Utilisateur utilisateuru) throws SQLException {
        utilisateurRepository.update(utilisateuru);
    }

    public void delete(Utilisateur utilisateuru) throws SQLException {
        utilisateurRepository.delete(utilisateuru);
    }
}
