$(function() {
  $("*[data-href]").on("click", function() {
    window.location = $(this).data("href");
  });
});

function consultarNotas() {
  document
    .querySelector("body")
    .onload(GetIt("http://127.0.0.1:3000/consultarNotas"));
}

function FactoryXMLHttpRequest() {
  if (window.XMLHttpRequest) {
    return new XMLHttpRequest(); // Opera 8.0+, Firefox, Chrome, Safari
  } else if (window.XDomainRequest) {
    return new XDomainRequest(); // Antigo Safari
  } else if (window.ActiveXObject) {
    var msxmls = new Array( // Internet Explorer
      "Msxml2.XMLHTTP",
      "Microsoft.XMLHTTP",
      "Msxml3.XMLHTTP",
      "Msxml2.XMLHTTP.7.0",
      "Msxml2.XMLHTTP.6.0",
      "Msxml2.XMLHTTP.5.0",
      "Msxml2.XMLHTTP.4.0",
      "Msxml2.XMLHTTP.3.0"
    );
    for (var i = 0; i < msxmls.length; i++) {
      try {
        return new ActiveXObject(msxmls[i]);
      } catch (e) {}
    }
  } else throw new Error("Could not instantiate XMLHttpRequest");
}

function GetIt(url) {
  var xmlhttp = new FactoryXMLHttpRequest();

  xmlhttp.onreadystatechange = function() {
    if (xmlhttp.readyState == 4) {
      // Javascript function JSON.parse to parse JSON data
      var jsonObj = JSON.parse(xmlhttp.responseText);

      document.getElementById("NF-ID").innerHTML = jsonObj.id;
      document.getElementById("NF-descricao").innerHTML = jsonObj.descricao;
      document.getElementById("NF-valorUnit").innerHTML = jsonObj.valorUnit;
      document.getElementById("NF-quant").innerHTML = jsonObj.quantidade;
      document.getElementById("NF-data").innerHTML = jsonObj.dataEmissaoNF;
    }
  };

  if (xmlhttp) {
    xmlhttp.open("get", url, true);
    xmlhttp.send();
  }
}
