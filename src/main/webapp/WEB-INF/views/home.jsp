<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <script src='https://cloud.tinymce.com/stable/tinymce.min.js'></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/resources/style.css">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

    <style type="text/css">
        .mce-notification-warning {
            display: none;
        }
    </style>

    <script>
        tinymce.init({
            selector: '#texteditor',
            height: 800,
            theme: 'modern',
            plugins: 'print preview fullpage powerpaste searchreplace autolink directionality advcode visualblocks visualchars fullscreen image link media template codesample table charmap hr pagebreak nonbreaking anchor toc insertdatetime advlist lists textcolor wordcount tinymcespellchecker a11ychecker imagetools mediaembed linkchecker contextmenu colorpicker textpattern help',
            toolbar1: 'formatselect fontselect | undo redo | bold italic strikethrough forecolor backcolor | link | alignleft aligncenter alignright alignjustify | numlist bullist outdent indent | removeformat',
            image_advtab: true,
            templates: [
                {title: 'Test template 1', content: 'Test 1'},
                {title: 'Test template 2', content: 'Test 2'}
            ],
            content_css: [
                '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
                '//www.tinymce.com/css/codepen.min.css'
            ]
        });
    </script>
</head>
<body>


<div id="data-container">
    <form class="form-inline" id="upload-file-form" method="post" enctype="multipart/form-data">

        <div class="form-group"><input type="file" name="docFile" class="form-control-file"
                                       accept="application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                                       required/></div>
        <button type="submit" id="upload-btn" class="btn btn-primary">Upload</button>

    </form>
    <form id="form-data" method="post">
        <textarea class="tinymce" id="texteditor"></textarea>
    </form>

    <script>
        $(document).ready(function () {

            $("#form-data").submit(function (e) {

                var content = tinymce.get("texteditor").getContent();

                $("#data-container-1").html(content);

                return false;

            });


            $("#upload-file-form").submit(function (e) {
                $("#upload-btn").attr('disabled', 'disabled');
                $("#upload-btn").append("<i class=\"fa fa-spinner fa-spin loading-icon\">");
                var form = $("#upload-file-form")[0];
                var formData = new FormData(form);
                console.log(formData);
                $.ajax({
                    type: "POST",
                    url: "/uploadFile",
                    enctype: 'multipart/form-data',
                    timeout: 100000,
                    data: formData,
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function (data) {
                        $('#upload-btn').attr('disabled', false);
                        $("#upload-btn .loading-icon").remove();
                        tinymce.get("texteditor").setContent(data);
                    },
                    error: function (e) {
                        $('#upload-btn').attr('disabled', false);
                        $("#upload-btn .loading-icon").remove();
                    }
                });


                return false;
            });
        });

    </script>
</div>
</body>
</html>
