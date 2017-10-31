<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
    <script src="./jquery-3.2.1.min.js" ></script>
    <script type="text/javascript">
            function saveUser() {
                //$(document).ready(function(){
                var saveDataAry=[];
                var data1='{"userName":"test","address":"gz"}';
                alert('save');
                /*  var data2={"userName":"ququ","address":"gr"};
                 var  dataJson ;
                 alert("save user");
                 saveDataAry.push(data1);
                 saveDataAry.push(data2);
                 dataJson =JSON.stringify(saveDataAry);
                 alert(dataJson);
                 */

                $.ajax({
                    type:"POST",
                    url:"/user/saveUser",
                    //dataType:"json",
                    contentType:"application/json",
                    data:data1,
                    success:function(data){
                        alert('success');
                    }
                });
            };
        </script>
</head>
<style>
    h2 {
        font-family: '宋体', cursive;
    }

    p {
        font-family: '宋体', cursive;
    }
</style>

<body>
<h2>风控测试!</h2>

<input type="button" value="创建测试" onClick="javascript:saveUser();"></input>
<p>This is a Example of Spring MVC REST using JavaConfig.</p>

<h2>This Will Display a Web Page</h2>

<p>If you want to see if the controller really works just click <a href="./ask/JohnathanMarkSmith">here</a> (REST call
    for JohnathanMarkSmith).</p>

<p>If you want to see if the controller really works just click <a href="./ask/Regan">here</a> (REST call for Regan).
</p>


<h2>This Will Return JSON</h2>

<p>If you want to see if the controller really works just click <a href="./api/JohnathanMarkSmith">here</a> (REST call
    for JohnathanMarkSmith).</p>

<p>If you want to see if the controller really works just click <a href="./api/Regan">here</a> (REST call for Regan).
</p>


<b><i><a href="http://www.JohnathanMarkSmith.com">http://www.JohnathanMarkSmith.com</a></i></b>
</body>
</html>
