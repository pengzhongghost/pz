<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html  >
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.2/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
    <script type="text/javascript">
        function topage(data) {
            var index = $(data).children(1).val();
            location.href="/JobController/showJobs?index1="+index;
        }
        function insert() {
            location.href="/JobController/insert";
        }
        function thisPage(data) {
            var index = $(data).prev().val();
            location.href="/JobController/excelCreate?index1="+index;
        }
        function allPage() {
            location.href="/JobController/excelCreate";
        }
    </script>
</head>
<body style="background-image:url(/images/light-veneer.png);">
<div class="ui button teal" onclick="insert()">查询</div>
<input hidden="hidden" id="thisindex"  value="${pageBean.index}" >
<div class="ui button teal" onclick="thisPage(this)">打印此页</div>
<div class="ui button teal" onclick="allPage()">打印全部</div>
<table style="border: 1px aqua">
    <tr >
        <th>职位名</th>
        <th>公司名</th>
        <th>薪资</th>
        <th>工作地点</th>
    </tr>
    <c:forEach items="${pageBean.list}" var="job">
    <tr >
            <th >${job.jobName}</th>
            <th >${job.companyName}</th>
            <th >${job.money}</th>
            <th >${job.location}</th>
    </tr>
    </c:forEach>

</table>
<div class="content right aligned ui container " >
    <div class="ui buttons" >
        <input hidden="hidden" id="rightindex" value="${pageBean.index}">
        <c:if test="${pageBean.index>1}">
        <button class="ui teal icon button" onclick="topage(this)" value="${pageBean.index-1}">
            <input hidden="hidden"  value="${pageBean.index-1}">
            <i class="hand point left outline icon" ></i>
        </button>
        </c:if>
        <div class="container" >
            <c:forEach items="${pageBean.numbers}" var="num">
                <c:if test="${pageBean.index!=num}">
                    <button class="ui teal icon button"   onclick="topage(this)">
                        <input hidden="hidden" value="${num}">
                        <i>${num}</i>
                    </button>
                </c:if>
                <c:if test="${pageBean.index==num}">
                    <button class="ui teal icon button"  onclick="javascript:0">
                        <input hidden="hidden" value="${num}">
                        <i>${num}</i>
                    </button>
                </c:if>
            </c:forEach>
        </div>
        <c:if test="${pageBean.index<pageBean.totalPageCount}">
        <button class="ui teal icon button"   onclick="topage(this)" value="${pageBean.index+1}">
            <input hidden="hidden"  value="${pageBean.index+1}">
            <i  class="hand point right outline icon" ></i>
        </button>
        </c:if>
    </div>
</div>


</body>
</html>