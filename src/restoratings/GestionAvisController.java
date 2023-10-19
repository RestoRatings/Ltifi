/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restoratings;

import com.mysql.jdbc.Connection;
import java.net.URL;
import static java.sql.JDBCType.NULL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import tn.esprit.entities.Avis;
import tn.esprit.entities.Restaurant;
import tn.esprit.entities.User;
import tn.esprit.services.ServiceAvis;
import tn.esprit.services.Servicerestaurant;
import javafx.scene.control.ComboBox;
import java.lang.String;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.Notifications;
import tn.esprit.entities.Badge;




/**
 * FXML Controller class
 *
 * @author Ltifi
 */
public class GestionAvisController implements Initializable {



     private ServiceAvis serviceAvis = new ServiceAvis();
     private Servicerestaurant restaurantService = new Servicerestaurant(); 
     Restaurant restaurant = new Restaurant();

     Connection connexion;
   
    @FXML
    private Button btnAfficherq;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;

    @FXML
    private ComboBox<String> comboBoxRestaurant;

    @FXML
    private TextArea pubavisTextArea;

    @FXML
    private TextField titreavisTextfield;
    
    
     
    @FXML
    private TextField RestoNameTextfield1;
     @FXML
    private TableColumn<Avis, String> usernameCol;
      @FXML
    private TableView<Avis> tabviewAvis;
      @FXML
    private TableColumn<Avis, LocalDate> dateCol;
    @FXML
    private TableColumn<Avis, String> nomRestoCol;

    @FXML
    private TableColumn<Avis, String> pubCol;
    @FXML
    private TableColumn<Avis, String> titreCol;
    
    
    
    
    public void initialize(URL url, ResourceBundle rb) {
    configureComboBox();
    configureColumns();
        tabviewAvis.getStylesheets().add(getClass().getResource("GestionAis.css").toExternalForm());

        tabviewAvis.getStyleClass().add("my-table-view");

        tabviewAvis.setRowFactory(tv -> {
            TableRow<Avis> row = new TableRow<>();
            row.getStyleClass().add("my-table-cell");
            return row;
        });

        
        
    tabviewAvis.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            Avis selectedAvis = tabviewAvis.getSelectionModel().getSelectedItem();
            remplirComboBoxAvecAvisSelectionne(selectedAvis);
        }
    });
    RestoNameTextfield1.textProperty().addListener((observable, oldValue, newValue) -> {
            rechercherAvis(null);
        });
     chargerAvis(null);
}
    
    
    
    
    /////////////////////////////////////////Ajouter Avis//////////////////////////////////////////////////

@FXML
private void ajouterAvis(ActionEvent event) throws SQLException {
    // Récupérez le texte des champs titreAvis et pubAvis
    String pubAvis = pubavisTextArea.getText();
    String titreAvis = titreavisTextfield.getText();
    String restaurantSelectionne = comboBoxRestaurant.getValue();

    User user = new User();
    user.setIduser(1);

    if (titreAvis != null && !titreAvis.isEmpty() && pubAvis != null && !pubAvis.isEmpty()
        && restaurantSelectionne != null) {
       

        boolean titreLongueurValide = titreAvis.length() <= 15;
        boolean pubLongueurValide = pubAvis.length() <= 150;

       
            
            if (titreLongueurValide && pubLongueurValide) {
                titreAvis = titreAvis.replaceFirst("israel","");
                pubAvis = pubAvis.replaceFirst("israel", "");

                Avis nouvelAvis = new Avis();
                nouvelAvis.setPubAvis(pubAvis);
                nouvelAvis.setTitreAvis(titreAvis);
                nouvelAvis.setDateAvis(LocalDate.now()); 

                Restaurant restaurant = new Restaurant();
                restaurant.setIdrestau(restaurantService.getIdRestaurantParNom(restaurantSelectionne));

                try {
                    ServiceAvis.getInstance().ajouter(nouvelAvis, restaurant, user);
                    showAlert(Alert.AlertType.INFORMATION, "Succès", "Avis ajouté avec succès", "");

                   
                    comboBoxRestaurant.setValue(null);
                    titreavisTextfield.clear();
                    pubavisTextArea.clear();
                    show();
                        Notification();

                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de l'ajout de l'avis", e.getMessage());
                    e.printStackTrace();
                }
            } else {
                if (!titreLongueurValide) {
                    showAlert(Alert.AlertType.INFORMATION, "Erreur de saisie", "Le titre ne doit pas dépasser 15 caractères", "");
                }
                if (!pubLongueurValide) {
                    showAlert(Alert.AlertType.INFORMATION, "Erreur de saisie", "La publication ne doit pas dépasser 150 caractères", "");
                }
            }
        
    } else {
        showAlert(Alert.AlertType.INFORMATION, "", "Remplissez tous les champs, y compris le restaurant", "");
    }
}






     
    private void showAlert(Alert.AlertType type, String title, String header, String content) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
}
 ////////////////////////////////////////////          ///////////////////////////////:
 ///////////////////////////////////////////Afficher//////////////////////////////////////////////:
 @FXML
    private void chargerAvis(ActionEvent event) {
        try {
            ObservableList<Avis> observableBadges = FXCollections.observableArrayList(serviceAvis.recuperer());
            tabviewAvis.setItems(observableBadges);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors du chargement des badges", e.getMessage());
            e.printStackTrace();
        }
    }
///////////////////////////////////////////////Modifier Avis/////////////////////////////////////////
    @FXML
private void updateAvis(ActionEvent event) throws SQLException {
    Avis selectedAvis = tabviewAvis.getSelectionModel().getSelectedItem();

    if (selectedAvis != null) {
        String pubAvis = pubavisTextArea.getText();
        String titreAvis = titreavisTextfield.getText();
        String restaurantSelectionne = comboBoxRestaurant.getValue();

        if (pubAvis.isEmpty() || titreAvis.isEmpty() || restaurantSelectionne.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Champs vides", "Veuillez remplir tous les champs", "Assurez-vous de remplir tous les champs avant de mettre à jour l'avis.");
        } else {
            LocalDate currentDate = LocalDate.now();

            boolean pubValide = pubAvis.length() <= 150;
            boolean titreValide = titreAvis.length() <= 30;
            

            if (pubValide && titreValide ) {
                try {
                    int selectedRestaurantId = restaurantService.getIdRestaurantParNom(restaurantSelectionne);

                    titreAvis = titreAvis.replaceFirst("israel","");
                    pubAvis = pubAvis.replaceFirst("israel", "");
                    selectedAvis.setPubAvis(pubAvis);
                    selectedAvis.setDateAvis(currentDate);
                    selectedAvis.getRestaurant().setIdrestau(selectedRestaurantId);
                    selectedAvis.setTitreAvis(titreAvis);

                    serviceAvis.modifier(selectedAvis.getId(), selectedAvis, selectedRestaurantId);
                    show();
                    chargerAvis(event);
                    comboBoxRestaurant.setValue(null);

                    titreavisTextfield.clear();
                    pubavisTextArea.clear();
                        Notification1();
                    showAlert(Alert.AlertType.INFORMATION, "Succès", "Avis mis à jour avec succès", "L'avis a été mis à jour avec succès.");
                } catch (SQLException e) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la mise à jour de l'avis", e.getMessage());
                }
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Erreur de saisie", "Assurez-vous que le titre ne dépasse pas 15 caractères et que la publication ne dépasse pas 150 caractères, et qu'ils ne contiennent que des lettres, '@' et '#'.", "");
            }
        }
    }
    chargerAvis(event);
}
    
 ////////////////////////////////////////////          ///////////////////////////////:
 ///////////////////////////////////////////Supprimer//////////////////////////////////////////////:


    private Avis avisSelectionne;
    
    private static int utilisateurConnecte = 1;
@FXML
private void supprimerAvis(ActionEvent event) throws SQLException {
    Avis avisSelectionne = tabviewAvis.getSelectionModel().getSelectedItem();
    if (avisSelectionne != null) {
        User utilisateurAvis = avisSelectionne.getUser();

        if (utilisateurAvis.getIduser() == utilisateurConnecte) {
            ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setContentText("Voulez-vous supprimer cet avis ?");
            dialog.getDialogPane().getButtonTypes().addAll(okButtonType, cancelButtonType);

            Optional<ButtonType> result = dialog.showAndWait();

            if (result.isPresent() && result.get() == okButtonType) {
                serviceAvis.supprimer(avisSelectionne.getId());
                showAlert(Alert.AlertType.CONFIRMATION, "Succès", "Suppression de l'avis réussie", "");
                Notification2();
            } else {
                System.out.println("Annulation de la suppression");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Vous ne pouvez pas supprimer cet avis", "Cet avis n'appartient pas à vous.");
        }

    }
    show();
}



/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////
 
 
 private void remplirComboBoxAvecAvisSelectionne(Avis avis) {
    if (avis != null) {
        comboBoxRestaurant.setValue(avis.getRestaurant().getNom());
        titreavisTextfield.setText(avis.getTitreAvis());
        pubavisTextArea.setText(avis.getPubAvis());
        
    }
}


public void rechercherAvis(ActionEvent event) {
    String restoName = RestoNameTextfield1.getText();
    

    List<Avis> result = new ArrayList<>();

    
    if (!restoName.isEmpty()) {
        // Recherche par nom de restaurant
        for (Avis avis : tabviewAvis.getItems()) {
            if (avis.getRestaurant().getNom().toLowerCase().contains(restoName.toLowerCase())) {
                result.add(avis);
            }
        }
    
    } else {
        chargerAvis(event);
        return;  
    }

    tabviewAvis.setItems(FXCollections.observableArrayList(result));
    
}


  





    
    


 





















  public void show() throws SQLException
    {
        
    ObservableList<Avis> data = FXCollections.observableArrayList(ServiceAvis.getInstance().recuperer());
       dateCol.setCellValueFactory(new PropertyValueFactory<>("dateAvis"));
pubCol.setCellValueFactory(new PropertyValueFactory<>("pubAvis"));
titreCol.setCellValueFactory(new PropertyValueFactory<>("titreAvis"));
//userCol.setCellValueFactory(new PropertyValueFactory<>("username"));
//nomRestoCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
usernameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getUsername()));
nomRestoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRestaurant().getNom()));

        tabviewAvis.setItems(data);
        
     
        FilteredList<Avis> filteredData = new FilteredList<>(data, b -> true);

        RestoNameTextfield1.textProperty().addListener((observable, oldValue, newValue) -> {
           filteredData.setPredicate(avis -> {
        String restoName = RestoNameTextfield1.getText().toLowerCase();
        

        String avisRestaurantName = avis.getRestaurant().getNom().toLowerCase();
       

        boolean nomRestaurantCorrespond = avisRestaurantName.contains(restoName);
        

        return (restoName.isEmpty() || nomRestaurantCorrespond);
    });
            });
        

        SortedList<Avis> sortedData = new SortedList<>(filteredData);


        sortedData.comparatorProperty().bind(tabviewAvis.comparatorProperty());

        tabviewAvis.setItems(sortedData);   
        
    }



 /////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////Config/////////////////////////////////////////////////////
   
private void configureComboBox() {
    try {
        Servicerestaurant restaurantService = new Servicerestaurant();

        List<String> restaurantNames = restaurantService.getRestaurantNames();

        ObservableList<String> restaurantNameList = FXCollections.observableArrayList(restaurantNames);

      comboBoxRestaurant.setItems(restaurantNameList);
    } catch (SQLException e) {
        showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors du chargement des noms de restaurants", e.getMessage());
        e.printStackTrace();
    }
}

    
   
 private void configureColumns() {
dateCol.setCellValueFactory(new PropertyValueFactory<>("dateAvis"));
pubCol.setCellValueFactory(new PropertyValueFactory<>("pubAvis"));
titreCol.setCellValueFactory(new PropertyValueFactory<>("titreAvis"));
//userCol.setCellValueFactory(new PropertyValueFactory<>("username"));
//nomRestoCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
usernameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getUsername()));
nomRestoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRestaurant().getNom()));

 }
 
 
 
 

      public void Notification1(){
	Platform.runLater(new Runnable() {
		@Override
		public void run() {
			Notifications notify = Notifications.create().title("Avis")
					.text("modif avec success")
					.hideAfter(javafx.util.Duration.seconds(4))
					.position(Pos.BOTTOM_RIGHT);
			notify.darkStyle();
			notify.showInformation();
		}
	}); 


}
       public void Notification(){
	Platform.runLater(new Runnable() {
		@Override
		public void run() {
			Notifications notify = Notifications.create().title("Avis")
					.text("Ajout avec success")
					.hideAfter(javafx.util.Duration.seconds(4))
					.position(Pos.BOTTOM_RIGHT);
			notify.darkStyle();
			notify.showInformation();
		}
	}); 


}
       public void Notification2(){
	Platform.runLater(new Runnable() {
		@Override
		public void run() {
			Notifications notify = Notifications.create().title("Avis")
					.text("suppression avec success")
					.hideAfter(javafx.util.Duration.seconds(4))
					.position(Pos.BOTTOM_RIGHT);
			notify.darkStyle();
			notify.showInformation();
		}
	}); 


}

  
  
  
}

//     @FXML
//    private TableColumn<Avis, String> usernameCol;

//
//    @FXML
//    private TableColumn<Avis, String> nomRestoCol;
//
//    @FXML
//    private TableColumn<Avis, String> pubCol;
//    @FXML
//    private TableColumn<Avis, String> titreCol;