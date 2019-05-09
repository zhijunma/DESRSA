//开始加密
$("#beginEN").click(function(){
    let text_1 = $(".text_1").val();
    let text_2 = $(".text_2").val();

    //发送post请求
    let xhr = new XMLHttpRequest();
    var postStr = "word="+ text_1 +"&keySize="+ text_2;
    xhr.open("post","http://192.168.3.48:82/customRSA/addRSA",true);
    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    // 解决跨域
    xhr.setRequestHeader("Access-Control-Allow-Origin",true);
    xhr.onreadystatechange=function(){
        if(xhr.readyState==4&&xhr.status==200){
            let d = xhr.responseText;
            if(d=="添加成功！"){
                alert('加密成功!');
            }else{
                alert('加密失败!');
            }
        }
    }
    xhr.send(postStr);
});

//获取密文
$("#getPass").click(function(){
    let text_3 = $(".text_3").val();

    let xhr = new XMLHttpRequest();
    var postStr = "id="+ text_3;
    xhr.open("post","http://192.168.3.48:82/customRSA/getRSAPasswordById",true);
    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xhr.setRequestHeader("Access-Control-Allow-Origin",true);
    xhr.onreadystatechange=function(){
        if(xhr.readyState==4&&xhr.status==200){
            let d = xhr.responseText;
            $(".text_4").val(d);
        }
    }
    xhr.send(postStr);
});

// 获取私钥
$("#getPassText").click(function(){
    let text_5 = $(".text_5").val();

    let xhr = new XMLHttpRequest();
    var postStr = "rsaId="+ text_5;
    xhr.open("post","http://192.168.3.48:82/customRSA/getRSAPrivateKeyById",true);
    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xhr.setRequestHeader("Access-Control-Allow-Origin",true);
    xhr.onreadystatechange=function(){
        if(xhr.readyState==4&&xhr.status==200){
            let d = xhr.responseText;
            $(".text_6").val(d);
        }
    }
    xhr.send(postStr);
});

//开始解密
$("#endEN").click(function(){
    let text_7 = $(".text_7").val();
    let text_8 = $(".text_8").val();

    let xhr = new XMLHttpRequest();
    var postStr = "password="+ text_7 +"&privateKey="+ text_8;
    xhr.open("post","http://192.168.3.48:82/customRSA/getRSA",true);
    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xhr.setRequestHeader("Access-Control-Allow-Origin",true);
    xhr.onreadystatechange=function(){
        if(xhr.readyState==4&&xhr.status==200){
            let d = xhr.responseText;
            console.log(d)
            $(".text_9").val(d);
        }
    }
    xhr.send(postStr);
});
