/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectdb;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ibrahim
 */
public class ConnectDB {
    
    private final String url="jdbc:postgresql://localhost:5432/deliver2me";//DB link
    private final String user="ibo52";//pgadmin user
    private final String passwd="1234";//pgadmin passwd

    private final Connection conn;//connection object
    public user account;//account object to auth user logged in program
    private int logged=0;// is there any user that logged in? 1:0 | usage onlogin method
    
    //constructor method
    public ConnectDB(){
        //initialize connection between driver and database
        this.conn=this.connect();
    }
    /** 
     */
    
    //user subclass to authorize account from DB
    public class user{
        private String id;
        private String email;
        
        private int city_id;//the province code where user lives
        private String city;
        
        
        //constructor
        public user(String email){
            this.email=email;
            this.id=id;
            
            String Query="SELECT customer_id,city_id,city_name FROM public.\"customer\""
                + " natural join city"
                + " WHERE email=\'"+email+"\'";
            
            ResultSet rs=ConnectDB.this.sqlQuery(Query);
            setID(rs);
        }
        
        private void setID(ResultSet rs){
            try{
                while( rs.next() ){
                    this.id=rs.getString(1);
                    this.city_id=rs.getInt(2);
                    this.city=rs.getString(3);
                }
                
            } catch (SQLException ex) {
                System.out.println("setID func:while fetching user info: "+ex.getMessage());
                //Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        public String[] getID(){
            String[] l={this.id,this.email,this.city};
            return l;
        }
        
        public void getRestaurants(){
        //get restaurants near customer city/address
        
            String Query="WITH restaurants_on_city AS"
                    + " (SELECT * FROM restaurant NATURAL JOIN branch )"
                    + " SELECT distinct on(restaurant_id) restaurant_id, restaurant_name,phone_num,address"
                    + " FROM restaurants_on_city NATURAL JOIN city"
                    + " WHERE city_id="+this.city_id+";";
        
            ResultSet rs=ConnectDB.this.sqlQuery(Query);
            
            String cityQuery="SELECT city_name FROM city WHERE city_id="+this.city_id;
            ResultSet city_rs=ConnectDB.this.sqlQuery(cityQuery);
            
            cityQuery=returnFirstResult(city_rs);

            System.out.println("-------------------------------------"
                    + "\n RESTAURANTS NEAR YOU("+cityQuery
                    + ")\n---------------------------------------");
            printQueryResults(rs);
            
        }
        
        public List<List< String >> getRestaurantsPopularity(){
            /*List the name and popularity of restaurants
            that resides in same city with customer’s.*/
            
            String Query="WITH popularities AS(SELECT restaurant_name,avg(rate) AS \"liking rate\""
                    + " FROM restaurant NATURAL JOIN branch"
                    + " NATURAL JOIN \"comment\" WHERE city_id="+this.city_id
                    + " GROUP BY restaurant_name)"
                    + " SELECT restaurant_id,restaurant_name,\"liking rate\""
                    + " FROM popularities NATURAL JOIN restaurant"
                    + " ORDER BY \"liking rate\" DESC";
            
            ResultSet rs=ConnectDB.this.sqlQuery(Query);
            /*
            *
            *
            */
            List<List< String >> list=new ArrayList<>();
            try {

                for (int i = 0; rs.next(); i++) {

                    String id=rs.getString(1);
                    String name=rs.getString(2);
                    String rate=rs.getString(3).substring(0, 4);

                    if (rate == null) {
                        rate="no rate yet";
                    }
                    if (id==null) {
                        id="no id";
                    }

                    list.add(new ArrayList<String>());
                    
                    list.get(i).add(id);
                    list.get(i).add(name);
                    list.get(i).add(rate);

                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            return list;
        }
        
        public List<List< String >> getMenues(int restaurant_id){
        //get all menu of specific restaurants near customer city/address
        
            String Query="WITH restaurants_on_city AS"
                    + " (SELECT * FROM restaurant NATURAL JOIN branch WHERE city_id="+this.city_id
                    +"AND restaurant_id="+restaurant_id+ ")"
                    +" SELECT menu_name,price"
                    +" FROM restaurants_on_city NATURAL JOIN \"OffersMenu\"";
        
            ResultSet rs=ConnectDB.this.sqlQuery(Query);
            /*
            *
            *
            */
            return dumpResultsToArray(rs);
            
        }
        
        public List<List< String >> getCommentsOfRestaurant(int restaurant_id){
            /*returns (if exists) the comments and rate of menu specified
            */
            String Query="WITH all_comments AS(SELECT * FROM restaurant"
                    +" NATURAL JOIN \"comment\" NATURAL JOIN \"orderDetails\")"
                    +" SELECT name,entry,date,rate"
                    + " FROM all_comments natural join customer WHERE customer_id=customer_id"
                    +" and restaurant_id="+restaurant_id;
            ResultSet rs=ConnectDB.this.sqlQuery(Query);
            
            return dumpResultsToArray(rs);
            
        }
        
        public List<List< String >> dumpResultsToArray(ResultSet rs){
            List<List< String >> list=new ArrayList<>();
            
            try {
                int columnsPerRow=rs.getMetaData().getColumnCount();
                
                for (int i = 0; rs.next(); i++) {
                    list.add(new ArrayList<String>());
                    
                    
                    for (int j = 0; j < columnsPerRow; j++) {

                        String s=rs.getString(j+1);
                        if (s == null) {
                            s="null";
                        }
                        list.get(i).add(s);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return list;
            
        }
        
        public void getMostPopularRestaurant(){
            /*List the name and popularity of restaurants
            that resides in same city with customer’s.*/
            
            String Query="WITH popularities AS"
                    + " (SELECT restaurant_name,avg(rate) AS \"rating\""
                    + " FROM restaurant NATURAL JOIN branch"
                    + " NATURAL JOIN \"comment\" WHERE city_id="+this.city_id
                    + " GROUP BY restaurant_name)"
                    + " SELECT restaurant_id,restaurant_name AS \"most pop. restaurant\",rating"
                    + " FROM popularities NATURAL JOIN restaurant"
                    +" WHERE rating>=ALL(SELECT rating FROM popularities)";
            
            ResultSet rs=ConnectDB.this.sqlQuery(Query);
            
            String cityQuery="SELECT city_name FROM city WHERE city_id="+this.city_id;
            ResultSet cityRs=ConnectDB.this.sqlQuery(cityQuery);
            cityQuery=returnFirstResult(cityRs);
            
            System.out.println("--------------------------------------\n"
                             + "MOST POPULAR RESTAURANT IN "+cityQuery+" BY USER RATINGS"
                             + "\n--------------------------------------");
            printQueryResults(rs);
        }
        
        public void getUserCommentsOfOrder(){
            /*returns the comments and rate of all delivered orders of customer
            */
            String Query="SELECT restaurant_name,order_id,date,entry,rate FROM restaurant"
                    +" NATURAL JOIN \"comment\" NATURAL JOIN \"orderDetails\""
                    +" WHERE customer_id="+this.id;
            
            ResultSet rs=ConnectDB.this.sqlQuery(Query);
            
            System.out.println("--------------------------------------\n"
                             + "COMMENTS YOU LEAVED ON RESTAURANTS"
                             + "\n--------------------------------------");
            printQueryResults(rs);
            
        }
        
        
        
        public void getCategories(){
        //get all categories from all restaurants near customer city/address
        
            String Query="SELECT * FROM food_category";
        
            ResultSet rs=ConnectDB.this.sqlQuery(Query);
            
            System.out.println("-------------------------------------"
                    + "\n ALL CATEGORIES IN DB"
                    + "\n---------------------------------------");
            printQueryResults(rs);
            
        }
        
        public void getMenuesByCategory(int restaurant_id,int category_id){
            /*searches for restaurants that provide at least
            one item of sepecified category in their menu*/
            
            //mbc stands for "menu by category"
            String Query="WITH mbc AS (SELECT * FROM \"OffersMenu\""
                    + " NATURAL JOIN menu NATURAL JOIN food NATURAL JOIN food_category)"
                    +" SELECT DISTINCT ON(menu_name) menu_name,"
                    +"menu_id,price,food_name,category_name"
                    +" FROM mbc"
                    +" WHERE restaurant_id="+restaurant_id
                    +" AND (category_id="+category_id
                    +" OR opt_category_id="+category_id
                    +" OR opt_category_id2="+category_id+")";
            
            ResultSet rs=ConnectDB.this.sqlQuery(Query);
            /*
            *
            */
            String catNameQuery="SELECT category_name FROM food_category"
                    + " WHERE category_id="+category_id;
            
            ResultSet rs2=ConnectDB.this.sqlQuery(catNameQuery);
            String catName=returnFirstResult(rs2);
            
            System.out.println("-------------------------------------"
                    + "\n  RESTAURANT's MENUS CONSIST -"+catName+"- ITEMS "
                    + "\n---------------------------------------");
            printQueryResults(rs);
        }
        
        public void getMenuesByCatExceptSomeCat(int restaurant_id,int wantedCat,int exceptCat){
            /*returns menues that contain cat_1 on menus sub items
            except the menus that contain cat_2 on menus sub items
            */
            String Query="WITH mbc AS (SELECT * FROM \"OffersMenu\""
                    +" NATURAL JOIN menu NATURAL JOIN food NATURAL JOIN food_category"
                    +" WHERE restaurant_id="+restaurant_id+"),"
                    +" contain_wanted AS (SELECT DISTINCT ON(menu_name) menu_name FROM mbc"
                    +" WHERE (category_id="+wantedCat
                    +" OR opt_category_id="+wantedCat
                    +" OR opt_category_id2="+wantedCat+")),"
                    +" contain_exception AS"
                    +" (SELECT DISTINCT ON(menu_name) menu_name FROM mbc"
                    +" WHERE (category_id="+exceptCat
                    +" OR opt_category_id="+exceptCat
                    +" OR opt_category_id2="+exceptCat+")),"
                    +" final_query AS((SELECT * FROM contain_wanted)"
                    + "EXCEPT (SELECT * FROM contain_exception))"
                    +" SELECT menu_id,menu_name,price,"
                    +"food_name,category_name"
                    +" FROM mbc"
                    +" NATURAL JOIN final_query";
            
            ResultSet rs=ConnectDB.this.sqlQuery(Query);
            
            //get category names---------------------
            String catQuery="SELECT category_name FROM food_category"
                    + " WHERE category_id=";
            
            ResultSet cat_rs=ConnectDB.this.sqlQuery(catQuery+wantedCat);
            String cat1=returnFirstResult(cat_rs);
            
            cat_rs=ConnectDB.this.sqlQuery(catQuery+exceptCat);
            String cat2=returnFirstResult(cat_rs);
            //--------------------------------------
            
            System.out.println("-------------------------------------"
                    + "\n MENUS that contain -"+cat1+"- but do not contain -"+cat2+"-"
                    + "\n---------------------------------------");
            printQueryResults(rs);
        }
        
        public void getDeliverHistory(String status){
        //get all delivered or sended/waiting orders of users.
        
        //coho stands for Customer-OrderDetails-History-of-Orderings
            String Query="WITH coho as (SELECT * FROM \"customer_order_history\" NATURAL JOIN orderings WHERE customer_id="+this.id+")"
                    +" SELECT order_id,menu_id,menu_name,tot_price,restaurant_name,date"
                    +" FROM (SELECT menu_id,menu_name FROM \"OffersMenu\") as OM  JOIN \"coho\" using(menu_id)"
                    +" WHERE status='"+status+"'";
        
            ResultSet rs=ConnectDB.this.sqlQuery(Query);
            
            switch(status){
                case "s":{
                    System.out.println("-------------------------------------"
                    + "\n Sended delivers"
                    + "\n---------------------------------------");
                    break;
                }
                case "d":{
                    System.out.println("-------------------------------------"
                    + "\n HISTORY OF DELIVERED PRODUCTS"
                    + "\n---------------------------------------");
                    break;
                }
            }
            printQueryResults(rs);
            
        }
        
    }
    
    private Connection connect(){
        //Initialize jdbc driver and establish a connection to DB
        Connection conn=null;
        
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("POSTGRE driver could not load. Does not exist or not added to library.");
            System.out.println(ex.getMessage());
            //Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            
            conn=DriverManager.getConnection(url,user,passwd);
            System.out.println("Driver initialized and connected to DB succesfully!"
            +"\n--------------------------------------------------");
        }catch (SQLException e){
            System.out.println("connect func initialize connection: "+e.getMessage() );
        }
        
        return conn;
    }
    
    public int updateDB(String query_string){
        //to update database contents
        //send a query to update contents of database and receive response(integer)
        Statement stmt=null;//sorgu metni
        try{
            stmt=this.conn.createStatement();
            
        }catch(SQLException e){
            System.out.println("updateDB func statement create: "+e.getMessage());
            //Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE,null,e);
        }
        
        //operate database update process
        int response=-1;
        try {
            response=stmt.executeUpdate(query_string);
        } catch (SQLException ex) {
            System.out.println("updateDB func execute to receive response: "+ex.getMessage());
            //Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //return response in order to check/ensure operation happened
        //if<0: error
        return response;
    }
    
    public ResultSet sqlQuery(String query_string){
        //send a query(query_string) to DB(remoteConn) and receive a result set
        
        Statement stmt=null;//sorgu metni
        try{
            stmt=this.conn.createStatement();
            
        }catch(SQLException e){
            System.out.println("sqlQuery statement create: "+e.getMessage());
            //Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE,null,e);
        }
        
        //*************************
        ResultSet rs=null;
        
        try{
            rs=stmt.executeQuery(query_string);
        }catch(SQLException e){
            System.out.println("Query can not be executed: "+query_string);
            System.out.println(e.getMessage());
            //Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE,null,e);
        }
        
        return rs;
    }
    
    //public static void printQueryResults
    public static void printQueryResults(ResultSet rs){
        //print sql query results; columns name and values
        
        //rsmd to print result table's column names
        try{
            ResultSetMetaData rsmd=rs.getMetaData();
            int a=rsmd.getColumnCount();
            
            int row_len=18;//max allowed length of string at a row
            String strFmt="%-"+row_len+"s ";
            
            for (int i = 1; i < a+1; i++) {
                
                //System.out.println("class name: "+rsmd.getColumnClassName(i));
                System.out.printf(strFmt ,rsmd.getColumnName(i));  
            }

            System.out.println("");

            //print rows
            while(rs.next() ){
                for (int i = 1; i < a+1; i++) {

                    String content=rs.getString(i);
                       
                    //veritabanında boş içerik varsa jdbc değişkenlere değer bağlamiyy
                    if(content==null){
                        System.out.printf(strFmt,"Null value"); 
                    }
                    else if (content.length() <= row_len) {
                        System.out.printf(strFmt,content ); 
                    }
                    else{
                    System.out.printf("%s... ",rs.getString(i).substring(0,row_len-3) );
                    }
                    
                }
                System.out.println("");
            }
        
        }catch(SQLException ex){
            //Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error while printing query results:"+ex.getMessage());
        }
        System.out.println("--------------------------------------------------");
        
    }
    
    public boolean ResultExists(ResultSet rs){
        //checks results if returned val of DB is 'f' or 't'
        boolean var=false;
        
        try{
            while( rs.next() ){
                if (rs.getString(1).equals("f")) {
                    var=false;
                    break;
                }
                else{
                    var=true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("ResultExist func: "+ex.getMessage());
            //Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return var;
        
    }
    
    public String returnFirstResult(ResultSet rs){
        //checks results if returned val of DB is 'f' or 't'
        String var="";
        try{
            
            while( rs.next() ){
                var=rs.getString(1);
                break;
            }
        } catch (SQLException ex) {
            System.out.println("returnFirstResult func: "+ex.getMessage());
            //Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return var;
        
    }
    
    public void logIn(String email, String passwd){
        //check if a user registered in DB, if true; authorize
        
        //if there was any user Logged in, kick them first
        if (this.logged==1) {
            
            System.out.println(this.account.email+" logged out from system automatically");
            this.logOut();
        }
        
        String Query="SELECT EXISTS(SELECT customer_id FROM public.\"customer\""
                + " WHERE email=\'"+email+"\' AND"
                + " passwd=\'"+passwd+"\')";
        
        ResultSet rs=this.sqlQuery(Query);
        
        boolean key=this.ResultExists(rs);
        if (key) {
            System.out.println("Authorized user:"+email+" Logged in");
            
            this.account=new user(email);//concat user to system
            this.logged=1;
            
        }
        else{
            System.out.println("No such user exists. Possibly email or passwd is wrong!");
        }
    }
    
    public void logOut(){
        if (this.logged==1) {
            this.account=null;
            this.logged=0;
        }
    }
    public int isLogged(){
        return this.logged;
    }
    
    // to show example  restaurants on gui
    public List<List<String>> getRestaurantsGUI(){
        
            /*List the name and popularity of restaurants
            that resides in same city with customer’s.*/
            
        String Query="WITH popularities AS(SELECT restaurant_name,avg(rate) AS \"liking rate\""
                + " FROM restaurant NATURAL JOIN branch"
                + " NATURAL JOIN \"comment\""
                + " GROUP BY restaurant_name)"
                + " SELECT restaurant_id,restaurant_name,\"liking rate\""
                + " FROM popularities NATURAL JOIN restaurant"
                + " ORDER BY \"liking rate\" DESC";
        

        ResultSet rs=ConnectDB.this.sqlQuery(Query);
        /*
        *
        *
        */
        List<List< String >> list=new ArrayList<>();
        try {
            
            for (int i = 0; rs.next(); i++) {
                
                String id=rs.getString(1);
                String name=rs.getString(2);
                String rate=rs.getString(3).substring(0, 4);
                
                if (rate == null) {
                    rate="no rate yet";
                }
                
                list.add(new ArrayList<String>());
                list.get(i).add(id);
                list.get(i).add(name);
                list.get(i).add(rate);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<List< String >> getMenues(int restaurant_id){
        //get all menu of specific restaurants near customer city/address
        
            String Query="WITH restaurants_on_city AS"
                    + " (SELECT * FROM restaurant NATURAL JOIN branch"
                    +" WHERE restaurant_id="+restaurant_id+ ")"
                    +" SELECT menu_name,price"
                    +" FROM restaurants_on_city NATURAL JOIN \"OffersMenu\"";
        
            ResultSet rs=ConnectDB.this.sqlQuery(Query);
            /*
            *
            *
            */
            return dumpResultsToArray(rs);
        }
    
    public List<List< String >> getCommentsOfRestaurant(int restaurant_id){
            /*returns (if exists) the comments and rate of menu specified
            */
            String Query="WITH all_comments AS(SELECT * FROM restaurant"
                    +" NATURAL JOIN \"comment\" NATURAL JOIN \"orderDetails\")"
                    +" SELECT name,entry,date,rate"
                    + " FROM all_comments natural join customer WHERE customer_id=customer_id"
                    +" and restaurant_id="+restaurant_id;
            ResultSet rs=ConnectDB.this.sqlQuery(Query);
            
            return dumpResultsToArray(rs);
            
        }
        public List<List< String >> dumpResultsToArray(ResultSet rs){
            List<List< String >> list=new ArrayList<>();
            
            try {
                int columnsPerRow=rs.getMetaData().getColumnCount();
                
                for (int i = 0; rs.next(); i++) {
                    list.add(new ArrayList<String>());
                    
                    
                    for (int j = 0; j < columnsPerRow; j++) {

                        String s=rs.getString(j+1);
                        if (s == null) {
                            s="null";
                        }
                        list.get(i).add(s);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return list;
            
        }
    
    

    
    /* MAIN deliver2me.java dosyasında da var. MAIN: Driver program
    public static void main(String[] args) throws SQLException{
        
        ConnectDB app=new ConnectDB();//new DB object to communicate with remote
        
        //SAMPLE LOGIN SIMULATON/ TESTING
        //an user logs in
        app.logIn("halilibo@mail.com", "ibo123");
        app.account.getRestaurants();
        app.account.getCategories();
        
        System.out.println("DONE");
    }
    */
}
