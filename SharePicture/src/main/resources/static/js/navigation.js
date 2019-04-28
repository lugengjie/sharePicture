$(document).ready(function(){
         /*Websocket连接 */
  	      var websocket = new WebSocket("ws://"+window.location.host+"/websocket");
	  	  window.onbeforeunload = function(){
	  	        websocket.close();
	  	  }
	  	  //接收到消息的回调方法
	     websocket.onmessage = function(event){
	  		 var privateLetter = JSON.parse(event.data)
	    	 $("#showPrivateLetterPanel").append("<div class='chat-sender'><div><img src='"+privateLetter.senderPicture+"'></div><div></div>"+
   					 "<div><div class='chat-left_triangle'></div><span>"+privateLetter.privateLetterWord+"</span></div></div>");
	  		 $('#showPrivateLetterPanel').scrollTop( $('#showPrivateLetterPanel')[0].scrollHeight );
	  		 $('#unReadPrivateLetterNumber').load("/privateLetter/findUnreadMessageNum",function(){
	  			 if($('#unReadPrivateLetterNumber').text()==0){
	  				 $('#unReadPrivateLetterNumber').hide();
	  			 }else{
	  				$('#unReadPrivateLetterNumber').show();
	  			 } 
	  		 });
	     }
  	     /*Websocket连接 */
	     /*导航栏*/
	     	$("#navchangeName li a").click(function(){
	     		$('#navChangeContent').text($(this).text());
	     	});
	     	$(".glyphicon-search").click(function(){
	     		
	     		var navChangeContent = $('#navChangeContent').text();
	     		var navSearchInput = $("#navSearchInput input").val();
	     		if(navSearchInput=='') return;
	     		if(navChangeContent=='相册'){
	     			window.location.href="/personalCenter/tosearchAlbum?likeStr="+navSearchInput;
	     		}else if(navChangeContent=='收藏'){
	     			window.location.href="/personalCenter/tosearchPicture?likeStr="+navSearchInput;
	     		}else{
	     			window.location.href="/personalCenter/tosearchUser?likeStr="+navSearchInput;
	     		}
	     		
	     	});
	     	$("#outLogin").click(function(){
	     		console.log(11);
	     		$.ajax({
	  				  url: "/personalCenter/destroySession",
	  				  type: "POST",
	  				  processData: false,	
	  				  contentType: false ,
	  				  async:false,
	  				  success: function(data){
	  					location.reload();
	                  }
	  	  		 });
	     	});
	     	
	     /*导航栏*/
  	     /*私信提醒模态框*/
	      if($('#unReadPrivateLetterNumber').text()==0){
				 $('#unReadPrivateLetterNumber').hide();
			 }else{
				$('#unReadPrivateLetterNumber').show();
			} 
  	     	$("#navPrivateLetterRemindButton").click(function(){
	  	     		$.ajax({
	  				  url: "/privateLetter/showPrivateLetterUnReaded",
	  				  type: "POST",
	  				  processData: false,	
	  				  contentType: false ,
	  				  async:false,
	  				  success: function(data){
	  					  $("#showPrivateLetterRemindPanel ul").empty();
	  					  if(data=='') return;
	  					  var privateLetterRemind = JSON.parse(data);
	  					  for(var i=0;i<privateLetterRemind.length;i++)
	                 {
	  						   $("#showPrivateLetterRemindPanel ul").append("<li class='list-group-item'  style='height: 70px'>"+
	  					    	  "<a href='/personalCenter/personalCenterOfAlbum?userId="+privateLetterRemind[i].senderId+"'><img src='"+privateLetterRemind[i].senderPicture+"' style='height: 50px;width: 50px;margin-top: -35px'></a>"+
	  					    	  "<div class='privateLetterRemindWord' senderId='"+privateLetterRemind[i].senderId+"' style='display: inline-block;margin-left: 5px;min-width:460px'><p>"+privateLetterRemind[i].senderName+"</p><p>"+privateLetterRemind[i].privateLetterWord+"</p></div></li>");     
	                 } 
		  					$('.privateLetterRemindWord').click(function(){
		  					  $('#privateLetterRemind').modal('hide');
		  		  	    	  var senderId = $(this).attr('senderId');
		  		  	    	  console.log(senderId);
		  		  	    	  openPrivateLetterModel(senderId);
		  		  	      });
	              }
	  	  		   });
	  	     		$('#privateLetterRemind').modal('show');
  	     	});
  	     /*私信提醒模态框*/
  	     /*私信模态框 */
  	     function openPrivateLetterModel(receiverId)
  	     {
  	    	 var formDataOfPrivateLetter = new FormData();
 	    	 formDataOfPrivateLetter.append('otherUserId',receiverId);
 	    	 //加载私聊记录
 	    	 $.ajax({
				  url: "/privateLetter/showPrivateLetter",
				  type: "POST",
				  data: formDataOfPrivateLetter,
				  processData: false,	
				  contentType: false ,
				  async:false,
				  success: function(data){	
					  $("#showPrivateLetterPanel").empty();
					  if(data=='') return;
					  var privateLetter = JSON.parse(data);
					  for(var i=0;i<privateLetter.length;i++)
                {
						   
                     if(privateLetter[i].senderPicture!=null){
                   	   $("#showPrivateLetterPanel").append("<div class='chat-receiver'><div><img src='"+privateLetter[i].senderPicture+"'></div><div></div>"+
              					 "<div><div class='chat-right_triangle'></div><span>"+privateLetter[i].privateLetterWord+"</span></div></div>");
                   	 }
                     else{
                   	  $("#showPrivateLetterPanel").append("<div class='chat-sender'><div><img src='"+privateLetter[i].receiverPicture+"'></div><div></div>"+
               					 "<div><div class='chat-left_triangle'></div><span>"+privateLetter[i].privateLetterWord+"</span></div></div>");
                     } 
                }  
             }
	  		   });	
 	    	 	var myUserPicture = $('#navUserPicture img').attr('src');
 	    	 	$('#privateLetter').modal('show');
 	    	 	setTimeout(function(){  $('#showPrivateLetterPanel').scrollTop( $('#showPrivateLetterPanel')[0].scrollHeight ); }, 200);
 	    	 	/*评论框无内容时禁止点击发送*/
 	          $("#privateLetterText").bind('input propertychange',function () { 
 	              var TextAreaContent = $("#privateLetterText").val();
 	              if (TextAreaContent == "") {
 	                $("#privateLetterButton").attr("disabled","disabled");
 	              }else{
 	                $("#privateLetterButton").removeAttr("disabled");
 	              }
 	          });
 	          //前端发送
 	          $("#privateLetterButton").click(function(){
 	          	 var TextAreaContent = $("#privateLetterText").val();
 	          	 if(TextAreaContent=='')return;
 	          	 $("#showPrivateLetterPanel").append("<div class='chat-receiver'><div><img src='"+myUserPicture+"'></div><div></div>"+
 					 "<div><div class='chat-right_triangle'></div><span>"+TextAreaContent+"</span></div></div>");
 	          	 $('#showPrivateLetterPanel').scrollTop( $('#showPrivateLetterPanel')[0].scrollHeight );
 	          	 $("#privateLetterText").val('');
 	          	 $("#privateLetterButton").attr("disabled","disabled");
 	          	 var formDataTemp = new FormData();
 	          	 formDataTemp.append("receiverId",receiverId);
 	          	 formDataTemp.append("privateLetterWord",TextAreaContent);
 	          	 var message = {"receiverId":receiverId,"senderPicture":myUserPicture,"privateLetterWord":TextAreaContent};
 	          	 //保存私信数据到数据库
 	          	 $.ajax({
					   url: "/privateLetter/savePrivateLetter",
					   type: "POST",
					   data: formDataTemp,
					   processData: false,	
					   contentType: false ,
					   success: function(data){	
						   websocket.send(JSON.stringify(message));
	               }
		  		    }); 
 	          });
 	          //关闭聊天模态框时清空未聊天记录
	     		$('#privateLetter').on('hidden.bs.modal', function () {  
	     			$.ajax({
	  				  url: "/privateLetter/setIsRead?otherUserId="+receiverId,
	  				  type: "POST",
	  				  processData: false,	
	  				  contentType: false ,
	  				  async:false,
	  				  success: function(data){
	  					  $('#unReadPrivateLetterNumber').load("/privateLetter/findUnreadMessageNum",function(){
		  	 	  			  if($('#unReadPrivateLetterNumber').val()==0){
		  	 	  				 $('#unReadPrivateLetterNumber').hide();
		  	 	  			  }else{
		  	 	  				$('#unReadPrivateLetterNumber').show();
		  	 	  			  }
		  	 	  		   });
	  				  }
	  	  		   });	
	  	     	});
  	     }
  	     
  	     $("#openPrivateLetterModel").click(function(){
  	    	 var receiverId = $(this).attr('userId');
  	    	 openPrivateLetterModel(receiverId);
  	    	 
  	     });
  	     /*私信模态框 */
    	    /*点击导航按钮进行跳转*/
    	    $('#toPersonalCenterOfCollect').click(function(){
    	       var userId = $(this).attr('userId');
    	       window.location.href="/personalCenter/personalCenterOfCollect?userId="+userId;
      	 });
    	    $('#toPersonalCenterOfAlbum').click(function(){
     	       var userId = $(this).attr('userId');
     	       window.location.href="/personalCenter/personalCenterOfAlbum?userId="+userId;
       	 });
    	    $('#toPersonalCenterOfLike').click(function(){
     	       var userId = $(this).attr('userId');
     	       window.location.href="/personalCenter/personalCenterOfLike?userId="+userId;
       	 });
         /*点击导航按钮进行跳转*/
});