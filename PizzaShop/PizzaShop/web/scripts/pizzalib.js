/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function getHttp () {    
    if (navigator.appName == "Microsoft Internet Explorer")
        return new ActiveXObject("Microsoft.XMLHTTP");
    else
        return new XMLHttpRequest();
}

 
function sendRequest(http, action, responseHandler) {
  
    http.open("GET", action);
    http.onreadystatechange = responseHandler;
    http.send(null);
}


function sendPUTRequest(http, action, data, responseHandler) {
  
    http.open("PUT", action);
    http.onreadystatechange = responseHandler;
    http.setRequestHeader("Content-type","application/xml");
    http.send(data);  
}
             
function checkName(uname){
    if((uname.value == null)||(uname.value=="")||(uname.value.length < 1)){
        var messageName= "<img src=\"img/wrong-icon.jpg\">"+"Please input Username";
    } else {
        var messageName = "<img src=\"img/right-icon.jpg\">"; 
                  
    }
    document.getElementById("demoName").innerHTML = messageName;
}
function checkPassword(password){
    if((password.value == null) || (password.value == "") || (password.value.length<6)){
        var messagePass = "<img src=\"img/wrong-icon.jpg\">"+"At least 6 Characters";
                    
    } else {
        var messagePass = "<img src=\"img/right-icon.jpg\">"; 
    }
    document.getElementById("demoPass").innerHTML = messagePass; 
                 
}
function checkConPassword(confirmpassword){
    if((confirmpassword.value == null) || (confirmpassword.value == "") || (confirmpassword.value.length<6)){
        var messagePass = "<img src=\"img/wrong-icon.jpg\">"+"At least 6 Characters, it should match the previous password input.";
                    
    } else {
        var messagePass = "<img src=\"img/right-icon.jpg\">"; 
    }
    document.getElementById("demoConPass").innerHTML = messagePass; 
}
function checkZip(zipcode){
    var RE = /^-{0,1}\d*\.{0,1}\d+$/;
    if (RE.test(myForm.zipcode.value)&&(myForm.zipcode.value.length ==4))  
    {  
        var messageEmail = "<img src=\"img/right-icon.jpg\">";
    } else {
        var messageEmail = "<img src=\"img/wrong-icon.jpg\">" + "4 Digial Numbers";
    }
    document.getElementById("demoZip").innerHTML = messageEmail;  
}
function checkPhone(phone){
    var RE = /^-{0,1}\d*\.{0,1}\d+$/;
    if (RE.test(myForm.phone.value)&&(myForm.phone.value.length ==8))  
    {  
        var messageEmail = "<img src=\"img/right-icon.jpg\">";
    } else {
        var messageEmail = "<img src=\"img/wrong-icon.jpg\">" +"8 digital Numbers";
    }
    document.getElementById("demoPhone").innerHTML = messageEmail;  
}
function checkEmail(emailAddr)
{
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm.emailAddr.value))  
    {  
        var messageEmail = "<img src=\"img/right-icon.jpg\">";
    } else {
        var messageEmail = "<img src=\"img/wrong-icon.jpg\">"+"Format: abc@c.com";
    }
    document.getElementById("demoEmail").innerHTML = messageEmail;  
}    
