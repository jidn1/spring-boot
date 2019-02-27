$(function () {
    $("nav.header-nav ul li").click(function (e) {
        $("nav.header-nav ul li").each(function () {
            $(this).find("a").removeClass('header-nav__cur')
        })
        $(this).find("a").addClass('header-nav__cur');
    })
})

function getRootPath() {
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： /uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName);
}

function player(){
    var path = getRootPath();
    var hrefUrl = window.document.location.href;
    let strings = hrefUrl.split("player/");
    $.ajax({
        type: "POST",
        url: path+"/getPlayer",
        data: {mid:strings[1]},
        cache: false,
        dataType: "json",
        success: function (data) {
            if(data != undefined && data != ''){
                $("#main").attr("src",data.URL);
            }
        }
    });
}


function login(){
    var path = getRootPath();
    var user = $("#user").val();
    var pass = $("#pass").val();
    if(!user){
        layer.msg("邮箱不能为空", {icon: 2});
        return false;
    }
    if(!pass){
        layer.msg("密码不能为空", {icon: 2});
        return false;
    }
    if(!check_email(user)){
        layer.msg("邮箱格式不正确", {icon: 2});
        return false;
    }
    pass = md5(pass);
    $.ajax({
        type: "POST",
        url: path+"/loginService",
        data: {email:user,password:pass},
        cache: false,
        dataType: "json",
        success: function (data) {
            if(data.success){
                layer.msg(data.msg, {icon: 1});
                window.location.href = path;
            } else {
                layer.msg(data.msg, {icon: 2});
            }
        }
    });
}


function regist(){
    var path = getRootPath();
    var email = $("#email").val();
    var password = $("#password").val();
    var respassword = $("#respassword").val();

    if(!email){
        layer.msg("邮箱不能为空", {icon: 2});
        return false;
    }
    if(!password){
        layer.msg("密码不能为空", {icon: 2});
        return false;
    }
    if(!respassword || password != respassword){
        layer.msg("两次密码不一致", {icon: 2});
        return false;
    }
    if(!check_email(email)){
        layer.msg("邮箱格式不正确", {icon: 2});
        return false;
    }

    if(!check_password(password)){
        layer.msg("密码为8~12位数字加字母", {icon: 2});
        return false;
    }

    password = md5(password);
    $.ajax({
        type: "POST",
        url: path+"/registService",
        data: {email:email,password:password},
        cache: false,
        dataType: "json",
        success: function (data) {
            if(data.success){
                layer.msg(data.msg, {icon: 1});
            } else {
                layer.msg(data.msg, {icon: 2});
            }
            $("#email").val("");
            $("#password").val("");
            $("#respassword").val("");
        }
    });
}