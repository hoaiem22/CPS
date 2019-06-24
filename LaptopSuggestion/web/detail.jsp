<%-- 
    Document   : detail
    Created on : Mar 27, 2019, 11:07:53 PM
    Author     : EmVH <hoaiem.heli22@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Detail</title>
        <meta charset="utf-8"/>
        <meta name="keywords" content="porfolio, web designer, web developer,
              backend, frontend, software"/>
        <link href="https://fonts.googleapis.com/css?
              family=Gaegu|Open+Sans+Condensed:300" rel="stylesheet">
        <link rel="stylesheet" href="css/main.css"/>
        <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css"/>
        <script src="js/index.js" type="text/javascript"></script>
        <script src="js/cookie.js" type="text/javascript"></script>
    </head>
    <body onload="getCheaps(); getSeen(); getView(); searchLaptop(${param.id}); searchRelativeByPrice(document.getElementById('tdPrice').getAttribute('data'));">      
        <button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
        <nav id="main_header">
            <div id="main_header_wrapper">
                <div id="logo">
                    <a href="index.jsp"><img src="img/ls.png"/></a>
                </div>
                <div id="search_tool">
                    <div id="search_box">
                        <form name="myForm" onsubmit="return submitForm(this);">
                            <div class="search_box_input">
                                <input id="txtSearch" type="text" name="txtPrice" value="${param.txtPrice}" placeholder="Input price or name..."/>
                            </div>
                            <div class="search_box_button">
                                <a href="#output_search">
                                    <input class="" id="btnSearch" type="button" value="Search"
                                           onclick="searchLaptopsByPrice(this.form.txtPrice.value)"/>
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
                <div id="cart_tool">

                </div>
            </div>
        </nav>
        <section id="main_body">
            <div id="main_body_wrapper">
                <aside id="main_body_left_content">
                    <ul id="main_body_left_menu">
                        <li class="left_sub_menu">
                            <a href="#output_cheap">
                                Sản phẩm giá tốt
                            </a>
                        </li>
                        <li class="left_sub_menu">
                            <a href="#output_seen">
                                Tìm kiếm gần đây
                            </a>
                        </li>
                        <li class="left_sub_menu">
                            <a href="#output_view">
                                Sản phẩm nhiều người xem
                            </a>
                        </li>
                    </ul>
                </aside>
                <section id="main_body_right_content">
                    <div class="main_panel" id="output_detail">
                    </div>
                    <div class="main_panel" id="output_search">
                    </div>
                    <div class="main_panel" id="output_relative">
                    </div>
                    <div class="main_panel" id="output_cheap">
                    </div>
                    <div class="main_panel" id="output_seen">
                    </div>
                    <div class="main_panel" id="output_view">
                    </div>
                </section>
            </div>
        </section>

        <footer>
            <div>
                <p>&copy;copyright 2019 Võ Hoài Em</p>
                <p>Contact by email: emvhse62399@fpt.edu.vn; phone: 0374656683</p>
            </div>
        </footer>
    </body>
    <script type="text/javascript">
        document.addEventListener('DOMContentLoaded', function () {
            console.log("Ready");
            var id = ${param.id} + "";
            console.log(id);
            var seen = [];
            if (!checkCookie("seen")) {
                //if !exist create cookie
                console.log("!Exist");
                seen.push(id);
                setCookie("seen", seen, 30);
                console.log(getCookie("seen"));
            }
            seen = getCookie("seen").split(',');
            if (!containArray(seen, id)) {
                if (seen.length > 7) {
                    seen.shift(); //remove first child
                }
                seen.push(id);
            }
            setCookie("seen", seen, 30); //update cookie
            console.log(seen);
            loadXMLDoc("SeenServlet?seens=" + seen, "seen.xsl", "output_seen");
        });
        function containArray(arrs, search) {
            for (i = 0; i < arrs.length; i++) {
                if (arrs[i] == search) {
                    return true;
                }
            }
            return false;
        }

    </script>
</html>
