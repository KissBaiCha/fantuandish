
$(".order-details").click(function () {
    let orderId = $(this).next().val()
    console.log(orderId)
    $.ajax({
        url:'customer/getFlow/'+orderId,
        type: 'GET',
        success:function (result) {
            console.log(result)
            if(result.data === null){
                layer.open({
                    type: 1,
                    skin: 'layui-layer-rim', //加上边框
                    area: ['420px', '240px'], //宽高
                    content: '<span class="flow-msg">订单编号:'+orderId+'</span>'
                });
            }else{
                layer.open({
                    type: 1,
                    skin: 'layui-layer-rim', //加上边框
                    area: ['420px', '240px'], //宽高
                    content: '<span class="flow-msg">订单编号:'+orderId+'</span><br><span class="flow-msg">流水号:'+result.data.flow.flowNumber+'</span>'
                });
            }
        }
    })
})
$(".goPay").click(function () {
    let newOrderId = $(".AliPayNum").val()
    $.ajax({
        url:'alipay/goAlipay',
        type:'POST',
        data:{
            orderNum :newOrderId
        }
    })
})