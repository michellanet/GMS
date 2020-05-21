package com.gms.dao.member;

import com.gms.dao.Database;
import com.gms.model.member.Difficulty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DifficultyDao {

    Connection connection;

    public DifficultyDao(){
        connection = Database.getDatabase().getConn();
    }

    public ObservableList<Difficulty> getAllDifficulty(){
        ObservableList<Difficulty> difficulties = FXCollections.observableArrayList();

        String sql ="SELECT * FROM DIFFICULTY";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                Difficulty difficulty = new Difficulty();
                difficulty.setId(rs.getInt("DIFFICULTY_ID"));
                difficulty.setDifficulty(rs.getString("DIFFICULTY"));

                difficulties.add(difficulty);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return difficulties;
    }

}
