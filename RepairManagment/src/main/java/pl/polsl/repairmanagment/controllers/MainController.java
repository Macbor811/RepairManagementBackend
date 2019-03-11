package pl.polsl.repairmanagment.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.polsl.repairmanagment.entities.Client;
import pl.polsl.repairmanagment.entities.ClientRepository;

@Controller
public class MainController {

    @FXML
    private Label label;

    @FXML
    private TableView table;

    @Autowired
    private ClientRepository clientRepository;

    @FXML
    private void buttonClicked(){
//        table.setEditable(true);
//        table.getColumns().add(new TableColumn("Id"));
//        table.getColumns().add(new TableColumn("Name"));
//
//
//        label.setText( clientRepository.selectAll().get(0).getName());
//        clientRepository.selectAll();

        Client client = new Client();
        client.setName("z kliku");

        clientRepository.save(client);
    }



}
