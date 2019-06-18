<?php
  session_start();
  require_once('vars.php');
?>
<!DOCTYPE html>
<html>
<head>
	<title>Dmytro Godovanyk. Portfolio web site.</title>
	<meta http-equiv="Content-Type"; content="text/html"; charset="utf-8">
	<link rel="stylesheet" type="text/css" media="screen" href="css/slider.css">	
	<link rel="stylesheet" type="text/css" media="screen" href="css/mains.css">
	<link rel="shortcut icon" href="pics/godoicon.png" type="image/x-icon">
	<script type="text/javascript" src="js/jquery-1.7.min.js"></script>	
	<script type="text/javascript" src="js/easyTooltip.js"></script>
	<script src="//ajax.aspnetcdn.com/ajax/jquery.ui/1.10.3/jquery-ui.min.js"></script>
	<script>
		(function ($) {
		var hwSlideSpeed = 700;
		var hwTimeOut = 5000;
		var hwNeedLinks = true;

		$(document).ready(function(e) {
			$(function() {	
				$('.folder, .file, .person, .mail').draggable({
			        containment: "#wrapper"
			    });
			});
			$(".folder, .file, .person, .mail").easyTooltip({ clickRemove:false, content:"Move me or click for enter" });
			$(".folder, .file, .person, .mail").click(function() { document.location.href = this.attributes.rel.value; });

			$('.slide').css(
				{"position" : "absolute",
				 "top":'0', "left": '0'}).hide().eq(0).show();
			var slideNum = 0;
			var slideTime;
			slideCount = $("#slider .slide").size();
			var animSlide = function(arrow){
				clearTimeout(slideTime);
				$('.slide').eq(slideNum).fadeOut(hwSlideSpeed);
				if(arrow == "next"){
					if(slideNum == (slideCount-1)){slideNum=0;}
					else{slideNum++}
					}
				else if(arrow == "prew") {
					if(slideNum == 0){slideNum=slideCount-1;}
					else{slideNum-=1}
				}
				else {
					slideNum = arrow;
					}
				$('.slide').eq(slideNum).fadeIn(hwSlideSpeed, rotator);
				$(".control-slide.active").removeClass("active");
				$('.control-slide').eq(slideNum).addClass('active');
				}
		if(hwNeedLinks){
		var $linkArrow = $('<a id="prewbutton" href="#">&lt;</a><a id="nextbutton" href="#">&gt;</a>')
			.prependTo('#slider');		
			$('#nextbutton').click(function(){
				animSlide("next");
				return false;
				})
			$('#prewbutton').click(function(){
				animSlide("prew");
				return false;
				})
		}
			
			var pause = false;
			var rotator = function(){
					if(!pause){slideTime = setTimeout(function(){animSlide('next')}, hwTimeOut);}
					}
			$('#slider-wrap').hover(	
				function(){clearTimeout(slideTime); pause = true;},
				function(){pause = false; rotator();
				});
			rotator();
		});
		})(jQuery);

		$(function() {	
			$('.folder, .file, .person, .mail').draggable({
		        containment: "#wrapper"
		    });
		});

		$(".folder, .file, .person, .mail").easyTooltip({ clickRemove:false, content:"Move me or Double click for enter" });
		$(".folder, .file, .person, .mail").dblclick(function() { document.location.href = this.attributes.rel.value; });
</script>	
</head>
<body draggable='false'>
	<script>
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

	  ga('create', 'UA-43818670-10', 'auto');
	  ga('send', 'pageview');

	</script>
	<img src="pics/Portfolio/Slider/001.gif" style="display:none;">
	<div id="wrapper">			
		<div id="intro">
			<h3>Hi</h3>
			<p>I'm Dmytro. I’ve emigrated from Ukraine and live in this beautiful city of Raleigh in North Carolina since.</p>
			<p>Whole my life I’ve been working in design and programming world: polygraph and web graphics, front and back ends of web developing, applications and games developing, you name it.</p>
			<p>This portfolio-site is created just for employers and clients to see my work and get to know me better.
				You always can contact me with your questions, ideas and job offers!</p>
			<p>Let’s do something meaningful together!</p>
		</div>
		<div id="news">
			<h2>News</h2>
			<div class="nblock">
				<p><img class="rightimg" src="pics/news/TwoPhones.png">
					Finaly finished development of few aplications and web-site for Electrical company. This is Electrical/ Plumbing/ A/C Truck List makers and the Electrical Inspection App. Very interesting work. First time I fully wrote the code on SWIFT language for iOS. <a href="http://truckstockapp.com" target="_blank">  - - - Web site</a>
				</p>
			</div>
			<div class="nblock">
				<p><img class="leftimg" src="pics/news/gdd_pencil.png">
					Recently I’ve started a new project “GoDoDraw!” which is an drawing education application for kids and it will be Android, Smartphone and iPhones friendly.
					<a href="http://gododraw.com" target="_blank">  - - - Web site</a>
				</p>
			</div>
			<div class="nblock">
				<p><img class="rightimg" src="pics/news/Pepper.png">
					Just finished design package for “Deutche Veg”, it was fun and very educational, I’ve learned a lot.
				</p>
			</div>
			<!--
			<div class="nblock">
				<p><br>
					Started very difficult project on the corporate portal for Godo Systems, Inc company. I hope we will be good.
				</p>
			</div>
			-->			
		</div>
		<div id="slider-wrap">
			<div id="slider">
				<div class="slide"><img src="pics/Portfolio/Slider/Plumbing.png" height="200"></div>
				<div class="slide"><img src="pics/Portfolio/Slider/012.jpg" height="250"></div>
				<div class="slide"><img src="pics/Portfolio/Slider/001.jpg" height="250"></div>
				<div class="slide"><img src="pics/Portfolio/Slider/004.jpg" height="250"></div>
				<div class="slide"><img src="pics/Portfolio/Slider/008.gif" width="270" style="margin-top:50px;"></div>
				<div class="slide"><img src="pics/Portfolio/Slider/011.jpg" height="250"></div>	
				<div class="slide"><img src="pics/Portfolio/Slider/009.jpg" height="250"></div>
				<div class="slide"><img src="pics/Portfolio/Slider/006.jpg" height="250"></div>
				<div class="slide"><img src="pics/Portfolio/Slider/003.jpg" height="250"></div>
				<div class="slide"><img src="pics/Portfolio/Slider/005.jpg" height="250"></div>
				<div class="slide"><img src="pics/Portfolio/Slider/010.jpg" height="250"></div>			
				<div class="slide"><img src="pics/Portfolio/Slider/002.jpg" height="250"></div>
			</div>
			<br>
			<a href="portfolio.php">All pics ...</a>
		</div>
		<? include 'units/Resume.php'; ?>
		<? include 'units/Mail.php'; ?>
		<? include 'units/About.php'; ?>
		<? include 'units/Portfolio.php'; ?>
	</div>
	<footer>
		<div class="gorizontal_menu">  
		    <a href="about.php">About Me</a>
		    <a href="portfolio.php">Portfolio</a>
		    <a href="resume.php">Resume</a>
		    <a href="textme.php">Text Me</a>
		</div><br>
		<div class="a2a_kit a2a_default_style" style="width:60px; margin:0 auto;">
		<a class="a2a_button_facebook"></a>
		<a class="a2a_button_twitter"></a>
		<a class="a2a_button_google_plus"></a>
		</div>
		<script type="text/javascript">
			var a2a_config = a2a_config || {};
			a2a_config.linkname = "Dmytro Godovanyk. Resume.";
			a2a_config.linkurl = "http://godoask.com/";
		</script>
		<script type="text/javascript" src="//static.addtoany.com/menu/page.js"></script>
		<br>
		
		<p>© Dmytro Godovanyk 2016 Raleigh, North Carolina</p>
	</footer>  	
</body>
</html>
