package controller;

import View.Frame;
import View.loginpanel.RegisterFrame;
import controller.Server.Connectivity;
import controller.Server.Register;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController {
    private Register model;
    private RegisterFrame view;
    private Connectivity con;
    private Frame previouesView;


    public RegisterController(Register register, RegisterFrame registerFrame, Frame previouesView) {
        this.model = register;
        this.view = registerFrame;
        this.previouesView = previouesView;
        setViewButtonSignUp();
        setViewButtonGoToLogin();
    }

    public RegisterController(Register register, RegisterFrame registerFrame,Frame previouesView, Connectivity con) {
        this.model = register;
        this.view = registerFrame;
        this.con = con;
        this.previouesView = previouesView;
        setViewButtonSignUp();
        setViewButtonGoToLogin();
    }

    private String getLogin()
    {
        return model.getLogin();
    }
    private String getPassword()
    {
        return model.getPassword();
    }



    private void setErrorMessage(String errorMessage)
    {
        model.setErrorMessage(errorMessage);
    }
    private void setLogin(String login)
    {
        model.setLogin(login);
    }
    private void setPassword(String password)
    {
        model.setPassword(password);
    }


    private void setViewButtonSignUp()
    {
        view.setButtonSignUp(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("click register button");
                register();
            }
        });
    }

    private void setViewButtonGoToLogin()
    {
        view.setButtonGoToLogIn(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                view.setVisible(false);
                previouesView.setVisible(true);
            }
        });
    }

    private boolean isAdded(String login, Connectivity con)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql="select * from users where login=?";


        try {
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                return true;
            }
            else
            {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void register()
    {



        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        setErrorMessage("");
        String err = "";

        // login
        String login = String.valueOf(view.getInputLogin());
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(login);
        if (!matcher.matches()) {
            err += "Wpisano niepoprawny login - login może zawierać tylko znaki używane w słowach\n";
        }

        //pass
        String pass = String.valueOf(view.getInputPassword());
        String repass = String.valueOf(view.getInputRepeatPassword());
        pattern = Pattern.compile("[^ ]+");
        matcher = pattern.matcher(pass);
        if (!matcher.matches()) {
            err += "Wpisane hasło jest niepoprawne - hasło nie może zawierać białych znakow\n";
        } else if (!pass.equals(repass)) {
            err += "Wpisane hasła nie są takie same\n";
        }


        if (err == "") {
            String sql = "{call addClient(?,?)}";
            try {
                con = new Connectivity();

                if (isAdded(login, con)) {
                    err += "Podany login już istnieje \n";

                    view.setErrorLabel(err);
                    //todo

                } else if (err.length() < 1) {
                    setLogin(view.getInputLogin());
                    setPassword(String.valueOf(view.getInputPassword()));

                    preparedStatement = con.getConn().prepareStatement(sql);

                    preparedStatement.setString(1, getLogin());
                    preparedStatement.setString(2, getPassword());


                    resultSet = preparedStatement.executeQuery();
                    if (isAdded(getLogin(), con)) {
                        view.dispose();
                        previouesView.setVisible(true);

                    }
                }


            } catch (SQLException ex) {
                System.out.println(ex);
                con.close();
            } catch (Exception e) {
                System.out.println(e);
                con.close();
            } finally {
                con.close();
            }

        }
        else
        {
            view.setErrorLabel(err);
            System.out.println("Wiadomość o błędach \n"+err);
        }
    }




}
