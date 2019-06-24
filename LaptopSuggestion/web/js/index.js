var xml;
var xsl;
var price;
//Load data from file name, apply xsl and inner to output
function loadXMLDoc(filename, xslFile, output)
{
    if (window.ActiveXObject)
    {
        xhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } else
    {
        xhttp = new XMLHttpRequest();
    }
    xhttp.open("GET", filename, false);
    try {
        xhttp.responseType = "msxml-document";
    } catch (err) {
    } // Helping IE11
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            xml = xhttp.responseXML;
            loadXSL(xslFile, output);
        }
    };
    xhttp.send("");
}

function loadXSL(filename, output) {
    if (window.ActiveXObject)
    {
        xhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } else
    {
        xhttp = new XMLHttpRequest();
    }
    xhttp.open("GET", filename, false);
    try {
        xhttp.responseType = "msxml-document";
    } catch (err) {
    } // Helping IE11
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            xsl = xhttp.responseXML;
            if (xsl != null && xml != null) {
                var xslTemplate = new ActiveXObject("MSXML2.XSLTemplate.6.0");
                xslTemplate.stylesheet = xsl;
                var xsltProcessor = xslTemplate.createProcessor();
                xsltProcessor.input = xml;
                xsltProcessor.transform();
                document.getElementById(output).innerHTML = xsltProcessor.output;
            }

        }
    };
    xhttp.send("");
    //return xhttp.responseXML;
}

function searchLaptopsByPrice(priceValue) {
    console.log("search");
    loadXMLDoc("LaptopServlet?txtPrice=" + priceValue, "search.xsl", "output_search");
}

function searchRelativeByPrice(priceValue) {
    if (priceValue > 0) {
        loadXMLDoc("LaptopServlet?txtPrice=" + priceValue, "relative.xsl", "output_relative");
    }
}


function getCheaps() {
    loadXMLDoc("CheapStatisticServlet", "cheap.xsl", "output_cheap");
}
function searchLaptop(id) {
    console.log("Search");
    loadXMLDoc("SearchServlet?id=" + id, "detail.xsl", "output_detail");
}

function getSeen() {
    seen = getCookie("seen").split(',');
    loadXMLDoc("SeenServlet?seens=" + seen, "seen.xsl", "output_seen");
}

function getView() {
    seen = getCookie("seen").split(',');
    loadXMLDoc("PopularServlet", "view.xsl", "output_view");
}
window.onscroll = function () {
    scrollFunction();
};

function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        document.getElementById("myBtn").style.display = "block";
    } else {
        document.getElementById("myBtn").style.display = "none";
    }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}