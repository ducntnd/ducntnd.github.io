$('#header').load('layout/header.html');
$('#footer').load('layout/footer.html');
$('.side-news').load('layout/sidebar.html');
var i=0;
$('.show').on('click',function(){
	if(i==0){
		$('.disnone').css('display','flex');
		$('.show').html('Ẩn đi')
		i++
	}else{
		$('.disnone').css('display','none');
		$('.show').html('Hiện thêm')
		i--
	}
})