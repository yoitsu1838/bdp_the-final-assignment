<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<html lang="ja">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <meta name="format-detection" content="telephone=no" />
    <!-- 電話番号の自動リンク機能を制御 for iPhone -->
    <meta name="format-detection" content="address=no" />
    <!-- 住所の自動リンク機能抑制　for Android-->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Chrome, Firefox OS and Opera -->
    <meta name="theme-color" content="#2A4B66">
    <!-- social -->

    <!-- // social -->

    <title>ANISON DATABASE</title>

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <!-- Google Fonts -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
    <!-- Bootstrap core CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.18.0/css/mdb.min.css" rel="stylesheet">
    <!-- original_css -->
    <link href="css/style.css" rel="stylesheet" type="text/css" media="screen">
</head>

<body>

    <!-- display_loading -->
    <div id="loader-bg">
        <div id="loader">
            <div class="spinner-grow" style="width: 3rem; height: 3rem;" role="status"> <span class="sr-only">Loading...</span> </div>
        </div>
    </div>
    <!-- //display_loading -->

    <!--loaded_content-->
    <div id="loaded_content">

        <!--Main Navigation-->
        <header>

            <!--Navbar-->
            <nav class="navbar navbar-expand-lg navbar-dark unique-color">

                <!-- Additional container -->
                <div class="container">

                    <!-- Navbar brand -->
                    <a class="navbar-brand" href="AnisonSearch">ANISON DATABASE</a>

                    <!-- Collapse button -->
                    <div class="animated-icon">
                        <button class="navbar-toggler cross-button" type="button" data-toggle="collapse" data-target="#basicExampleNav" aria-controls="basicExampleNav" aria-expanded="false" aria-label="Toggle navigation"> <span></span><span></span><span></span><span></span> </button>
                    </div>

                    <!-- Collapsible content -->
                    <div class="collapse navbar-collapse" id="basicExampleNav">

                        <!-- Links -->
                        <!-- InstanceBeginEditable name="navLink" -->
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active hoverlink"> <a class="nav-link" href="AnisonSearch">Home <span class="sr-only">(current)</span></a></li>

                        </ul>
                        <!-- InstanceEndEditable -->
                        <!-- Links -->

                    </div>
                    <!-- Collapsible content -->

                </div>
                <!-- Additional container -->

            </nav>
            <!--/.Navbar-->

        </header>
        <!--// Main Navigation-->

        <!--Main layout-->
        <main class="mt-5">
            <!--Main container-->
            <div class="container">

                <% boolean isPost = (boolean)request.getAttribute("isPost");%>
                <% if(!isPost){ %>

                <!--Grid row-->
                <div class="row">
                    <!--Grid column-->
                    <div class="col-lg-12 col-md-12 mb-4">
                        <h2>ANISON DATABASE</h2>
                        <hr>
                        <p>
                            <span class="todayTask none">このシステムを使うことで，アニメソングの情報を検索することができます。アニメソングの楽曲ページでは，その楽曲に関連するアニメーションなどの詳細を確認することができます。</span><br>

                        </p>
                    </div>
                    <!--Grid column-->
                </div>
                <!--Grid row-->

                <!--Grid row-->
                <div class="row">
                    <!--Grid column-->
                    <div class="col-12">
                        <h2>検索</h2>
                        <hr>
                    </div>
                    <!--Grid column-->
                </div>
                <!--Grid row-->

                <!-- Material form grid -->
                <form action="AnisonSearch" method="post">
                    <!-- Grid row -->
                    <div class="row justify-content-center">
                        <!-- Grid column -->
                        <div class="col-11">
                            <!-- Material input -->
                            <div class="md-form mt-0">
                                <input type="text" class="form-control" placeholder="楽曲名" name="musicName">
                            </div>
                        </div>
                        <!-- Grid column -->
                    </div>
                    <!-- Grid row -->
                    <!-- Grid row -->
                    <div class="row justify-content-center">
                        <!-- Grid column -->
                        <div class="col-11">
                            <!-- Material input -->
                            <div class="md-form mt-0">
                                <input type="text" class="form-control" placeholder="アーティスト名" name="artistName">
                            </div>
                        </div>
                        <!-- Grid column -->
                    </div>
                    <!-- Grid row -->
                    <!-- Grid row -->
                    <div class="row justify-content-center">
                        <!-- Grid column -->
                        <div class="col-11">
                            <!-- Material input -->
                            <div class="md-form mt-0">
                                <input type="text" class="form-control" placeholder="作品名" name="programName">
                            </div>
                        </div>
                        <!-- Grid column -->
                    </div>
                    <!-- Grid row -->

                    <!-- Material form grid -->

                    <!--Grid row-->
                    <div class="form-row">
                        <!--繰り返し単位-->
                        <div class="col-12">


                            <button class="btn btn-elegant btn-rounded btn-lg my-0 col-12" type="submit">検索</button>



                            <!--/.Card-->
                        </div>

                        <!--//繰り返し単位-->
                    </div>
                    <!--Grid row-->
                </form>

                <!--//form-->


                <% }else if(isPost) { %>

                <!--Grid row-->
                <div class="row">
                    <!--Grid column-->
                    <div class="col-lg-12 col-md-12 mb-4">
                        <h2>検索結果</h2>
                        <hr>
                    </div>
                    <!--Grid column-->
                </div>
                <!--Grid row-->

                <!--Grid row-->
                <div class="row">
                    <% ArrayList<HashMap<String,String>> rows = (ArrayList<HashMap<String,String>>)request.getAttribute("rows"); %>

                    <!--繰り返し単位-->
                    <% for (HashMap<String,String> columns : rows) { %>
                    <div class="col-md-12 col-lg-6 mb-4">
                        <!--Card-->
                        <div class="card">
                            <!--Card content-->
                            <div class="card-body">
                                <!--Title-->
                                <h4 class="card-title"><%= columns.get("musicname") %></h4>
                                <!--Text-->
                                <p class="card-text"><span class="programName">作品名：<%= columns.get("programname") %></span><br><span class="artistName">アーティスト名：<%= columns.get("artistname") %></span></p><a href="musicInfo?musicId=<%= columns.get("musicid") %>" class="btn btn btn-indigo">詳細</a>
                            </div>
                        </div>
                        <!--/.Card-->
                    </div>
                    <% } %>
                    <!--//繰り返し単位-->
                </div>
                <!--Grid row-->

                <% } %>


            </div>
            <!--Main container-->
        </main>
        <!-- Footer -->
        <footer class="page-footer font-small indigo pt-4 mt-4 unique-color">


            <!-- Copyright -->
            <div class="footer-copyright text-center py-3">
                © 2020 <a href="#" target="_blank"> DBP2020最終課題</a>
            </div>
            <!-- Copyright -->
        </footer>
        <!-- //Footer -->

    </div>
    <!-- //loaded_content -->

    <!-- JQuery -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.18.0/js/mdb.min.js"></script>
    <!-- custom -->
    <script src="js/common.js"></script>
</body>

</html>
