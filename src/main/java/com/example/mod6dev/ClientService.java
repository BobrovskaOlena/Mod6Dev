package com.example.mod6dev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private static final String INSERT_NEW_CLIENT_STRING = "INSERT INTO client (id,name) VALUES (?,?)";
    private static final String SELECT_BY_ID_STRING = "SELECT id, name FROM client WHERE id = ?";
    private static final String SELECT_ALL_STRING = "SELECT id, name FROM client";
    private static final String UPDATE_CLIENT_STRING = "UPDATE client SET name = ? WHERE id = ?";
    private static final String DELETE_CLIENT_BY_ID_STRING = "DELETE FROM client WHERE id = ?";
    public PreparedStatement selectAllStatement;
    public PreparedStatement selectById;
    public PreparedStatement insertNewClient;
    public PreparedStatement updateStatement;
    public PreparedStatement deleteStatement;
    public Connection connection;
    public ClientService(Connection connection){
        try {
            this.connection = connection;
            this.insertNewClient = connection.prepareStatement(INSERT_NEW_CLIENT_STRING);
            this.selectById = connection.prepareStatement(SELECT_BY_ID_STRING);
            this.selectAllStatement = connection.prepareStatement(SELECT_ALL_STRING);
            this.updateStatement = connection.prepareStatement(UPDATE_CLIENT_STRING);
            this.deleteStatement = connection.prepareStatement(DELETE_CLIENT_BY_ID_STRING);

        }catch (SQLException e){
            System.out.println("Client Service construction exception. Reason: " + e.getMessage());
        }
    }
    public long create(Long id, String name){
        try {
            this.insertNewClient.setLong(1, id);
            this.insertNewClient.setString(2, name);
            int identifier = this.insertNewClient.executeUpdate();
            if (identifier > 0) {
                ResultSet generatedKeys = this.insertNewClient.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Create client exception. Reason: " + e.getMessage());
        }
        return 1;

}
    public String getById(Long id){
        try {
            this.selectById.setLong(1,id);
            try (ResultSet resultSet = this.selectById.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getString("name");
                }
            }
        }catch(SQLException e) {
            System.out.println("Select client exception. Reason: " + e.getMessage());
    }return null;}

    public void setName(Long id, String name){
        try {
            updateStatement.setString(1, name);
            updateStatement.setLong(2, id);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Set name exception. Reason: " + e.getMessage());
        }
    }

    public void deleteById(Long id){
        try {
            this.deleteStatement.setLong(1, id);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete client exception. Reason: " + e.getMessage());
        }
    }

    public List<Client> listAll(){
        List<Client> clients = new ArrayList<>();
        try (ResultSet resultSet = this.selectAllStatement.executeQuery()) {
            while (resultSet.next()) {
                Client client = new Client(resultSet.getLong("id"), resultSet.getString("name"));
                clients.add(client);
            }
        } catch (SQLException e) {
            System.out.println("Select ALL user exception. Reason: " + e.getMessage());
        }
        return clients;
    }

    public static void main(String[] args) {
        Connection connection = PostgresDatabase.getInstance().getPostgresConnection();
        ClientService clientService = new ClientService(connection);
        //1
        System.out.println("New client: " +clientService.create(7L,"Mark Zumenko"));
        //2
        System.out.println("Client: " +clientService.getById(3L));
        //3
        clientService.setName(5L, "Olena Manulenko");
        //4
        clientService.deleteById(4L);
        //5
        List<Client> clients = clientService.listAll();
        for (Client client : clients) {
            System.out.println("Client ID: " + client.getId());
            System.out.println("Client Name: " + client.getName());}
    }
}
