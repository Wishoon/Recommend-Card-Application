<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
	<% 
	request.setCharacterEncoding("euc-kr"); 

	String id = request.getParameter("id"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>여러개 마커에 이벤트 등록하기2</title>
<style>
	.map_wrap, .map_wrap * {
	margin: 0;
	padding: 0;
	font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
	font-size: 12px;
}

.map_wrap {
	position: relative;
	width: 100%;
	height: 0px;
}   	
#category {
	position: absolute;
	top: 10px;
	left: 10px;
	border-radius: 5px;
	border: 1px solid #909090;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
	background: #fff;
	overflow: hidden;
	z-index: 2;
}

#category li {
	float: left;
	list-style: none;
	width: 50px; px;
	border-right: 1px solid #acacac;
	padding: 6px 0;
	text-align: center;
	cursor: pointer;
}

#category li.on {
	background: #eee;
}

#category li:hover {
	background: #ffe6e6;
	border-left: 1px solid #acacac;
	margin-left: -1px;
}

#category li:last-child {
	margin-right: 0;
	border-right: 0;
}

#category li span {
	display: block;
	margin: 0 auto 3px;
	width: 27px;
	height: 28px;
}

#category li .category_bg {
	background:
		url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
		no-repeat;
}

#category li .bank {
	background-position: -10px 0;
}

#category li .mart {
	background-position: -10px -36px;
}

#category li .pharmacy {
	background-position: -10px -72px;
}

#category li .oil {
	background-position: -10px -108px;
}

#category li .cafe {
	background-position: -10px -144px;
}

#category li .store {
	background-position: -10px -180px;
}

#category li.on .category_bg {
	background-position-x: -46px;
}
	.container-1 input#query{
	  width: 338px;
	  height: 50px;
	  background: white;
	  
	  font-size: 10pt;
	  float: left;
	  color: black;
	  padding-left: 15px;
	  -webkit-border-radius: 5px;
	  -moz-border-radius: 5px;
	  border-radius: 5px;
	  margin-bottom: 5px;
	}
	.container-1 input#query::-webkit-input-placeholder {
	   color: #65737e;
	}
	 
	.container-1 input#query:-moz-placeholder { /* Firefox 18- */
	   color: #65737e;  
	}
	 
	.container-1 input#query::-moz-placeholder {  /* Firefox 19+ */
	   color: #65737e;  
	}
	 
	.container-1 input#query:-ms-input-placeholder {  
	   color: #65737e;  
	}
	.container-1 button.icon{
	  -webkit-border-top-right-radius: 5px;
	  -webkit-border-bottom-right-radius: 5px;
	  -moz-border-radius-topright: 5px;
	  -moz-border-radius-bottomright: 5px;
	  border-top-right-radius: 5px;
	  border-bottom-right-radius: 5px;
	 
	  border: none;
	  background: #232833;
	  height: 49px;
	  width: 50px;
	  color: #4f5b66;
	  
	  font-size: 10pt;
	 
	 
	}
    #query {}
   	#location_img {position:absolute;top:10px;right:8px;width:30px;height:30px;overflow:hidden;z-index:2;background-color:#f5f5f5;}
</style>
<script src="http://192.168.64.94:8080/card/resources/js/myLocation.js" type="text/javascript"></script>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous">
	</script>
	<div class="box">
		<div class="container-1">
			<input type="search" id="query" name="query" placeholder="Search..."/>
		
			<button class ="icon" type="button" id="submit"><i class="fa fa-search"></i></button>
		</div>
	</div>
	
	
	<c:set var="list" value="${param}"/>
	<input type="hidden" id="x" value="${list.x}">
	<input type="hidden" id="y" value="${list.y}">
	
	<div class="map_wrap">
		<div id="map_1"
			style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
		<ul id="category">
			<li id="MT1" data-order="1"><span class="category_bg mart"></span>
				마트</li>
			<li id="OL7" data-order="3"><span class="category_bg oil"></span>
				주유소</li>
			<li id="CE7" data-order="4"><span class="category_bg cafe"></span>
				카페</li>
			<li id="CS2" data-order="5"><span class="category_bg store"></span>
				편의점</li>
		</ul>
	</div>
	<div id="map" style="width: 400px; height: 700px;">
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=72cf4e74330c69dd7f58bd73099f6924&libraries=services"></script>
	
		<script>
		var x = $("#x").val();
		var y = $("#y").val();

		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = { 
		        center: new kakao.maps.LatLng(35.151222, 129.012187), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };
		var markers = [], // 마커를 담을 배열입니다
		currCategory = ''; // 현재 선택된 카테고리를 가지고 있을 변수입니다
		
		// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
		var map = new kakao.maps.Map(mapContainer, mapOption);
		// 장소 검색 객체를 생성합니다
		var ps = new kakao.maps.services.Places(map);
			
		kakao.maps.event.addListener(map, 'idle', searchPlaces);

		// 각 카테고리에 클릭 이벤트를 등록합니다
		addCategoryClickEvent();
		
		// 카테고리 검색을 요청하는 함수입니다
		function searchPlaces() {
			if (!currCategory) {
				return;
			}
			// 커스텀 오버레이를 숨깁니다 
			//placeOverlay.setMap(null);

			// 지도에 표시되고 있는 마커를 제거합니다
			removeMarker();

			ps.categorySearch(currCategory, placesSearchCB, {
				useMapBounds : true
			});
			console.log("실행");
		}
		// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
		function placesSearchCB(data, status, pagination) {
			if (status === kakao.maps.services.Status.OK) {

				// 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
				displayPlaces(data);
			} else if (status === kakao.maps.services.Status.ZERO_RESULT) {
				// 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요

			} else if (status === kakao.maps.services.Status.ERROR) {
				// 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요

			}
		}
		// 지도에 마커를 표출하는 함수입니다
		function displayPlaces(places) {

			// 몇번째 카테고리가 선택되어 있는지 얻어옵니다
			// 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
			var order = document.getElementById(currCategory).getAttribute(
					'data-order');

			for (var i = 0; i < places.length; i++) {

				// 마커를 생성하고 지도에 표시합니다
				var marker = addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);

				// 마커와 검색결과 항목을 클릭 했을 때
				// 장소정보를 표출하도록 클릭 이벤트를 등록합니다
				(function(marker, place) {
					kakao.maps.event.addListener(marker, 'click',
							function() {

								// 이부분 제거해서  안드로이드랑 연결하기
								console.log(place.place_name);
								
								var id = "<%= id %>";
	   							var title = place.place_name.split(" ")[0];
	   							
	   							console.log(title);
	   							console.log(id);
	   							// spring을 통해 title 값과 db값의 title을 비교
	   							$.ajax({
	   				   				method : "POST",
	   				   				url : "/app/store/"+title,
	   				   				data : {
	   				   					id : id
	   				   				},
	   				   				
	   				   			}).done(function(msg) {
	   				   				var ajson = new Array();
	   				   				console.log("msg의 오류 찾기")
	   				   				console.log(msg.toString())
	   				   				if(msg.toString()===("")){
	   				   					Android.plus(msg)
		   				   			}
	   				   				else{
	   				   					arraylength = msg.length
		   				   				for( i=0; i<arraylength; i++){
		   	   				   				var Aobj = new Object();
		   				   					console.log(msg[i].card_NAME)
		   				   					Aobj.card_Name  = msg[i].card_NAME
		   				   					Aobj.card_Number = msg[i].card_IMG
		   				   					if(msg[i].store_CARD_CASH_SALE!=0)
		   				   						Aobj.card_discount = msg[i].store_CARD_CASH_SALE + "원";
		   				   					else
		   				   						Aobj.card_discount = msg[i].store_CARD_PERSENT_SALE + "%"
		   				   					Aobj.card_Title =  place.place_name;
		   				   					ajson.push(Aobj)
		   				   					console.log(ajson.toString() + "Aobj 입니다")
		   				   				}
		   				   				console.log(ajson.toString())
		   				   				var sjson = JSON.stringify(ajson)
		   				   				console.log(sjson.toString())
		   				   				Android.plus(sjson)
	   				   				}
	   				   				//console.log(msg.toString())
	   				   				//for(i=0;i<msg.length;i++){
	   				   					//console.log(msg[i].title)
	   				   					//console.log(msg[i].card_NAME)
	   				   				//}
	   				   			});
								//displayPlaceInfo(place);
							});
					
				})(marker, places[i]);
			}
		}
		// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
		function addMarker(position, order) {
			var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
			imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
			imgOptions = {
				spriteSize : new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
				spriteOrigin : new kakao.maps.Point(46, (order * 36)), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
				offset : new kakao.maps.Point(11, 28)
			// 마커 좌표에 일치시킬 이미지 내에서의 좌표
			}, markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize,
					imgOptions), marker = new kakao.maps.Marker({
				position : position, // 마커의 위치
				image : markerImage
			});

			marker.setMap(map); // 지도 위에 마커를 표출합니다
			markers.push(marker); // 배열에 생성된 마커를 추가합니다

			return marker;
		}

		// 지도 위에 표시되고 있는 마커를 모두 제거합니다
		function removeMarker() {
			for (var i = 0; i < markers.length; i++) {
				console.log(i);
				markers[i].setMap(null);
			}
			markers = [];
		}
		// 각 카테고리에 클릭 이벤트를 등록합니다
		function addCategoryClickEvent() {
			var category = document.getElementById('category'), children = category.children;

			for (var i = 0; i < children.length; i++) {
				children[i].onclick = onClickCategory;
			}
		}

		// 카테고리를 클릭했을 때 호출되는 함수입니다
		function onClickCategory() {
			var id = this.id, className = this.className;

		//	placeOverlay.setMap(null);

			if (className === 'on') {
				currCategory = '';
				changeCategoryClass();
				removeMarker();
			} else {
				currCategory = id;
				changeCategoryClass(this);
				searchPlaces();
			}
		}

		// 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
		function changeCategoryClass(el) {
			var category = document.getElementById('category'), children = category.children, i;
			
			for (i = 0; i < children.length; i++) {
				children[i].className = '';
			}

			if (el) {
				el.className = 'on';
			}
		}	
		function open_close() {
			var layout = document.getElementById('category');
			
			layout.style.visibility="hidden";
			
		}
	</script>
	<img id="location_img" width="50" height="50" src="${contextPath}/app/resources/image/icon.png">
	
	
	</div>
	<script>
		$(document).ready(function(){
	   		$("#location_img").click(function() {
	   			console.log("click");
	   			Android.showToast('hello Android');
	   		});
		});
	</script>
	<script>
	$(document).ready(function(){
   		$("#submit").click(function() {
   			Android.marker("");
   			var query = $("#query").val();
   			var x = $("#x").val();
   			var y = $("#y").val();
   			// 사용자 위치 전송
   			//var x = "129.012187";
   			//var y = "35.151222";
   			
   			$.ajax({
   				method : "GET",
   				url : "https://dapi.kakao.com/v2/local/search/keyword.json",
   				data : {
   					query : query,
   					y : y,
   					x : x,
   					radius : "3000"
   				},
   				headers : {
   					Authorization : "KakaoAK 72cf4e74330c69dd7f58bd73099f6924"
   				}
   			}).done(function(msg) {
  				console.log(msg);
  				open_close();
  				changeCategoryClass(this);
  				removeMarker();
  				
   				var MARKER_WIDTH = 33, // 기본, 클릭 마커의 너비
   				MARKER_HEIGHT = 36, // 기본, 클릭 마커의 높이
   				OFFSET_X = 12, // 기본, 클릭 마커의 기준 X좌표
   				OFFSET_Y = MARKER_HEIGHT, // 기본, 클릭 마커의 기준 Y좌표
   				OVER_MARKER_WIDTH = 40, // 오버 마커의 너비
   				OVER_MARKER_HEIGHT = 42, // 오버 마커의 높이
   				OVER_OFFSET_X = 13, // 오버 마커의 기준 X좌표
   				OVER_OFFSET_Y = OVER_MARKER_HEIGHT, // 오버 마커의 기준 Y좌표
   				SPRITE_MARKER_URL = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markers_sprites2.png', // 스프라이트 마커 이미지 URL
   				SPRITE_WIDTH = 126, // 스프라이트 이미지 너비
   				SPRITE_HEIGHT = 146, // 스프라이트 이미지 높이
   				SPRITE_GAP = 10; // 스프라이트 이미지에서 마커간 간격

   				var markerSize = new kakao.maps.Size(MARKER_WIDTH, MARKER_HEIGHT), // 기본, 클릭 마커의 크기
   				markerOffset = new kakao.maps.Point(OFFSET_X, OFFSET_Y), // 기본, 클릭 마커의 기준좌표
   				overMarkerSize = new kakao.maps.Size(OVER_MARKER_WIDTH,
   						OVER_MARKER_HEIGHT), // 오버 마커의 크기
   				overMarkerOffset = new kakao.maps.Point(OVER_OFFSET_X, OVER_OFFSET_Y), // 오버 마커의 기준 좌표
   				spriteImageSize = new kakao.maps.Size(SPRITE_WIDTH, SPRITE_HEIGHT); // 스프라이트 이미지의 크기

   				var positions = [] // 마커의 위치
   			
   				for (var i = 0; i < msg.documents.length; i++) {
   					positions[i] = {
   										title : msg.documents[i].place_name,
   										latlng : new kakao.maps.LatLng(msg.documents[i].y, msg.documents[i].x)
   									}
   				}
   				var selectedMarker = null;   				
   				  
   				// 클릭시 보이는 내용 배열에 저장
   				/*
   				var content = [];
   				for(var i = 0; i < msg.documents.length; i++){
   	         		content[i] = '<div class="wrap">' + 
	   	            '    <div class="info">' + 
	   	            '        <div class="title">' + msg.documents[i].place_name  + 
	   	            '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' + 
	   	            '        </div>' + 
	   	            '        <div class="body">' + 
	   	            '            <div class="desc">' + 
	   	            '                <div class="ellipsis"> '+ msg.documents[i].place_name +'</div>' + 
	   	            '                <div class="jibun ellipsis">(우) 63309 (지번) 영평동 2181</div>' + 
	   	            '                <div><a href="https://www.kakaocorp.com/main" target="_blank" class="link">홈페이지</a></div>' + 
	   	            '            </div>' + 
	   	            '        </div>' + 
	   	            '    </div>' +    
	   	            '</div>';
   	         	}
   				*/
   				var mapContainer = document.getElementById('map'), // 지도를 표시할 div
   				mapOption = {
   					center : new kakao.maps.LatLng(y, x), // 지도의 중심좌표
   					level : 5
   				// 지도의 확대 레벨
   				};
   				// 지도 재설정한 범위정보를 가지는 객체 생성	    
   				var bounds = new kakao.maps.LatLngBounds();
   				
   				var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

   				
			
				
   				// 지도 위에 마커를 표시합니다
   				for (var i = 0; i < positions.length; i++) {
   					var gapX = (MARKER_WIDTH + SPRITE_GAP), // 스프라이트 이미지에서 마커로 사용할 이미지 X좌표 간격 값
   					originY = (MARKER_HEIGHT + SPRITE_GAP) * i, // 스프라이트 이미지에서 기본, 클릭 마커로 사용할 Y좌표 값
   					overOriginY = (OVER_MARKER_HEIGHT + SPRITE_GAP) * i, // 스프라이트 이미지에서 오버 마커로 사용할 Y좌표 값
   					normalOrigin = new kakao.maps.Point(0, originY), // 스프라이트 이미지에서 기본 마커로 사용할 영역의 좌상단 좌표
   					clickOrigin = new kakao.maps.Point(gapX, originY), // 스프라이트 이미지에서 마우스오버 마커로 사용할 영역의 좌상단 좌표
   					overOrigin = new kakao.maps.Point(gapX * 2, overOriginY); // 스프라이트 이미지에서 클릭 마커로 사용할 영역의 좌상단 좌표

   					// 마커를 생성하고 지도위에 표시합니다
   					addMarker(positions[i].latlng, normalOrigin, overOrigin, clickOrigin, positions[i].title);
   				}

   				// 마커를 생성하고 지도 위에 표시하고, 마커에 mouseover, mouseout, click 이벤트를 등록하는 함수입니다
   				function addMarker(position, normalOrigin, overOrigin, clickOrigin, name) {

   					// 마커를 생성하고 이미지는 기본 마커 이미지를 사용합니다
   					var marker = new kakao.maps.Marker({
   						map : map,
   						position : position,
   						title : name,
   					});
   					console.log(marker.getTitle());
   					bounds.extend(position);
   					/*
   					var overlay = new kakao.maps.CustomOverlay({
	   				    content: content[i],
	   				    map: map,
	   				    position: position       
	   				});
   					*/
   					// map에 다시 좌표 뿌리기
   					map.setBounds(bounds);
   					
   					// 마커에 mouseover 이벤트를 등록합니다
   					kakao.maps.event.addListener(marker, 'mouseover', function() {

   						// 클릭된 마커가 없고, mouseover된 마커가 클릭된 마커가 아니면
   						// 마커의 이미지를 오버 이미지로 변경합니다
   						if (!selectedMarker || selectedMarker !== marker) {
   							//marker.setImage(overImage);
   						}
   					});

   					// 마커에 mouseout 이벤트를 등록합니다
   					kakao.maps.event.addListener(marker, 'mouseout', function() {

   						// 클릭된 마커가 없고, mouseout된 마커가 클릭된 마커가 아니면
   						// 마커의 이미지를 기본 이미지로 변경합니다
   						if (!selectedMarker || selectedMarker !== marker) {
   							//marker.setImage(normalImage);
   						}
   					});

   					// 마커에 click 이벤트를 등록합니다
   					 
   					kakao.maps.event.addListener(marker, 'click', function() {

   						// 클릭된 마커가 없고, click 마커가 클릭된 마커가 아니면
   						if (!selectedMarker || selectedMarker !== marker) {
   							var id = "<%= id %>";
   							var title = marker.getTitle().split(" ")[0];
   							var title1 = marker.getTitle()
   							console.log(marker.getTitle());
   							console.log("ㅅㅍ");
   							console.log(title);
   							console.log(id);
   							// spring을 통해 title 값과 db값의 title을 비교
   							$.ajax({
   				   				method : "POST",
   				   				url : "/app/store/"+title,
   				   				data : {
   				   					id : id
   				   				},
   				   				
   				   			}).done(function(msg) {
   				   				var ajson = new Array();
   				   				console.log("msg의 오류 찾기")
   				   				console.log(msg.toString())
   				   				if(msg.toString()===("")){
   				   					Android.plus(msg)
	   				   			}
   				   				else{
   				   					arraylength = msg.length
	   				   				for( i=0; i<arraylength; i++){
	   	   				   				var Aobj = new Object();
	   				   					console.log(msg[i].card_NAME)
	   				   					Aobj.card_Name  = msg[i].card_NAME
	   				   					Aobj.card_Number = msg[i].card_IMG
	   				   					if(msg[i].store_CARD_CASH_SALE!=0)
	   				   						Aobj.card_discount = msg[i].store_CARD_CASH_SALE + "원";
	   				   					else
	   				   						Aobj.card_discount = msg[i].store_CARD_PERSENT_SALE + "%"
	   				   					Aobj.card_Title = marker.getTitle();
	   				   					ajson.push(Aobj)
	   				   					console.log(ajson.toString() + "Aobj 입니다")
	   				   				}
	   				   				console.log(ajson.toString())
	   				   				var sjson = JSON.stringify(ajson)
	   				   				console.log(sjson.toString())
	   				   				Android.plus(sjson)
   				   				}
   				   				//console.log(msg.toString())
   				   				//for(i=0;i<msg.length;i++){
   				   					//console.log(msg[i].title)
   				   					//console.log(msg[i].card_NAME)
   				   				//}
   				   			});
   							
   						}

   						// 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
   						selectedMarker = marker;
   					});
   				}
			
   			});
   		});
	});
		
	</script>
	
</body>
</html>