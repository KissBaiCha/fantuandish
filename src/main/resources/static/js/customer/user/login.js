
$("#loginByUser").click(function (){
    console.log("正在登录")
    $.ajax({
        type:"POST",
        url:"customer/login",
        data:{
            customerName:$(".login-username").val(),
            customerPwd:$(".login-password").val()
        },
        success:function (result){
            alert("登录"+result.message);
            console.log("result:" + result.message);
            console.log("result:" + result.code);
            console.log("result:" + result.data.token);
            //保存信息到本地，里面都 token
            if (result.message==="成功"){
                var token = result.data.token;
                window.location.href="shop/shopsift/1";
            }

        },
    })
})
function send() {
    var count = 60;
    var countdown = setInterval(CountDown, 1000);
    function CountDown() {
        $("#codebtn").attr("disabled", true);
        $("#codebtn").text("重新发送" + count );
        if (count == 0) {
            $("#codebtn").removeAttr("disabled");
            clearInterval(countdown);
            $("#codebtn").text("发送验证码");
        }
        count--;
    }
}
//发送验证码
$("#codebtn").click( function (){
    $.ajax({
        type:"post",
        url:"customer/sendCode/"+$("#customerTelno").val(),
        success: function (data){
            if (data.code===200){
                alert(data.message)
                send();
            }else
                alert(data.message)
        }
    })
})

$("#loginByCode").click( function() {
    $.ajax({
        type: "post",
        url: "customer/loginByCode/" + $("#customerTelno").val() + "/" + $("#verCode").val(),

        success: function (data) {
            if (data.message==="成功"){
                var token = data.data.token;
                alert("登录"+data.message)
                window.location.href="shop/shopsift/1";
            }
            else{
                alert(data.message)
            }
        },
        error:function (data){
            alert("手机号或验证码不能为空,请重新输入");
        }
    })
})
