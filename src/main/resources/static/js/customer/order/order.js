var time =$(".time").text( $(".time").text().replace("T"," "));
$(".couponCard").hide();
$(".couponList").click(function (){
    $(".couponCard").toggle();
})


$(".quXiaoBtn").click(function (){
    let gongJi = $(".price").text();
    gongJi = Number( gongJi)
    $(".newCouponId").val(0)
    $(".n-price").text(0)
    $(".payNum").text(gongJi)
    $(".couponCard").hide();
})



layui.use('form', function(){
    var form = layui.form;
    //提交
    form.on('submit(formDemo)', function(data){
        let couponId = data.field.couponId;
        $(".newCouponId").val(couponId)
        $.ajax({
            url:'http://localhost:8080/fan/myCoupon/getMyCouponPrice/'+couponId,
            type:'GET',
            success:function (data) {
                let gongJi = $(".price").text();
                gongJi = Number(gongJi)
                $(".n-price").text(data)
                $(".payNum").text(gongJi - data)
            }
        })
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
