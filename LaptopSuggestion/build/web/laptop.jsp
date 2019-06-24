<%-- 
    Document   : marshallToJS
    Created on : Mar 8, 2019, 12:15:45 AM
    Author     : EmVH <hoaiem.heli22@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSPS Page</title>
        <style>
            table {
                border-collapse: collapse;
                width: 100%;
            }
            th, td {
                text-align: left;
                padding: 8px;
            }
            tr:nth-child(even) {background-color: #f2f2f2;}
            th {
                background-color: #4CAF50;
                color: white;
            }
            /*Hover*/
            .zoom {
                padding: 50px;
                transition: transform .2s;
                width: 200px;
                height: 200px;
                margin: 0 auto;
            }

            .zoom:hover {
                -ms-transform: scale(1.5); /* IE 9 */
                -webkit-transform: scale(1.5); /* Safari 3-8 */
                transform: scale(1.5); 
            }
        </style>
        <script src="js/xhttp.js" type="text/javascript"></script>
    </head>
    <body>
        <form name="myForm" onsubmit="return submitForm(this);">
            Price <input id="txtSearch" type="text" name="txtPrice" value="${param.txtPrice}"/><br/>
            <input id="btnSearch" type="button" value="Transform"
                   onclick="applyXLSTemplateWithXML(this.form.txtPrice.value)"/>
        </form>
            <div id="output"></div>
    </body>
    <script type="text/javascript">
        var input = document.getElementById("txtSearch");
        // Execute a function when the user releases a key on the keyboard
        input.addEventListener("keyup", function (event) {
            // Number 13 is the "Enter" key on the keyboard
            if (event.keyCode === 13) {
                // Cancel the default action, if needed
                event.preventDefault();
                // Trigger the button element with a click
                document.getElementById("btnSearch").click();
            }
        });
        
        function submitForm(form){
            applyXLSTemplateWithXML(form.txtPrice.value);
            return false;
        }
    </script>
</html>
