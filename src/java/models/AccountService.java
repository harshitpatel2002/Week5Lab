/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author hsp28
 */
public class AccountService
{
//    Non - static method
    public User login (String username, String password)
    {
        User user = null;
        if ((username.equals("abe") || username.equals("barb"))&& password.equals("password"))
        {
            user = new User(username, null);
        }
        return user;
    }
}

