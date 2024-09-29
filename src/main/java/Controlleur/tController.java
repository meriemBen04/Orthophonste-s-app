package Controlleur;

import Model.Test;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class tController {

    @FXML
    private Button testid;




    public void setData(Test test)
    {
        testid.setOnAction(event ->
        {
            try
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tp_poo/testbuton.fxml"));
                Parent root = loader.load();
                testbuttomController fiche = loader.getController();
                fiche.setficheData(test);

                //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                Scene scene = new Scene(root, 1000, 670);
//                stage.setScene(scene);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        });





    }

}
