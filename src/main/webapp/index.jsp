<html>

<head>
    <head>
        <script type="text/javascript">
            function saveUser() {
                //$(document).ready(function(){
                var saveDataAry=[];
                var data1='{"userName":"test","address":"gz"}';
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
                    url:"user/saveUser",
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

<button onClick="javascript:saveUser();">创建测试</button>
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
