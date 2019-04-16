$(function(){
    $('.avatar-holder').each(function(){
    	var avatarEl = $(this).find('img').attr('src');
    	$(this).backgroundBlur({
    		imageURL : avatarEl,
            blurAmount : 20, 
            imageClass : 'avatar-blur'
    	});

    });
});