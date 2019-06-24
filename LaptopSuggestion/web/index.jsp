<%-- 
    Document   : index
    Created on : Mar 27, 2019, 7:09:46 PM
    Author     : EmVH <hoaiem.heli22@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home</title>
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
    <body onload="getCheaps(); getSeen(); getView();">
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
                    <div id="carsourel">
                        <img src="img/carsourel_1.jpg">
                        <img src="img/carsourel_2.jpg">
                        <img src="img/carsourel_3.jpg">
                        <img src="img/carsourel_4.jpg">
                        <img src="img/carsourel_5.jpg">
                    </div>
                    <div class="main_panel" id="output_search">
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

        function submitForm(form) {
            searchLaptopsByPrice(form.txtPrice.value);
            return false;
        }
    </script>
</html>
