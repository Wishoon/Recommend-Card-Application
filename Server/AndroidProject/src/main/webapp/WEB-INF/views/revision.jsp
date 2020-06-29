
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<c:set var="line" value="${line}" />
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="ie-edge">
<title>API 연습</title>
<title>키워드로 장소검색하고 목록으로 표출하기</title>
<style>
    .wrap {position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
    .wrap * {padding: 0;margin: 0;}
    .wrap .info {width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
    .wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
    .info .title {padding: 5px 0 0 10px;height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold;}
    .info .close {position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');}
    .info .close:hover {cursor: pointer;}
    .info .body {position: relative;overflow: hidden;}
    .info .desc {position: relative;margin: 13px 0 0 90px;height: 75px;}
    .desc .ellipsis {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
    .desc .jibun {font-size: 11px;color: #888;margin-top: -2px;}
    .info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
    .info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
    .info .link {color: #5085BB;}
</style>

</head>
<body>

	<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous">
	</script>

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=72cf4e74330c69dd7f58bd73099f6924&libraries=services"></script>
	<script>
		
	</script>
	<div id="map" style="width: 100%; height: 550px;">
		<input type="text" id="query" name="query">
		<button type="button" id="submit">전송</button>
	</div>
	<script>
		
		$(document).ready(function(){
	   		$("#submit").click(function() {
	   			var query = $("#query").val();
	   			
	   		// 사용자 위치 전송
	   			var x = "129.012187";
	   			var y = "35.151222";
	   			
	   			$.ajax({
	   				method : "GET",
	   				url : "https://dapi.kakao.com/v2/local/search/keyword.json",
	   				data : {
	   					query : query,
	   					y : y,
	   					x : x,
	   					radius : "2000"
	   				},
	   				headers : {
	   					Authorization : "KakaoAK 72cf4e74330c69dd7f58bd73099f6924"
	   				}
	   			}).done(function(msg) {
	   				//console.log(msg.documents[0].x);
	   				
	   				//console.log(msg.documents[1].x);
	   				
	   				var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	   				mapOption = {
	   					center : new kakao.maps.LatLng(y, x), // 지도의 중심좌표
	   					level : 5
	   				// 지도의 확대 레벨
	   				};
	   				var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	   				/*
	   				// 마커를 표시할 위치와 title 객체 배열입니다 
	   				var positions = [
	   				    {
	   				        title: '스타벅스 동서대 DT', 
	   				        latlng: new kakao.maps.LatLng(35.1507635950012, 129.009863887033)
	   				    },
	   				    {
	   				        title: '스타벅스 부산개금역점', 
	   				        latlng: new kakao.maps.LatLng(35.1529631470169, 129.022597790943)
	   				    },
	   				    {
	   				        title: '스타벅스 사상주례DT점', 
	   				        latlng: new kakao.maps.LatLng(35.1548961428588, 128.996091591424)
	   				    },
	   				];*/
	   				// 마커를 표시할 위치와 title 객체를 동적으로 저장하는 배열입니다 
	   				var positions2 = [];
	   				for(var i = 0; i < msg.documents.length; i++){
	   					positions2[i] = {
	   										title: msg.documents[i].place_name,
	   										latlng: new kakao.maps.LatLng(msg.documents[i].y, msg.documents[i].x)
	   									}
	   					console.log(msg.documents[i].place_name);
	   					console.log(positions2[i].place_name);
	   				}
	   				
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
	   	         	
	   				// 마커 이미지의 이미지 주소입니다
	   				var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
	   				// 지도 재설정한 범위정보를 가지는 객체 생성	    
	   				var bounds = new kakao.maps.LatLngBounds();
	   				// 마커를 저장할 객체 변수 생성
	   				var marker = [];
	   				
	   				    
	   				    // 마커 이미지의 이미지 크기 입니다
	   				var imageSize = new kakao.maps.Size(24, 35); 
	   				    
	   				    // 마커 이미지를 생성합니다    
	   				var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
	   				    
	   				for (var i = 0; i < positions2.length; i ++) {
	   					addMarker(positions[i].latlng);
	   				  
	   			        
	   			        
	   			     	
	   				}
	   				function addMarker(postion){
	   					var marker = new kakao.maps.Marker({
	   						map: map,
	   						position : position,
	   						image : normalImage
	   					});
	   					
	   					bounds.extend(position);
	   				//지도 위치 재 설정하기
		   				map.setBounds(bounds);	
		   				
	   				}
	   				
	   				
	   				
	   			});
	   		
	   		});
		});
		
	</script>
	
</body>
</html>