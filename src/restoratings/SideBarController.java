package restoratings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class SideBarController implements Initializable {
    @FXML
    private BorderPane bp;  // Assurez-vous d'avoir un BorderPane dans votre fichier FXML

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void GestionAvis(MouseEvent event) {
        loadPage("GestionAvis");
    }

    @FXML
    void GestionBadge(MouseEvent event) {
        loadPage("GestionBadge");
    }

    private void loadPage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (Exception ex) {
            Logger.getLogger(SideBarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);  // Cela suppose que votre BorderPane est bien nommé bp
    }
}
