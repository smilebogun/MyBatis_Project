<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<%@ include file="../include/header.jsp" %> <!-- 한번더 밖으로 나가서 header를 불러와야함 -->

<!-- <div class="container w-75 mt-5 p-5 shadow"> -->
<div class="container text-center">

	<!-- 이미지 업로드 -->
	<h1 class="title">동물 분류기 테스트</h1>
	 
    <script class="jsbin" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	
    <div class="container file-upload">
      <div class="image-upload-wrap">
        <input class="file-upload-input" type='file' onchange="readURL(this);" accept="image/*" />
        <div class="drag-text">
          <!-- <img src="img/upload.svg" class="mt-5 pt-5 upload"> -->
          <img src="resources/imgs/picture.png" class="mt-5 pt-5 upload">
          <h3 class="mb-5 pb-5 pt-4  upload-text">사진 업로드 장소<br>(Drag & Drop)</h3>
        </div>
      </div>
      <div class="file-upload-content">
        <img class="file-upload-image" id="face-image" src="#" alt="your image" />
        <div class="image-title-wrap">
          <button type="button" onclick="removeUpload()" class="remove-image">Remove
            <span class="image-title">Uploaded Image</span>
          </button>
        </div>
      </div>
    </div>
    
    <div class="text-center mt-3">
		<button style="display:none;" class="btn btn-success" id="start-btn" type="button" onclick="init()">시작</button>
	    <button style="display:none;" class="btn btn-danger" id="result-btn" type="button" onclick="predict()">분석</button>
    </div>
    
    <div id="label-container"></div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <!-- <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script> -->

    <!-- 인공지능 JS API -->
    <script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs@latest/dist/tf.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/@teachablemachine/image@latest/dist/teachablemachine-image.min.js"></script>


    <script>
   	var startBtn = document.getElementById("start-btn");
   	var resultBtn = document.getElementById("result-btn");
   	var lbl = document.getElementById("label-container");
   	
        function readURL(input) {
            if (input.files && input.files[0]) {	// 이미지가 업로드 되는부분
                var reader = new FileReader();
                reader.onload = function(e) {
                    $('.image-upload-wrap').hide();
                    $('.file-upload-image').attr('src', e.target.result);
                    $('.file-upload-content').show();
                    $('.image-title').html(input.files[0].name);
                };
                reader.readAsDataURL(input.files[0]);
                startBtn.style.display = "block";
            } else {
                removeUpload();
            }
        }

        function removeUpload() {
            $('.file-upload-input').replaceWith($('.file-upload-input').clone());
            $('.file-upload-content').hide();
            $('.image-upload-wrap').show();
            startBtn.style.display = "none";
            resultBtn.style.display= "none";
            labelContainer.innerHTML = "";
        }
        $('.image-upload-wrap').bind('dragover', function() {
            $('.image-upload-wrap').addClass('image-dropping');
        });
        $('.image-upload-wrap').bind('dragleave', function() {
            $('.image-upload-wrap').removeClass('image-dropping');
        });
    </script>

    <!-- 인공지능 텐서 스크립트 -->
    <script type="text/javascript">
      
 	// 모델방식 - 사이트에서 생성한 것을 다운로드 함(URL은 한달유효기간)
      const URL = "resources/my_model/";
      let model, webcam, labelContainer, maxPredictions;
      
      async function init() {

          const modelURL = URL + "model.json";
          const metadataURL = URL + "metadata.json";

          model = await tmImage.load(modelURL, metadataURL);
          maxPredictions = model.getTotalClasses();
          labelContainer = document.getElementById("label-container");
          for (let i = 0; i < maxPredictions; i++) { // and class labels
              labelContainer.appendChild(document.createElement("div"));
          }
          startBtn.style.display= "none";
          resultBtn.style.display= "block";
          
      }

      async function predict() {
          // predict can take in an image, video or canvas html element
          var image = document.getElementById("face-image")
          const prediction = await model.predict(image, false);
          
          if(prediction[0].className == "강아지" && prediction[0].probability.toFixed(2) >= 0.90){
        	  labelContainer.childNodes[0].innerHTML = "강아지 입니다."
          } else if(prediction[1].className == "고양이" && prediction[1].probability.toFixed(2) >= 0.90){
        	  labelContainer.childNodes[0].innerHTML = "고양이 입니다."
          } else {
        	  labelContainer.childNodes[0].innerHTML = "알 수 없습니다!!"
          }
          
/*           for (let i = 0; i < maxPredictions; i++) {
              const classPrediction =
                  prediction[i].className + ": " + prediction[i].probability.toFixed(2);
              labelContainer.childNodes[i].innerHTML = classPrediction;
          } */
          
          startBtn.style.display= "block";
          resultBtn.style.display= "none";
      }
  </script>

<%@ include file="../include/footer.jsp" %>