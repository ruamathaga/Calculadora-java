/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Calculo;

/**
 * FXML Controller class
 *
 * @author Ruama
 */
public class HistoricoController implements Initializable {

    @FXML
    private TableView<?> tbHistorico;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnFechar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listar();
    }    

    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/Calculadora.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),900,682);
        Stage stage = new Stage();
        stage.setTitle("Calculadora");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Fechar(ActionEvent event) {
    Stage stage = (Stage) btnFechar.getScene().getWindow();
    stage.close();    
    }
    
    private void listar() {      
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("calculadora");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT a FROM Calculo a");

        List<Calculo> calculadora = query.getResultList();

        ObservableList oCalculo = FXCollections.observableArrayList(calculadora);
        tbHistorico.setItems(oCalculo);  
    }
    
}
