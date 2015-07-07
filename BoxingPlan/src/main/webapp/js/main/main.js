$(document).ready(function(){
	initMainPage();
	
	$('.zone>.block').mouseover(function (){
		$(this).css({"background-color":"grey"});
		$(this).children(".black_layer").css({"opacity":"0.5"});
		
	});
	$('.zone>.block').mouseout(function (){
		$(this).css("background-color","white");
		$(this).children(".black_layer").css({"opacity":"0"});
	}); 
	
//	$('.login_frame button[type=submit]').click(function (){
//		login();
//	});
	$('.login_bottom>a').click(function (){
		$('.login_frame').fadeOut("slow");
		$('.regist_frame').fadeIn("slow"); 
	});
	$('.regist_bottom>button[type=button]').click(function (){
		$('.regist_frame').fadeOut("slow");
		$('.login_frame').fadeIn("slow");
	});
	$('.regist_bottom>button[type=submit]').click(function (){
		regist();
	});
	/*$(window).keydown(function(event){
	    switch(event.keyCode) {
	    	case 13:{ 
	    		login();
	    		break;
	    	}
	    }
	});*/
	
	function login(){
		var username=$('.login_frame input[placeholder=username]').val();
		var password=$('.login_frame input[placeholder=password]').val();
		alert($(".login_frame>form").serialize());
		var msg = Sys.Util.http.getMessage("/user/login.do","userName="+username+"&password="+password);
		if(msg.result == false){
			dialog({
				title:'Message',
				content:"login error,check username or password",
				ok:true,
				okValue:'confirm'
			}).showModal();
		}else{ 
			$('.login_frame').slideToggle("slow","linear",function (){
				$('.frame_layer').hide();
			}); 
		}
	}
	function regist(){
		var username=$('.regist_frame input[placeholder=username]').val();
		var password=$('.regist_frame input[placeholder=password]').val();
		
		var password2=$('.regist_frame input[placeholder=password-confirm]').val();
		if(password != password2){
			dialog({
				title:'Message',
				content:"You two entered passwords do not match",
				ok:true,
				okValue:'confirm'
			}).showModal();
			return;
		}
		var msg = Sys.Util.http.getMessage("/user/regist.do","userName="+username+"&password="+password);
		if(msg.result == false){
			dialog({
				title:'Message',
				content:"regist error,check username or password",
				ok:true,
				okValue:'confirm'
			}).showModal();
		}else{ 
			$('.login_frame').fadeIn("slow"); 
			$('.regist_frame').fadeOut("slow");
		}
	}
	
});
function initMainPage(){
	//$('.login_frame input[placeholder=username]').attr("value",getCookieValue("userName"));
	var msg = Sys.Util.http.getMessage("/user/check.do");
	if(msg.result == true){
		$('.login_frame').fadeOut();
		$('.frame_layer').hide();
	} 
}
//ªÒ»°cookie  
function getCookieValue(cookieName)  
{  
   var cookieValue = document.cookie;
   var cookieStartAt = cookieValue.indexOf(""+cookieName+"=");  
   if(cookieStartAt==-1)  
   {  
       cookieStartAt = cookieValue.indexOf(cookieName+"=");  
   }  
   if(cookieStartAt==-1)  
   {  
       cookieValue = null;  
   }  
   else  
   {  
       cookieStartAt = cookieValue.indexOf("=",cookieStartAt)+1;  
       cookieEndAt = cookieValue.indexOf(";",cookieStartAt);  
       if(cookieEndAt==-1)  
       {  
           cookieEndAt = cookieValue.length;  
       }  
       cookieValue = unescape(cookieValue.substring(cookieStartAt,cookieEndAt));//Ω‚¬Îlatin-1  
   }  
   return cookieValue;  
}