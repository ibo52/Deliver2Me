/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliver2me;

import java.util.Scanner;

/**
 *
 * @author ibrahim
 */
public class Deliver2Me {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        ConnectDB app=new ConnectDB();//new DB object to communicate with remote
        
        /*
        //SAMPLE SIMULATON/ TESTING
        app.logIn("halilibo@mail.com", "ibo123");
        app.account.getRestaurants();
        app.account.getCategories();
        app.account.getMenues(2);
        app.account.getDeliverHistory("d");
        
        app.account.getMenuesByCategory(13,8);// category8 =vegan
        //app.account.getMenuesByCategory(8, 6); //category6 = et
        
        app.account.getRestaurantsPopularity();
        app.account.getUserCommentsOfOrder();
        app.account.getMostPopularRestaurant();
        System.out.println("DONE");
        */
        
        /*
        *
        *
        *
        */
        Scanner input=new Scanner(System.in);
        //login logic
        
        while(true){
            /*
            System.out.print("Enter email: ");
            String email=input.nextLine();
            
            System.out.print("enter passwd: ");
            String pass=input.nextLine();
            
            app.logIn(email, pass);
            */
            //auto login
            app.logIn("halilibo@mail.com", "ibo123");
            if (app.isLogged()==1) {
                break;
            }
        }
        //options logic
        
        while(true){
            
            System.out.println("----------------------------------\n"
                    + "1. list all restaurants\n"
                    + "2. list restaurants by popularity as descending\n"
                    + "3. list most popular restaurant in city\n"
                    + "4. get my delivered order history\n"
                    + "5. get my orders that waiting to deliver\n"
                    + "6. get all the comments i leaved on restaurants");
            System.out.print("Enter your choice: ");
            int choice =input.nextInt();
            
            switch(choice){
                case 1:{
                    app.account.getRestaurants();
                    break;
                }
                case 2:{
                    app.account.getRestaurantsPopularity();
                    break;
                }
                case 3:{
                    app.account.getMostPopularRestaurant();
                    break;
                }
                case 4:{
                    app.account.getDeliverHistory("d");
                    continue;
                }
                case 5:{
                    app.account.getDeliverHistory("s");
                    continue;
                }
                case 6:{
                    app.account.getUserCommentsOfOrder();
                    continue;
                }
                default:{
                    continue;
                }
            }
            
            System.out.print("to list its menu, please select a restaurant id: ");
            choice=input.nextInt();
            int restaurant_id=choice;
            
            System.out.println("------------------\n"
                    + "1. list all menues\n"
                    + "2. list by selected categories\n"
                    + "3. list menus that contain some category -Except- another category");
            System.out.print("Enter your choice: ");
            choice=input.nextInt();
            
            switch(choice){
                case 1:{
                    app.account.getMenues(restaurant_id);
                    break;
                }
                case 2:{
                    app.account.getCategories();
                    System.out.print("Enter choice of category: ");
                    choice=input.nextInt();
                    
                    app.account.getMenuesByCategory(restaurant_id, choice);
                    break;
                }
                case 3:{
                    app.account.getCategories();
                    System.out.print("Enter wanted category: ");
                    int cat1=input.nextInt();
                    
                    System.out.print("Enter Exception category");
                    int cat2=input.nextInt();
                    app.account.getMenuesByCatExceptSomeCat(restaurant_id, cat1, cat2);
                    break;
                }
            }
            
            //order anything from restaurant
            int[] order_menu=new int[10];
            int order_choice;
            int idx=0;
            
            do{
                System.out.print("To order a menu, please select menu_id: ");
                int menu_choice=input.nextInt();
                
                System.out.println("1. see comments leaved to this menu\n"
                        + "2. add more order\n"
                        + "3. submit order");
                System.out.print("Enter choice: ");
                order_choice=input.nextInt();
                
                switch(order_choice){
                    
                    case 1:{
                        app.account.getCommentsOfMenu(menu_choice);
                        System.out.print("Do you still want to add this menu to order? (y-n): ");
                        String r=input.next();
                        
                        if (r.equals("y")) {
                            
                            if(idx<10){
                            order_menu[idx]=menu_choice;idx++;
                            
                                System.out.print("Do you want add order? (y-n): ");
                                r=input.next();
                                if (r.equals("y")) {
                                    order_choice=2;
                                }else{
                                    order_choice=3;
                                }
                            }
                            else{
                                System.out.println("cant append. Max allowed order is 10");
                                choice=3;
                            }
                        }
                        else{
                            System.out.print("Do you want add order? (y-n): ");
                            r=input.next();
                            
                            if (r.equals("y")) {
                                order_choice=2;
                            }else{
                                order_choice=3;
                            }
                        }
                        break;
                    }
                    
                    case 2:{
                        if (idx<10) {
                            order_menu[idx]=menu_choice;idx++;
                        }
                        else{
                            System.out.println("Cant append. Max allowed num order is 10.");
                        }
                        
                        System.out.print("Do you want to look menu again? (y-n): ");
                        String r=input.next();
                    
                        //print all menues again
                        if(r.equals("y")){
                            app.account.getMenues(restaurant_id);
                        }
                        
                        break;
                    }
                    case 3:{
                        if (idx<10) {
                            order_menu[idx]=menu_choice;idx++;
                        }
                        else{
                            System.out.println("Cant append. Max allowed num order is 10.");
                        }
                        
                        break;
                    }
                }
                
            }while(order_choice==2);
            
            //insert oreder to database
            /*
            System.out.println("ordered menu id's");
            for (int i = 0; i < order_menu.length; i++) {
                System.out.print(order_menu[i]+"-");
            }
            */
            System.out.println("Order Sended :^) Please wait for restaurant to prepare.\n\n");
            continue;
            
        }
    }

}
