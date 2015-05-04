function init() {

	$('.lnk-category').click(function(event) {

		$('.lnk-category').removeClass('active');

		$(this).addClass('active')
		feedList($(this).find('#cat_id').text());

	});
	
	$('.sync-all').click(function(event) {

		$.ajax({
			url : 'channel',
			type : 'POST',
			data : {
				'act' : 'synchronize'
			},
			success : function(data) {
				if(data != '' && data > 0){
					location.href='index';
				}
				else{
					$('#sync_loading').html('');
				}
			},
			beforeSend : function() {
				$('#sync_loading').html(loading);
			},
		});

	});	
	
	$('.mark-all-as-read').click(function(event) {

		var categoryID = $('.lnk-category.active').attr('id');

		$.ajax({
			url : 'index',
			type : 'POST',
			data : {
				'act' : 'markAllAsRead',
				'categoryID' : categoryID
			},
			success : function(data) {
				location.href='index';
			},
			beforeSend : function() {
				$('#sync_loading').html(loading);
			},
		});

	});

	var categoryID = $('.categories a:first-child').attr('id');

	feedList(categoryID);
}

function feedList(categoryID) {

	$.ajax({
		url : 'index',
		type : 'POST',
		data : {
			'act' : 'feedList',
			'categoryID' : categoryID
		},
		success : function(data) {
			$('#feed_content').html(data);

			$('.lnk-feed').click(function(event) {


				var link = $(this).find('#link').text();
				var feedID = $(this).find('#feed_id').text();

				window.open(link, '_blank');
				
				if($(this).hasClass('list-group-item-info')){
					
					$(this).removeClass('list-group-item-info');

					markAsRead(feedID);
				}

			});
		},
		beforeSend : function() {
			$('#feed_content').html(loading);
		},
	});

}

function markAsRead(feedID) {

	$.ajax({
		url : 'index',
		type : 'POST',
		data : {
			'act' : 'markAsRead',
			'feedID' : feedID
		},
		success : function(data) {

			var total = $('.lnk-category.active').find('.badge').text();
			$('.lnk-category.active').find('.badge').text(total - 1);
		},
	});

}
