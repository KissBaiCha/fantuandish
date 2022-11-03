// $(".update").click(function (){
//     console.log("修改信息")
//     $.ajax({
//         type:"POST",
//         url:"info/show",
//         data:{
//             customerName:$(".login-username").val(),
//             customerPwd:$(".login-password").val()
//         },
//         success:function (result){
//             alert(result.message);
//             console.log("result:" + result.message);
//             console.log("result:" + result.code);
//             console.log("result:" + result.data.token);
//             //保存信息到本地，里面都 token
//             var token = result.data.token;
//             window.location.href="shop/shopsift/1";
//         },
//     })
// })