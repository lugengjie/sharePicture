/*懒加载和瀑布流布局插件(版本3.0)*/
/*参数：主图片class和用户头像class参数和第一个固定面板的高度，
 * pictureDescribleDiv:主图片描述框id
 * 所有参数不能为空*/
(function($){
    $.fn.lazyLoadAndWaterFall = function(options){
	 var setting = $.extend({
        'mainPicture':'',
        'userPicture':'',
        'firstPanelHeight':0,
        'pictureDescribleDiv':''
      },options);
	  var mainPicture = setting.mainPicture;
	  var userPicture = setting.userPicture;
	  var firstPanelHeight = setting.firstPanelHeight;
	  var pictureDescribleDiv=setting.pictureDescribleDiv;
	  if(mainPicture==''||userPicture==''||firstPanelHeight==0){
		  return;
	  }
      var index=0; 
      var picturesOfBox=this[0].children;  
      var pageWidth=window.innerWidth;
      var imageWidth=236;
      var gap = 17;
      var isAllowScroll= 0; 
      var box =this;
      var heightArray=[];
	  var widthArray=[];
	  widthArray.push(0);
	  heightArray.push(firstPanelHeight);
	  var boxColum=parseInt(pageWidth / (imageWidth + gap));
	  this.children().each(function(){
		    $(this).hide();
	  });
       /*递归调用懒加载*/
      recursionAndOnload(picturesOfBox.length,picturesOfBox,imageWidth,boxColum,gap,heightArray,widthArray);
       /*  节流滚动条移动时触发图片懒加载与瀑布流 */     
      var canRun = true;
      window.onscroll=function(){
           if(isAllowScroll == 1){
             if(!canRun){
              return;
             }
             canRun = false;
             setTimeout( function () {
               scrollAndOnload(picturesOfBox.length,picturesOfBox,imageWidth,boxColum,gap,heightArray,widthArray);
               canRun = true;
             },130);
           }
       } 
        
       /*   递归调用图片懒加载和瀑布流布局 */
       function recursionAndOnload(imagesLength,images,imageWidth,boxColum,gap,heightArray,widthArray){
        if(index<imagesLength){
            pictureLoad(images);
            $(images[index]).find(mainPicture)[0].onload=function(){
              waterFall(images,imageWidth,boxColum,gap,heightArray,widthArray); 
              if((images[index].offsetTop<=($(window).height() + $(window).scrollTop()))){                    
                  index++;
                  recursionAndOnload(imagesLength,images,imageWidth,boxColum,gap,heightArray,widthArray); 
              }else{
                isAllowScroll = 1;     
              }
            }
        }  
      }

      /* 滚动滑动条后递归调用图片懒加载和瀑布流布局  */
      function scrollAndOnload(imagesLength,images,imageWidth,boxColum,gap,heightArray,widthArray){
          if((index+1)<imagesLength){ 
            if(images[index].offsetTop<=($(window).height() + $(window).scrollTop())){
              index++;
              pictureLoad(images);
              $(images[index]).find(mainPicture)[0].onload=onload=function(){
                waterFall(images,imageWidth,boxColum,gap,heightArray,widthArray);
                scrollAndOnload(imagesLength,images,imageWidth,boxColum,gap,heightArray,widthArray);  
              }       
            }
          }
      }

      /* 瀑布流布局*/
      function waterFall(images,imageWidth,boxColum,gap,heightArray,widthArray){   
        if(index<boxColum-1){     
          images[index].style.top = 0;
		  images[index].style.left = (imageWidth+gap)*(index+1)+'px';
		  widthArray.push((imageWidth+gap)*(index+1));
		  heightArray.push($(images[index]).height());
        }else{
        	var minHeightIndex = heightArray.indexOf(Math.min.apply(null,heightArray));
			images[index].style.top = heightArray[minHeightIndex]+gap+'px';
			images[index].style.left = widthArray[minHeightIndex]+'px';
			heightArray[minHeightIndex] = heightArray[minHeightIndex]+$(images[index]).height()+gap;
        }
        $(images[index]).show();
       
    }
     /* 图片加载 */
     function pictureLoad(images){
    	  var userPictureStr = $(images[index]).find(userPicture).attr('data_src'); 
		  $(images[index]).find(userPicture).attr('src',userPictureStr);
    	  var data_src = $(images[index]).find(mainPicture).attr('data_src');
		  $(images[index]).find(mainPicture).attr('src',data_src);
     }

    };
})(jQuery);

/*轮播插件：左右按钮加点击小相册内图片加载到大图片展示框(2.0版本）
参数：大图片展示框id,左按钮id,右按钮id，大图片展示框中图片在小相册中索引*/
/*littlePictureDescribe:小相册框中图片的描述
pictureDescribe:描述框的Id*/
(function($){
   $.fn.carouselPlugin = function(options){
         var setting = $.extend({
            'mainPicture':'',
            'leftButton':'',
            'rightButton':'',
            'pictureIndex':0
//            'beforePictureDescrible':''
            	
         },options);
         if(setting.leftButton != '' && setting.rightButton!='' && setting.mainPicture!=''){
//        	  var beforePictureDescrible = setting.pictureDescribe;
              var leftButton = $('#'+setting.leftButton);
              var rightButton = $('#'+setting.rightButton);
              var mainPicture = $('#'+setting.mainPicture);
              var pictureIndex =setting.pictureIndex; 
              var picturesOfAlbum = this[0].children;
              var album = this;

              /* 轮播左按钮*/
              if(pictureIndex == 0){
                  leftButton.hide();
              }
              leftButton.click(function(){
                 picturesOfAlbum[pictureIndex].style.opacity = 0.3;
                 mainPicture.attr("src",picturesOfAlbum[(--pictureIndex)].getAttribute("data_src"));
                 
                 var describeWord = picturesOfAlbum[pictureIndex].getAttribute("littlePictureIdDescribe");
                 if(describeWord!=null&&describeWord!=''){
                 	$(pictureDescribleDiv).html('<div id="pictureDescrible"><span>'+describeWord+'</span></div>');
                 }else{
                 	$(pictureDescribleDiv).empty();
                 }
                 
                 if(pictureIndex < picturesOfAlbum.length-1){
                    rightButton.show();
               }
               picturesOfAlbum[pictureIndex].style.opacity = 1;
               if(picturesOfAlbum[pictureIndex].offsetTop < album.scrollTop()){
                  album.scrollTop(picturesOfAlbum[pictureIndex].offsetTop-picturesOfAlbum[pictureIndex].offsetHeight);
               }
               if(pictureIndex == 0){
                     leftButton.hide();
               }
           });
           /*轮播右按钮*/
           if(pictureIndex == picturesOfAlbum.length-1){
        	   rightButton.hide();
           }
           rightButton.click(function(){     
               

                picturesOfAlbum[pictureIndex].style.opacity = 0.3;
                mainPicture.attr("src", picturesOfAlbum[(++pictureIndex)].getAttribute("data_src"));
                
                var describeWord = picturesOfAlbum[pictureIndex].getAttribute("littlePictureIdDescribe");
                if(describeWord!=null&&describeWord!=''){
                	$(pictureDescribleDiv).html('<div id="pictureDescrible"><span>'+describeWord+'</span></div>');
                }else{
                	$(pictureDescribleDiv).empty();
                }
       
                if(pictureIndex > 0){
                  leftButton.show();
               }
               picturesOfAlbum[pictureIndex].style.opacity = 1;
              if((picturesOfAlbum[pictureIndex].offsetTop+picturesOfAlbum[pictureIndex].offsetHeight) > (album.height() + album.scrollTop())){                
                       album.scrollTop(picturesOfAlbum[pictureIndex].offsetTop);
              }
              if(pictureIndex == picturesOfAlbum.length-1){
                     rightButton.hide();
                }     
           });

           // /*点击小相册中图片解除滤镜和将该图片放到大框中*/
           this.children('img').click(function(){
        	   
        	   $(this).css("opacity",1);
               picturesOfAlbum[pictureIndex].style.opacity = 0.3;
               pictureIndex=$(this).index();
               $("#mainPicture").attr("src", picturesOfAlbum[(pictureIndex)].getAttribute("data_src"));
               
               var describeWord = picturesOfAlbum[pictureIndex].getAttribute("littlePictureIdDescribe");
               if(describeWord!=null&&describeWord!=''){
               	$(pictureDescribleDiv).html('<div id="pictureDescrible"><span>'+describeWord+'</span></div>');
               }else{
               	$(pictureDescribleDiv).empty();
               }
               
               if(pictureIndex < picturesOfAlbum.length-1){
                    rightButton.show();
               }
               if(pictureIndex > 0){
                  leftButton.show();
               }
               if(pictureIndex == 0){
                  leftButton.hide();
               }
               if(pictureIndex == picturesOfAlbum.length-1){
                   rightButton.hide();
               }
              

           });
       }
   };
})(jQuery);


/*懒加载和滚动加载插件
参数为第一进入轮播模态框时，大图片框展示中图片在小相册中索引*/
(function($){
    $.fn.myPlugin = function(pictureIndex){
      var index=0; 
      var picturesOfAlbum=this[0].children;  
      var imageWidth=83;
      var gap = 2;
      var heightArray=[];
      var boxColum=3;
      var isAllowScroll= 0; 
      var album =this;

       /*递归调用懒加载*/
      recursionAndOnload(picturesOfAlbum.length,picturesOfAlbum,imageWidth,boxColum,gap,heightArray);
       /*  节流滚动条移动时触发图片懒加载与瀑布流 */     
      var canRun = true;
      this[0].onscroll=function(){
           if(isAllowScroll == 1){
             if(!canRun){
              return;
             }
             canRun = false;
             setTimeout( function () {
               scrollAndOnload(picturesOfAlbum.length,picturesOfAlbum,imageWidth,boxColum,gap,heightArray);
               canRun = true;
             },130);
           }
        } 
        
       /*   递归调用图片懒加载和瀑布流布局 */
       function recursionAndOnload(imagesLength,images,imageWidth,boxColum,gap,heightArray){
        if(index<imagesLength){
            pictureLoad(images);
            images[index].onload=function(){
              waterFall(images,imageWidth,boxColum,gap,heightArray); 
              if((images[index].offsetTop<=($("#picturesOfAlbum").height() + $("#picturesOfAlbum").scrollTop()))||index<pictureIndex){                    
                  index++;
                  recursionAndOnload(imagesLength,images,imageWidth,boxColum,gap,heightArray); 
              }else{
                isAllowScroll = 1;
                images[pictureIndex].style.opacity = 1;
                album.scrollTop(images[pictureIndex].offsetTop);
              }
              if(index==imagesLength-1){
            	  images[pictureIndex].style.opacity = 1;
              }
            }
        }  
      }

      /* 滚动滑动条后递归调用图片懒加载和瀑布流布局  */
      function scrollAndOnload(imagesLength,images,imageWidth,boxColum,gap,heightArray){
          if((index+1)<imagesLength){ 
            if(images[index].offsetTop<=($("#picturesOfAlbum").height() + $("#picturesOfAlbum").scrollTop())){
              index++;
              /*console.log("scroll");*/
              pictureLoad(images);
              images[index].onload=function(){
                waterFall(images,imageWidth,boxColum,gap,heightArray);
                scrollAndOnload(imagesLength,images,imageWidth,boxColum,gap,heightArray);  
              }       
            }
          }
      }

      /* 瀑布流布局*/
      function waterFall(images,imageWidth,boxColum,gap,heightArray){   
        if(index<boxColum){     
          images[index].style.top = 0;
          images[index].style.left = (imageWidth+gap)*index+'px';
          heightArray.push(images[index].offsetHeight); 
        }else{
          var minHeightIndex = heightArray.indexOf(Math.min.apply(null,heightArray));
          images[index].style.top = heightArray[minHeightIndex]+gap+'px';
          images[index].style.left = images[minHeightIndex].offsetLeft+'px';
          heightArray[minHeightIndex] = heightArray[minHeightIndex]+images[index].offsetHeight+gap;
        }
    }
     /* 图片加载 */
     function pictureLoad(images){ 
           /*console.log(index); */
          images[index].style.display='block';
          images[index].setAttribute('src',images[index].getAttribute("data_src"));  
      }

    };
})(jQuery);