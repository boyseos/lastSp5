"use_strict"
var app = app || {}
app = (()=>{
	let y, jsx, pops, ctx
	let init =x=>{
		alert(x)
		jsx = "<%=session.getAttribute(js)%>"
		y = x+'/resources/js/vue/yy_vue.js'
		pops = x+'/resources/js/pop.js'
		ctx = x
	}
	let run = x =>{
		init(x)
		$.when(
			$.getScript(y),
			$.getScript(pops))
			.done(()=>{
				onCreate(x)				
			})
			.fail(()=>{
				
			})
	}
	
	let onCreate = x =>{
		$(pop.view()).appendTo('body')
		pop.open()
		setContentView(x)
	}
	
	let setContentView=x=>{
		setTimeout(() => {
			$('<table id ="table" style="border: solid; width: 80%; height: 800px; border-collapse:collapse"></table>')
			.appendTo('body')
			let arr= [
				{name: 'user', text: '유저메뉴'},
				{name: 'naver', text: '네이버', url: 'naver'},
				{name: 'bugs', text: '벅스', url: 'bugs'},
				{name: 'cgv', text: 'CGV', url: 'cgv/db'},
				{name: 'Stadium', text: '풋살 경기장', url: 'Stadium'}
			]
			$.each(arr,(i,j)=>{
				$('<tr name="'+i+'"></tr>').appendTo('#table')
				$('<td>',{
					text: j.text,
					name: j.name,
					style: 'width :100px; text-align: center; border: solid;'
				}).appendTo('tr[name='+i+']')
				.click(function(){
					$(this).css({'background-color':'yellow'})
					$(this).siblings().css({'background-color':'white'})
					if(($(this).attr("name"))!=='user')
						$.getJSON(ctx+'/crawl/'+j.url,d=>{
							$('#right').empty()
							console.log('크롤링해서 받아온 리스트의 갯수 : '+d.length)
							recent_view({page: 1, pagesize: 5, list: d})
						})
					else user_menu(x)
				})
			})
			$('<td id="right" rowspan="'+arr.length+'" ></td>').appendTo('tr[name=0]')
		}, 10);
	}
	
	let paging = x =>{
		let list = x.list
		let page = x.page, pagesize = x.pagesize, rowcount = list.length, blocksize = 5
		let pagecount = parseInt((rowcount -1) / pagesize)+1
		let blockcount = parseInt((pagecount-1) / blocksize)+1
		let startpage = parseInt((page - 1) / blocksize) * blocksize + 1
		startpage = startpage < pagecount ? startpage : pagecount
		let endpage = (startpage + blocksize-1) < pagecount ? startpage + blocksize -1: pagecount
		let startrow = (page-1)*pagesize
		let endrow = (page * pagesize -1) < rowcount ? (page * pagesize -1) : rowcount - 1
		let	prevexist = startpage > 2
		let	nextexist =  blockcount > (parseInt((endpage-1)/blocksize)+1)			
		let search
		return {list,page,pagesize,rowcount,blocksize,pagecount,blockcount
			,startpage,endpage,endpage,startrow,endrow,prevexist,nextexist,search}
	}
	
	let recent_view =x=>{
		let pxy, list
		if(!x.pxy){
			pxy = paging(x)
			list = paging(x).list
		}else{
			pxy = x.pxy
			list = x.list
		}
		console.log('page : '+pxy.page+'    pagesize : '+pxy.pagesize+'     rowcount : '+pxy.rowcount+'     startrow : '+pxy.startrow
				+'    endrow : '+pxy.endrow+'    pagecount : '+pxy.pagecount+'    startpage : '+pxy.startpage+'    endpage : '+pxy.endpage
				+'    blockcount : '+pxy.blockcount+'    prevexist : '+pxy.prevexist+'    nextexist : '+pxy.nextexist)
		$('#right').empty()
		$.each(list,(i,j)=>{
			if(pxy.startrow <= i && i <= pxy.endrow){
				$('<div/>',{
					text : j.a+' '+j.b+' '+((j.c == undefined) ? '':j.c)
					+((j.d == undefined) ? '':j.d),
					name : i
				}).css({'border': '1px solid black','text-align':'center',width:'50%','margin': 'auto'})
				.appendTo('#right')
				if(j.img)
					$('<img src="'+j.img+'">').prependTo('#right div[name='+i+']')
			}
		})
		$('<div id="p_nation" style="text-align: center;"></div>').appendTo('#right')
		$('<select>',{style:'float: right; margin-right: 40px;',selected:'true', value:pxy.pagesize})
		.appendTo('#p_nation').change(function(){
			app.recent_view({page: pxy.page, pagesize: $(this).val(), list: list})
		})
		$.each([5,10,15],(i,j)=>{
			$('<option>',{value: j, text:j+'개'})
			.appendTo('#p_nation select')
		})
		$('select').val(pxy.pagesize)
		let arr = [{text: 'prev', page: pxy.page - pxy.blocksize, see: pxy.prevexist}
				,{text: 'next', page: pxy.page + pxy.blocksize, see: pxy.nextexist}]
		for(let i = pxy.endpage; i>= pxy.startpage; i--) arr.splice(1,0,{text: i, page: i})
		$.each(arr,(i,j)=>{
			if(j.see!=false)
				$('<span>',{text: j.text ,style : "border: solid; width: 30px; display: inline-block;"})
				.appendTo('#p_nation')
				.click(()=>{
					app.recent_view({page: j.page , pagesize: pxy.pagesize, list: list})
				})
		})
	}
	
	let user_menu =x=>{
		$.each([{text: 'db생성', url: 'createdb',type: 'GET'},
			{text: 'db삭제', url: 'dropdb',type: 'GET'},
			{text: 'user입력', url: 'insert',type: 'POST', data:{dd:'ttta'}},
			{text: 'user숫자', url: 'count',type: 'GET'},
			{text: 'user명단', url: 'list',type: 'GET'},
			{text: 'user 셀렉트', url: 'xxx',type: 'GET'}]	,(i,j)=>{
		$('<div>',{text: j.text}).css({'text-align': 'center'})
		.appendTo('#right')
		.click(()=>{
			$.ajax({
				url: ctx+'/users/'+j.url,
				type: j.type,
				data: (j.data) ? JSON.stringify(j.data) : '',
				dataType: 'json',
				contentType: 'application/json',
				success: d=>{alert('성공')},
				error: e=>{alert('실패')}
			})
		})
	})
	}
	return {run,recent_view}
})()