layui.use(['rate'], function () {
    let rate = layui.rate;
    //只读
    rate.render({
        elem: '.grade-tip'
        , value: $(".shop-score").val()
        , readonly: true
        , half: true
    });
});
//这是一个全局变量
let kg = false; //给一个开关并赋值，用来进行后面的 if else 条件判断
$(".comment-perfect").click(function () { //给button按钮一个点击事件
    let dianZan = $(this).find("i");
    let dianZanText = $(this).find("span");
    if (kg) { //进行判断
        dianZan.css("background", "url(\"/images/details/perfect.svg\") no-repeat")
    } else {
        dianZan.css("background", "url(\"/images/details/perfect_cilk.svg\") no-repeat")
    }
    kg = !kg; //这里的感叹号是取反的意思，如果你没有写，当你点击切换回第一张图片时，就会不生效
})

//分页
layui.use(['laypage', 'layer'], function(){
    let pageNum = $(".curr").val();
    let total = $(".total").val();
    let laypage = layui.laypage
        ,layer = layui.layer;
    laypage.render({
        elem: 'pages'
        ,count: total
        ,theme: '#FF5722'
        ,curr : pageNum
        ,limit: 10
        ,jump: function(obj, first){
            let newPageNum = obj.curr;
            //obj包含了当前分页的所有参数，比如：
            console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
            console.log(obj.limit); //得到每页显示的条数
            //首次不执行
            if(!first){
                document.querySelector('#anchor').scrollIntoView(true)
                let url = window.location.href
                newUrl = url.substring(0,url.length - 1).concat(obj.curr)
                window.location.href = newUrl
            }
        }
    });
});
// layui.use('flow', function(){
//     var $ = layui.jquery; //不用额外加载jQuery，flow模块本身是有依赖jQuery的，直接用即可。
//     var flow = layui.flow;
//     flow.load({
//         elem: '#demo' //指定列表容器
//         ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
//             var lis = [];
//             //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
//             $.get('/api/list?page='+page, function(res){
//                 //假设你的列表返回在data集合中
//                 layui.each(res.data, function(index, item){
//                     lis.push('<li>'+ item.title +'</li>');
//                 });
//
//                 //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
//                 //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
//                 next(lis.join(''), page < res.pages);
//             });
//         }
//     });
// });

