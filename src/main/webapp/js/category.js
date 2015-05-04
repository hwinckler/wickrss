function init() {

	$('.btn-add').click(function(event) {

		var title = $('#title').val();
		var id = $('#id').val();
		
		var act = id != '' ? 'update' : 'insert';
		
		$.ajax({
			url : 'category',
			type : 'POST',
			data : {
				'act' : act,
				'title' : title,
				'id' : id
			},
			success : function(data) {

				$('.btn-clear').trigger('click');
				categoryList();

			},
			beforeSend : function() {
				$('#category_content').html(loading);
			},
		});

	});
	
	$('.btn-clear').click(function(event) {
		$('.btn-add').text('add');
		$('form').trigger('reset');
	});
	
	categoryList();
}

function categoryList() {

	$.ajax({
		url : 'category',
		type : 'POST',
		data : {
			'act' : 'categoryList'
		},
		success : function(data) {
			$('#category_content').html(data);
			
			$('.lnk-delete').click(function(event) {
				
				var id = $(this).find('#cat_id').text();
				
				$.ajax({
					url : 'category',
					type : 'POST',
					data : {
						'act' : 'delete',
						'id' : id
					},
					success : function(data) {

						$('.btn-clear').trigger('click');
						categoryList();

					},
					beforeSend : function() {
						$('#category_content').html(loading);
					},
				});

			});
			
			$('.lnk-update').click(function(event) {
				
				var title = $(this).find('#upd_title').text();
				var id = $(this).find('#upd_id').text();
				
				$('#title').val(title);
				$('#id').val(id);
				
				$('.btn-add').text('update');

			});
			

		},
		beforeSend : function() {
			$('#category_content').html(loading);
		},
	});

}
