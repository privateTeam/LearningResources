(function(){
	
	
	
	//����ϵͳ��ͨ�õ� ���� Sys
	(function(){
		if(!!window.Sys){
			throw new Error("ϵͳ���Ѿ����� Sys ����");
		}
		window.Sys = {};
	})();
	
	//���߰�
	Sys.Util = (function(){
		var Module = {
			http:(function(){
				return {
					//ͨ��URL���ģ��, ͬ��ģʽ
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
					//ͨ��URL, ��÷�������Message���͵���Ϣ
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