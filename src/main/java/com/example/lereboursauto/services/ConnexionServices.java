package com.example.lereboursauto.services;

import com.example.lereboursauto.models.Utilisateur;
import com.example.lereboursauto.repository.ConnexionRepository;
import com.example.lereboursauto.repository.UtilisateurRepository;
import java.sql.SQLException;
import java.util.HashMap;

public class ConnexionServices {
    ConnexionRepository connexionRepo;
    UtilisateurRepository utilRepo;
    public ConnexionServices() {
        connexionRepo = new ConnexionRepository();
        utilRepo = new UtilisateurRepository();
    }

    // fonction appelé lors de la tentative de connexion
    public Utilisateur verifUser(String login, String password) throws SQLException {
        int code = connexionRepo.LoginMdpEstValide(login, password);
        if (code != 0) {
            return utilRepo.findByCode(code);
        }
        return null;
    }

    // Créer un nouvel utilisateur
    public void inscrireUtilisateur(Utilisateur utilisateur) throws SQLException {
        utilRepo.create(utilisateur);
        if (verifUser(utilisateur.getLogin(), utilisateur.getMdp())!= null) {
            System.out.println(utilisateur.getLogin() + " inscrit !");
        }
        else{
            System.out.println(utilisateur.getLogin() + " inscription échoués !");
        }
    }

    public HashMap<Integer, Integer> countNbLeconParPermis(int idUtilisateur) throws SQLException {
        return connexionRepo.nbLeconParPermis(idUtilisateur);
    }

}
