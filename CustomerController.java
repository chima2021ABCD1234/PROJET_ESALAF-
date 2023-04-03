package ma.fstt.esalaf;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class CustomerController implements Initializable {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    @FXML
    private Label welcomeText;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnSave;

    @FXML
    private TextField tCredit;

    @FXML
    private TextField tFName;

    @FXML
    private TextField tLastName;
    @FXML
    private TableColumn<Client, Integer> colCredit;

    @FXML
    private TableColumn<Client, String> colfName;

    @FXML
    private TableColumn<Client, Integer> colid;

    @FXML
    private TableColumn<Client, String> collName;

    @FXML
    private TableView<Client> table;
    int id = 0;

    @FXML
    void clearField(ActionEvent event) {
        clear();

    }

    @FXML
    void creatClient(ActionEvent event) {
        String insert = "insert into clients(FirstName,LastName,Credit) values(?,?,?)";
        con = DataBaseCon.getCon();
        try {
            st = con.prepareStatement(insert);
            st.setString(1,tFName.getText());
            st.setString(2,tLastName.getText());
            st.setInt(3, Integer.parseInt(tCredit.getText()));
            st.executeUpdate();
            clear();
            showClients();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void getData(MouseEvent event) {
        Client client = table.getSelectionModel().getSelectedItem();
        id_client = client.getId_client();
        tFName.setText(client.getPrenom());
        tLastName.setText(client.getNom());
        tCredit.setText(String.valueOf(client.getCredit()));
        btnSave.setDisable(true);


    }
    void clear(){
        tFName.setText(null);
        tLastName.setText(null);
        tCredit.setText(null);
        btnSave.setDisable(false);



    }

    @FXML
    void deleteClient(ActionEvent event) {
        String delete = "delete from clients where id_client = ?";
        con = DataBaseCon.getCon();
        try {
            st = con.prepareStatement(delete);
            st.setInt(1,id_client);
            st.executeUpdate();
            showClients();
            clear();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void updateClient(ActionEvent event) {
        String update = "update clients set FirstName = ?, LastName =?, Credit = ? where id_client=?";
        con = DataBaseCon.getCon();
        try {
            st = con.prepareStatement(update);
            st.setString(1,tFName.getText());
            st.setString(2,tLastName.getText());
            st.setInt(3, Integer.parseInt(tCredit.getText()));
            st.setInt(4,id);
            st.executeUpdate();
            showClients();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showClients();

    }
    public ObservableList<Client> getClients(){
        ObservableList<Client> clients = FXCollections.observableArrayList();

        String query = "select* from clients";
        con = DataBaseCon.getCon();
        try {
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()){
                Client st = new Client();
                st.setId_client(rs.getInt("id_client"));
                st.setPrenom(rs.getString("FirstName"));
                st.setNom(rs.getString("LastName"));
                st.setCredit(rs.getInt("Credit"));
                clients.add(st);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }

    public void showClients(){
        ObservableList<Client> list = getClients();
        table.setItems(list);
        colid.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id_client"));
        colfName.setCellValueFactory(new PropertyValueFactory<Client,String>("prenom"));
        collName.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
        colCredit.setCellValueFactory(new PropertyValueFactory<Client,Integer>("credit"));
    }
}
