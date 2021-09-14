package com.itproger_9_hw;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class RegController {

    @FXML
    private TextField email_chan;

    @FXML
    private TextField login_chan;

    @FXML
    private PasswordField passw_chan;

    @FXML
    private Button btn_chan;

    DB db = new DB();

    @FXML
    void initialize() {
        login_chan.setText("Admin");
        email_chan.setText("example@mail.ru");
        btn_chan.setOnAction(event -> {
            login_chan.setStyle("-fx-border-color: #fafafa");
            login_chan.setStyle("-fx-border-color: #fafafa");
            login_chan.setStyle("-fx-border-color: #fafafa");
            btn_chan.setText("Готово");

            if(login_chan.getCharacters().length() <= 3){
                login_chan.setStyle("-fx-border-color: red");
                return;
            } else if (email_chan.getCharacters().length() <= 5){
                email_chan.setStyle("-fx-border-color: red");
                return;
            }else if(passw_chan.getCharacters().length() <= 3){
                passw_chan.setStyle("-fx-border-color: red");
                return;
            }
            String pass = md5String(passw_chan.getCharacters().toString());
            try {
               boolean isReg=  db.changeUser(login_chan.getCharacters().toString(), email_chan.getCharacters().toString(), pass);
               if(isReg){
                   login_chan.setText("");
                   email_chan.setText("");
                   passw_chan.setText("");
                   btn_chan.setText("Изменить данные");
               }else
                   login_chan.setText("ВВедите другой логин");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    public  static String md5String(String pass){
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(pass.getBytes(StandardCharsets.UTF_8));
            digest = messageDigest.digest();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInteger = new BigInteger(1,digest);
        String md5Hex = bigInteger.toString(16);
        while (md5Hex.length() < 32){
            md5Hex = "0"+md5Hex;
        }
        return md5Hex;
    }
}
