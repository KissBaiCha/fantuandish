var time =$(".time").text( $(".time").text().replace("T"," "));
$(".order-details").click(function () {
    let orderNum = $(this).next().val()
    let flowNum = $(this).next().next().val()
    let payTime = $(this).next().next().next().val()
    console.log(orderNum)
    layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['420px', '240px'], //宽高
        content: '<span class="flow-msg">订单编号:'+orderNum+'</span><br><span class="flow-msg">流水号:'+flowNum+'</span><br><span class="flow-msg">支付时间:'+payTime+'</span><br>'
    });
})