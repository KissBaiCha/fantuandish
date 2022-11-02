$(".couponCard").hide();
$(".couponList").click(function (){
    let shopId = $(".shopId").val();
    $(".couponCard").toggle();
})
layui.use('form', function(){
    var form = layui.form;
    //提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});


// <script>
//     //Demo
//     layui.use('form', function(){
//     var form = layui.form;
//
//     //提交
//     form.on('submit(formDemo)', function(data){
//     layer.msg(JSON.stringify(data.field));
//     return false;
// });
// });
// </script>
