<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>正在签到</title>
<style>
body
{
     background: url(/images/background.png)   top center no-repeat; background-size:cover;
}
.main {
	text-align: center; /*让div内部文字居中*/	
	height: 50px;
	width: 100%;
	margin: 0 auto;
	position: absolute;
	line-height: 80px;
	top:  30%;
	left: 0;
	right: 0;
	bottom: 0;
	color : #a9a9a9;
	font-family:STXinwei;
	font-size:xx-large;
}
</style>
</head>
<script type="text/javascript" >
function onload(){
//window.location.href= "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd7242ea0fe746d9f&redirect_uri=http%3a%2f%2fwww.qiaohserver%3a8080%2fYK%2fwechat%2fSignInSuccess&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
//window.location.href= "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd7242ea0fe746d9f&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fssm%2FSignInSuccess&response_type=code&scope=snsapi_userinfo&state=&connect_redirect=1#wechat_redirect";
//请求微信获取权限链接
window.location.href= "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd7242ea0fe746d9f&redirect_uri=http%3A%2F%2Fweixin.iguopin.com%2Fwechatcontroller%2FgetOpenid&response_type=code&scope=snsapi_userinfo&state=&connect_redirect=1#wechat_redirect";
 }
</script>
<body onload="onload();" >
  <div class="main">
		<h1>正在签到，请稍后...</h1>
		
	</div>
</body>

</html>