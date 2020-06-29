
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>여러개 마커에 이벤트 등록하기2</title>
<style>
    
    #query {width:345px; height:25px; margin-bottom: 10px}
   	#location_img {position:absolute;top:10px;right:8px;width:30px;height:30px;overflow:hidden;z-index:2;background-color:#f5f5f5;}
</style>
<script src="http://192.168.64.94:8080/card/resources/js/myLocation.js" type="text/javascript"></script>
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous">
	</script>
	<input type="text" id="query" name="query">
	<button type="button" id="submit">전송</button>
	<c:set var="list" value="${param}"/>
	<input type="hidden" id="x" value="${list.x}">
	<input type="hidden" id="y" value="${list.y}">
	
	<div id="map" style="width: 400px; height: 670px;">
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=72cf4e74330c69dd7f58bd73099f6924&libraries=services"></script>
	
		<script>
		var x = $("#x").val();
		var y = $("#y").val();

		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = { 
		        center: new kakao.maps.LatLng(35.151222, 129.012187), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };
	
		// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
		var map = new kakao.maps.Map(mapContainer, mapOption);
	</script>
	<img id="location_img" width="50" height="50" src="${contextPath}/card/resources/image/icon.png">
	
	
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
   						// 마커의 이미지를 클릭 이미지로 변경합니다
   						if (!selectedMarker || selectedMarker !== marker) {

   							var title = marker.getTitle();
   							console.log(marker.getTitle());
   							
   							// spring을 통해 title 값과 db값의 title을 비교
   							$.ajax({
   				   				method : "GET",
   				   				url : "https://dapi.kakao.com/v2/local/search/keyword.json",
   				   				data : {
   				   					title : title
   				   				},
   				   				
   				   			}).done(function(msg) {
   				   			
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