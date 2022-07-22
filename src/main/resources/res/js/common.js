//设置全局ajax处理逻辑
$.ajaxSetup({
    //设置ajax请求结束后的执行动作
    complete: function (xhr) {
        // 通过XMLHttpRequest取得响应头，sessionstatus
        if("REDIRECT" === xhr.getResponseHeader("REDIRECT")){ //若HEADER中含有REDIRECT说明后端想重定向，
            var win = window;
            alert('ok')
            while(win != win.top){
                win = win.top;
            }
            console.log(xhr.getResponseHeader("CONTENTPATH"));
            win.location.href = xhr.getResponseHeader("CONTENTPATH");//将后端重定向的地址取出来,使用win.location.href去实现重定向的要求
        }
    }
});
