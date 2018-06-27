package com.demo.spring.boot.core.serialize;

import com.demo.spring.boot.core.entities.student.Student;

import java.io.*;
import java.sql.*;
import java.util.Vector;

public class SerializeToDatabase {

    private static final String SQL_SERIALIZE_OBJECT = "INSERT INTO serialized_java_objects(serialized_id, object_name, serialized_object) VALUES (?,?, ?)";
    private static final String SQL_DESERIALIZE_OBJECT = "SELECT serialized_object FROM serialized_java_objects WHERE serialized_id = ?";

    public static void serializeJavaObjectToDB(Connection connection,
                                               Object objectToSerialize, long serialized_id) throws Exception {

        PreparedStatement pstmt = connection
                .prepareStatement(SQL_SERIALIZE_OBJECT);

        // just setting the class name
        pstmt.setLong(1, serialized_id);
        pstmt.setString(2, objectToSerialize.getClass().getName());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oout = new ObjectOutputStream(baos);
        oout.writeObject(objectToSerialize);
        oout.close();
        pstmt.setObject(3, baos.toByteArray());
        pstmt.executeUpdate();
        pstmt.close();
        System.out.println("Java object serialized to database. Object: "
                + objectToSerialize);
    }

    /**
     * To de-serialize a java object from database
     *
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws ClassNotFoundException
     */
    public static Object deSerializeJavaObjectFromDB(Connection connection,
                                                     long serialized_id) throws SQLException, IOException,
            ClassNotFoundException {
        PreparedStatement pstmt = connection
                .prepareStatement(SQL_DESERIALIZE_OBJECT);
        pstmt.setLong(1, serialized_id);
        ResultSet rs = pstmt.executeQuery();
        rs.next();

        // Object object = rs.getObject(1);

        byte[] buf = rs.getBytes(1);
        ObjectInputStream objectIn = null;
        if (buf != null)
            objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));

        Object deSerializedObject = objectIn.readObject();

        rs.close();
        pstmt.close();

        System.out.println("Java object de-serialized from database. Object: "
                + deSerializedObject + " Classname: "
                + deSerializedObject.getClass().getName());
        return deSerializedObject;
    }

    /**
     * Serialization and de-serialization of java object from mysql
     *
     * @throws ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public static void main(String args[]) throws ClassNotFoundException,
            Exception {
        Connection connection = null;

        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.15.20.42)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.15.20.42)(PORT = 1521))(LOAD_BALANCE = yes)(CONNECT_DATA =(SERVER = DEDICATED)(SID = SOT)(FAILOVER_MODE=(TYPE=select)(METHOD=basic))))";
        String username = "lf_portal";
        String password = "lf";
        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);

        // a sample java object to serialize
        Vector<Object> vtSend = new Vector<Object>();
        Vector<Object> vtReturn = new Vector<Object>();
        vtReturn.add("1");
        vtReturn.add("Ha noi");
        vtReturn.add("Vietnam");
        vtReturn.add(true);
        vtReturn.add("2");
        vtSend.add(vtReturn);
        vtSend.add("bdfdfd");
        Student student = new Student("student", 27, "22");
        student.setMark(10);
        vtSend.add(student);

        Vector obj = new Vector();
        obj.add("java");
        obj.add("papers");
        long serialized_id = 8;
        // serializing java object to mysql database
        serializeJavaObjectToDB(connection, vtSend, serialized_id);

        // de-serializing java object from mysql database
        Vector objFromDatabase = (Vector) deSerializeJavaObjectFromDB(
                connection, serialized_id);
        connection.close();
    }
}