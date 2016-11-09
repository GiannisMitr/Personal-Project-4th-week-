<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp"%>

<%@ include file="header.html"%>


    <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <input type="checkbox" id="checkbox_toggle">
            <div class="navbar-header">
                <label class="navbar-toggle icon-bar" for="checkbox_toggle"><i class="material-icons">menu</i></label>
                <a class="navbar-brand" href="">
                    <p>GiannisMitr</p>
                </a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <form class="navbar-form navbar-right" role="form" method="POST" action="login">
                    <div class="form-group">
                        <input type="email" name="email" placeholder="Email" class="form-control" maxlength="40" required>
                        <label class="login-field-icon material-icons">perm_identity</label>
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" placeholder="Password" class="form-control" maxlength="40" required>
                        <label class="login-field-icon material-icons">lock</label>
                    </div>
                    <button type="submit" class="btn btn-secondary">Login</button>
                </form>
            </div>
            <!--/.navbar-collapse -->
        </div>
    </nav>
    <%if(session.getAttribute("wrong")!=null){%>
   <div style="float:right;margin-top:50px;background:white;text-align:right">
   <h4 style="margin:10px 45px;color:red">Username or Password provided are not Correct!</h4>
</div>
<%} session.setAttribute("wrong", null); %>

<div class="dum">
 <a href="#contact" class="btn btn-default btn-lg" id="mainbutton">Contact Me!</a>
<section class="art">
      <div class="red">
        <h1 class="first thin huge welcome">Welcome&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1>
        <h1 class="first fat ">&nbsp;</h1>
        <h1 class="first fat less mitr">Giannis</h1>
      

        </div>
        <div class="white">
        <h1 class="first fat huge">&nbsp;</h1>
        <h1 class="first thin less to">&nbsp;&nbsp;&nbsp;To</h1>
        <h1 class="first thin">&nbsp;&nbsp;Mitropoulos</h1>
        <h1 class="first fat huge web">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;WebPage</h1>

        </div>

        </section>
         
  </div>
     <div class="clear-fix">
        </div>
<hr>
          <div id="contact" class="container-fluid back-white" >

  <h2 class="text-center mainHeading">CONTACT</h2>
  <h2></h2>
  <form class="second" method="POST" action="">
  <div class="row">

    <div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4  col-md-offset-4 slideanim">
      <div class="row">

        <div class="col-xs-12 col-sm-6 col-md-6 form-group">
          <input class="form-control" id="name" name="name" placeholder="Name" type="text" maxlength="40" required>
        </div>
        <div class="col-xs-12 col-sm-6 col-md-6 form-group">
          <input class="form-control" id="email" name="email" placeholder="Email" type="email" maxlength="40" required>
        </div>
      </div>
      <textarea class="form-control" id="comments" name="comments" placeholder="Comment" rows="7" required></textarea><br>
       <h4 class="text-center green <%if(session.getAttribute("subm")==null){%>notvis<%}%>">Your comment has been successfully submitted, Thank you!</h4>
      <div class="row">
        <div class="col-xs-12 form-group">
          <button class="btn btn-default btn-lg center-block" type="submit" <%if(session.getAttribute("subm")!=null){%> disabled <%}%> > Send <i class="material-icons contact-icon">mail</i></button>
        </div>
      </div>
    </div>
   </div>
   </form>
  
   </div>
   
<%@include file="footer.html"%>
    </body>
</html>
