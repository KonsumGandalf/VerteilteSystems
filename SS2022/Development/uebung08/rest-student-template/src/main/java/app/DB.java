package app;

import java.sql.*;
import java.util.ArrayList;

public class DB {
    private static String host = "im-vm-011";
    private static String port = "3306";
    private static String username = "vs-08";
    private static String password = "vs-08-pw";
    private static String database = "vs-08";
    private static String error = "?autoReconnect=true&useSSL=false";

    private static Connection con;
    public static boolean isConnected(){
        return (con == null) ? false : true;
    }

    public static void connect() throws ClassNotFoundException {
        if (!isConnected()){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("jdbc:mysql://"+host+"/"+database+error);
                con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database+error, username, password);
                System.out.println("DB is connected");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void disconnect(){
        if (isConnected()){
            try{
                con.close();
                System.out.println("DB disconnected");
            } catch (SQLException e){
                e.printStackTrace();
            }

        }
    }

    public static void update(String qry){
        try {
            PreparedStatement ps = con.prepareStatement(qry);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean execute(String qry) {
        return execute(qry, true);
    }

    public static boolean execute(String qry, boolean commit) {
        try {
            con.setAutoCommit(commit);
            Statement statement = con.createStatement();
            statement.execute(qry);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<ArrayList<String>> read(String qry){
        try {
            ArrayList<ArrayList<String>> dbList = new ArrayList<ArrayList<String>>();
            PreparedStatement ps = con.prepareStatement(qry);
            Statement stmt = con.createStatement() ;
            ResultSet rs = stmt.executeQuery(qry) ;
            try {
                while ( rs.next() ) {
                    int numColumns = rs.getMetaData().getColumnCount();
                    ArrayList<String> line = new ArrayList<String>();
                    for ( int i = 1 ; i <= numColumns ; i++ ) {
                        // Column numbers start at 1.
                        // Also there are many methods on the result set to return
                        //  the column as a particular type. Refer to the Sun documentation
                        //  for the list of valid conversions.
                        line.add(rs.getObject(i).toString());
                        // System.out.println( "COLUMN " + i + " = " + rs.getObject(i) );
                    }
                    dbList.add(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // String joinedString = String.join("", dbList);

            return dbList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
