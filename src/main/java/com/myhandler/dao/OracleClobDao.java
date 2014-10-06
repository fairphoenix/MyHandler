package com.myhandler.dao;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import oracle.sql.CLOB;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.sql.*;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by anatoliy on 04.10.14.
 */
public class OracleClobDao {

    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("-------- Oracle JDBC Connection Testing ------");

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;

        }

        System.out.println("Oracle JDBC Driver Registered!");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:ORCL", "sa",
                    "root");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }

        File file = new File("E:\\doc\\proj\\java\\MyHandler\\src\\main\\resources\\lu_Condiment_Food_Table.xml");
        String xml = Files.toString(file, Charsets.UTF_8);
        PreparedStatement pstmt;
        try {

            for (int i = 1; i < 40000; i++) {
                pstmt = connection.prepareStatement("INSERT INTO SA.JR_REFERENCE_CLOB (REF, REQTYPE, DATA_CREATE, PARAMS) values (?, ?, ?, ?)");
                pstmt.setString(1, UUID.randomUUID().toString().substring(0, 19));
                pstmt.setString(2, "Привет");
                pstmt.setTimestamp(3, createTime(i));

                //Clob clob = getCLOB(xml, connection);
                java.sql.Clob clob =
                        oracle.sql.CLOB.createTemporary(
                                connection, false, oracle.sql.CLOB.DURATION_SESSION);

                clob.setString(1, xml);
                pstmt.setObject(4, clob);


                pstmt.execute();
                //Важно, иначе растет тейблспейс TEMP!!!!
                clob.free();

                connection.commit();
                if( i%100 == 0)
                    System.out.println("Commit row " + i);
                pstmt.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.close();
        }


    }

    private static Timestamp createTime(int addHour) {
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new java.util.Date()); // sets calendar time/date
        cal.add(Calendar.MINUTE, addHour); // adds one hour
        return new Timestamp(cal.getTime().getTime());
    }

    private static CLOB getCLOB(String xmlData, Connection conn) throws SQLException {
        CLOB tempClob = null;
        try {
            // If the temporary CLOB has not yet been created, create one
            tempClob = CLOB.createTemporary(conn, true, CLOB.DURATION_SESSION);

            // Open the temporary CLOB in readwrite mode, to enable writing
            tempClob.open(CLOB.MODE_READWRITE);
            // Get the output stream to write
            Writer tempClobWriter = tempClob.getCharacterOutputStream();
            // Write the data into the temporary CLOB
            tempClobWriter.write(xmlData);

            // Flush and close the stream
            tempClobWriter.flush();
            tempClobWriter.close();

            // Close the temporary CLOB
            tempClob.close();
        } catch (SQLException sqlexp) {
            tempClob.freeTemporary();
            sqlexp.printStackTrace();
        } catch (Exception exp) {
            tempClob.freeTemporary();
            exp.printStackTrace();
        }
        return tempClob;
    }
}
