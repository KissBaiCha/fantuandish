
$(".loginBtn").click(function (){
    console.log("正在登录")
    $.ajax({
        type:"post",
        url:"customer/login",
        data:{
            customerName:$(".login-username").val(),
            customerPwd:$(".login-password").val()
        },
        success:function (result){
            console.log("result:" + result.message);
            console.log("result:" + result.code);
            console.log("result:" + result.data.token);
            //保存信息到本地，里面都 token
            var token = result.data.token;
            // console.log("token:" + token);
            localStorage.setItem("token",token);
            $.ajax({
                type: "post",
                url: "jiemi",
                headers: {'token': token},
                success:function (data) {
                    alert("登录成功");
                    console.log(data)
                }
            })
        },
        error:function (errResult) {
            alert(errResult.message)
        }
    })
})
