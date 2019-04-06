/*懒加载和瀑布流布局插件*/
(function($){
    $.fn.lazyLoadAndWaterFall = function(mainPicture){
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
	  heightArray.push(126);
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
		  widthArray.push(($(images[index]).width()+gap)*(index+1));
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
    	  console.log(index);
    	  var data_src = $(images[index]).find(mainPicture).attr('data_src');
		  $(images[index]).find(mainPicture).attr('src',data_src);
     }

    };
})(jQuery);