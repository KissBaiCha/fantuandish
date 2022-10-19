layui.use(['rate'], function(){
    var rate = layui.rate;
     //只读
rate.render({
elem: '#test8'
,value: 5
,readonly: true
});});


const { createEditor, createToolbar } = window.wangEditor

const editorConfig = {
    placeholder: 'Type here...',
    onChange(editor) {
      const html = editor.getHtml()
      console.log('editor content', html)
      // 也可以同步到 <textarea>
    }
}

const editor = createEditor({
    selector: '#editor-container',
    html: '<p><br></p>',
    config: editorConfig,
    mode: 'default', // or 'simple'
})

const toolbarConfig = {}

const toolbar = createToolbar({
    editor,
    selector: '#toolbar-container',
    config: toolbarConfig,
    mode: 'default', // or 'simple'
})


layui.use(['rate'], function(){
    var rate = layui.rate;
     //只读
rate.render({
elem: '#test9'
,value: 5
,readonly: true
});});

layui.use(['rate'], function(){
    var rate = layui.rate;
     //只读
rate.render({
elem: '#test10'
,value: 5
,readonly: true
});});

layui.use(['rate'], function(){
    var rate = layui.rate;
     //只读
rate.render({
elem: '#test11'
,value: 5
,readonly: true
});});