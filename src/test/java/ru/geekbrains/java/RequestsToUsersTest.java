package ru.geekbrains.java;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RequestsToUsersTest {



    @Test
    public void checkRequests(){
        int RowCounts=0;
        List<User> users;


        RequestsToUsers requests = new RequestsToUsers();

        requests.showAllUsers();
        requests.showUsersByAge(20, 30);
        RowCounts=requests.returnCountOfRows();
        requests.insertUser("Boris 18 boris@mail.ru");
        assertEquals(RowCounts+1,requests.returnCountOfRows());

        users=requests.getResultByParameter(ColumnList.NAME,"Boris");
        assertEquals("Boris",users.get(0).getName());
        assertEquals(18, users.get(0).getAge());
        assertEquals("boris@mail.ru",users.get(0).getEmail());
        requests.deleteByName("Boris");
        assertEquals(RowCounts,requests.returnCountOfRows());

    }

}