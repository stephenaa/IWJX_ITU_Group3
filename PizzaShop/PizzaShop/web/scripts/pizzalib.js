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





