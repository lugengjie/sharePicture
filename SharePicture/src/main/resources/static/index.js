$(function(){
	        /*var $avatarEl = $('.avatar');
	        var $avatarHolderEl = $('.avatar-holder');
	        var avatars = [];
	        $avatarEl.each(function(){
	            var $avatar = $(this);
	            var avatarImage = $avatar.find('img').first().attr('src');
	            avatars.push(avatarImage);
	        });
	        $avatarHolderEl.backgroundBlur({
	            imageURL : avatars[0],
	            blurAmount : 50, 
	            imageClass : 'avatar-blur' 
	        });*/

	        $('.avatar-holder').each(function(){
	        	var avatarEl = $(this).find('img').attr('src');
	        	$(this).backgroundBlur({
	        		imageURL : avatarEl,
	                blurAmount : 20, 
	                imageClass : 'avatar-blur'
	        	});

	        });
});