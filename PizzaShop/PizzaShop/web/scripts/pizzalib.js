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





