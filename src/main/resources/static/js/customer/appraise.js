// layui.use('layedit', function(){
//     var layedit = layui.layedit
//         ,$ = layui.jquery;
//
//     //构建一个默认的编辑器
//     var index = layedit.build('LAY_demo1');
//
//     //编辑器外部操作
//     var active = {
//         content: function(){
//             alert(layedit.getContent(index)); //获取编辑器内容
//         }
//         ,text: function(){
//             alert(layedit.getText(index)); //获取编辑器纯文本内容
//         }
//         ,selection: function(){
//             alert(layedit.getSelection(index));
//         }
//     };
//
//     $('.site-demo-layedit').on('click', function(){
//         var type = $(this).data('type');
//         active[type] ? active[type].call(this) : '';
//     });
//
// });
layui.use(['rate'], function(){
    var rate = layui.rate;
    //只读
    rate.render({
        elem: '#test8'
        ,value: 5
        ,readonly: true
        ,half: true         //半星
        ,theme: '#FF6B37'   //颜色
    })
})



layui.use(['rate'], function(){
    var rate = layui.rate;
    //只读
    rate.render({
        elem: '#test9'
        ,value: 4.5
        ,half: true         //半星
        ,theme: '#FF6B37'   //颜色
    })
})

layui.use(['rate'], function(){
    var rate = layui.rate;
    //只读
    rate.render({
        elem: '#test10'
        ,value: 3.5
        ,half: true         //半星
        ,theme: '#FF6B37'   //颜色
    })
})

layui.use(['rate'], function(){
    var rate = layui.rate;
    //只读
    rate.render({
        elem: '#test11'
        ,value: 4
        ,half: true         //半星
        ,theme: '#FF6B37'   //颜色
    })
})


//评价
var score;
layui.use(['rate'], function(){
    var rate = layui.rate;
    rate.render({
        elem: '#pingfen'
        ,value: 0           //初始值
        ,half: true         //半星
        ,text: true         //文本
        ,theme: '#FF6B37'   //颜色
        ,setText: function(value){
            this.span.text(value + " 分");
            score = value;
        }
    })
})

$("#pingfen").click(function(){
    var val = $("#pingfen").children().last().text();
    console.log(val);
})

var arr = [];
var upload_formEle = $(".upload_form");
$(".myimg").change(function () {
    var len = upload_formEle.children().length;
    if(len < 5){
        // this.files[0]：获取当前图片的js对象
        // 2、URL.createObjectURL(this.files[0])：为当前图片对象设置一个虚拟路径
        var img_src = URL.createObjectURL(this.files[0]);
        // 设置图片标签的src为这个选择的图片的虚拟路径   这样就可以完成图片预览的功能了
        console.log(img_src);
        upload_formEle.prepend("<a href='"+img_src+"' target='_blank' class='upload_a' style='display:inline-block;'><img class='upimg' src='"+ img_src +"'></a>");
        upload_formEle.ajaxSubmit({
            url: "upload_eva",
            method: 'POST',
            datType: 'json',
            success: function (data) {
                console.log(data);
                arr.push(data);
                console.log(arr)
            },
            error: function (data) {
                alert(data);
            }
        })
    }
    else{
        alert("达到上限，无法继续上传！");
    }
})



$(".layui-btn-danger").click(function () {
    $(".layui-btn-danger").attr("disabled","disabled");
    $.ajax({
        type:"post",
        url: "evaluation/save",
        data:{
            orderId:$("#orderId").val(),
            foodId:$("#foodId").val(),
            evaScore:score,
            evaContent:$("#eva_content").val(),
            imgArr:arr
        },
        traditional: true,//防止深度序列化
        success: function (data) {
            arr = [];
            alert("发表成功");
            console.log(data)
        },
        error: function (data) {
            alert(data);
        }
    })

})




