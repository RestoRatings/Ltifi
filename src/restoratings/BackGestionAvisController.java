/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restoratings;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.entities.Avis;
import tn.esprit.entities.Badge;
import tn.esprit.entities.Restaurant;
import tn.esprit.services.ServiceAvis;
import tn.esprit.services.Servicerestaurant;

/**
 * FXML Controller class
 *
 * @author Ltifi
 */
public class BackGestionAvisController implements Initializable {

    /**
     * Initializes the controller class.
     */
    

    

    @FXML
    private TextField recheercheParRestoTextfield;

    @FXML
    private TextField recheercheParUsernameTextfield;

    

    @FXML
    private Button supprimerbk;
    
    @FXML
    private TableView<Avis> tableviewback;

    @FXML
    private TableColumn<Avis,String> titreAvisbk;

    @FXML
    private TableColumn<Avis,String> userbk;
  @FXML
    private TableColumn<Avis,String> restobk;
@FXML
    private TableColumn<Avis,LocalDate> datebk;

    @FXML
    private TableColumn<Avis,String> pubavbk;
    @FXML
    private DatePicker Rechdate;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configureColumns();
        
                         tableviewback.getStylesheets().add(getClass().getResource("GestionAis.css").toExternalForm());

        // Appliquer les classes de style à la TableView
        tableviewback.getStyleClass().add("my-table-view");

        // Appliquer les classes de style aux cellules de la TableView
        tableviewback.setRowFactory(tv -> {
            TableRow<Avis> row = new TableRow<>();
            row.getStyleClass().add("my-table-cell");
            return row;
        });
        try {
            show();
        } catch (SQLException ex) {
            Logger.getLogger(BackGestionAvisController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
 }    
    private Avis avisSelectionne;
     private ServiceAvis serviceAvis = new ServiceAvis();
     private Servicerestaurant restaurantService = new Servicerestaurant();
     Restaurant restaurant = new Restaurant();
    @FXML

private void showAlert(Alert.AlertType type, String title, String header, String content) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
}

@FXML
private void supprimer(ActionEvent event) throws SQLException {
            Avis avisSelectionne = tableviewback.getSelectionModel().getSelectedItem(); 

    ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
    ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setContentText("Voulez-vous supprimer cet Avis ?");
    dialog.getDialogPane().getButtonTypes().addAll(okButtonType, cancelButtonType);

    Optional<ButtonType> result = dialog.showAndWait();

    if (result.isPresent() && result.get() == okButtonType) {
        // Supprimez le badge
        serviceAvis.supprimer(avisSelectionne.getId());
        showAlert(Alert.AlertType.CONFIRMATION, "Succès", "Suppression de Avis réussie", "");
        // Appelez ici la méthode pour mettre à jour l'affichage
    } else {
        System.out.println("Annulation de la suppression");
    }
    show();
    // Appelez ici la méthode pour mettre à jour l'affichage si nécessaire
}


@FXML
private void handleSearch(ActionEvent event) {
    LocalDate date = Rechdate.getValue();
    String username = recheercheParUsernameTextfield.getText();
    String restoName = recheercheParRestoTextfield.getText().toLowerCase();
   

    List<Avis> result = new ArrayList<>();

    for (Avis avis : tableviewback.getItems()) {
        boolean matchesResto = avis.getRestaurant().getNom().toLowerCase().contains(restoName);
        
        boolean matchesUsername = avis.getUser().getUsername().toLowerCase().contains(username);
        boolean matchesDate = (avis == null) || avis.getDateAvis().equals(date);

        if (matchesResto  && matchesUsername && matchesDate) {
            result.add(avis);
        }
    }

    // Update the TableView with the search results
    tableviewback.setItems(FXCollections.observableArrayList(result));
}



private void configureColumns() {
datebk.setCellValueFactory(new PropertyValueFactory<>("dateAvis"));
pubavbk.setCellValueFactory(new PropertyValueFactory<>("pubAvis"));
titreAvisbk.setCellValueFactory(new PropertyValueFactory<>("titreAvis"));
//userCol.setCellValueFactory(new PropertyValueFactory<>("username"));
//nomRestoCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
userbk.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getUsername()));
restobk.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRestaurant().getNom()));

 }

public void show() throws SQLException {
    ObservableList<Avis> data = FXCollections.observableArrayList(ServiceAvis.getInstance().recuperer());

    // Configure your TableColumn cell value factories here

    tableviewback.setItems(data);

    // Wrap the ObservableList in a FilteredList (initially display all data).
    FilteredList<Avis> filteredData = new FilteredList<>(data, b -> true);

    // Set the filter Predicate whenever the filter fields change.
    recheercheParRestoTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(avis -> {
            String restoName = recheercheParRestoTextfield.getText().toLowerCase();
            String avisRestaurantName = avis.getRestaurant().getNom().toLowerCase();
            boolean nomRestaurantCorrespond = avisRestaurantName.contains(restoName);
            return restoName.isEmpty() || nomRestaurantCorrespond;
        });
    });

    

    recheercheParUsernameTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(avis -> {
            String username = recheercheParUsernameTextfield.getText().toLowerCase();
            return avis.getUser().getUsername().toLowerCase().contains(username);
        });
    });

    Rechdate.valueProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(avis -> {
            LocalDate date = Rechdate.getValue();
            return avis.getDateAvis().equals(date);
        });
    
    });

    // Wrap the FilteredList in a SortedList.
    SortedList<Avis> sortedData = new SortedList<>(filteredData);

    // Bind the SortedList comparator to the TableView comparator.
    sortedData.comparatorProperty().bind(tableviewback.comparatorProperty());

    // Add sorted (and filtered) data to the table.
    tableviewback.setItems(sortedData);
}


}












///////////////////////////////////////////////////////////////////////////////////////

