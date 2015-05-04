function init() {

	$('#link').change(function(event) {

		var link = $(this).val();

		if(link != ''){
			
			$.ajax({
				url : 'feed',
				type : 'POST',
				data : {
					'act' : 'parse',
					'link' : link
				},
				success : function(data) {
					$('.link-loading').html('');
					
					var obj = JSON.parse(data);
					
					$('#link').val(obj.link);
					$('#title').val(obj.title);
					$('#description').val(obj.description);
					
					$('.btn-add').prop('disabled', false);
					
				},
				beforeSend : function() {
					$('.link-loading').html(loading);
				},
			});
		}

	});
	
	$('.btn-add').click(function(event) {

		var link = $('#link').val();
		var categoryID = $('#category option:selected').val();
		
		var id = $('#id').val();
		
		var act = id != '' ? 'update' : 'insert';
		
		$.ajax({
			url : 'feed',
			type : 'POST',
			data : {
				'act' : act,
				'link' : link,
				'categoryID' : categoryID,
				'id' : id
			},
			success : function(data) {

				$('.btn-clear').trigger('click');
				channelListByCategory(categoryID);

			},
			beforeSend : function() {
				$('#channel_content').html(loading);
			},
		});

	});
	
	$('.btn-clear').click(function(event) {
		$('.btn-add').text('add');
		$('.btn-add').prop('disabled', true);
		$('form').trigger('reset');
	});
	
	categoryList();
}

function categoryList() {

	$.ajax({
		url : 'feed',
		type : 'POST',
		data : {
			'act' : 'categorySelectList'
		},
		success : function(data) {
			$('#category_content').html(data);
			
			var categoryID = $('#category option:selected').val();
			channelListByCategory(categoryID);
			
			$('#category').change(function(event) {

				var categoryID = $('#category option:selected').val();
				channelListByCategory(categoryID);
				
			});
			
		},
		beforeSend : function() {
			$('#category_content').html(loading);
		},
	});

}

function channelListByCategory(categoryID) {

	$.ajax({
		url : 'feed',
		type : 'POST',
		data : {
			'act' : 'channelListByCategory',
			'categoryID' : categoryID
		},
		success : function(data) {
			$('#channel_content').html(data);
			
			$('.link-delete').click(function(event) {

				var id = $(this).find('#chan_id').text();
				
				$.ajax({
					url : 'feed',
					type : 'POST',
					data : {
						'act' : 'delete',
						'id' : id
					},
					success : function(data) {

						$('.btn-clear').trigger('click');
						
						var categoryID = $('#category option:selected').val();
						channelListByCategory(categoryID);

					},
					beforeSend : function() {
						$('#channel_content').html(loading);
					},
				});
				
			});
			
			$('.lnk-update').click(function(event) {
				
				var link = $(this).find('#upd_link').text();
				var title = $(this).find('#upd_title').text();
				var id = $(this).find('#upd_id').text();
				var description = $(this).find('#upd_description').text();
				
				$('#link').val(link);
				$('#title').val(title);
				$('#id').val(id);
				$('#description').val(description);
				
				$('.btn-add').text('update');
				$('.btn-add').prop('disabled', false);

			});
			
			
		},
		beforeSend : function() {
			$('#channel_content').html(loading);
		},
	});

}
