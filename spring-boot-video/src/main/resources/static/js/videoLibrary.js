$(function () {

    $.ajax({
        type : "POST",
        dataType : "JSON",
        url : _ctx + "/library",
        cache : false,
        success : function(data) {

        }
    })





})

function getRealPath(){
    var localObj = window.location;
    var contextPath = localObj.pathname.split("/")[1];
    var basePath = localObj.protocol + "//" + localObj.host + "/"+ contextPath;
    return basePath ;
};