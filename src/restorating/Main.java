/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package restorating;



import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import tn.esprit.entities.Avis;
import tn.esprit.entities.Badge;
import tn.esprit.entities.Restaurant;
import tn.esprit.entities.TypeBadge;
import tn.esprit.entities.User;
import tn.esprit.services.IserviceBadge;
import tn.esprit.services.ServiceAvis;
import tn.esprit.services.ServiceBadge;
import tn.esprit.utils.Datasource;



/**
 *
 * @author remo
 */
public class Main {

 
    public static void main(String[] args) throws SQLException {
        // Initialisez votre connexion et votre service
        Datasource.getInstance().getCnx(); // Assurez-vous que la connexion est correctement initialisée
        ServiceAvis serviceAvis = ServiceAvis.getInstance();
        ServiceBadge serviceBadge;
        serviceBadge = ServiceBadge.getInstance();
        
        
        
        
        
        
        
        
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
      
                     //++++++++++++++++ CRUD Avis++++++++++++++   

////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
        //-------------------------------------------Test Ajouter Avis----------------------------------------------------------------
    
 
        
    
            Avis nouvelAvis = new Avis();
            Restaurant restaurant = new Restaurant();
            User user = new User();
            nouvelAvis.setDateAvis(LocalDate.now()); 
            nouvelAvis.setPubAvis("Madrid Rojla " ); 
            nouvelAvis.setTitreAvis("Madrid lgar7a "); 
            restaurant.setIdrestau(6);
            restaurant.setNom("walid");
            user.setIduser(1);
            try {
               
                serviceAvis.ajouter(nouvelAvis,restaurant,user);
                  
                System.out.println("Avis ajouté avec succès ! L'ID généré est : " + nouvelAvis.getId());
                
            System.out.println("/////////////////////////////////////////////////////////////////////////////////////////\n");
            
            System.out.println("Liste des avis pour le restaurant " + restaurant.getNom() + ":");
                
                for (Avis avis : restaurant.getAvis()) {
                System.out.println("ID: " + avis.getId() + ", Titre: " + avis.getTitreAvis());
                System.out.println("ID: " + avis.getPubAvis()+ ", Titre: " + avis.getTitreAvis());
                
                }
            System.out.println("/////////////////////////////////////////////////////////////////////////////////////////\n");
            System.out.println("Liste des avis de l'utilisateur " + user.getIduser() + ":");
             for (Avis avis : user.getAvis()) {
            System.out.println("ID: " + avis.getId() + ", Titre: " + avis.getTitreAvis());
             }
           
                
                
            } catch (SQLException e) {
                System.err.println("Erreur lors de l'ajout de l'avis : " + e.getMessage());
            }       
           
            
        
          
    
        
      
      
      
       
       //-------------------------------------------Test Modifier Avis----------------------------------------------------------------
//      
//        Avis nouvelAvis = new Avis();
//
//       try {
//           
//            Avis avisModifie = new Avis();
//            avisModifie.setDateAvis(LocalDate.now());
//            avisModifie.setPubAvis("Nouveau contenu de l'avis");
//            avisModifie.setTitreAvis("Nouveau Titre");
//
//          
//            int idAvisAModifier = 168; 
//
//        
//            int nouvelIdRestaurant = 8; 
//
//            
//            serviceAvis.modifier(idAvisAModifier, avisModifie, nouvelIdRestaurant);
//
//            System.out.println("Mise à jour de l'avis réussie !");
//        } catch (SQLException e) {
//            System.err.println("Erreur lors de la mise à jour de l'avis : " + e.getMessage());
//        }
//      
//    
    
      
      
      
      
      
      
       //-------------------------------------------Test Supprimer Avis----------------------------------------------------------------
      

    /*
    int idAvisASupprimer = 127; 
    
    try {
       
        serviceAvis.supprimer(idAvisASupprimer);
        System.out.println("Avis supprimé avec succès !");
    } catch (SQLException e) {
        System.err.println("Erreur lors de la suppression de l'avis : " + e.getMessage());
    }
      */
      
    
    
    
    
    
     //-------------------------------------------Test Recuperer Avis----------------------------------------------------------------
      
//    try {
//             List<Avis> listeAvis = serviceAvis.recuperer();
//
//           for (Avis avis : listeAvis) {
//                System.out.println("ID de l'avis: " + avis.getId());
//                System.out.println("Date de l'avis: " + avis.getDateAvis());
//                System.out.println("Contenu de l'avis: " + avis.getPubAvis());
//                System.out.println("Titre de l'avis: " + avis.getTitreAvis());
//                
//                
//                if (avis.getRestaurant() != null) {
//                    System.out.println("ID du Restaurant associé: " + avis.getRestaurant().getIdrestau());
//                    System.out.println("Nom du Restaurant associé: " + avis.getRestaurant().getNom());
//                } else {
//                    System.out.println("Aucun restaurant associé à cet avis.");
//                }
//                
//                 if (avis.getUser() != null) {
//                    System.out.println("ID de l'Utilisateur associé: " + avis.getUser().getIduser());
//                    System.out.println("Nom de l'Utilisateur associé: " + avis.getUser().getUsername());
//                } else {
//                    System.out.println("Aucun utilisateur associé à cet avis.");
//                }
//
//                System.out.println("-----------------------------------");
//            }
//        } catch (SQLException e) {
//            System.err.println("Erreur lors de la récupération des avis : " + e.getMessage());
//        }
//    }
//}

      
      
 
      
////////////////////////////////////////////////////////////////////////////////////////////////////////////
      
                     //++++++++++++++++ CRUD Badge++++++++++++++   

////////////////////////////////////////////////////////////////////////////////////////////////////////////
      //-------------------------------------------Test Ajouter Badge----------------------------------------------------------------
//       
//    
//            Badge nouvelBadge = new Badge();
//            Restaurant restaurant = new Restaurant();
//            User user = new User();
//            nouvelBadge.setDateBadge(LocalDate.now());
//            nouvelBadge.setCommantaire("Madrid Rojla " ); 
//            nouvelBadge.setTypeBadge(TypeBadge.Diamant); 
//            restaurant.setIdrestau(8);
//            user.setIduser(1);
//            try {
//                
//                serviceBadge.ajouter(nouvelBadge,restaurant,user);
//                System.out.println("Badge ajouté avec succès ! L'ID généré est : " + nouvelBadge.getId());
//            } catch (SQLException e) {
//                System.err.println("Erreur lors de l'ajout de le badge : " + e.getMessage());
//            }        
//  
////    
        
             //-------------------------------------------Test Modifier Badge----------------------------------------------------------------
//      
//        Badge nouvelbadge = new Badge();
//
//       try {
//          
//            Badge badgeModifie = new Badge();
//            badgeModifie.setDateBadge(LocalDate.now());
//            badgeModifie.setCommantaire("Nouveau contenu de l'avis");
//            badgeModifie.setTypeBadge(TypeBadge.VIP); 
//            
//            int idBadgeAModifier = 7; 
//            
//            int nouvelIdRestaurant = 8;
//            serviceBadge.modifier(idBadgeAModifier, badgeModifie, nouvelIdRestaurant);
//
//            System.out.println("Mise à jour de le badge réussie !");
//        } catch (SQLException e) {
//            System.err.println("Erreur lors de la mise à jour de le badge : " + e.getMessage());
//        }
//      
// 
       
        //-------------------------------------------Test Supprimer Badge----------------------------------------------------------------
       
//        int idBadgeASupprimer = 26;
//    try {
//        
//        serviceBadge.supprimer(idBadgeASupprimer);
//        System.out.println("Badge supprimé avec succès !");
//    } catch (SQLException e) {
//        System.err.println("Erreur lors de la suppression de l'Badge : " + e.getMessage());
//    }
//    
       
       
               //-------------------------------------------Test Récuperer Badge----------------------------------------------------------------
// 
//      try {
//            List<Badge> listeBadges = serviceBadge.recuperer();
//
//             for (Badge badge : listeBadges) {
//                System.out.println("ID de la badge: " + badge.getId());
//                System.out.println("Date de la badge: " + badge.getDateBadge());
//                System.out.println("Commantaire de la badge: " + badge.getCommantaire());
//                System.out.println("Type de la badge: " + badge.getTypeBadge());
//                
//               if (badge.getRestaurant() != null) {
//                    System.out.println("ID du Restaurant associé: " + badge.getRestaurant().getIdrestau());
//                    System.out.println("Nom du Restaurant associé: " + badge.getRestaurant().getNom());
//                } else {
//                    System.out.println("Aucun restaurant associé à cet badge.");
//                }
//                
//                if (badge.getUser() != null) {
//                    System.out.println("ID du User associé: " + badge.getUser().getIduser());
//                    System.out.println("Nom d'utilisateur du User associé: " + badge.getUser().getUsername());
//                } else {
//                    System.out.println("Aucun user associé à cet badge.");
//                }
//
//                System.out.println("-----------------------------------");
//            }
//        } catch (SQLException e) {
//            System.err.println("Erreur lors de la récupération des badges : " + e.getMessage());
//        }


    }}
    

