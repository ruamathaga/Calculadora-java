/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author Ruama
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Button btnEntrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
     @FXML
    private void fechar(){
    Stage stage = (Stage) btnEntrar.getScene().getWindow();
    stage.close();
    }
    @FXML
    private void Entrar() throws IOException {      
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("calculadora");
        EntityManager em = emf.createEntityManager();
       
        Query query = em.createQuery("SELECT u FROM Usuario as u WHERE u.usuario = :user");
        query.setParameter("user", txtUsuario.getText());
        
         if(query.getResultList().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("O usuário não existe");
            alert.showAndWait();
            txtUsuario.setText("");
            txtSenha.setText("");
        }
        else{
             Usuario u = (Usuario) query.getSingleResult();
                if (!u.getSenha().equals(txtSenha.getText())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Senha não confere");
                alert.showAndWait();
                txtSenha.setText("");
            }
            else{        
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/Calculadora.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Bolsonaro 17");
                stage.setScene(scene);
                stage.show();
                
            
               txtSenha.setText("");
               fechar();
            }
        }
    }  
    @FXML
   private void onEnter(KeyEvent event) throws IOException {
       if (event.getCode() == KeyCode.ENTER) {
           Entrar();
       }
   }
   
   //Fazer o login clicando com o mouse
   @FXML
   private void validar(ActionEvent event) throws IOException{
       Entrar();                   
   }
}

    

