function loginwatch(){
	$.ajax({
		   url: 'authenticated',
		   data: {
		      format: 'json'
		   },
		   error: function() {
			   console.log( "error ajax: loginwatch. " );
		   },
		   dataType: 'json',
		   success: function(data) {
			   console.log( "success login -- try redirect." );
			   window.location.href = "index.html";
		   },
		   type: 'GET'
		});
}