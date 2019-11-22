<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!doctype html> 
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
	<link rel= "stylesheet" href= "https://cdnjs.cloudflare.com/ajax/libs/normalize/3.0.3/normalize.min.css" />
	<link rel= "stylesheet" href= "https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.0.0/magnific-popup.min.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src= "https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.0.0/jquery.magnific-popup.min.js" ></script >
	<script src="${js}app.js"></script>
	<script src="${js}pop.js"></script>
	<script src="${js}vue/yy_vue.js"></script>
	<script>
		app.run('${ctx}')
	</script>
</head>
<body>
	<div id='wrapper'></div>
</body>
</html>