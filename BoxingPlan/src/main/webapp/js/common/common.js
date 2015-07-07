(function(){
	
	
	
	//构造系统中通用的 对象 Sys
	(function(){
		if(!!window.Sys){
			throw new Error("系统中已经存在 Sys 对象");
		}
		window.Sys = {};
	})();
	
	//工具包
	Sys.Util = (function(){
		var Module = {
			http:(function(){
				return {
					//通过URL获得模版, 同步模式
					getTpl:function(url){
						var ret = "";
						$.ajax({
							url:url,
							type:"GET",
							async:false,
							dataType:'text', //"jsonp"
							success:function(res){
								ret = res;
							}
						});
						return ret;
					},
					//通过URL, 获得服务器的Message类型的消息
					getMessage : function(url,data){
						var ret = undefined;
						data = data || {};
						$.ajax({
							url:url,
							type:"POST",
							data:data,
							async:false,
							dataType:'json',
							success:function(res){
								var message = res.message;
								ret = message;
							},
							error : function(jqXHR, statusText, error ){
								var log = function(){};
								if(!!console){
									log = console.log;
								}
								log(statusText);
							}
						});
						return ret;
					}
				};
			})(),
		};
		return Module;
	})();
	
	
})();