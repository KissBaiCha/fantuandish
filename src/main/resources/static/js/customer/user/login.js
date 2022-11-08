
$(".login-btn").click(function (){
    console.log("正在登录")
    $.ajax({
        type:"POST",
        url:"customer/login",
        data:{
            customerName:$(".login-username").val(),
            customerPwd:$(".login-password").val()
        },
        success:function (result){
            alert(result.message);
            console.log("result:" + result.message);
            console.log("result:" + result.code);
            console.log("result:" + result.data.token);
            //保存信息到本地，里面都 token
            var token = result.data.token;
            window.location.href="shop/shopsift/1";
        },
    })
})
//发送验证码
$("#codebtn").click( function (){
    $.ajax({
        type:"post",
        url:"customer/sendCode/"+$("#customerTelno").val(),
        success: function (data){
            alert(data)
        }
    })
})

$("#loginByCode").click( function() {
    console.log("customer/loginByCode/" + $("#customerTelno").val() + "/" + $("#verCode").val())
    $.ajax({
        type: "post",
        url: "customer/loginByCode/" + $("#customerTelno").val() + "/" + $("#verCode").val(),

        success: function (data) {
            alert(data.message);
            if (data.message==="成功"){
                var token = data.data.token;
                window.location.href="shop/shopsift/1";
            }
        },
        error: function (data) {
            alert("登录,请稍后重试！")

        }
    })
})
