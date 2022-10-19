layui.use(['form', 'util', 'laydate'], function(){
    var form = layui.form;
    var layer = layui.layer;
    var util = layui.util;
});

$(".get-verifyCodeBtn").click(function (){
    $(".get-verifyCodeBtn").html("60s")
    layer.msg('验证码发送成功');
})
//^[1][3,4,5,7,8][0-9]{9}$ 电话号
$(".userTel").blur(function (){
   let userTel = $(".userTel").val();
   if(userTel === "" || !/^[1][3,4,5,7,8][0-9]{9}$/.test(userTel)){
       layer.msg('请输入合法手机号');
       return false;
   }
   return true;
})
