/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restoratings;

import java.net.URL;
import static java.sql.JDBCType.NULL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.Notifications;
import tn.esprit.entities.Avis;
import tn.esprit.entities.Badge;
import tn.esprit.entities.TypeBadge;
import tn.esprit.services.ServiceBadge;

/**
 * FXML Controller class
 *
 * @author Ltifi
 */
public class BackGestionBadgeController implements Initializable {

      @FXML
    private TextField RestoNameRech;

    @FXML
    private TextField TypeBadgeRech;

    @FXML
    private TextField UsernameRech;

    @FXML
    private DatePicker dateRech;
    
    @FXML
    private Button actualiserech;

    

    @FXML
    private Button rechercherbk;

    @FXML
    private Button supprimerbk;

    @FXML
    private TableView<Badge> tabviewRech;

    @FXML
    private TableColumn<Badge, TypeBadge> typebadget;

    @FXML
    private TableColumn<Badge, String> usert;
    @FXML
    private TableColumn<Badge, String> restot;
    @FXML
    private TableColumn<Badge, LocalDate> datet;
      @FXML
    private TableColumn<Badge, String> commantairet;

    /**
     * Initializes the controller class.
     */
      ServiceBadge serviceBadge=new ServiceBadge();
   @Override
public void initialize(URL url, ResourceBundle rb) {
    configureColumns();
        tabviewRech.getStylesheets().add(getClass().getResource("GestionAis.css").toExternalForm());

        // Appliquer les classes de style à la TableView
        tabviewRech.getStyleClass().add("my-table-view");

        // Appliquer les classes de style aux cellules de la TableView
        tabviewRech.setRowFactory(tv -> {
            TableRow<Badge> row = new TableRow<>();
            row.getStyleClass().add("my-table-cell");
            return row;
        });

    // Configure your ComboBoxes and input fields here

    try {
        show(); // Call the show function to populate the table with filtered data
    } catch (SQLException ex) {
        Logger.getLogger(GestionBadgeController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }    



     
//    @FXML
//private void handleSearch(ActionEvent event) {
// 
//    
//    LocalDate date = dateRech.getValue();
//    
//    String username = UsernameRech.getText();
//    
//    
//    String restoName = RestoNameRech.getText();
//    String badgeName = TypeBadgeRech.getText();
//
//    List<Badge> result = new ArrayList<>();
//
//    if (!restoName.isEmpty() && !badgeName.isEmpty()) {
//        // Recherche par les deux critères
//        for (Badge badge : tabviewRech.getItems()) {
//            if (badge.getRestaurant().getNom().toLowerCase().contains(restoName.toLowerCase()) &&
//                badge.getTypeBadge().name().toLowerCase().contains(badgeName.toLowerCase())) {
//                result.add(badge);
//            }
//        }
//    } else if (!restoName.isEmpty()) {
//        // Recherche par nom de restaurant
//        for (Badge badge : tabviewRech.getItems()) {
//            if (badge.getRestaurant().getNom().toLowerCase().contains(restoName.toLowerCase())) {
//                result.add(badge);
//            }
//        }
//    } else if (!badgeName.isEmpty()) {
//        // Recherche par nom de badge (TypeBadge)
//        for (Badge badge : tabviewRech.getItems()) {
//            if (badge.getTypeBadge().name().toLowerCase().contains(badgeName.toLowerCase())) {
//                result.add(badge);
//            }
//        }
//    } else {
//        // Les deux champs sont vides, rechargez tous les badges
//        charger(event);
//        return; // Sortez de la fonction pour éviter de répéter le code de chargement
//    }
//
//    // Mettez à jour la TableView avec les résultats de la recherche
//    tabviewRech.setItems(FXCollections.observableArrayList(result));
//}
@FXML
private void handleSearch(ActionEvent event) {
    LocalDate date = dateRech.getValue();
    String username = UsernameRech.getText();
    String restoName = RestoNameRech.getText().toLowerCase();
    String badgeName = TypeBadgeRech.getText().toLowerCase();

    List<Badge> result = new ArrayList<>();

    for (Badge badge : tabviewRech.getItems()) {
        boolean matchesResto = badge.getRestaurant().getNom().toLowerCase().contains(restoName);
        boolean matchesTypeBadge = badge.getTypeBadge().name().toLowerCase().contains(badgeName);
        boolean matchesUsername = badge.getUser().getUsername().toLowerCase().contains(username);
        boolean matchesDate = (date == null) || badge.getDateBadge().equals(date);

        if (matchesResto && matchesTypeBadge && matchesUsername && matchesDate) {
            result.add(badge);
        }
    }

    // Update the TableView with the search results
    tabviewRech.setItems(FXCollections.observableArrayList(result));
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////




@FXML
private void supprimerback(ActionEvent event) throws SQLException {
            Badge badgeSelectionne = tabviewRech.getSelectionModel().getSelectedItem(); 

    ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
    ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setContentText("Voulez-vous supprimer cet badge ?");
    dialog.getDialogPane().getButtonTypes().addAll(okButtonType, cancelButtonType);

    Optional<ButtonType> result = dialog.showAndWait();

    if (result.isPresent() && result.get() == okButtonType) {
        // Supprimez le badge
        serviceBadge.supprimer(badgeSelectionne.getId());
        showAlert(Alert.AlertType.CONFIRMATION, "Succès", "Suppression de badge réussie", "");
        // Appelez ici la méthode pour mettre à jour l'affichage
    } else {
        System.out.println("Annulation de la suppression");
    }
    show();
    // Appelez ici la méthode pour mettre à jour l'affichage si nécessaire
}






//////////////////////////////////////////////////////////////////////////////////
@FXML
    private void charger(ActionEvent event) {
        try {
            ObservableList<Badge> observableBadges = FXCollections.observableArrayList(serviceBadge.recuperer());
            tabviewRech.setItems(observableBadges);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors du chargement des badges", e.getMessage());
            e.printStackTrace();
        }
    }
private void showAlert(Alert.AlertType type, String title, String header, String content) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
}

private void configureColumns() {
datet.setCellValueFactory(new PropertyValueFactory<>("dateBadge"));
typebadget.setCellValueFactory(new PropertyValueFactory<>("typeBadge"));
commantairet.setCellValueFactory(new PropertyValueFactory<>("commantaire"));
//userCol.setCellValueFactory(new PropertyValueFactory<>("username"));
//nomRestoCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
usert.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getUsername()));
restot.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRestaurant().getNom()));

 }
//@FXML
//    private TableView<Badge> tabviewRech;
//
//    @FXML
//    private TableColumn<Badge, TypeBadge> typebadget;
//
//    @FXML
//    private TableColumn<Badge, String> usert;
//    @FXML
//    private TableColumn<Badge, String> restot;
//    @FXML
//    private TableColumn<Badge, LocalDate> datet;
//      @FXML
//    private TableColumn<Badge, String> commantairet;
//
//   @FXML
//    private TextField RestoNameRech;
//
//    @FXML
//    private TextField TypeBadgeRech;
//
//    @FXML
//    private TextField UsernameRech;
//
//    @FXML
//    private DatePicker dateRech;
//    
public void show() throws SQLException {
    ObservableList<Badge> data = FXCollections.observableArrayList(ServiceBadge.getInstance().recuperer());

    // Configure your TableColumn cell value factories here

    tabviewRech.setItems(data);

    // Wrap the ObservableList in a FilteredList (initially display all data).
    FilteredList<Badge> filteredData = new FilteredList<>(data, b -> true);

    // Set the filter Predicate whenever the filter fields change.
    RestoNameRech.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(badge -> {
            String restoName = RestoNameRech.getText().toLowerCase();
            String avisRestaurantName = badge.getRestaurant().getNom().toLowerCase();
            boolean nomRestaurantCorrespond = avisRestaurantName.contains(restoName);
            return restoName.isEmpty() || nomRestaurantCorrespond;
        });
    });

    TypeBadgeRech.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(badge -> {
            String typeBadge = TypeBadgeRech.getText().toLowerCase();
            return badge.getTypeBadge().name().toLowerCase().contains(typeBadge);
        });
    });

    UsernameRech.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(badge -> {
            String username = UsernameRech.getText().toLowerCase();
            return badge.getUser().getUsername().toLowerCase().contains(username);
        });
    });

    dateRech.valueProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(badge -> {
            LocalDate date = dateRech.getValue();
            return badge.getDateBadge().equals(date);
        });
    });

    // Wrap the FilteredList in a SortedList.
    SortedList<Badge> sortedData = new SortedList<>(filteredData);

    // Bind the SortedList comparator to the TableView comparator.
    sortedData.comparatorProperty().bind(tabviewRech.comparatorProperty());

    // Add sorted (and filtered) data to the table.
    tabviewRech.setItems(sortedData);
}



  
  
  
  
}



