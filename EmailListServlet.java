package murach.email;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import murach.business.user ;
import murach.data.UserDB;



public class EmailListServlet extends HttpSeverlet  {
    @Override 
    protected void  doPost (HttpSeverletRequest request,
     HttpSeverletResponse response)
     throws ServletException, IOException {
        String url = "/index.html";
        
        //get current action 
        String action = request.getParamater("action");
        if( action == null){
            action = "join "; //default action 
        }

     if(action.equals ("join")){
        url = "index.html";
     }
     else if(action.equal("add")){
        String firstName = request.getParamater("firstName");
        String lastName = request.getParamater("lastName");
        String email = request.getParamater("email");
        

        User user = new User(firstName,lastName,email);
        UserDB.insert(user);


        request.setAttribute("user",user);
        url = "/thank.jsp";
     }

     getServletContext().getRequestDispatcher(url).forward(request,response);
    }
    @Override
    protected void doGet ( HttpSeverletRequest request,HttpSeverletResponse response)throws ServletException, IOException{
        doPost(request,response);
    }
}